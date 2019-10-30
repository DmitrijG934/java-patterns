package nn.dgord.patterns.observer.weatherdata.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class WeatherData extends BaseData {
    private float temperature;
    private float humidity;
    private float pressure;
}
