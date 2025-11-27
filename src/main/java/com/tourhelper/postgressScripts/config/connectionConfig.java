package com.tourhelper.postgressScripts.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class connectionConfig {

    @Value("${spring.datasource.url}")
    String pgUrl;

    @Value("${spring.datasource.username}")
    String userName;

    @Value("${spring.datasource.password}")
    String password;

    @Bean
    public Connection postgresConnection() throws SQLException {
        return DriverManager.getConnection(pgUrl,userName,password);
    }
}
