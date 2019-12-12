package com.springboot.scheduling.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class CustomerMethodAsyncConfig {
  @Bean(name = "beijing-executor-for-method")
  public Executor threadPoolTaskExecutor() {
    return new ThreadPoolTaskExecutor();
  }
}
