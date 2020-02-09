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
public class Test_RequestParam_Annotation {

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
   * 添加 @RequestParam，参数值正确被绑定。
   * 注意：@RequestParam接收的参数来自requestHeader中，即请求头。Content-Type：“application/x-www-form-urlencoded”。
   *
   * @throws Exception
   */
  @Test
  public void testWhenUseRequestParamAnnotationForGet_thenCorrect() throws Exception {
    String mobileNumber = "18610000000";

    MvcResult mvcResult =
        mvc.perform(
            MockMvcRequestBuilders.get("/api/user/loginWhenUseRequestParamAnnotationForGet")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_FORM_URLENCODED)
                .param("mobileNumber", mobileNumber)
        ).andReturn();

    int status = mvcResult.getResponse().getStatus();
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    assertEquals(mobileNumber, content);
  }
}