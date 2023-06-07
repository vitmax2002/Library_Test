package com.esempla.library.service.dto;

import java.util.List;

public record BookSaveDto(String isbn ,
                          String name,
                          Integer publisherId,
                          String publishYear,
                          Integer copies,
                          String picture,
                          List<Integer> authors) {
}
