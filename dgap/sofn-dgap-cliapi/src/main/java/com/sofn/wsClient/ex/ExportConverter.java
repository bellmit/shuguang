package com.sofn.wsClient.ex;

import com.sofn.model.dgap.FieldData;
import com.sofn.model.dgap.RowData;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.codec.binary.Base64;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by helong on 18-3-13.
 */
public class ExportConverter {

    private static Base64 base64 = new Base64(-1);

    public static <T> List<T> convertFromRowData(List<RowData> data, Class<T> type) throws InstantiationException, IllegalAccessException {
        if(data == null){
            return new ArrayList<T>();
        }

        if(data.size() > 1000){
            throw new IllegalArgumentException("batch size greater than limit: 1000");
        }
        List<T> list = new ArrayList<T>();
        for(RowData t:data){
            //System.out.println("row:"+t);
            list.add(convertFromRowDataList(t, type));
        }
        return list;
    }

    static <T> T convertFromRowDataList(RowData data, Class<T> type) throws IllegalAccessException, InstantiationException {

        T ret = type.newInstance();
        try {
            Map<String, Object> properties = PropertyUtils.describe(ret);
            for(FieldData fd:data.getFieldsData()){
                String fieldName = fieldNameFromColumnName(fd.getColumnName());
                //System.out.println("fieldName:"+fieldName);
                if(properties.keySet().contains(fieldName)){
                    Class dataType = PropertyUtils.getPropertyType(ret,fieldName);
                    Object value = fromString(fd.getValue(),dataType);
                    if(value != null) {
                        BeanUtils.setProperty(ret, fieldName, value);
                    }
                }else{
                    System.err.println("fieldName:"+fieldName+ " not found!");
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return ret;
    }

    static String fieldNameFromColumnName(String columnName){
        StringBuffer sb = new StringBuffer();
        boolean toUpper = false;
        for(int i=0;i < columnName.length(); i++){
            if('_' == (columnName.charAt(i))){
                toUpper = true;
                continue;
            }

            if(toUpper){
                sb.append(Character.toUpperCase(columnName.charAt(i)));
            }else{
                sb.append(Character.toLowerCase(columnName.charAt(i)));
            }
            toUpper = false;
        }
        return sb.toString();
    }

    private static Object fromString(String data,Class type){
        Object ret = null;
        if (data == null)
            return null;

        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (Timestamp.class.equals(type)) {
                return Timestamp.valueOf(data);
            } else if (Date.class.equals(type)) {
                return formatter.parse(data);
            } else {
                if (BigDecimal.class.equals(type)) {
                    //System.out.println("BigDecimal precision:"+((BigDecimal) data).precision() +",scale:"+((BigDecimal) data).scale());
                    ret = new BigDecimal(data);
                    if (((BigDecimal) ret).scale() > 4) {
                        throw new IllegalArgumentException("number's scale exceeds litmit of 4");
                    }
                } else if (String.class.equals(type)) {
                    ret = (String) data;
                    if (data.length() > 1000) {
                        throw new IllegalArgumentException("varchar2 field length exceed limit of 1000");
                    }
                } else if (byte[].class.equals(type)) {
                    ret = base64.decodeBase64(data);
                } else {
                    System.err.println("unkown type:"+type);
                }
                return ret;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
