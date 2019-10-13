package nn.dgord.patterns.strategy.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nn.dgord.patterns.strategy.domain.info.order.OrderInfo;
import nn.dgord.patterns.strategy.exception.PaymentException;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {
    @Getter
    @Setter
    private OrderInfo orderInfo;

    public void performPayment() {
        if(checkBalance()) {
            PaymentInfo pay = orderInfo.getPaymentStrategy().pay(this);
            System.out.println("Result of payment: " + pay);
        }
    }

    private boolean checkBalance() {
        User maintainer = orderInfo.getMaintainer();
        if (maintainer.getUserInfo().getBalance() < orderInfo.getPrice()) {
            throw new PaymentException(String.format("Unable to perform payment for user %s with id %s." +
                            " Order price is higher than user balance.", maintainer.getUserInfo().getUsername(),
                    maintainer.getUserInfo().getUserId()));
        }
        return true;
    }
}
