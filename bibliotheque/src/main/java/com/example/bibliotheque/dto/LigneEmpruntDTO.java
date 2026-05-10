package com.example.bibliotheque.dto;

import lombok.Data;

@Data
public class LigneEmpruntDTO {
    private Long livreId;
    private int quantite;
}
