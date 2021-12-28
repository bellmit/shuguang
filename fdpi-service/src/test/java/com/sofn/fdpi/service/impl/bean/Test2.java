package com.sofn.fdpi.service.impl.bean;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import reactor.core.publisher.Mono;
import sun.security.krb5.Realm;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author wg
 * @Date 2021/4/13 9:48
 **/
public class Test2<T> {
    public static void main(String[] args){
        if (1+1==2 || 1/0==2){
            System.out.println("asd");
        }
    }
}

abstract class F{
    String str;
    public F(String str) {
        this.str=str;
        System.out.println("父类构造器被调用");
        get();
    }

    public F() {

    }

    protected abstract  void get();
}

class Z extends F{
    public Z() {
        super();
        System.out.println("子类构造器被调用");
    }
    @Override
    public void get() {
        System.out.println(this.hashCode());
    }
}
