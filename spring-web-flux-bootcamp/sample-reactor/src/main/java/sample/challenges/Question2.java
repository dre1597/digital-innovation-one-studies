package sample.challenges;

import reactor.core.publisher.Mono;

import java.util.List;

public class Question2 {

  public static void main(String[] args) {
    var q = new Question2();

    var users = List.of(
        new User(1L, "admin1", "admin1@email.com", "any_password1", true),
        new User(2L, "user1", "user1@email.com", "any_password2", false),
        new User(3L, "user2", "user2@email.com", "any_password3", false),
        new User(4L, "user3", "user3@email.com", "any_password4", false),
        new User(5L, "admin2", "admin2@email.com", "any_password5", true)
    );

    Mono<Long> adminCount = q.countAdmins(users);
    adminCount
        .subscribe(
            v -> System.out.println("Admin count ===> " + v),
            e -> System.err.println("Error ===> " + e),
            () -> System.out.println("Completed")
        );
  }

  /*
  Recebe uma lista de usuários e retorna a quantos usuários admin tem na lista
   */
  public Mono<Long> countAdmins(final List<User> users) {
    return Mono.just(users)
        .flatMapIterable(usersList -> usersList)
        .filter(User::isAdmin)
        .count();
  }

}

