package nn.dgord.patterns.observer.journal.domain.observer;

import lombok.Data;
import lombok.EqualsAndHashCode;
import nn.dgord.patterns.observer.journal.domain.BaseEntity;
import nn.dgord.patterns.observer.journal.domain.Journal;
import nn.dgord.patterns.observer.journal.domain.info.OperationInfo;
import nn.dgord.patterns.observer.journal.domain.producer.Producer;
import nn.dgord.patterns.observer.journal.util.JsonUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class DefaultJournalSubscriber extends BaseEntity implements Observer<Journal, UUID> {
    private List<Journal> journals;
    private Producer producer;

    public DefaultJournalSubscriber(Producer journalProducer) {
        this.journals = new ArrayList<>();
        this.producer = journalProducer;
        System.out.println(journalProducer.registerObserver(this));
    }

    @Override
    public OperationInfo updateData(Journal data) {
        return OperationInfo.builder()
                .isSucceed(journals.add(data))
                .message(String.format("Added new journal %s from subscriber: %s",
                        data, producer))
                .build();
    }

    @Override
    public UUID getIdentity() {
        return this.id;
    }

    @Override
    public String toString() {
        return JsonUtils.toJson(this);
    }
}
