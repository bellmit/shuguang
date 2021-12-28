package com.sofn.common.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * 通用vo
 * @author tc
 * @param <V> 继承BaseVo
 */
@Data
@Slf4j
public abstract class BaseVo<V extends BaseVo> implements Serializable {

    private static final long serialVersionUID = -5460449441516490680L;
    /**
     * 通用ID
     */
    private String id;

    /**
     * 通过BeanUtils，把vo的数据复制到model中，如出现异常则返回null
     * @param clazz 复制vo的目标类
     * @return
     */
    public <M> M convertToModel(Class<M> clazz){
        M m = null;
        try {
            //创建model实例
            m = clazz.getConstructor().newInstance();
            //复制数据
            BeanUtils.copyProperties(this, m);
        } catch (Exception e){
            log.error("convertToModel error:{}", e);
        }
        return m;
    }

    /**
     * 通过BeanUtils，把model的数据复制到vo中，如出现异常则返回null
     * @param m
     * @return
     */
    public <M> V convertFromModel(M m){
        V v = null;
        try {
            //获取当前vo对象
            v = (V) this;
            //复制数据
            BeanUtils.copyProperties(m,v);
        } catch (Exception e){
            log.error("convertFromModel error:{}", e);
        }
        return v;
    }

}
