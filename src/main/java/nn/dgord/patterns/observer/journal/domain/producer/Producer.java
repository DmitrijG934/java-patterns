package nn.dgord.patterns.observer.journal.domain.producer;

import nn.dgord.patterns.observer.journal.domain.BaseEntity;
import nn.dgord.patterns.observer.journal.domain.info.OperationInfo;
import nn.dgord.patterns.observer.journal.domain.observer.Observer;

public interface Producer<DT extends BaseEntity> {
    OperationInfo registerObserver(Observer observer);
    OperationInfo removeObserver(Observer observer);
    void notifyAllObservers();
    OperationInfo clearObservers();

    OperationInfo updateContent(DT data);
}
