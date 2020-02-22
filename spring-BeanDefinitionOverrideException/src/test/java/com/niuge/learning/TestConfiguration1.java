package com.niuge.learning;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfiguration1 {
  class TestBean1 {
    private String name;

    // standard getters and setters

  }

  @Bean
  public TestBean1 testBean(){
    return new TestBean1();
  }
}
