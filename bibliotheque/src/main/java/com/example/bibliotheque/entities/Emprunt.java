package com.example.bibliotheque.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Emprunt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idE;

    private LocalDate dateE;
    private LocalDate dateRetourPrevue;
    private LocalDate dateRetourEffective;
    private String statut; // EN_COURS, RETOURNE_PARTIELLEMENT, RETOURNE

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "adherent_id")
    private Adherent adherent;

    @OneToMany(mappedBy = "emprunt", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<LigneEmprunt> lignes = new ArrayList<>();

    public Emprunt() {}

    public Long getIdE() { return idE; }
    public void setIdE(Long idE) { this.idE = idE; }

    public LocalDate getDateE() { return dateE; }
    public void setDateE(LocalDate dateE) { this.dateE = dateE; }

    public LocalDate getDateRetourPrevue() { return dateRetourPrevue; }
    public void setDateRetourPrevue(LocalDate dateRetourPrevue) { this.dateRetourPrevue = dateRetourPrevue; }

    public LocalDate getDateRetourEffective() { return dateRetourEffective; }
    public void setDateRetourEffective(LocalDate dateRetourEffective) { this.dateRetourEffective = dateRetourEffective; }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }

    public Adherent getAdherent() { return adherent; }
    public void setAdherent(Adherent adherent) { this.adherent = adherent; }

    public List<LigneEmprunt> getLignes() { return lignes; }
    public void setLignes(List<LigneEmprunt> lignes) { this.lignes = lignes; }

    public void addLigne(LigneEmprunt ligne) {
        lignes.add(ligne);
        ligne.setEmprunt(this);
    }

    public boolean isEnRetard() {
        return dateRetourPrevue != null
                && dateRetourEffective == null
                && LocalDate.now().isAfter(dateRetourPrevue)
                && !"RETOURNE".equals(statut);
    }
}