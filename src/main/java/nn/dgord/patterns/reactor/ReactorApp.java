package nn.dgord.patterns.reactor;

import nn.dgord.patterns.reactor.service.reader.FileReaderService;
import nn.dgord.patterns.reactor.service.reader.ReaderService;

import java.io.File;
import java.io.IOException;

public class ReactorApp {
    public static void main(String[] args) throws IOException {
        ReaderService readerService = new FileReaderService();
        readerService.readLines(new File("test.json"), 3)
                .subscribe(System.out::println);
        /*readerService.readAllLines(new File("test.json"))
                .subscribe(System.out::println);*/
    }
}
