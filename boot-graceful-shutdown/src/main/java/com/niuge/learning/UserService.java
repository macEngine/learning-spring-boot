package com.niuge.learning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class UserService {
  /**
   * 200328, 错误：There is more than on 'TaskExecutor' type.
   * 有一个是我自己定义的：CustomTaskExecutor。
   * 另外一个是系统定义的：TaskExecutionAutoConfiguration.class。
   * <p>
   * 所以这里spring不知道要使用哪个。
   * 添加 @Qualifier("taskExecutor")，
   * Qualifier是不是name? 不是。
   * Qualifier的意思就是限定词，修饰语。
   * Qualified name 并不等同于 Bean name，后者必须是唯一的，但是前 者类似于 tag 或者 group 的作用。
   */
  @Autowired
  @Qualifier("taskExecutor")
  private TaskExecutor taskExecutor;


  /**
   * 池子大小是2，for循环最多同时启动2个线程（等两个线程结束后，第3个才能启动）。
   *
   * @throws Exception
   */
  @PostConstruct
  public void runTaskOnStartup() throws Exception {
    System.out.println("异步任务开始。");
    for (int i = 0; i < 3; i++) {
      taskExecutor.execute(() -> {
        try {
          Thread.sleep(20000l);

          System.out.println("异步任务进行中。。。");
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      });
    }

    Thread.sleep(6000l);
    System.out.println("异步任务结束。");
  }
}
