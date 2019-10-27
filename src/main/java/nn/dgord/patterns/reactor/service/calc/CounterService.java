package nn.dgord.patterns.reactor.service.calc;

import reactor.core.publisher.Mono;

public interface CounterService {
    <F extends Number, S extends Number> Mono<? extends Number> count(F x, S y, CalcOperations operations);
}
