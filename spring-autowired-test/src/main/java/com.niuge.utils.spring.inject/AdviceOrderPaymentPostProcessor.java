/*
 * Copyright 2014-present Miyou Tech Co., LTD. All Rights Reserved.
 */

package com.niuge.utils.spring.inject;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdviceOrderPaymentPostProcessor extends PaymentPostProcessorWithSubscribedPurposes {
  @Override
  protected List<String> getSubscribedPurposes() {
    return Lists.newArrayList();
  }

  @Override
  public void doPostProcessPayment() {
  }
}
