package nn.dgord.patterns.observer.journal.domain.info;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OperationInfo {
    private Boolean isSucceed;
    private String message;
}
