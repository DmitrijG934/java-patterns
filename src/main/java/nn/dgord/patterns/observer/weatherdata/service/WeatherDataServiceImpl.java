package nn.dgord.patterns.observer.weatherdata.service;

import lombok.Getter;
import nn.dgord.patterns.observer.weatherdata.domain.BaseData;
import nn.dgord.patterns.observer.weatherdata.domain.ResponseData;
import nn.dgord.patterns.observer.weatherdata.domain.WeatherData;
import nn.dgord.patterns.observer.weatherdata.observer.Observer;
import nn.dgord.patterns.observer.weatherdata.publisher.Publisher;

import java.util.ArrayList;
import java.util.List;

import static nn.dgord.patterns.observer.weatherdata.service.WeatherDataServiceImpl.OperationType.NOTIFY;
import static nn.dgord.patterns.observer.weatherdata.service.WeatherDataServiceImpl.OperationType.REGISTER;
import static nn.dgord.patterns.observer.weatherdata.service.WeatherDataServiceImpl.OperationType.REMOVE;
import static nn.dgord.patterns.observer.weatherdata.service.WeatherDataServiceImpl.OperationType.UPDATE;

public class WeatherDataServiceImpl implements WeatherDataService, Publisher {
    private static List<Observer> observers;
    @Getter
    private WeatherData weatherData;

    public enum OperationType {
        REGISTER, REMOVE, UPDATE, NOTIFY
    }

    static {
        observers = new ArrayList<>();
    }

    @Override
    public <T extends BaseData> ResponseData setUpdatedData(T data) {
        this.weatherData = (WeatherData) data;
        return ResponseData.builder()
                .isSucceed(true)
                .operationType(UPDATE)
                .receivedData(data)
                .build();
    }

    @Override
    public ResponseData registerObserver(Observer observer) {
        ResponseData.ResponseDataBuilder responseDataBuilder = ResponseData.builder();
        responseDataBuilder.operationType(REGISTER);
        responseDataBuilder.receivedData(observer);
        if (observers.contains(observer)) {
            responseDataBuilder.isSucceed(false);
        } else {
            observers.add(observer);
            responseDataBuilder.isSucceed(true);
        }
        return responseDataBuilder.build();
    }

    @Override
    public ResponseData removeObserver(Observer observer) {
        ResponseData.ResponseDataBuilder responseDataBuilder = ResponseData.builder();
        responseDataBuilder.operationType(REMOVE);
        responseDataBuilder.receivedData(observer);
        if (!observers.isEmpty()) {
            if (!observers.contains(observer)) {
                responseDataBuilder.isSucceed(false);
            } else {
                observers.remove(observer);
                responseDataBuilder.isSucceed(true);
            }
        } else {
            responseDataBuilder.isSucceed(false);
        }
        return responseDataBuilder.build();
    }

    @Override
    public ResponseData notifyObservers() {
        ResponseData.ResponseDataBuilder responseDataBuilder = ResponseData.builder();
        responseDataBuilder.operationType(NOTIFY);
        StringBuilder sb = new StringBuilder();
        if (!observers.isEmpty()) {
            observers.forEach(o -> {
                o.updateData(weatherData);
                responseDataBuilder.isSucceed(true);
                sb.append(o);
            });
            responseDataBuilder.receivedData(
                    String.format("Observer %s has been received updated data.", sb.toString())
            );
        } else {
            responseDataBuilder.isSucceed(false);
            responseDataBuilder.receivedData("Observers were not specified.");
        }
        return responseDataBuilder.build();
    }
}
