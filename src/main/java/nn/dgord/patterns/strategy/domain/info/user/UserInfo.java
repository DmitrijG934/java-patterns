package nn.dgord.patterns.strategy.domain.info.user;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class UserInfo implements Serializable {
    @NonNull
    private UUID userId;
    @NonNull
    private String username;
    @NonNull
    private String password;
    private LocalDateTime lastVisit;
    private boolean isActive;
    @NonNull
    private Long balance;
}
