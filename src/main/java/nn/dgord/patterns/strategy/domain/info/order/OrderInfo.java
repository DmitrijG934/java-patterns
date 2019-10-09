package nn.dgord.patterns.strategy.domain.info.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import nn.dgord.patterns.strategy.domain.User;
import nn.dgord.patterns.strategy.strategy.PaymentStrategy;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderInfo {
    @NonNull
    private UUID orderId;
    @NonNull
    private User maintainer;
    @NonNull
    private String caption;
    @NonNull
    private Long price;
    @NonNull
    private PaymentStrategy paymentStrategy;
}
