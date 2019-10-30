package nn.dgord.patterns.observer.weatherdata.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import nn.dgord.patterns.observer.weatherdata.service.WeatherDataServiceImpl;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class ResponseData extends BaseData {
    private boolean isSucceed;
    private WeatherDataServiceImpl.OperationType operationType;
    private Object receivedData;

    public String toString() {
        return String.format(
                "Id: %s\nOperation type: %s\nSuccess: %s\nReceived: %s\n",
                this.dataId, operationType, isSucceed, receivedData
        );
    }
}
