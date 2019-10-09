package nn.dgord.patterns.strategy.strategy.impl;

import nn.dgord.patterns.strategy.domain.Order;
import nn.dgord.patterns.strategy.domain.PaymentInfo;
import nn.dgord.patterns.strategy.domain.User;
import nn.dgord.patterns.strategy.domain.info.user.UserInfo;
import nn.dgord.patterns.strategy.service.impl.UserDataStorageImpl;
import nn.dgord.patterns.strategy.strategy.PaymentStrategy;

import java.time.LocalDateTime;
import java.util.UUID;

public class CreditCardPaymentStrategy implements PaymentStrategy {
    private final UserDataStorageImpl dataStorageService;

    public CreditCardPaymentStrategy(UserDataStorageImpl dataStorageService) {
        this.dataStorageService = dataStorageService;
    }

    @Override
    public PaymentInfo pay(Order order) {
        System.out.println("Payment is performing...");
        User maintainer = order.getOrderInfo().getMaintainer();
        UUID userId = maintainer.getUserInfo().getUserId();

        Long orderPrice = order.getOrderInfo().getPrice();

        System.out.println("Payment will begin for user " + "[" + maintainer.getUserInfo().getUsername() + "] " +
                "...");
        System.out.println("Current user balance: " + maintainer.getUserInfo().getBalance());

        UserInfo entityData = dataStorageService.getEntityData(userId);
        entityData.setBalance(entityData.getBalance() - orderPrice);

        UserInfo updatedEntityData = dataStorageService.getEntityData(userId);
        System.out.println("Current user balance updated: " + updatedEntityData.getBalance());

        return new PaymentInfo(true,
                maintainer.getUserInfo().getUsername(),
                order.getOrderInfo().getCaption(),
                LocalDateTime.now());
    }
}
