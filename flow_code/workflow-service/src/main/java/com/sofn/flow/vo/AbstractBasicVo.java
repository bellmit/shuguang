package com.sofn.flow.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 将InforFlow提供的类单独抽离出来形成新的VO
 * @author heyongjie
 * @date 2020/5/13 11:03
 */
@Data
public abstract class AbstractBasicVo<T,E extends Serializable> {

    /**
     * ID
     */
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 获取VO对象
     * @param e  InforFlow提供的类信息
     * @return VO对象
     */
    public abstract T getVo(E e);

}
