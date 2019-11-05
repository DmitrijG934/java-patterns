package nn.dgord.patterns.observer.weatherdata.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CommonWeatherData extends BaseData {
    protected float temperature;
    protected float humidity;
    protected float pressure;
}
