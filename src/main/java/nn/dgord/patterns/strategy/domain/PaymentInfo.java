package nn.dgord.patterns.strategy.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class PaymentInfo {
    private boolean isSucceed;
    private String username;
    private String orderCaption;
    private LocalDateTime paymentTime;
}
