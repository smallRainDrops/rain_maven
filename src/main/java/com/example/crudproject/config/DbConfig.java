/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 * <p>
 * 数据库配置
 *
 * @author Mark sunlightcs@gmail.com
 *//*


package com.example.crudproject.config;


import com.example.crudproject.dao.*;
import com.example.crudproject.utils.RRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

*/
/**
 * 数据库配置
 *
 * @author Mark sunlightcs@gmail.com
 *//*

@Configuration
public class DbConfig {
    @Value("${renren.database: mysql}")
    private String database;
    @Autowired
    private MySQLGeneratorDao mySQLGeneratorDao;


    @Bean
    @Primary
    public GeneratorDao getGeneratorDao(){
        if("mysql".equalsIgnoreCase(database)){
            return mySQLGeneratorDao;
        }else {
            throw new RRException("不支持当前数据库：" + database);
        }
    }
}
*/
