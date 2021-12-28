package com.sofn.core.util;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/** 序列化工具类
 * Created by Administrator on 2016/9/27.
 */
public class HessianCodeFactoryUtil{

    /**
     * 序列化
     * @param obj
     * @return
     * @throws IOException
     */
    public static byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream baos = null;
        HessianOutput output = null;
        baos = new ByteArrayOutputStream(1024);
        output = new HessianOutput(baos);
        output.startCall();
        output.writeObject(obj);
        output.completeCall();
        if(output != null){
            baos.close();;
        }
        return baos != null?baos.toByteArray() : null;
    }

    /**
     * 反序列化
     * @param in
     * @return
     * @throws IOException
     */
    public static Object deSerialize(byte[] in) throws IOException {
        Object obj = null;
        ByteArrayInputStream bais = null;
        HessianInput input = null;
        try{
            bais = new ByteArrayInputStream(in);
            input = new HessianInput(bais);
            input.startReply();
            obj = input.readObject();
        }catch (Throwable throwable){
            throwable.printStackTrace();
        }finally {
            if(input != null){
                bais.close();
            }
        }
        return obj;
    }
}
