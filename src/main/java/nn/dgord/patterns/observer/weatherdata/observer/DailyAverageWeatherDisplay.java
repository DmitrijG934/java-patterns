package nn.dgord.patterns.observer.weatherdata.observer;

import nn.dgord.patterns.observer.weatherdata.display.Displayable;
import nn.dgord.patterns.observer.weatherdata.domain.BaseData;
import nn.dgord.patterns.observer.weatherdata.domain.CommonWeatherData;
import nn.dgord.patterns.observer.weatherdata.domain.ResponseData;
import nn.dgord.patterns.observer.weatherdata.publisher.Publisher;
import nn.dgord.patterns.observer.weatherdata.service.WeatherDataServiceImpl;

public class DailyAverageWeatherDisplay implements Observer, Displayable {
    private CommonWeatherData commonWeatherData;

    public DailyAverageWeatherDisplay(Publisher publisher) {
        if (publisher != null) {
            System.out.println(publisher.registerObserver(this));
        } else {
            throw new UnsupportedOperationException("Unable to register new observer. Publisher is null.");
        }
    }

    @Override
    public String display() {
        return String.format("======DAILY WEATHER======\n" +
                        "\tTemperature: %.2f\n\tHumidity: %.2f\n\tPressure: %.2f\n===========================\n",
                commonWeatherData.getTemperature(), commonWeatherData.getHumidity(), commonWeatherData.getPressure()
        );
    }

    @Override
    public <T extends BaseData> ResponseData updateData(T data) {
        ResponseData.ResponseDataBuilder responseDataBuilder = ResponseData.builder();
        this.commonWeatherData = (CommonWeatherData) data;

        commonWeatherData.setTemperature(commonWeatherData.getTemperature() / 2);
        commonWeatherData.setHumidity(commonWeatherData.getHumidity() / 2);
        commonWeatherData.setPressure(commonWeatherData.getPressure() / 2);

        responseDataBuilder.receivedData(data);
        responseDataBuilder.isSucceed(true);
        responseDataBuilder.operationType(WeatherDataServiceImpl.OperationType.UPDATE);
        return responseDataBuilder.build();
    }
}
