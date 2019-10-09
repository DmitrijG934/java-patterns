package nn.dgord.patterns.strategy.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nn.dgord.patterns.strategy.domain.info.user.UserInfo;

@Data
@Builder
@NoArgsConstructor
public class User {
    private UserInfo userInfo;
}
