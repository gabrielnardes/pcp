package com.gabrielnardes.pcp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PcpApplication {

    public static void main(String[] args) {
        SpringApplication.run(PcpApplication.class, args);
        System.out.println("Server on");
    }
}
