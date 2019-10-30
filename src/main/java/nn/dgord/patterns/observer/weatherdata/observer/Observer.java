package nn.dgord.patterns.observer.weatherdata.observer;

import nn.dgord.patterns.observer.weatherdata.domain.BaseData;
import nn.dgord.patterns.observer.weatherdata.domain.ResponseData;

public interface Observer {
    <T extends BaseData> ResponseData updateData(T data);
}
