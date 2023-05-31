package library.service;

import java.time.LocalDate;

public record UserDto(Integer id ,
                      String username,
                      String password,

                      String authority
) {
}
