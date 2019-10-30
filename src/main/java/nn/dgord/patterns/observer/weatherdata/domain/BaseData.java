package nn.dgord.patterns.observer.weatherdata.domain;

import lombok.Data;
import lombok.Getter;

import java.util.UUID;

@Data
public abstract class BaseData {
    @Getter
    protected UUID dataId = UUID.randomUUID();
}
