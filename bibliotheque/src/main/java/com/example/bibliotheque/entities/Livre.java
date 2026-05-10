package com.example.bibliotheque.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Livre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLivre;

    private String titre;
    private String auteur;
    private String isbn;
    private int anneePublication;
    private int quantite;
    private int quantiteDisponible;

    @ManyToOne
    @JoinColumn(name = "bibliotheque_id")
    private Bibliotheque bibliotheque;

    @OneToMany(mappedBy = "livre")
    @JsonIgnore  // ✅ EMPECHE LA BOUCLE JSON
    private List<Emprunt> emprunts = new ArrayList<>();

    public Livre() {}

    public Long getIdLivre() { return idLivre; }
    public void setIdLivre(Long idLivre) { this.idLivre = idLivre; }

    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }

    public String getAuteur() { return auteur; }
    public void setAuteur(String auteur) { this.auteur = auteur; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public int getAnneePublication() { return anneePublication; }
    public void setAnneePublication(int anneePublication) { this.anneePublication = anneePublication; }

    public Bibliotheque getBibliotheque() { return bibliotheque; }
    public void setBibliotheque(Bibliotheque bibliotheque) { this.bibliotheque = bibliotheque; }

    public List<Emprunt> getEmprunts() { return emprunts; }
    public void setEmprunts(List<Emprunt> emprunts) { this.emprunts = emprunts; }

    public int getQuantite() { return quantite; }
    public void setQuantite(int quantite) { this.quantite = quantite; }

    public int getQuantiteDisponible() { return quantiteDisponible; }
    public void setQuantiteDisponible(int quantiteDisponible) { this.quantiteDisponible = quantiteDisponible; }

    public String afficherDetails() {
        return "Livre: " + titre + " par " + auteur + " (ISBN: " + isbn + ") - Stock total: " + quantite + ", Disponible: " + quantiteDisponible;
    }

    public boolean verifierDisponibilite() {
        return this.quantiteDisponible > 0;
    }

    public void decrementerStock() {
        if (this.quantiteDisponible > 0) {
            this.quantiteDisponible--;
        }
    }

    public void incrementerStock() {
        if (this.quantiteDisponible < this.quantite) {
            this.quantiteDisponible++;
        }
    }
}