package nn.dgord.patterns.observer.weatherdata.publisher;

import nn.dgord.patterns.observer.weatherdata.domain.ResponseData;
import nn.dgord.patterns.observer.weatherdata.observer.Observer;

public interface Publisher {
    ResponseData registerObserver(Observer observer);
    ResponseData removeObserver(Observer observer);
    ResponseData notifyObservers();
}
