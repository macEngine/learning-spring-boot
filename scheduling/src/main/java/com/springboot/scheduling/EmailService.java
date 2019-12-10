package com.springboot.scheduling;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

@Service
//@RestController
public class EmailService {
  @Async
  public void sendEmail() {
    try {
      Thread.sleep(2000l);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("111");
  }

}
