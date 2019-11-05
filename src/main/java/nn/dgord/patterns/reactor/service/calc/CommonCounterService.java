package nn.dgord.patterns.reactor.service.calc;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class CommonCounterService implements CounterService {
    @Override
    public <F extends Number, S extends Number> Mono<? extends Number> count(F x, S y, CalcOperations operations) {
        return Mono.defer(() -> {
            switch (operations) {
                case ADDITION:
                    return add(x, y);
                case DIVISION:
                    return div(x, y);
                case SUBTRACTION:
                    return sub(x, y);
                case MULTIPLICATION:
                    return mul(x, y);
                default:
                    return Mono.empty();
            }
        })
                .switchIfEmpty(Mono.error(new RuntimeException("Unable to perform operation.")));
    }

    private <F extends Number, S extends Number> Mono<? extends Number> add(F x, S y) {
        return Flux.just(x, y)
                .reduce((number, number2) -> number.intValue() + number2.intValue());
    }

    private <F extends Number, S extends Number> Mono<? extends Number> sub(F x, S y) {
        return Flux.just(x, y)
                .reduce((number, number2) -> number.intValue() - number2.intValue());
    }

    private <F extends Number, S extends Number> Mono<? extends Number> mul(F x, S y) {
        return Flux.just(x, y)
                .reduce((number, number2) -> number.intValue() * number2.intValue());
    }

    private <F extends Number, S extends Number> Mono<? extends Number> div(F x, S y) {
        return Flux.just(x, y)
                .filter(number -> number.intValue() != 0)
                .switchIfEmpty(Mono.just(1))
                .reduce((number, number2) -> number.intValue() / number2.intValue());
    }
}
