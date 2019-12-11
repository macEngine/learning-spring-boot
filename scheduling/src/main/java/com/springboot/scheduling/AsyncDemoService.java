package com.springboot.scheduling;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
public class AsyncDemoService {
  @Async
  public void sendEmail() {
    System.out.println("函数异步执行，threadName=" + Thread.currentThread().getName());
  }

  @Async
  public Future<Integer> countUser() {
    System.out.println("函数异步执行，threadName=" + Thread.currentThread().getName());
    try {
      Thread.sleep(2000l);
      return new AsyncResult<>(100);
    } catch (InterruptedException e) {
      //
    }
    return null;
  }

  @Async("beijing-executor-for-method")
  public void asyncWithCustomerMethodConfigExecutor() {
    System.out.println("函数异步执行，threadName=" + Thread.currentThread().getName());
  }

  @Async
  public void asyncWithCustomerApplicationConfigExecutor() {
    System.out.println("函数异步执行，threadName=" + Thread.currentThread().getName());
  }

}
