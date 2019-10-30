package nn.dgord.patterns.strategy.strategy.impl;

import nn.dgord.patterns.BaseTest;
import nn.dgord.patterns.strategy.domain.Order;
import nn.dgord.patterns.strategy.domain.PaymentInfo;
import nn.dgord.patterns.strategy.domain.User;
import nn.dgord.patterns.strategy.domain.info.order.OrderInfo;
import nn.dgord.patterns.strategy.domain.info.user.UserInfo;
import nn.dgord.patterns.strategy.service.impl.UserDataStorageImpl;
import nn.dgord.patterns.strategy.strategy.PaymentStrategy;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CreditCardPaymentStrategyTest extends BaseTest {
    private CreditCardPaymentStrategy creditCardPaymentStrategy;
    private UserDataStorageImpl userDataStorageMock;

    @Before
    public void setUp() {
        userDataStorageMock = mock(UserDataStorageImpl.class);
        creditCardPaymentStrategy = new CreditCardPaymentStrategy(userDataStorageMock);
    }

    @Test(expected = NullPointerException.class)
    public void when_payPerformingWithNullOrder_thenResultIsError() {
        creditCardPaymentStrategy.pay(null);
    }

    @Test
    public void when_payPerforming_thenResultIsPaymentInfo() {
        final PaymentInfo expectedPaymentInfo = PaymentInfo.builder()
                .isSucceed(true)
                .orderCaption("test")
                .username("fucker")
                .build();
        User user = mock(User.class);
        UserInfo userInfo = mock(UserInfo.class);

        final UUID userId = UUID.randomUUID();
        when(userInfo.getBalance()).thenReturn(10000L);
        when(userInfo.getLastVisit()).thenReturn(null);
        when(userInfo.getPassword()).thenReturn("test");
        when(userInfo.getUserId()).thenReturn(userId);
        when(userInfo.getUsername()).thenReturn("fucker");

        when(user.getUserInfo()).thenReturn(userInfo);

        PaymentStrategy paymentStrategy = mock(PaymentStrategy.class);

        Order order = mock(Order.class);
        OrderInfo orderInfo = mock(OrderInfo.class);

        when(order.getOrderInfo()).thenReturn(orderInfo);
        when(orderInfo.getCaption()).thenReturn("test");
        when(orderInfo.getMaintainer()).thenReturn(user);
        when(orderInfo.getOrderId()).thenReturn(UUID.randomUUID());
        when(orderInfo.getPrice()).thenReturn(1000L);
        when(orderInfo.getPaymentStrategy()).thenReturn(paymentStrategy);

        when(userDataStorageMock.getEntityData(eq(userId))).thenReturn(userInfo);

        PaymentInfo pay = creditCardPaymentStrategy.pay(order);
        assertNotNull(pay);
        assertEquals(expectedPaymentInfo.getOrderCaption(), pay.getOrderCaption());
        assertEquals(expectedPaymentInfo.getUsername(), pay.getUsername());

        verify(userDataStorageMock, times(2)).getEntityData(eq(userId));
    }
}