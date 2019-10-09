package nn.dgord.patterns.strategy.strategy.impl;

import nn.dgord.patterns.strategy.domain.PaymentInfo;
import nn.dgord.patterns.strategy.strategy.PaymentStrategy;

public class PaypalPaymentStrategy implements PaymentStrategy {
    @Override
    public PaymentInfo pay() {
        return null;
    }
}
