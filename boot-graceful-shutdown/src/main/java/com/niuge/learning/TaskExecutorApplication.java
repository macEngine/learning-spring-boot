package com.niuge.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
public class TaskExecutorApplication {

  public static void main(String[] args) {
    SpringApplication.run(TaskExecutorApplication.class, args);
  }

}
