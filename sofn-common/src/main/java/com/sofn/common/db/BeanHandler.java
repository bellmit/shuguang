package com.sofn.common.db;

import com.sofn.common.exception.SofnException;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class BeanHandler<T> implements IResultSetHandler<List<T>> {

    // 创建字节码对象
    private Class<T> clazz;

    // 创建有参构造函数，用于传入具体操作对象的类型
    public BeanHandler(Class<T> clazz) {
        this.clazz = clazz;
    }

    /**
     * 数据库集操作类
     *
     * @param rSet 数据库处理结果集
     * @return 数据库结果集 List 集合
     */
    @Override
    public List<T> handler(ResultSet rSet) {
        // 创建 List 用于存放装箱后的对象
        List<T> list = new ArrayList<>();
        try {
            if (isBaseClass()) {
                while(rSet.next()){
                    // 是基本类型返回第一列
                    list.add((T) rSet.getObject(1));
                }
                return list;
            }
            // 根据结果集进行装箱操作
            while (rSet.next()) {
                T obj = clazz.newInstance();
                Field[] fields = clazz.getDeclaredFields();
                if (fields != null) {
                    for (Field field : fields) {
                        String fieldName = field.getName();
                        Object value;
                        try{
                            value = rSet.getObject(fieldName);
                        }catch (Exception e){
                            e.printStackTrace();
                            throw new SofnException(fieldName + "列明无效，请在SQL中添加别名");
                        }
                        field.setAccessible(true);
                        if(value instanceof  BigDecimal){
                            if(Integer.class.equals(field.getType()) || int.class.equals(field.getType())){
                                field.set(obj, new BigDecimal(value.toString()).intValue());
                            }else  if(Long.class.equals(field.getType()) || long.class.equals(field.getType())){
                                field.set(obj, new BigDecimal(value.toString()).longValue());
                            }else{
                                field.set(obj,value);
                            }
                        }else{
                            field.set(obj,value);
                        }
                        field.setAccessible(false);
                    }
                }
                list.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 是否是基本类型
     *
     * @return true 是
     */
    private boolean isBaseClass() {
        if (String.class.equals(clazz) || Integer.class.equals(clazz)
                || int.class.equals(clazz) || Double.class.equals(clazz) || double.class.equals(clazz) ||
                Float.class.equals(clazz) || float.class.equals(clazz) || BigDecimal.class.equals(clazz) ||
                BigInteger.class.equals(clazz)) {
            return true;
        }
        return false;
    }

}
