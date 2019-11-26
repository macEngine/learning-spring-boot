package com.example.demo;

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

import static junit.framework.TestCase.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest
public class TestRequestBodyAnnotation {

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
   * 测试不添加 @RequestBody 时，参数值不能正确被绑定，返回空。
   *
   * @throws Exception
   */
  @Test
  public void whenNotUserRequestBody_thenWrong() throws Exception {

    UserLoginRequest userLoginRequest = new UserLoginRequest();
    userLoginRequest.mobileNumber = "18610000000";
    userLoginRequest.verificationCode = "141212";

    MvcResult mvcResult =
        mvc.perform(
            MockMvcRequestBuilders.post("/api/user/loginWrong1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(userLoginRequest)) // 指定客户端能够接收的内容类型
        ).andReturn();

    int status = mvcResult.getResponse().getStatus();
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    assertEquals("", content);
  }
  /**
   * 添加 @RequestBody 后，参数值正确被绑定，返回输入时的电话号码。
   *
   * @throws Exception
   */
  @Test
  public void whenNotUserRequestBody_thenCorrect() throws Exception {

    UserLoginRequest userLoginRequest = new UserLoginRequest();
    userLoginRequest.mobileNumber = "18610000000";
    userLoginRequest.verificationCode = "141212";

    MvcResult mvcResult =
        mvc.perform(
            MockMvcRequestBuilders.post("/api/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(userLoginRequest)) // 指定客户端能够接收的内容类型
        ).andReturn();

    int status = mvcResult.getResponse().getStatus();
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    assertEquals( userLoginRequest.mobileNumber, content);
  }
}