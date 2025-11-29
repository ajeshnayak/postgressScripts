package com.tourhelper.postgressScripts.service;

import java.sql.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AccountTableService {

    @Autowired
    CommonService commonService;

    private static final String USER_ACCOUNT ="user_account";
    private static final String CREATE_ACCOUNT_DDL= """ 
            CREATE TABLE "user_account"(
                "user_guid" VARCHAR(50) PRIMARY KEY,
                "user_name" VARCHAR(50),
                "email_id" VARCHAR(50) ,
                "password" VARCHAR);
            """;
    private static final String CREATE_INDEX_DDL = """
             CREATE INDEX account_index ON user_account (email_id);
           """;

    public void createAccountTable(Connection postgresConnection) throws Exception {
        commonService.createTableIfNotExists(postgresConnection, USER_ACCOUNT,CREATE_ACCOUNT_DDL);
        commonService.createIndexIfNotExists(postgresConnection,"account_index",USER_ACCOUNT,CREATE_INDEX_DDL);
    }

}


