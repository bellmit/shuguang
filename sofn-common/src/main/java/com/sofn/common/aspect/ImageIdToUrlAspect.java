package com.sofn.common.aspect;

import com.sofn.common.annotation.DataImage;
import com.sofn.common.annotation.ImageTran;
import com.sofn.common.fileutil.SysFileManageVo;
import com.sofn.common.fileutil.SysFileUtil;
import com.sofn.common.model.Result;
import com.xiaoleilu.hutool.util.ReflectUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.*;

/**
  * @Description: 图片ID转URL aop
  * @Author: quzhijie
  * @Date: 2019/11/27 13:48
  **/
@Aspect
@Component
@Slf4j
public class ImageIdToUrlAspect {

    @Pointcut("@annotation(imageTran)")
    public void includeService(ImageTran imageTran) {
    }

    @Around("includeService(imageTran)")
    public Object doAround(ProceedingJoinPoint pjp, ImageTran imageTran) throws Throwable {
    	long time1=System.currentTimeMillis();	
        Object result = pjp.proceed();
        long time2=System.currentTimeMillis();
        log.debug("获取JSON数据 耗时："+(time2-time1)+"ms");
        long start=System.currentTimeMillis();
        Object parseResult = this.parse(result);
        long end=System.currentTimeMillis();
        log.debug("解析注入JSON数据  耗时"+(end-start)+"ms");
        return parseResult;
    }

    private Object parse(Object obj) throws IllegalAccessException {
        if (obj instanceof Result) {
            Result result = (Result) obj;
            if (result.getData() != null) {
                result.setData(parseObject(result.getData()));
            }
            return result;
        }

        return obj;
    }

    private Object parseObject(Object obj) throws IllegalAccessException {
        // 集合
        if (obj instanceof Iterable){
            List<Object> list = new ArrayList<>();
            Iterable iterable = (Iterable) obj;
            iterable.forEach(item->{
                try {
                    if (!excludeType(item)){
                        list.add(parseObject(item));
                    }else {
                        list.add(item);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            });

            return list;
        }
        // 对象
        else if (!excludeType(obj)) {
            Map<String,Object> resultMap = bean2Map(obj);
            for (Field field : getAllFields(obj)) {
                Object fieldValue = ReflectUtil.getFieldValue(obj, field);
                if (fieldValue == null) {
                    continue;
                }

                DataImage dataImage = field.getAnnotation(DataImage.class);
                if (dataImage != null) {
                    if (Iterable.class.isAssignableFrom(field.getType()) && dataImage.isList()) {
                        Iterable iterable = (Iterable) fieldValue;
                        List list = new ArrayList();
                        iterable.forEach(item->{
                            if (item instanceof String){
                                list.add(SysFileUtil.getSysFileInfo((String) item));
                            }
                        });
                        if (resultMap != null) {
                            resultMap.put(field.getName()+"_info", list);
                        }
                    }else if (field.getType() == String.class){
                        String fileId = (String) fieldValue;
                        SysFileManageVo vo = SysFileUtil.getSysFileInfo(fileId);
                        if (vo != null) {
                            resultMap.put(field.getName()+"_info", vo);
                        }
                    }
                }else {
                    if (!excludeType(fieldValue)) {
                        if (resultMap != null) {
                            resultMap.put(field.getName(),parseObject(fieldValue));
                        }
                    }
                }
            }

            return resultMap;
        }else {
            return obj;
        }
    }

    private boolean excludeType(Object obj){
        return obj.getClass().isPrimitive()
                || obj instanceof String
                || obj instanceof Date
                || obj instanceof Calendar
                ;
    }

    private Field[] getAllFields(Object object) {
        Class<?> clazz = object.getClass();
        List<Field> fieldList = new ArrayList<>();
        while (clazz != null) {
            fieldList.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
            clazz = clazz.getSuperclass();
        }
        Field[] fields = new Field[fieldList.size()];
        fieldList.toArray(fields);
        return fields;
    }

    /**
     * 转换bean为map
     *
     * @param source 要转换的bean
     * @param <T>    bean类型
     * @return 转换结果
     */
    public static <T> Map<String, Object> bean2Map(T source) throws IllegalAccessException {
        Map<String, Object> result = new HashMap<>();

        Class<?> sourceClass = source.getClass();
        //拿到所有的字段,不包括继承的字段
        Field[] sourceFiled = sourceClass.getDeclaredFields();
        for (Field field : sourceFiled) {
            field.setAccessible(true);
            result.put(field.getName(), field.get(source));
        }
        return result;
    }

}
