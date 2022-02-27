package com.gabrielnardes.pcp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class PcpApplication {

    public static void main(String[] args) {
        SpringApplication.run(PcpApplication.class, args);
        System.out.println("Server on");
    }
}
