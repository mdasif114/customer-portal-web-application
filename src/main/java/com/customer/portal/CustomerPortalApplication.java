package com.customer.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;

@SpringBootApplication
@EnableJdbcHttpSession
public class CustomerPortalApplication {
    public static void main(String[] args){
        SpringApplication.run(CustomerPortalApplication.class, args);
    }
}