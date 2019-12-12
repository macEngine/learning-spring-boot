package com.springboot.scheduling;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;

public class CustomAsyncExceptionHandler
    implements AsyncUncaughtExceptionHandler {

  @Override
  public void handleUncaughtException(
      Throwable throwable, Method method, Object... obj) {

    System.out.println("函数异步执行错误，threadName=" + Thread.currentThread().getName());
    System.out.println("执行出现错误，errorMsg=" + throwable.getMessage());
    System.out.println("执行出现错误，methodName=" + method.getName());
    for (Object param : obj) {
      System.out.println("执行出现错误，param=" + param);
    }
  }
}
