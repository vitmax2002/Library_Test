package library.authentication;

public record RegisterDto(String username,
                          String password,
                          String authority) {
}
