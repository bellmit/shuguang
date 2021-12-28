package com.sofn.fdzem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sofn.common.exception.SofnException;
import com.sofn.common.utils.PageUtils;
import com.sofn.fdzem.content.MessageContent;
import com.sofn.fdzem.mapper.IndexMapper;
import com.sofn.fdzem.model.Index;
import com.sofn.fdzem.service.IndexService;
import com.sofn.fdzem.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gaosheng
 * Date: 2020/05/19 9:13
 * Description:
 * Version: V1.0
 */
@Service
public class IndexServiceImpl implements IndexService {
    @Autowired
    private IndexMapper indexMapper;


    @Override
    public PageUtils<Index> listPage(Integer pageNum, Integer pageSize, String indexType, String indexName, String startTime, String endTime) {
        PageHelper.startPage(pageNum, pageSize);
        PageHelper.orderBy("add_date  desc");
        QueryWrapper<Index> queryWrapper = new QueryWrapper<>();
        if (indexType != null && !indexType.equals("")) {
            if (!indexType.equals(MessageContent.TOP_INDEX_CATEGORY) && !indexType.equals(MessageContent.SEC_INDEX_CATEGORY)) {
                throw new SofnException("参数异常,无法识别的指标类型");
            }
            queryWrapper.eq("index_type", indexType);
        }
        if (indexName != null && !indexName.equals("")) {
            queryWrapper.like("index_name", indexName);
        }
        if (startTime != null && !"".equals(startTime)) {
            queryWrapper.ge("add_date", startTime);
        }
        if (endTime != null && !"".equals(endTime)) {
            queryWrapper.lt("add_date", endTime);
        }
        List<Index> list = indexMapper.selectList(queryWrapper);
        list.stream().filter(li -> li.getParentId() != null).forEach(li -> {
            li.setParentName(indexMapper.selectParentName(li.getParentId()));
        });
        Page<Index> page = (Page<Index>) list;
        /*测试拿用户权限*/

        return new PageUtils<Index>(page.getResult(), (int) page.getTotal(), page.getPageSize(), pageNum);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveIndex(Index index) {
        if (index.getIndexType().equals(MessageContent.TOP_INDEX_CATEGORY)) {
            index.setParentId(null);
            index.setConsultValue(null);
            index.setScore(null);
        } else if (index.getIndexType().equals(MessageContent.SEC_INDEX_CATEGORY)) {
            Long parentId = index.getParentId();
            if (parentId == null) {
                throw new SofnException("二级指标的上级指标id不可缺失");
            }
            //添加二级指标时判断对应的一级指标是否正确
            Index ind = indexMapper.selectOne(new QueryWrapper<Index>().eq("id", parentId));
            if (ind == null || !ind.getIndexType().equals(MessageContent.TOP_INDEX_CATEGORY)) {
                throw new SofnException("请选择提供的一级指标为上级指标");
            }
            final int ZERO = 0;
            if (ZERO == ind.getState()) {
                throw new SofnException("添加失败：所选一级指标被禁用！");
            }
        } else {
            throw new SofnException("参数异常");
        }
        index.setAddDate(new Date());
        index.setState(1);
        String operator = index.getOperator();
        if (operator == null || operator.equals("")) {
            operator = UserUtils.getUserName();
            index.setOperator(operator);
        }
        indexMapper.insert(index);
    }

    @Override
    public Index getById(Long id) {
        return indexMapper.selectById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateIndex(Index index) {
        //1.通过指标id获取到指标数据
        Index oldIndex = indexMapper.selectById(index.getId());

        //2.判断指标的类型是否改变
        String indexCategory = index.getIndexType();
        String oldIndexCategory = oldIndex.getIndexType();
        if (!oldIndexCategory.equals(indexCategory)) {
            throw new SofnException("不可编辑指标的类型");
        }

        //3.判断数据是否存在质量问题
        if (indexCategory.equals(MessageContent.TOP_INDEX_CATEGORY)) {
            index.setParentId(null);
            index.setConsultValue(null);
            index.setScore(null);
        } else if (indexCategory.equals(MessageContent.SEC_INDEX_CATEGORY)) {
            Long parentId = index.getParentId();
            if (parentId == null) {
                throw new SofnException("二级指标的上级指标id不可缺失");
            }
            Index ind = indexMapper.selectOne(new QueryWrapper<Index>().eq("id", parentId));
            if (ind == null || !ind.getIndexType().equals(MessageContent.TOP_INDEX_CATEGORY)) {
                throw new SofnException("请选择提供的一级指标为上级指标");
            }

            final int ZERO = 0;
            if (ZERO == ind.getState()) {
                throw new SofnException("编辑失败：所选一级指标被禁用！");
            }
        } else {
            throw new SofnException("参数异常");
        }

        //4.将数据库数据更新
        indexMapper.updateById(index);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void updateIndexStatus(Long id, Integer state) {
        // 修改指标启停状态
        final int ONE = 1;
        final int ZERO = 0;
        Index originalIndex = indexMapper.selectById(id);
        if (originalIndex == null) {
            throw new SofnException("您操作数据不存在！");
        }
        if (ONE == state && originalIndex.getIndexType().equals(MessageContent.SEC_INDEX_CATEGORY)) {
            // 启用,验证上级是否被禁用
            Index parentIndex = indexMapper.selectById(originalIndex.getParentId());
            if (ZERO == parentIndex.getState()) {
                throw new SofnException("指标不能被启用，其顶级指标禁用中。");
            }
        }
        if (ZERO == state && originalIndex.getIndexType().equals(MessageContent.TOP_INDEX_CATEGORY)) {
            // 顶级禁用,并禁用其下级指标
            Index index = new Index();
            index.setState(state);
            indexMapper.update(index, new UpdateWrapper<Index>().eq("parent_id", id));
        }

        Index index = new Index();
        index.setId(id);
        index.setState(state);
        indexMapper.updateById(index);
    }

    @Override
    public List<Index> getTopIndex() {
        QueryWrapper<Index> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "index_name","state");
        queryWrapper.isNull("parent_id");
        // 被禁用的任然可以修改
        // queryWrapper.eq("state", 1);
        return indexMapper.selectList(queryWrapper);
    }
}
