package nn.dgord.patterns.observer.journal.domain.producer;

import lombok.Data;
import lombok.EqualsAndHashCode;
import nn.dgord.patterns.observer.journal.domain.BaseEntity;
import nn.dgord.patterns.observer.journal.domain.Journal;
import nn.dgord.patterns.observer.journal.domain.info.OperationInfo;
import nn.dgord.patterns.observer.journal.domain.observer.Observer;
import nn.dgord.patterns.observer.journal.util.JsonUtils;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class DefaultJournalProducer extends BaseEntity implements Producer<Journal> {
    private static List<Journal> journals;
    private static List<Observer> observers;

    static {
        journals = new ArrayList<>();
        observers = new ArrayList<>();
    }

    @Override
    public OperationInfo registerObserver(Observer observer) {
        return OperationInfo.builder()
                .isSucceed(observers.add(observer))
                .message(String.format("New journal observer was added: %s", observer))
                .build();
    }

    @Override
    public OperationInfo removeObserver(Observer observer) {
        return OperationInfo.builder()
                .isSucceed(observers.remove(observer))
                .message(String.format("Journal observer was removed: %s", observer.getIdentity()))
                .build();
    }

    @Override
    public void notifyAllObservers() {
        observers.forEach(observer -> journals.forEach(journal ->
                System.out.println(observer.updateData(journal))));
    }

    @Override
    public OperationInfo clearObservers() {
        observers.clear();
        return OperationInfo.builder()
                .isSucceed(true)
                .build();
    }

    @Override
    public OperationInfo updateContent(Journal data) {
        return OperationInfo.builder()
                .isSucceed(journals.add(data))
                .build();
    }

    public String toString() {
        return JsonUtils.toJson(this);
    }
}
