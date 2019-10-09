package nn.dgord.patterns.strategy.domain;

import nn.dgord.patterns.strategy.domain.info.order.OrderInfo;
import nn.dgord.patterns.strategy.domain.info.user.UserInfo;
import nn.dgord.patterns.strategy.service.impl.BaseTest;
import nn.dgord.patterns.strategy.service.impl.UserDataStorageImpl;
import nn.dgord.patterns.strategy.strategy.impl.CreditCardPaymentStrategy;
import nn.dgord.patterns.strategy.strategy.impl.PaypalPaymentStrategy;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static nn.dgord.patterns.strategy.utils.Constants.USER_ID_FIRST;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class OrderTest extends BaseTest {
    private Order order;

    @Before
    public void setUp() {
        order = new Order();
        order.setOrderInfo(OrderInfo.builder()
                .price(100L)
                .orderId(UUID.randomUUID())
                .caption("test")
                .maintainer(User.builder()
                        .userInfo(UserInfo.builder()
                                .username("")
                                .password("")
                                .userId(USER_ID_FIRST)
                                .balance(1000L)
                                .isActive(true)
                                .lastVisit(LocalDateTime.now())
                                .build())
                        .build())
                .paymentStrategy(new CreditCardPaymentStrategy(new UserDataStorageImpl()))
                .build());
    }

    @Test
    public void when_instanceCreating_thenInstanceNotNull() {
        assertNotNull(order);

        assertNotNull(order.getOrderInfo());
        assertNotNull(order.getOrderInfo().getCaption());
        assertNotNull(order.getOrderInfo().getMaintainer());
        assertNotNull(order.getOrderInfo().getOrderId());
        assertNotNull(order.getOrderInfo().getPaymentStrategy());
        assertNotNull(order.getOrderInfo().getPrice());
    }

    @Test
    public void when_creditCardPaymentStrategyWorks_thenResultIsPaymentInfo() {
        PaymentInfo pay = order.getOrderInfo().getPaymentStrategy().pay(order);
        assertTrue(order.getOrderInfo().getPaymentStrategy() instanceof CreditCardPaymentStrategy);
        assertNotNull(pay);
    }

    @Test
    public void when_creditPayPalPaymentStrategyWorks_thenResultIsPaymentInfo() {
        order.getOrderInfo().setPaymentStrategy(new PaypalPaymentStrategy());
        PaymentInfo pay = order.getOrderInfo().getPaymentStrategy().pay(order);
        assertTrue(order.getOrderInfo().getPaymentStrategy() instanceof PaypalPaymentStrategy);
        assertNull(pay);
    }
}