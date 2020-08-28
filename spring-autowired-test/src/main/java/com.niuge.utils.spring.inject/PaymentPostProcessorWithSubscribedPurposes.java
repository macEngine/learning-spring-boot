package com.niuge.utils.spring.inject;



import java.util.List;

public abstract class PaymentPostProcessorWithSubscribedPurposes implements IPaymentPostProcessor {
  public abstract void doPostProcessPayment();

  protected abstract List<String> getSubscribedPurposes();

  @Override
  public void postProcessPayment() {
  }

}
