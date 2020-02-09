package com.springboot.testannotation;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@WebMvcTest
public class Test_RequestBody_Annotation {

  private ObjectMapper objectMapper = new ObjectMapper();

  private MockMvc mvc;

  // Pull in the application context created by @ContextConfiguration
  // 使用了 @WebMvcTest，会自动加载默认配置，方便单元测试
  @Autowired
  WebApplicationContext webApplicationContext;

  @BeforeEach
  public void init() {
    mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }


  /**
   * 测试不添加 @RequestBody 时，参数值不能正确被绑定，返回空。
   *
   * @throws Exception
   */
  @Test
  public void whenNoRequestBodyAnnotation_thenWrong() throws Exception {

    UserLoginRequest userLoginRequest = new UserLoginRequest();
    userLoginRequest.mobileNumber = "18610000000";
    userLoginRequest.verificationCode = "141212";

    try {
      mvc.perform(
          MockMvcRequestBuilders.post("/api/user/loginWhenNoRequestBodyAnnotation")
              .contentType(MediaType.APPLICATION_JSON)
              .accept(MediaType.APPLICATION_JSON_VALUE)
              .content(objectMapper.writeValueAsString(userLoginRequest)) // 指定客户端能够接收的内容类型
      ).andReturn();
    } catch (Exception e) {
      // request.mobileNumber is null
    }
  }

  /**
   * 添加 @RequestBody 后，参数值正确被绑定。
   * 注意：@RequestBody接收的参数来自requestBody中，即请求体。Content-Type：“application/json”
   *
   * @throws Exception
   */
  @Test
  public void whenUseRequestBodyAnnotation_thenCorrect() throws Exception {

    UserLoginRequest userLoginRequest = new UserLoginRequest();
    userLoginRequest.mobileNumber = "18610000001";
    userLoginRequest.verificationCode = "141212";

    MvcResult mvcResult =
        mvc.perform(
            MockMvcRequestBuilders.post("/api/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(userLoginRequest))
        ).andReturn();

    int status = mvcResult.getResponse().getStatus();
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    assertEquals("success", content);
  }
}