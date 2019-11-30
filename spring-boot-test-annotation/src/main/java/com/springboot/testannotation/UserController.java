package com.springboot.testannotation;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

  @GetMapping(value = "/getMobileNumber")
  public String getMobileNumber() {
    return "18600000000";

  }

  @PostMapping(value = "/loginWhenNoRequestBodyAnnotation")
  public String loginWhenNoRequestBodyAnnotation(UserLoginRequest request) {
    System.out.println(request.mobileNumber.length());
    return "success";
  }

  @PostMapping(value = "/login")
  public String login(@RequestBody @Valid UserLoginRequest request) {
    System.out.println(request.mobileNumber);
    return "success";
  }


  @PostMapping(value = "/loginWhenNoValidAnnotation")
  public String loginWhenNoValidAnnotation(@RequestBody UserLoginRequest request) {
    return "success";
  }

  @GetMapping(value = "/loginWhenUseRequestParamAnnotationForGet")
  public String loginWhenUseRequestParamAnnotation(@RequestParam String mobileNumber) {
    return mobileNumber;
  }

  @PostMapping(value = "/loginWhenUseRequestParamAnnotationForPost")
  public String loginWhenUseRequestParamAnnotation(@RequestParam UserLoginRequest request) {
    return request.mobileNumber;
  }
}
