package com.niuge.utils.spring.inject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class Test_spring_inject_set {
  @Autowired
  private MockMvc mvc;

  @Autowired
  AdviceOrderPaymentPostProcessor adviceOrderPaymentPostProcessor;

  /**
   * 200828 MockMvc可以直接autowired使用，比原来的方式简单多了。
   * 使用mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();时，所有的autowired都会失败。
   * 结论：使用SpringBootTest，不要使用WebMvcTest。
   * <p>
   * <p>
   * 这个project的目的就是验证：不需要配置，set，数组等autowired时，会把所有子类都放进去。
   */
  @Test
  public void testSpringInjectSet_thenCorrect() throws Exception {
    String mobileNumber = "18600000000";

    MvcResult mvcResult =
        mvc.perform(
            MockMvcRequestBuilders.get("/api/user/getMobileNumber")
        ).andReturn();

    int status = mvcResult.getResponse().getStatus();
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    assertEquals(mobileNumber, content);
  }
}