package com.cowbro.springboot.testrestcontroller;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

  @GetMapping(value = "/getMobileNumber")
  public String getMobileNumber() {
    return "18600000000";

  }

  @PostMapping(value = "/login")
  public String login(@RequestBody @Valid UserLoginRequest request) {
    System.out.println(request.mobileNumber);
    return "success";
  }
}
