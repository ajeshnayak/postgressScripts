package com.tourhelper.postgressScripts.service;

import java.sql.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AccountTableService {

    private static final String USER_ACCOUNT ="USER_ACCOUNT";
    private static final String CREATE_ACCOUNT_DDL= """ 
            CREATE TABLE USER_ACCOUNT(
                USER_ID VARCHAR(50) PRIMARY KEY,
                NAME VARCHAR(50),
                EMAIL VARCHAR(50) ,
                PASSWORD BYTEA)
            """;

    public void createAccountTable(Connection postgresConnection) throws Exception {
        createTableIfNotExists(postgresConnection, USER_ACCOUNT,CREATE_ACCOUNT_DDL);
       // createIndexIfNotExists(postgresConnection,);
    }


    private void createTableIfNotExists(Connection conn, String table, String ddl) throws Exception {
        DatabaseMetaData meta = conn.getMetaData();
        ResultSet rs = meta.getTables(null, null, table, null);
        if (!rs.next()) {
            try (Statement stmt = conn.createStatement()) {
                stmt.executeUpdate(ddl);
                log.warn("TABLE CREATED : {}",table);
            }catch (Exception e){
                log.error("Exception during table creation : ",e);
            }
        } else {
            log.info("Table exists: {}" , table);
        }
    }

    private void addColumnIfNotExists(Connection conn, String table, String column, String ddl) throws Exception {
        DatabaseMetaData meta = conn.getMetaData();
        ResultSet rs = meta.getColumns(null, null, table, column);
        if (!rs.next()) {
            try (Statement stmt = conn.createStatement()) {
                stmt.executeUpdate(ddl);
                System.out.println("Column added: " + column);
            }
        } else {
            System.out.println("Column exists: " + column);
        }
    }

    private void createIndexIfNotExists(Connection conn, String indexName, String table, String ddl) throws Exception {
        DatabaseMetaData meta = conn.getMetaData();
        ResultSet rs = meta.getIndexInfo(null, null, table, false, false);

        boolean exists = false;
        while (rs.next()) {
            if (indexName.equalsIgnoreCase(rs.getString("INDEX_NAME"))) {
                exists = true;
                break;
            }
        }

        if (!exists) {
            try (Statement stmt = conn.createStatement()) {
                stmt.executeUpdate(ddl);
                System.out.println("Index created: " + indexName);
            }
        } else {
            System.out.println("Index exists: " + indexName);
        }
    }
}


