package com.tourhelper.postgressScripts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;

@Component
public class FriendTableService {
    @Autowired
    CommonService commonService;

    private static final String FRIEND ="friend";
    private static final String CREATE_TABLE_DDL= """ 
            CREATE TABLE friend (
                friend_entry_guid VARCHAR(50) PRIMARY KEY,
                user_guid VARCHAR(50),
                friend_guid VARCHAR(50),
                created DATE,
                last_updated TIMESTAMP);
            """;
    private static final String CREATE_INDEX_DDL = """
             CREATE INDEX friend_index ON friend (user_guid, friend_guid );
           """;

    public void createTable(Connection postgresConnection) throws Exception {
        commonService.createTableIfNotExists(postgresConnection, FRIEND,CREATE_TABLE_DDL);
        commonService.createIndexIfNotExists(postgresConnection,"friend_index",FRIEND,CREATE_INDEX_DDL);
    }
}
