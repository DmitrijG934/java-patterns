package nn.dgord.patterns.observer.simple.domain;

import lombok.Getter;

import java.util.UUID;

public abstract class IdentifiedDomain {
    @Getter
    protected final UUID id = UUID.randomUUID();
}
