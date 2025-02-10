package com.mypersonalbook.economy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.mypersonalbook.economy")
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
