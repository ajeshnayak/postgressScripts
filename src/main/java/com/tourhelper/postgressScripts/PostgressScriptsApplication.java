package com.tourhelper.postgressScripts;

import com.tourhelper.postgressScripts.service.AccountTableService;
import com.tourhelper.postgressScripts.service.FriendTableService;
import com.tourhelper.postgressScripts.service.GroupTableService;
import com.tourhelper.postgressScripts.service.GroupUserTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;

@SpringBootApplication
public class PostgressScriptsApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(PostgressScriptsApplication.class, args);
	}

    @Autowired
    AccountTableService accountTableService;

    @Autowired
    GroupTableService groupTableService;

    @Autowired
    GroupUserTableService groupUserTableService;

    @Autowired
    FriendTableService friendTableService;

    @Autowired
    Connection postgresConnection;
    @Override
    public void run(String... args) throws Exception {
        accountTableService.createAccountTable(postgresConnection);
        groupTableService.createGroupTable(postgresConnection);
        groupUserTableService.createTable(postgresConnection);
        friendTableService.createTable(postgresConnection);
        postgresConnection.close();
    }
}
