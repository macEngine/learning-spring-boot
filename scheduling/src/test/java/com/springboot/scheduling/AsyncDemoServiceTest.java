package com.springboot.scheduling;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.Future;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class AsyncDemoServiceTest {
  @Autowired
  private AsyncDemoService asyncDemoService;

  /**
   * 测试在Void方法上使用@Async。
   */
  @Test
  void testAsyncWithVoidMethod_thenSucceed() {
    asyncDemoService.sendEmail();
    System.out.println("主线程执行，threadName=" + Thread.currentThread().getName());
  }

  /**
   * 测试在有返回值的方法上使用@Async。
   */
  @Test
  void testAsyncWithNonVoidMethod_thenSucceed()
      throws Exception {
    System.out.println("主线程执行，threadName=" + Thread.currentThread().getName());
    Future<Integer> future = asyncDemoService.countUser();
    while (true) {
      if (future.isDone()) {
        System.out.println("函数异步执行完毕，result=" + future.get());
        return;
      }
      Thread.sleep(1000l);
    }
  }

  /**
   * 测试使用自己定义的Executor来执行@Async方法。
   */
  @Test
  void testAsyncWithCustomerMethodConfigExecutor_thenSucceed() {
    asyncDemoService.asyncWithCustomerMethodConfigExecutor();
    System.out.println("主线程执行，threadName=" + Thread.currentThread().getName());
  }

  /**
   * 测试使用自己定义的Executor来执行@Async方法。
   */
  @Test
  void testAsyncWithCustomerApplicationConfigExecutor_thenSucceed() {
    asyncDemoService.asyncWithCustomerApplicationConfigExecutor();
    System.out.println("主线程执行，threadName=" + Thread.currentThread().getName());
  }

}