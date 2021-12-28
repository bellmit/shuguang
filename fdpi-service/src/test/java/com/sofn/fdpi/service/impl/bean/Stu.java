package com.sofn.fdpi.service.impl.bean;

import lombok.Data;

/**
 * @Description
 * @Author wg
 * @Date 2021/4/6 11:04
 **/
@Data
public class Stu {
    private int age;
    private String name;

    public Stu(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public Stu() {
    }
}

