package nn.dgord.patterns.strategy;

import nn.dgord.patterns.strategy.domain.Order;
import nn.dgord.patterns.strategy.domain.User;
import nn.dgord.patterns.strategy.domain.info.order.OrderInfo;
import nn.dgord.patterns.strategy.domain.info.user.UserInfo;
import nn.dgord.patterns.strategy.service.impl.UserDataStorageImpl;
import nn.dgord.patterns.strategy.strategy.impl.CreditCardPaymentStrategy;

import java.util.UUID;

import static nn.dgord.patterns.strategy.utils.Constants.USER_ID_FIRST;

public class BankApplication {
    public static void main(String[] args) {
        // Scenario
        Order order = new Order();
        order.setOrderInfo(OrderInfo.builder()
                .caption("Apples")
                .maintainer(User.builder().userInfo(UserInfo.builder()
                        .password("root")
                        .username("juggernaut123")
                        .balance(10000L)
                        .userId(USER_ID_FIRST)
                        .build()).build())
                .orderId(UUID.randomUUID())
                .paymentStrategy(new CreditCardPaymentStrategy(new UserDataStorageImpl()))
                .price(1000L)
                .build());
        order.performPayment();
    }
}
