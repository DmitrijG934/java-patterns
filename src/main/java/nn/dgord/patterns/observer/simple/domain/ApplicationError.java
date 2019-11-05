package nn.dgord.patterns.observer.simple.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ApplicationError<T extends Throwable> {
    private T cause;
    private String message;
}
