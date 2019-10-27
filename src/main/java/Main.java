import reactor.core.publisher.Flux;

public class Main {
    public static void main(String[] args) {
        Flux.range(1, 10)
                .filter(n -> n % 2 == 0)
                .reduce((x, y) -> (x * x) + y)
                .subscribe(n -> System.out.println(n + " "));
    }
}
