package nn.dgord.patterns.strategy.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nn.dgord.patterns.strategy.domain.info.order.OrderInfo;

@Data
@Builder
@NoArgsConstructor
public class Order {
    private OrderInfo orderInfo;
}
