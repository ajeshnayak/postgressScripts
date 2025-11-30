package com.tourhelper.postgressScripts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;

@Component
public class GroupTableService {


    @Autowired
    CommonService commonService;

    private static final String GROUP ="group_data";
    private static final String CREATE_TABLE_DDL= """ 
            CREATE TABLE group_data (
                group_guid VARCHAR(50) PRIMARY KEY,
                group_name VARCHAR(50),
                owner VARCHAR(50),
                status VARCHAR(10),
                created TIMESTAMP,
                last_updated TIMESTAMP);
            """;
    private static final String CREATE_INDEX_DDL = """
             CREATE INDEX group_index ON group_data (group_name);
           """;

    public void createGroupTable(Connection postgresConnection) throws Exception {
        commonService.createTableIfNotExists(postgresConnection, GROUP,CREATE_TABLE_DDL);
        commonService.createIndexIfNotExists(postgresConnection,"group_index",GROUP,CREATE_INDEX_DDL);
    }
}
