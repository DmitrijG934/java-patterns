package nn.dgord.patterns.strategy.strategy;

import nn.dgord.patterns.strategy.domain.Order;
import nn.dgord.patterns.strategy.domain.PaymentInfo;

public interface PaymentStrategy {
    PaymentInfo pay(Order order);
}
