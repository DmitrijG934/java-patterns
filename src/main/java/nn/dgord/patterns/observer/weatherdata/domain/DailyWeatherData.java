package nn.dgord.patterns.observer.weatherdata.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DailyWeatherData extends CommonWeatherData {
    public DailyWeatherData(float temperature, float humidity, float pressure) {
        super(temperature / 2, humidity / 2, pressure / 2);
    }
}
