package com.example.bibliotheque.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class EmpruntDTO {
    private Long adherentId;
    private LocalDate dateE;
    private LocalDate dateRetourPrevue;
    private List<LigneEmpruntDTO> lignes;
}
