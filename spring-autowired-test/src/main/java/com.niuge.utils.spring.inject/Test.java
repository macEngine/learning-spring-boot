package com.niuge.utils.spring.inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 20087 这不是一个spring的project，测试不了注入的情况。
 */
@Component
public class Test {
  @Autowired
  public CDPlayers cdPlayers;
  public static void main(String[] args) {
    CDPlayers cdPlayers = BeanFactoryUtil.getBean(CDPlayers.class);
    cdPlayers.printInfo();

  }
}
