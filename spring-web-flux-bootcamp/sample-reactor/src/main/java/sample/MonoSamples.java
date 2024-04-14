package sample;

import reactor.core.publisher.Mono;

import java.util.Optional;

public class MonoSamples {
  public static void main(String[] args) {
    Mono.just(1)
        .doOnSuccess(System.out::println)
        .subscribe();

    Mono.justOrEmpty(Optional.empty())
        .doOnSuccess(System.out::println)
        .subscribe();

    Mono.justOrEmpty(Optional.empty())
        .defaultIfEmpty(1)
        .doOnSuccess(System.out::println)
        .subscribe();

    Mono.justOrEmpty(Optional.empty())
        .switchIfEmpty(Mono.defer(() -> Mono.error(new Exception())))
        .doOnSuccess(System.out::println)
        .subscribe();

    Mono.justOrEmpty(2)
        .switchIfEmpty(Mono.defer(() -> Mono.error(new Exception())))
        .doOnSuccess(System.out::println)
        .subscribe();

    Mono.justOrEmpty(2)
        .map(x -> x * x)
        .doOnSuccess(System.out::println)
        .subscribe();

    Mono.justOrEmpty(2)
        .map(String::valueOf)
        .doOnSuccess(n -> System.out.println(n.getClass()))
        .subscribe();

    Mono.justOrEmpty(2)
        .filter(a -> a % 2 == 0)
        .doOnSuccess(System.out::println)
        .subscribe();

    Mono.justOrEmpty(3)
        .filter(a -> a % 2 == 0)
        .doOnSuccess(System.out::println)
        .subscribe();
  }
}
