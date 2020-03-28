package com.niuge.learning;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 比如有Application，才能使用@SpringBootTest这个注解。
 */
@SpringBootTest
@Slf4j
public class TaskExecutorTest {
  @Autowired
  private UserService userService;

  /**
   * 200328，实际上，这个测试没啥用，不要启动application才能测试。
   * 用kill命令才好测试。
   *

   * @throws Exception
   */
  @Test
  void testAsyncWithVoidMethod_thenSucceed() throws Exception {
    userService.runTaskOnStartup();
  }
}
