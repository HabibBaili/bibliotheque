package com.example.bibliotheque.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class EmpruntDTO {
    private Long livreId;
    private Long adherentId;
    private LocalDate dateE;
    private LocalDate dateRetourPrevue;
    private LocalDate dateRetourEffective;
}
