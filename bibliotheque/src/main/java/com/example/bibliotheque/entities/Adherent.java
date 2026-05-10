package com.example.bibliotheque.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Adherent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idA;

    private String nom;
    private String prenom;
    private String adresse;
    private String tel;

    @ManyToOne
    @JoinColumn(name = "bibliotheque_id")
    private Bibliotheque bibliotheque;

    @OneToMany(mappedBy = "adherent")
    @JsonIgnore  // ✅ EMPECHE LA BOUCLE JSON
    private List<Emprunt> emprunts = new ArrayList<>();

    public Adherent() {}

    public Long getIdA() { return idA; }
    public void setIdA(Long idA) { this.idA = idA; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }

    public String getTel() { return tel; }
    public void setTel(String tel) { this.tel = tel; }

    public Bibliotheque getBibliotheque() { return bibliotheque; }
    public void setBibliotheque(Bibliotheque bibliotheque) { this.bibliotheque = bibliotheque; }

    public List<Emprunt> getEmprunts() { return emprunts; }
    public void setEmprunts(List<Emprunt> emprunts) { this.emprunts = emprunts; }
}