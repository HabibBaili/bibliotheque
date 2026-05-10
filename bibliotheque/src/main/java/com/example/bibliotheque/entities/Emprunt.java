package com.example.bibliotheque.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Emprunt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idE;

    private LocalDate dateE;
    private LocalDate dateRetourPrevue;
    private LocalDate dateRetourEffective;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "livre_id")
    private Livre livre;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "adherent_id")
    private Adherent adherent;

    public Emprunt() {}

    public Long getIdE() { return idE; }
    public void setIdE(Long idE) { this.idE = idE; }

    public LocalDate getDateE() { return dateE; }
    public void setDateE(LocalDate dateE) { this.dateE = dateE; }

    public LocalDate getDateRetourPrevue() { return dateRetourPrevue; }
    public void setDateRetourPrevue(LocalDate dateRetourPrevue) { this.dateRetourPrevue = dateRetourPrevue; }

    public LocalDate getDateRetourEffective() { return dateRetourEffective; }
    public void setDateRetourEffective(LocalDate dateRetourEffective) { this.dateRetourEffective = dateRetourEffective; }

    public Livre getLivre() { return livre; }
    public void setLivre(Livre livre) { this.livre = livre; }

    public Adherent getAdherent() { return adherent; }
    public void setAdherent(Adherent adherent) { this.adherent = adherent; }
}