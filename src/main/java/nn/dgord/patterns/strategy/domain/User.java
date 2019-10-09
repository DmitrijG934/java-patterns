package nn.dgord.patterns.strategy.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import nn.dgord.patterns.strategy.domain.info.user.UserInfo;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private UserInfo userInfo;

    public void increaseBalance(Long balance) {
        @NonNull Long previousBalance = userInfo.getBalance();
        System.out.println("Previous balance: " + previousBalance);
        userInfo.setBalance(previousBalance + balance);
        System.out.println("Balance amounted: " + userInfo.getBalance());
    }
}
