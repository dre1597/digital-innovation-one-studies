package sample.challenges;

public record User(
    Long id,
    String name,
    String email,
    String password,
    Boolean isAdmin
) {
}
