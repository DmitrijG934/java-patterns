package nn.dgord.patterns.reactor.service.reader;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.IOException;

public interface ReaderService {
    Mono<String> readLine(File file);
    Flux<String> readLines(File file, int count) throws IOException;
    Flux<String> readAllLines(File file);
    Mono<String> findLine(File file, String regex);
}
