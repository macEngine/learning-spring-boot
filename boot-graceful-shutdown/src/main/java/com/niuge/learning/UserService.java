package com.niuge.learning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  @Autowired
  private TaskExecutor taskExecutor;

  public void runTaskOnStartup()  {
    System.out.println("===================");
    for (int i = 0; i < 3; i++) {
      taskExecutor.execute(() -> {
        try {
          Thread.sleep(60000l);

          System.out.println("+++++++++++running+");
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      });
    }


    System.out.println("===========end===");
  }
}
