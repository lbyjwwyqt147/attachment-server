package com.jwell.file.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@ComponentScan("com.jwell.file")
public class FileServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileServerApplication.class, args);
    }
}
