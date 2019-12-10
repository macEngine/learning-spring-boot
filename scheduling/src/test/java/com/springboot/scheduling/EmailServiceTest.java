package com.springboot.scheduling;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
//@WebMvcTest
class EmailServiceTest  {

  @Autowired
  private EmailService emailService;
  @Test
  void sendEmail() {
    System.out.println(emailService);
    emailService.sendEmail();
  }
}