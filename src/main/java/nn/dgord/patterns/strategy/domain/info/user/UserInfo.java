package nn.dgord.patterns.strategy.domain.info.user;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class UserInfo {
    private UUID userId;
    private String username;
    private String password;
    private LocalDateTime lastVisit;
    private boolean isActive;
}
