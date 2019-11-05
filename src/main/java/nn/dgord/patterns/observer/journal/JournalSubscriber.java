package nn.dgord.patterns.observer.journal;

import nn.dgord.patterns.observer.journal.domain.Journal;
import nn.dgord.patterns.observer.journal.domain.observer.DefaultJournalSubscriber;
import nn.dgord.patterns.observer.journal.domain.observer.Observer;
import nn.dgord.patterns.observer.journal.domain.producer.DefaultJournalProducer;
import nn.dgord.patterns.observer.journal.domain.producer.Producer;

import java.util.UUID;

public class JournalSubscriber {
    public static void main(String[] args) {
        Producer<Journal> producer = new DefaultJournalProducer();
        Observer<Journal, UUID> observer = new DefaultJournalSubscriber(producer);
        Observer<Journal, UUID> observer2 = new DefaultJournalSubscriber(producer);
        Observer<Journal, UUID> observer3 = new DefaultJournalSubscriber(producer);

        producer.notifyAllObservers();
        producer.updateContent(Journal
                .builder()
                .year(1985)
                .journalName("Burda")
                .description("Some description")
                .build());

        producer.notifyAllObservers();
        System.out.println(producer.removeObserver(observer2));
        System.out.println(producer.clearObservers());

    }
}
