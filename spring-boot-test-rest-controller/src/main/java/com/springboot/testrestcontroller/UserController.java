package com.springboot.testrestcontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

  @GetMapping(value = "/getMobileNumber")
  public String getMobileNumber() {
    return "18600000000";

  }

  @PostMapping(value = "/login")
  public String login() {
    return "success";
  }
}
