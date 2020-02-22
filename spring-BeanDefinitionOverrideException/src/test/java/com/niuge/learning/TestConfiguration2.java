package com.niuge.learning;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfiguration2 {
  class TestBean2 {
    private String name;

    // standard getters and setters

  }

  @Bean
  public TestBean2 testBean() {
    return new TestBean2();
  }
}
