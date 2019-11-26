package com.example.demo;

import javax.validation.constraints.NotEmpty;

public class UserLoginRequest {
  @NotEmpty(message = "手机号不能为空")
  public String mobileNumber;
  @NotEmpty(message = "验证码不能为空")
  public String verificationCode;
}
