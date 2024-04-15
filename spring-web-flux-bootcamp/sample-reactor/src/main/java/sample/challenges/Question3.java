package sample.challenges;


import reactor.core.publisher.Mono;

public class Question3 {

  public static void main(String[] args) {
    var q = new Question3();
    var user = new User(1L, "admin1", "admin1@email.com", "any_password1", true);
    q.userIsValid(user)
        .subscribe(
            v -> System.out.println("Valid user ===> " + v),
            e -> System.err.println("Error ===> " + e),
            () -> System.out.println("Completed")
        );

    user = new User(1L, "admin1", "admin1@email.com", "invalid", false);
    q.userIsValid(user)
        .subscribe(
            v -> System.out.println("Valid user ===> " + v),
            e -> System.err.println("Error ===> " + e),
            () -> System.out.println("Completed")
        );
  }


  /*
  Verifica se o usuário passado é valido, caso seja retorna void, caso contrário deve disparar uma exception
  (para esse desafio vamos considerar que o usuário é valido quando ele tem uma senha com mais de 8 caractéres)
   */
  public Mono<Void> userIsValid(final User user) {
    return Mono.just(user)
        .filter(u -> u.password().length() > 8)
        .switchIfEmpty(Mono.error(new IllegalArgumentException("Invalid user")))
        .flatMap(u -> Mono.empty());
  }

}
