package com.sofn.mybatis.generator.plugin;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.sql.JDBCType;
import java.util.List;


public class BeanValidationPlugin extends PluginAdapter {

    public BeanValidationPlugin() {
    }

    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public boolean modelFieldGenerated(Field field, TopLevelClass topLevelClass,
                                       IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable,
                                       ModelClassType modelClassType) {
        if(!introspectedColumn.isNullable()){
            field.addAnnotation("@NotNull");
            topLevelClass.addImportedType("javax.validation.constraints.NotNull");
        }

        int length = introspectedColumn.getLength();
        if(introspectedColumn.isJdbcCharacterColumn()) {
            field.addAnnotation("@Size(max=" + length + ")");
            topLevelClass.addImportedType("javax.validation.constraints.Size");
        }
        int scale = introspectedColumn.getScale();
        if(JDBCType.valueOf(introspectedColumn.getJdbcType()).equals(JDBCType.DECIMAL)){
            field.addAnnotation("@Digits(integer="+(length-scale)+",fraction="+scale+")");
            topLevelClass.addImportedType("javax.validation.constraints.Digits");
        }

        return true;
    }

}
