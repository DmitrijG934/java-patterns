package nn.dgord.patterns.observer.journal.domain;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.Getter;
import nn.dgord.patterns.observer.journal.util.CustomView;

import java.io.Serializable;
import java.util.UUID;

@Data
public abstract class BaseEntity implements Serializable {
    @Getter
    @JsonView(CustomView.IdView.class)
    protected UUID id = UUID.randomUUID();
}
