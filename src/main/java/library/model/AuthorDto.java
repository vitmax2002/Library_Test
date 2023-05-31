package library.model;

import java.util.List;

public record AuthorDto(int id,
                        String firstName,
                        String lastName,
                        List<String> books) {
}
