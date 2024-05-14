package com.example.oficiosprojectspring.repository.model;

import oracle.jdbc.pool.OracleDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
public class MyDataSource {
    @Bean(name="oracleDataSource")
    public static DataSource getOracleDataSource(){

        OracleDataSource dataSource = null;

        try {
            dataSource = new OracleDataSource();
            // dataSource.setURL("jdbc:oracle:thin:@//192.168.1.145:1539/xe"); PC-CASA
            dataSource.setURL("jdbc:oracle:thin:@//172.28.201.239:1521/xe");
            // dataSource.setUser("sys as sysdba"); PC-CASA
            dataSource.setUser("C##1DAMRUBIO");
            // dataSource.setPassword("oracle"); PC-CASA
            dataSource.setPassword("password");
            //dataSource.setRoleName("sysdba");
        } catch (SQLException e ){
            e.printStackTrace();
        }

        return dataSource;
    }
}
