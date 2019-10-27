package nn.dgord.patterns.reactor.service.reader;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReaderService implements ReaderService {
    private static final String PATH = "D:\\IdeaProjects\\pattern-learn\\src\\main\\resources\\";

    @Override
    public Mono<String> readLine(File file) {
        return Mono.create(sink -> {
            try (FileReader fileReader = new FileReader(PATH + file)) {
                Scanner scanner = new Scanner(fileReader);
                sink.success(String.format("First line of document %s is: " + scanner.nextLine(), file));
            } catch (IOException e) {
                sink.error(e);
            }
        });
    }

    private Mono<Integer> numberOfLines(File file) {
        return Mono.create(sink -> {
            try (FileReader fileReader = new FileReader(PATH + file)) {
                Scanner scanner = new Scanner(fileReader);
                int numOfLines = 0;
                while (scanner.hasNext()) {
                    scanner.nextLine();
                    numOfLines++;
                }
                sink.success(numOfLines);
            } catch (IOException e) {
                sink.error(e);
            }
        });
    }

    @Override
    public Flux<String> readLines(File file, int count) {
        try (FileReader fileReader = new FileReader(PATH + file)) {
            Scanner scanner = new Scanner(fileReader);
            return numberOfLines(file)
                    .filter(numOfLines -> numOfLines != 0 && numOfLines >= 2)
                    .switchIfEmpty(Mono.error(new RuntimeException("Unable to read multiple lines." +
                            " There are no multiple lines.")))
                    .flatMapMany(num -> {
                        List<String> lines = new ArrayList<>();
                        int counter = 0;
                        while (scanner.hasNextLine()) {
                            if (counter == count) break;
                            lines.add(scanner.nextLine());
                            counter++;
                        }
                        return Flux.fromIterable(lines);
                    });
        } catch (IOException e) {
            return Flux.error(e);
        }
    }

    @Override
    public Flux<String> readAllLines(File file) {
        try (FileReader fileReader = new FileReader(PATH + file)) {
            Scanner scanner = new Scanner(fileReader);
            return numberOfLines(file)
                    .flatMapMany(num -> {
                        List<String> lines = new ArrayList<>();
                        while (scanner.hasNext()) {
                            lines.add(scanner.nextLine());
                        }
                        return Flux.fromIterable(lines);
                    });
        } catch (IOException e) {
            return Flux.error(e);
        }
    }

    @Override
    public Mono<String> findLine(File file, String regex) {
        return null;
    }
}
