package library.service;

public record BookSaveDto(String isbn ,
                          String name,
                          Integer publisherId,
                          String publishYear,
                          Integer copies,
                          String picture) {
}
