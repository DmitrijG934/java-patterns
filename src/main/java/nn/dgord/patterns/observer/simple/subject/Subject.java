package nn.dgord.patterns.observer.simple.subject;

import nn.dgord.patterns.observer.simple.observable.Observable;

public interface Subject {
    void registerObserver(Observable observable);
    void removeObserver(Observable observable);
    void notifyObservers();
    void updateData(Object object, boolean withReset);
    void cleanAll();
}
