package nn.dgord.patterns.strategy.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PaymentInfo {
    private boolean isSucceed;
    private LocalDateTime paymentTime;
}
