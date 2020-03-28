package com.niuge.learning;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class TaskExecutorTest {
  @Autowired
  private UserService userService;

  @Test
  void testAsyncWithVoidMethod_thenSucceed() {
    userService.runTaskOnStartup();
  }
}
