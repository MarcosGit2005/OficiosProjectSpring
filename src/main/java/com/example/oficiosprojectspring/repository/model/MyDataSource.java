package com.example.oficiosprojectspring.repository.model;

import com.mysql.cj.jdbc.MysqlDataSource;
import oracle.jdbc.pool.OracleDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class MyDataSource {
    @Bean(name="mysqlDataSource")
    public static DataSource getMySQLDataSorce(){
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/accesoJava");
        dataSource.setUser("root");
        dataSource.setPassword("1234");

        return dataSource;
    }
    @Bean(name="oracleDataSource")
    public static DataSource getOracleDataSource() throws SQLException{
        OracleDataSource dataSource = new OracleDataSource();
        dataSource.setURL("jdbc:oracle:thin:@172.28.201.239:1521:xe");
        dataSource.setUser("C##1DAMRUBIO");
        dataSource.setPassword("password");
        return dataSource;
    }
}
