package com.esempla.library.service.dto;

import java.time.LocalDate;

public record BorrowedBookDto(Integer id ,
                              String book,
                              Integer client,
                              LocalDate borrowDate
                                     )
{

}