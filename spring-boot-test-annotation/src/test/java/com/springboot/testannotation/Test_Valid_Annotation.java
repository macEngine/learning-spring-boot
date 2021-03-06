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
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@WebMvcTest
public class Test_Valid_Annotation {

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
   * 不添加 @Valid ，参数值(UserLoginRequest.mobileNumber等)不会进行校验，接口正常返回。
   *
   * @throws Exception
   */
  @Test
  public void whenNoValidAnnotation_thenCorrect() throws Exception {

    UserLoginRequest userLoginRequest = new UserLoginRequest();

    MvcResult mvcResult = mvc.perform(
        MockMvcRequestBuilders.post("/api/user/loginWhenNoValidAnnotation")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .content(objectMapper.writeValueAsString(userLoginRequest)) // 指定客户端能够接收的内容类型
    ).andReturn();
    int status = mvcResult.getResponse().getStatus();
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    assertEquals("success", content);
  }

  /**
   * 添加 @Valid ，参数值被校验，mobileNumber不传则抛错。
   *
   * @throws Exception
   */
  @Test
  public void whenUseValidAnnotation_thenWrong() throws Exception {

    try {
      UserLoginRequest userLoginRequest = new UserLoginRequest();
      // mobileNumber 不设置，默认是null。

      mvc.perform(
          MockMvcRequestBuilders.post("/api/user/login")
              .contentType(MediaType.APPLICATION_JSON)
              .accept(MediaType.APPLICATION_JSON_VALUE)
              .content(objectMapper.writeValueAsString(userLoginRequest)) // 指定客户端能够接收的内容类型
      ).andReturn();
    } catch (MethodArgumentNotValidException e) {
      // MethodArgumentNotValidException 应该被 catch。
    }
  }
}