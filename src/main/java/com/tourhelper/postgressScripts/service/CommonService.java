package com.tourhelper.postgressScripts.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Statement;

@Service
@Slf4j
public class CommonService {

    public void createTableIfNotExists(Connection conn, String table, String ddl) throws Exception {
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

    public void createIndexIfNotExists(Connection conn, String indexName, String table, String ddl) throws Exception {
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
                log.warn("Index created: " + indexName);
            }catch (Exception e){
                log.error("ERROR CREATING INDEX {} , ", indexName,e);
            }
        } else {
            log.info("Index exists: " + indexName);
        }
    }
}
