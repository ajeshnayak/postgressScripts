package com.tourhelper.postgressScripts;

import com.tourhelper.postgressScripts.service.AccountTableService;
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
    Connection postgresConnection;
    @Override
    public void run(String... args) throws Exception {
        accountTableService.createAccountTable(postgresConnection);
        postgresConnection.close();
    }
}
