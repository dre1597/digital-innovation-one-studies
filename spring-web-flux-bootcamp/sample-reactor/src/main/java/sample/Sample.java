package sample;

import reactor.core.publisher.Flux;

import java.util.List;

public class Sample {
  public static void main(String[] args) {
    Flux.fromIterable(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
        .doOnNext(System.out::println)
        .subscribe();

    Flux.fromIterable(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
        .map(x -> x * x)
        .doOnNext(System.out::println)
        .subscribe();

    Flux.fromIterable(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
        .map(x -> x * x)
        .collectList()
        .doOnNext(System.out::println)
        .subscribe();

    Flux.fromIterable(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
        .map(x -> x * 15)
        .filter(x -> x % 2 == 0)
        .collectList()
        .doOnNext(System.out::println)
        .subscribe();
  }
}
