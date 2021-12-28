package com.sofn.fdpi.service.impl.test;

import lombok.Data;
import org.junit.Test;
import org.springframework.stereotype.Component;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * @Description
 * @Author wg
 * @Date 2021/6/22 14:00
 **/
@Component
public class Mytest {

    @Test
    public void test() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        HashMap<Object, Object> hashMap = new HashMap<>();
        System.out.println(hashMap.values().getClass());
    }

    @Test
    public void get(){
        int[] ins={4,2,4,5,7,8,9};//19 28
        for (int i = 0; i < ins.length; i++) {
            for (int i1 = 0; i1 < ins.length; i1++) {
                if (ins[i]+ins[i1]==10){
                    System.out.println(ins[i]+"..."+ins[i1]);
                    i=ins.length;
                    break;
                }
            }
        }
    }
}

@Data
class Stu<T>{
    private int age;
    private T name;
    public static <E> E gete(E e){
        return e;
    }

}

class Dao<T>{
    public void addt(T t){
        System.out.println("数据库添加一条数据"+t);
    }
    public void delt(T t){
        System.out.println("数据库删除一条数据"+t);
    }
    public void uodate(List<? extends Dao> list){
        System.out.println(list);
    }
}

class Custom extends Dao<Stu>{

}
