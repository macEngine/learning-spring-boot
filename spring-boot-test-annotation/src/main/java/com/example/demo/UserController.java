package com.example.demo;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

  @GetMapping(value = "/getMobileNumber")
  public String getMobileNumber() {
    return "18600000000";

  }

  @PostMapping(value = "/loginWrong1")
  public String loginWrong1(UserLoginRequest request) {
    return request.mobileNumber;
  }

  @PostMapping(value = "/loginWhenNoValidAnnotation")
  public String loginWhenNoValidAnnotation(@RequestBody @Valid UserLoginRequest request) {
    return request.mobileNumber;
  }

  @PostMapping(value = "/login")
  public String login(@RequestBody @Valid UserLoginRequest request) {
    return request.mobileNumber;
  }
}
