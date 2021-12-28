package com.sofn.common.treeutil.example.dao;

import com.google.common.collect.Lists;
import com.sofn.common.db.BeanHandler;
import com.sofn.common.db.JdbcTemplate;
import com.sofn.common.exception.SofnException;
import com.sofn.common.treeutil.example.entity.SysTreeTest;
import com.sofn.common.treeutil.example.vo.SysTreeTestVo;
import com.sofn.common.treeutil.service.TreeService;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.Nullable;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * 这里测试使用就不分层了
 *
 * @author heyongjie
 * @date 2019/11/29 11:25
 */
public class TreeTestDao implements TreeService<SysTreeTestVo> {


    @Override
    public List<SysTreeTestVo> getInfoByIds(List<String> ids) {
        if (!CollectionUtils.isEmpty(ids)) {
            String sql = "SELECT ID,NAME,PARENT_ID parentId,PARENT_IDS parentIds,SORT,REMARK FROM SYS_TREE_TEST where ID in ";
            sql += getSqlByIds(ids);
            List<SysTreeTestVo> sysTreeTestVos = execuSql(sql);
            if (sysTreeTestVos != null) {
                return sysTreeTestVos;
            }
        }
        return null;
    }

    @Override
    public List<SysTreeTestVo> getInfoByCondition(Map<String, Object> params) {

        String sql = "SELECT ID,NAME,PARENT_ID parentId,PARENT_IDS parentIds,SORT,REMARK FROM SYS_TREE_TEST ";
        if (!CollectionUtils.isEmpty(params)) {
            String name = (String) params.get("name");

            if (!StringUtils.isBlank(name)) {
                sql += "where NAME LIKE '%" + name + "%'";
            }
        }
        List<SysTreeTestVo> sysTreeTestVos = execuSql(sql);
        if (sysTreeTestVos != null) {
            return sysTreeTestVos;
        }
        return null;
    }


    @Override
    public void deleteByIds(List<String> ids) {
        String sql = "UPDATE SYS_TREE_TEST SET DEL_FLAG = 'Y' WHERE ID IN "+ getSqlByIds(ids);
        JdbcTemplate.update(sql);
    }

    @Override
    public List<SysTreeTestVo> getInfoByParentId(List<String> parentIds) {
        String sql = "SELECT ID,NAME,PARENT_ID parentId,PARENT_IDS parentIds,SORT,REMARK FROM SYS_TREE_TEST where  PARENT_ID in " + getSqlByIds(parentIds);
        return execuSql(sql);
    }

    @Override
    public List<SysTreeTestVo> getInfoByLikeParentIds(String parentIds) {
        String sql = "SELECT ID,NAME,PARENT_ID parentId,PARENT_IDS parentIds,SORT,REMARK FROM SYS_TREE_TEST where PARENT_IDS like '" + parentIds + "%'";
        return execuSql(sql);
    }

    @Override
    public void updateParentId(String parentIds, List<String> ids) {

    }


    @Nullable
    private List<SysTreeTestVo> execuSql(String sql) {
        List<SysTreeTest> list = JdbcTemplate.query(sql, new BeanHandler<>(SysTreeTest.class));
        if (!CollectionUtils.isEmpty(list)) {
            List<SysTreeTestVo> sysTreeTestVos = Lists.newArrayList();
            // 这里一般都是放在Service里面，所以这里肯定是会取到根节点代码的，当然也可以自己判断
            list.forEach((e) -> sysTreeTestVos.add(SysTreeTestVo.getSysTreeTestVo(e,"0")));
            return sysTreeTestVos;
        }
        return null;
    }




    /**
     * 根据IDS 获取('','','') 字符串
     *
     * @param ids List<String>
     * @return (' ', ' ', ' ')
     */
    private String getSqlByIds(List<String> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            throw new SofnException("IDS为空");
        }
        StringBuilder sql = new StringBuilder("(");
        if (!CollectionUtils.isEmpty(ids)) {
            for (int i = 0; i < ids.size() - 1; i++) {
                sql.append("'");
                sql.append(ids.get(i));
                sql.append("',");
            }
            sql.append("'");
            sql.append(ids.get(ids.size() - 1));
            sql.append("')");
        }
        return sql.toString();
    }
}
