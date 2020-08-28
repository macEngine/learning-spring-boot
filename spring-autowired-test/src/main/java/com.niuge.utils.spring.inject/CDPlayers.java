package com.niuge.utils.spring.inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class CDPlayers {

  @Autowired
  private IPaymentPostProcessor[] mediaPlayerArr;

  @Autowired
  private List<IPaymentPostProcessor> mediaPlayerList;

  @Autowired
  private Map<String, IPaymentPostProcessor> mediaPlayerMap;

  @Autowired
  public void printInfo() {
    System.out.println(Arrays.toString(mediaPlayerArr));
    System.out.println(mediaPlayerList);
    System.out.println(mediaPlayerMap);
  }
  public static void main(String[] args) {

  }
}

