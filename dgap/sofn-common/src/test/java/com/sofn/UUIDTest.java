package com.sofn;

import com.sofn.core.util.RedisUtil;
import org.junit.Test;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * Created by dong4j on 16/9/1.
 * Description:
 */
public class UUIDTest {
    @Test
    public void test(){
        for(int i=0;i<100;i++){
            System.out.println((int)(Math.random()*10000)+"     "+new Date().getTime());
        }
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replace("-","");
        System.out.println(uuid.length());
    }
}
