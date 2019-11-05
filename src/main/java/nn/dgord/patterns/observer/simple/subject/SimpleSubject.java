package nn.dgord.patterns.observer.simple.subject;

import nn.dgord.patterns.observer.simple.domain.IdentifiedDomain;
import nn.dgord.patterns.observer.simple.domain.Message;
import nn.dgord.patterns.observer.simple.observable.Observable;

import java.util.ArrayList;
import java.util.List;

public class SimpleSubject implements Subject {
    private static List<Observable> observers;
    private static List<IdentifiedDomain> identifiedData;

    private enum OperationType {
        REGISTER, NOTIFY, REMOVE, CLEAN, UPDATE
    }

    static {
        observers = new ArrayList<>();
        identifiedData = new ArrayList<>();

        List<Message> predefinedMessages = List.of(
                Message.builder()
                        .text("some message")
                        .tag("tag1")
                        .build(),
                Message.builder()
                        .text("more texts")
                        .tag("one more tags")
                        .build(),
                Message.builder()
                        .text("to be or not to be!")
                        .tag("hamlet quotes")
                        .build()
        );

        identifiedData.addAll(predefinedMessages);
    }

    @Override
    public void updateData(Object object, boolean withReset) {
        if (object instanceof List && withReset) {
            identifiedData.clear();
        }
    }

    @Override
    public void registerObserver(Observable observable) {
        performOperation(OperationType.REGISTER, observable);
    }

    @Override
    public void removeObserver(Observable observable) {
       performOperation(OperationType.REMOVE, observable);
    }

    @Override
    public void notifyObservers() {
       performOperation(OperationType.NOTIFY, null);
    }

    @Override
    public void cleanAll() {
        performOperation(OperationType.CLEAN, null);
    }

    private void performOperation(OperationType operationType, Observable observable) {
        switch (operationType) {
            case REGISTER:
                if (!observers.contains(observable)) {
                    observers.add(observable);
                } else {
                    throw new UnsupportedOperationException(
                            String.format("Unable to add new observable: %s", observable)
                    );
                }
                break;
            case CLEAN:
                System.out.println("Cleaning observers...");
                if (!observers.isEmpty()) observers.clear();
                else {
                    throw new UnsupportedOperationException("Unable to notify observers. List of observers is empty.");
                }
                break;
            case NOTIFY:
                System.out.println("Notifying observers...");
                if (!observers.isEmpty()) observers.forEach(o -> {
                    o.updateData(identifiedData);
                    System.out.println(o + ": updated.");
                });
                else {
                    throw new UnsupportedOperationException("Unable to notify observers. List of observers is empty.");
                }
                break;
            case REMOVE:
                System.out.println("Removing observer...");
                if (!observers.isEmpty() && observers.contains(observable)) observers.remove(observable);
                else {
                    throw new UnsupportedOperationException("Unable to notify observers. List of observers is empty.");
                }
                break;
            case UPDATE:

            default:
                throw new UnsupportedOperationException("Unknown operation type.");
        }
    }

}
