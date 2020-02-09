package com.springboot.testannotation;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@WebMvcTest
public class Test_PathVariable_Annotation {

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
   * 展示 @PathVariable 在 URI 中的用法。
   * PathVariable 和 RequestParam 最本质的区别在于，
   * 前者是在path中传参数： /api/user/{id}/get
   * 后者是在问号后面传参数： /api/user/list?date=191130
   *
   * @throws Exception
   */
  @Test
  public void testWhenUsePathVariableAnnotationForGet_thenCorrect() throws Exception {
    String id = "100";

    MvcResult mvcResult =
        mvc.perform(
            MockMvcRequestBuilders.get("/api/user/100/get")
        ).andReturn();

    int status = mvcResult.getResponse().getStatus();
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    assertEquals(id, content);
  }
}