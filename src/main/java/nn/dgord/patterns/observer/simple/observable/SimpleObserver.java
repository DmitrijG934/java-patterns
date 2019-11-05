package nn.dgord.patterns.observer.simple.observable;

import lombok.Data;
import nn.dgord.patterns.observer.simple.subject.Subject;

import java.util.ArrayList;
import java.util.List;

@Data
public class SimpleObserver implements Observable {
    private static List<Object> messageList;

    static {
        messageList = new ArrayList<>();
    }

    public SimpleObserver(Subject subject) {
        subject.registerObserver(this);
    }

    @Override
    public void updateData(Object object) {
        messageList.add(object);
    }
}
