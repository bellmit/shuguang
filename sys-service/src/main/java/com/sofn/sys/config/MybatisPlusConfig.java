package com.sofn.sys.config;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.sofn.common.db.MyBatisSysbolInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Created by sofn
 */
@Configuration
@MapperScan("com.sofn.sys.mapper*")
public class MybatisPlusConfig {
    /**
     * mybatis-plus分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setDialectType(DbType.POSTGRE_SQL.getDb());

        return paginationInterceptor;
    }

    /**
     * 自定义拦截器实现模糊查询中的特殊字符处理
     * hyj 先取消    当有forEach查询时会有问题   SysOrgMapper.getChildrenOrgByIds调用
     */
//    @Bean
//    public MyBatisSysbolInterceptor myInterceptor() {
//      return new MyBatisSysbolInterceptor();
//    }

    /**
     * 逻辑删除
     * @doc: https://mybatis.plus/guide/logic-delete.html
     * @return
     */
    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }
}
