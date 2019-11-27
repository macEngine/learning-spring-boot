package com.springboot.testannotation;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest
public class Test_RequestParam_Annotation {

  private ObjectMapper objectMapper = new ObjectMapper();

  private MockMvc mvc;

  // Pull in the application context created by @ContextConfiguration
  // 使用了 @WebMvcTest，会自动加载默认配置，方便单元测试
  @Autowired
  WebApplicationContext webApplicationContext;

  @Before
  public void init() {
    mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }


  /**
   * 添加 @RequestParam，参数值正确被绑定。
   * 注意：@RequestParam接收的参数来自requestHeader中，即请求头。Content-Type：“application/x-www-form-urlencoded”
   *
   * @throws Exception
   */
  @Test
  public void whenUseRequestParamAnnotation_thenCorrect() throws Exception {

    UserLoginRequest userLoginRequest = new UserLoginRequest();
    userLoginRequest.mobileNumber = "18610000001";
    userLoginRequest.verificationCode = "141212";
    System.out.println(objectMapper.writeValueAsString(userLoginRequest));

    MvcResult mvcResult =
        mvc.perform(
            MockMvcRequestBuilders.post("/api/user/loginWhenUseRequestParamAnnotation")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .header("request", objectMapper.writeValueAsString(userLoginRequest))
        ).andReturn();

    int status = mvcResult.getResponse().getStatus();
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    assertEquals(userLoginRequest.mobileNumber, content);
  }
}