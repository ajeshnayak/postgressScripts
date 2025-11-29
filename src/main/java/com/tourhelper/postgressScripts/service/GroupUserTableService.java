package com.tourhelper.postgressScripts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;

@Component
public class GroupUserTableService {
    @Autowired
    CommonService commonService;

    private static final String GROUP_USER ="group_user";
    private static final String CREATE_TABLE_DDL= """ 
            CREATE TABLE group_user (
                groupuser_guid VARCHAR(50) PRIMARY KEY,
                user_guid VARCHAR(50),
                group_guid VARCHAR(50),
                status VARCHAR(10),
                created DATE,
                last_updated TIMESTAMP);
            """;
    private static final String CREATE_INDEX_DDL = """
             CREATE INDEX group_user_index ON group_user (user_guid, group_guid );
           """;

    public void createTable(Connection postgresConnection) throws Exception {
        commonService.createTableIfNotExists(postgresConnection, GROUP_USER,CREATE_TABLE_DDL);
        commonService.createIndexIfNotExists(postgresConnection,"group_user_index",GROUP_USER,CREATE_INDEX_DDL);
    }
}
