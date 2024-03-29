package nn.dgord.patterns.observer.weatherdata.observer;

import lombok.Data;
import nn.dgord.patterns.observer.weatherdata.display.Displayable;
import nn.dgord.patterns.observer.weatherdata.domain.BaseData;
import nn.dgord.patterns.observer.weatherdata.domain.CommonWeatherData;
import nn.dgord.patterns.observer.weatherdata.domain.ResponseData;
import nn.dgord.patterns.observer.weatherdata.publisher.Publisher;
import nn.dgord.patterns.observer.weatherdata.service.WeatherDataServiceImpl;

@Data
public class CurrentWeatherDisplay implements Observer, Displayable {
    private CommonWeatherData commonWeatherData;

    public CurrentWeatherDisplay(Publisher publisher) {
        if (publisher != null) {
            System.out.println(publisher.registerObserver(this));
        } else {
            throw new UnsupportedOperationException("Unable to register new observer. Publisher is null.");
        }
    }

    @Override
    public String display() {
        return String.format("======CURRENT WEATHER======\n" +
                        "\tTemperature: %.2f\n\tHumidity: %.2f\n\tPressure: %.2f\n===========================\n",
                commonWeatherData.getTemperature(), commonWeatherData.getHumidity(), commonWeatherData.getPressure()
        );
    }

    @Override
    public <T extends BaseData> ResponseData updateData(T data) {
        ResponseData.ResponseDataBuilder responseDataBuilder = ResponseData.builder();
        this.commonWeatherData = (CommonWeatherData) data;
        responseDataBuilder.receivedData(data);
        responseDataBuilder.isSucceed(true);
        responseDataBuilder.operationType(WeatherDataServiceImpl.OperationType.UPDATE);
        return responseDataBuilder.build();
    }
}
