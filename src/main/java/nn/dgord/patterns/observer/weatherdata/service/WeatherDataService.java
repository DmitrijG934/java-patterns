package nn.dgord.patterns.observer.weatherdata.service;

import nn.dgord.patterns.observer.weatherdata.domain.BaseData;
import nn.dgord.patterns.observer.weatherdata.domain.ResponseData;

public interface WeatherDataService {
    <T extends BaseData> ResponseData setUpdatedData(T data);
}
