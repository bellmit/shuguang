package com.sofn.common.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.BaseModel;
import com.sofn.common.utils.StringTools;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * base service
 *
 * @param <M> Mapper
 * @param <T> Model
 */
public class BaseService<M extends BaseMapper<T>, T extends BaseModel<T>> extends ServiceImpl<M, T> {

    /**
     * 超过50000条的list，改用多线程处理
     *
     * @param entityList
     * @return
     */
    @Override
    public boolean saveBatch(Collection<T> entityList) {
        List<T> list = Lists.newCopyOnWriteArrayList(entityList);
        int dataSize = list.size();
        int threadSize = 50000;
        int threadNum = dataSize / threadSize + 1;
        boolean special = dataSize % threadSize == 0;
        ExecutorService exec = Executors.newFixedThreadPool(threadNum);
        List<Callable<Integer>> tasks = Lists.newArrayList();
        Callable<Integer> task;
        List<T> cutList;
        for (int i = 0; i < threadNum; i++) {
            if (i == threadNum - 1) {
                if (special) {
                    break;
                }
                cutList = list.subList(threadSize * i, dataSize);
            } else {
                cutList = list.subList(threadSize * i, threadSize * (i + 1));
            }
            final List<T> listStr = cutList;
            task = () -> {
                listStr.forEach(BaseModel::preInsert);
                super.saveBatch(listStr, listStr.size());
                return null;
            };
            tasks.add(task);
        }
        try {
            exec.invokeAll(tasks);
            exec.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean updateBatchById(Collection<T> entityList) {
        entityList.forEach(BaseModel::preUpdate);
        return super.updateBatchById(entityList, entityList.size());
    }

    @Override
    public boolean save(T entity) {
        entity.preInsert();
        return super.save(entity);
    }

    @Override
    public boolean saveBatch(Collection<T> entityList, int batchSize) {
        entityList.forEach(BaseModel::pre);
        return super.saveBatch(entityList, batchSize);
    }

    @Override
    public boolean saveOrUpdate(T entity) {
        if (entity == null) {
            return false;
        }

        if (StringUtils.isNotBlank(entity.getId())) {
            entity.preUpdate();
        } else {
            entity.preInsert();
        }
        return super.saveOrUpdate(entity);
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<T> entityList) {
        entityList.forEach(entity -> {
            if (StringUtils.isNotBlank(entity.getId())) {
                entity.preUpdate();
            } else {
                entity.preInsert();
            }
        });
        return super.saveOrUpdateBatch(entityList, entityList.size());
    }

    @Override
    public boolean update(T entity, Wrapper<T> updateWrapper) {
        if (Objects.nonNull(entity)) {
            entity.preUpdate();
        }
        return super.update(entity, updateWrapper);
    }

    @Override
    public boolean updateById(T entity) {
        entity.preUpdate();
        return super.updateById(entity);
    }

    /**
     * 根据id查询未删除对象
     *
     * @param id
     * @return
     */
    protected T getOneById(String id) {
        return getOne(new QueryWrapper<T>().eq("DEL_FLAG", "N").eq("ID", id));
    }

    /**
     * 反射生成查询条件,不带id
     *
     * @param entity
     * @return
     */
    protected QueryWrapper<T> getQueryWrapper(T entity) {
        try {
            QueryWrapper<T> queryWrapper = new QueryWrapper<>();
            //拼接父类的DEL_FLAG
            try {
                entity.getClass().getSuperclass().getDeclaredField("delFlag");
                queryWrapper.eq("DEL_FLAG", "N");
            } catch (NoSuchFieldException ignore) {
            }
            //获取对象的class的包含字段
            Field[] declaredFields = entity.getClass().getDeclaredFields();
            int length = declaredFields.length;
            for (int i = 0; i < length; i++) {
                //强制访问私有成员变量
                declaredFields[i].setAccessible(true);
                //获取变量的值
                Object value = declaredFields[i].get(entity);
                if (value != null) {
                    //数据库列名
                    String columnName = StringTools.underscoreName(declaredFields[i].getName());
                    //追加条件
                    queryWrapper.eq(columnName, value);
                }
            }
            return queryWrapper;
        } catch (Exception e) {
            throw new SofnException("查询异常");
        }
    }

    /**
     * 反射生成查询条，带ID
     *
     * @param entity
     * @return
     */
    protected QueryWrapper<T> getQueryWrapper(String id, T entity) {
        return getQueryWrapper(entity).eq("ID", id);
    }
}
