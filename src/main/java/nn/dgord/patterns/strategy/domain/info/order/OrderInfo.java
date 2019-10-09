package nn.dgord.patterns.strategy.domain.info.order;

import lombok.Builder;
import lombok.Data;
import nn.dgord.patterns.strategy.domain.User;
import nn.dgord.patterns.strategy.strategy.PaymentStrategy;

import java.util.UUID;

@Data
@Builder
public class OrderInfo {
    private UUID orderId;
    private User maintainer;
    private String caption;
    private PaymentStrategy paymentStrategy;
}
