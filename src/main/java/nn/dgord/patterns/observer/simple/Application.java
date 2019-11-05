package nn.dgord.patterns.observer.simple;

import nn.dgord.patterns.observer.simple.domain.ApplicationError;
import nn.dgord.patterns.observer.simple.observable.Observable;
import nn.dgord.patterns.observer.simple.observable.SimpleObserver;
import nn.dgord.patterns.observer.simple.subject.SimpleSubject;

public class Application {
    public static void main(String[] args) {
        try {
            SimpleSubject simpleSubject = new SimpleSubject();

            Observable observable1 = new SimpleObserver(simpleSubject);
            Observable observable2 = new SimpleObserver(simpleSubject);
            Observable observable3 = new SimpleObserver(simpleSubject);

            simpleSubject.notifyObservers();

        } catch (Exception e) {
            System.out.println(new ApplicationError<>(e, e.getMessage()));
        }
    }
}
