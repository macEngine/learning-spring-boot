package com.niuge.utils.spring.inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class PaymentPostProcessorManager {
  @Autowired
  private Set<PaymentPostProcessorWithSubscribedPurposes> processList;

  public void postProcessPayment() {
    for (IPaymentPostProcessor process : processList) {
      process.postProcessPayment();
    }
  }
}

