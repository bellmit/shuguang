//package com.sofn.service;
//
//import com.sofn.model.generator.TDgapDataImportField;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.Assert.*;
//
///**
// * Created by helong on 18-4-2.
// */
//public class DataApplyOnDemandSqlBuilderTest {
//
//    List<TDgapDataImportField> fieldList = new ArrayList<>();
//
//    @Before
//    public void setup(){
//        for(int i = 0 ;i < 10; i++) {
//            TDgapDataImportField field = new TDgapDataImportField();
//            field.setEnglishName("field" + i);
//            fieldList.add(field);
//        }
//    }
//
//    @Test
//    public void insertData() throws Exception {
//        DataApplyOnDemandSqlBuilder.insertData("061ed021fda34921936ac1d2bb489c4612ca0aefefde42a2b3e57aca9e8921b4",
//                "organic_product",fieldList,"dgap_dw");
//    }
//
//    @Test
//    public void updateData() throws Exception {
//        DataApplyOnDemandSqlBuilder.updateData("061ed021fda34921936ac1d2bb489c4612ca0aefefde42a2b3e57aca9e8921b4",
//                "organic_product", fieldList,"dgap_dw");
//    }
//
//    @Test
//    public void deleteData() throws Exception {
//        DataApplyOnDemandSqlBuilder.deleteData("061ed021fda34921936ac1d2bb489c4612ca0aefefde42a2b3e57aca9e8921b4","organic_product","dgap_dw");
//    }
//
//}