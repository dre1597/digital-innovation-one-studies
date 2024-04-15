package sample.challenges;

import reactor.core.publisher.Flux;

import java.util.List;

public class Question1 {

  public static void main(String[] args) {

    var q = new Question1();
    var flux = q.inc(List.of(1L, 2L, 3L, 4L, 5L));
    flux.subscribe(
        v -> System.out.println("Value ===> " + v),
        e -> System.err.println("Error ===> " + e),
        () -> System.out.println("Completed")
    );
  }

  /*
  Recebe uma lista de longs, incrementa 1 nos valores e retorna um flux dos resultados
   */
  public Flux<Long> inc(final List<Long> numbers) {
    return Flux.fromIterable(numbers)
        .map(x -> x + 1L);
  }

}
