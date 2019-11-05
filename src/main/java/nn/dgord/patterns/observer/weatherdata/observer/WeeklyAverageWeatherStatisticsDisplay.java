package nn.dgord.patterns.observer.weatherdata.observer;

import nn.dgord.patterns.observer.weatherdata.display.Displayable;
import nn.dgord.patterns.observer.weatherdata.domain.BaseData;
import nn.dgord.patterns.observer.weatherdata.domain.CommonWeatherData;
import nn.dgord.patterns.observer.weatherdata.domain.DailyWeatherData;
import nn.dgord.patterns.observer.weatherdata.domain.ResponseData;
import nn.dgord.patterns.observer.weatherdata.publisher.Publisher;
import nn.dgord.patterns.observer.weatherdata.service.WeatherDataServiceImpl;
import nn.dgord.patterns.utils.Converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class WeeklyAverageWeatherStatisticsDisplay implements Observer, Displayable,
        Converter<CommonWeatherData, DailyWeatherData> {
    private List<DailyWeatherData> dailyWeatherData;
    private static final int DAYS_ON_WEEK = 7;

    public WeeklyAverageWeatherStatisticsDisplay(Publisher publisher) {
        this.dailyWeatherData = new ArrayList<>();
        if (publisher != null) {
            System.out.println(publisher.registerObserver(this));
        } else {
            throw new UnsupportedOperationException("Unable to register new observer. Publisher is null.");
        }
    }

    @Override
    public String display() {
        CommonWeatherData commonWeatherData = calculateWeeklyWeatherData();
        return String.format("======WEEKLY WEATHER======\n" +
                        "\tTemperature: %.2f\n\tHumidity: %.2f\n\tPressure: %.2f\n===========================\n",
                commonWeatherData.getTemperature(), commonWeatherData.getHumidity(), commonWeatherData.getPressure()
        );
    }

    private CommonWeatherData calculateWeeklyWeatherData() {
        validateListLength(dailyWeatherData.size());
        List<Float> weeklyHumidity = dailyWeatherData
                .stream()
                .map(CommonWeatherData::getHumidity)
                .collect(Collectors.toList());
        List<Float> weeklyTemperature = dailyWeatherData
                .stream()
                .map(CommonWeatherData::getTemperature)
                .collect(Collectors.toList());
        List<Float> weeklyPressure = dailyWeatherData
                .stream()
                .map(CommonWeatherData::getPressure)
                .collect(Collectors.toList());

        CommonWeatherData.CommonWeatherDataBuilder builder = CommonWeatherData.builder();
        Float averageHumidity = calcAverage(weeklyHumidity)
                .orElseThrow(() -> new UnsupportedOperationException("Unable to calculate weekly humidity."));
        builder.humidity(averageHumidity);

        Float averageTemperature = calcAverage(weeklyTemperature)
                .orElseThrow(() -> new UnsupportedOperationException("Unable to calculate weekly temperature."));
        builder.temperature(averageTemperature);

        Float averagePressure = calcAverage(weeklyPressure)
                .orElseThrow(() -> new UnsupportedOperationException("Unable to calculate weekly pressure."));
        builder.pressure(averagePressure);

        return builder.build();
    }

    private Optional<Float> calcAverage(List<Float> list) {
        int listSize = list.size();
        return list.stream()
                .reduce(Float::sum)
                .filter(result -> result != 0)
                .map(amount -> amount / listSize);
    }

    private <T extends Integer> void validateListLength(T length) {
        if ((Integer) length != DAYS_ON_WEEK) {
            throw new IllegalArgumentException("Unable to calculate weekly weather. Information provided not correct.");
        }
    }

    private <T extends DailyWeatherData> ResponseData appendDailyStatistics(T weatherData) {
        ResponseData.ResponseDataBuilder builder = ResponseData.builder();
        builder.operationType(WeatherDataServiceImpl.OperationType.UPDATE);
        builder.receivedData(weatherData);
        if (dailyWeatherData.size() == DAYS_ON_WEEK) {
            builder.isSucceed(false);
        } else {
            dailyWeatherData.add(weatherData);
            builder.isSucceed(true);
        }
        return builder.build();
    }

    @Override
    public <T extends BaseData> ResponseData updateData(T data) {
        if (data instanceof CommonWeatherData) {
            return appendDailyStatistics(convert((CommonWeatherData) data));
        }
        return ResponseData.builder()
                .receivedData(data)
                .isSucceed(false)
                .operationType(WeatherDataServiceImpl.OperationType.UPDATE)
                .build();
    }

    @Override
    public DailyWeatherData convert(CommonWeatherData src) {
        DailyWeatherData weatherData = new DailyWeatherData();
        if (src.getHumidity() != 0) {
            weatherData.setHumidity(src.getHumidity());
        }
        if (src.getPressure() != 0) {
            weatherData.setPressure(src.getPressure());
        }
        if (src.getTemperature() != 0) {
            weatherData.setTemperature(src.getTemperature());
        }
        return weatherData;
    }
}
