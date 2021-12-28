package com.sofn.wsClient.ex;

import com.sofn.model.dgap.FieldData;
import com.sofn.model.dgap.RowData;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.cxf.common.util.StringUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.groups.Default;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by helong on 18-3-13.
 */
public class BeanConverter {

    private static Base64 base64 = new Base64(-1);

    public static <T> RowData convertToRowData(T data){

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(data, Default.class);
        if(!violations.isEmpty()){
            for(ConstraintViolation<T> violation:violations){
                System.out.println(violation.toString());
            }
            throw new RuntimeException("data verification failed!");
        }

        RowData rowData = new RowData();
        try {
            Map<String, Object> properties = PropertyUtils.describe(data);
            //System.out.println(properties);
            String id = (String)PropertyUtils.getProperty(data,"id");
            if(StringUtils.isEmpty(id)){
                throw new IllegalArgumentException("id property CAN NOT be null");
            }
            rowData.setId(id);

            List<FieldData> fields = new ArrayList<FieldData>();
            for(String pName:properties.keySet()){
                if("dataid".equals(pName) || "status".equals(pName) || "operate".equals(pName) || "dataImportTime".equals(pName) || "mark".equals(pName)){
                    throw new IllegalArgumentException(pName+" property(dataid, status,operate,dataImportTime,mark) reserved by platform");
                }else if("class".equals(pName) || "id".equals(pName)){
                    continue;
                }
                FieldData field = new FieldData();
                String columnName = fieldName2ColumnName(pName);
                field.setColumnName(columnName);
                String value = toString(properties.get(pName));
                field.setValue(value);
                System.out.println("field:"+field);
                fields.add(field);
            }
            rowData.setFieldsData(fields);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return rowData;
    }

    public static <T> List<RowData> convertToRowDataList(List<T> data){
        if(data.size() > 1000){
            throw new IllegalArgumentException("batch size greater than limit: 1000");
        }
        List<RowData> list = new ArrayList<RowData>();
        for(T t:data){
            list.add(convertToRowData(t));
        }
        return list;
    }

    static String fieldName2ColumnName(String fieldName){
        StringBuffer sb = new StringBuffer();
        for(int i=0;i < fieldName.length(); i++){
            if(i>0 && Character.isUpperCase(fieldName.charAt(i))){
                sb.append("_");
            }
            sb.append(Character.toUpperCase(fieldName.charAt(i)));
        }
        return sb.toString();
    }

    private static String toString(Object data){
        String ret = null;
        if (data == null)
            return null;

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(data instanceof Timestamp){
            return Long.toString(((Timestamp) data).getTime());
        }else if(data instanceof Date){
            return formatter.format(data);
        }else{
            if(data instanceof BigDecimal){
                //System.out.println("BigDecimal precision:"+((BigDecimal) data).precision() +",scale:"+((BigDecimal) data).scale());
                if (((BigDecimal) data).scale() > 4){
                    throw new IllegalArgumentException("number's scale exceeds litmit of 4");
                }
                ret = data.toString();
            }else if(data instanceof String){
                ret = (String)data;
                if (ret.length() > 4000){
                    throw new IllegalArgumentException("varchar2 field length exceed limit of 4000");
                }
            }else if(data instanceof byte[]){
                ret = base64.encodeBase64String((byte[])data);
            }else{
                ret = data.toString();
            }
            return ret;
        }
    }
}
