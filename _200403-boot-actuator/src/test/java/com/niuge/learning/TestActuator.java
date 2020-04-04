package com.niuge.learning;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 1、actuator到底是什么？启动器，促动器。执行器。
 * 2、Endpoints：SpringBoot的Endpoint主要是用来监控应用服务的运行状况，内置的Endpoint比如HealthEndpoint会监控dist和db的状况。
 * 3、by default, all Actuator endpoints are now placed under the /actuator path.
 * 4、一个endpoint就是一个url，比如/shutdown – performs a graceful shutdown of the application。
 */
@SpringBootTest
@Slf4j
public class TestActuator {
  @Test
  void testAsyncWithVoidMethod_thenSucceed() throws Exception {
  }
}
