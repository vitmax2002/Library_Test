package com.esempla.library.service.dto;

import java.util.List;

public record AuthorDto(int id,
                        String firstName,
                        String lastName,
                        List<String> books) {
}
