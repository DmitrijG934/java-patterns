package nn.dgord.patterns.observer.journal.domain.observer;

import nn.dgord.patterns.observer.journal.domain.BaseEntity;
import nn.dgord.patterns.observer.journal.domain.info.OperationInfo;

public interface Observer<DT extends BaseEntity, ID> {
    OperationInfo updateData(DT data);
    ID getIdentity();
}
