package library.service;

import java.time.LocalDate;

public record BorrowedBookDto(Integer id ,
                              String book,
                              Integer client,
                              LocalDate borrowDate
                                     )
{

}