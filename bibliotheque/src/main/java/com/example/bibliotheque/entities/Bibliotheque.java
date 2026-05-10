package com.example.bibliotheque.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Bibliotheque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idB;

    private String nomB;
    private String adresseB;

    @OneToMany(mappedBy = "bibliotheque")
    @JsonIgnore  // ✅ EMPECHE LA BOUCLE JSON
    private List<Livre> livres = new ArrayList<>();

    @OneToMany(mappedBy = "bibliotheque")
    @JsonIgnore  // ✅ EMPECHE LA BOUCLE JSON
    private List<Adherent> adherents = new ArrayList<>();

    public Bibliotheque() {}

    public Long getIdB() { return idB; }
    public void setIdB(Long idB) { this.idB = idB; }

    public String getNomB() { return nomB; }
    public void setNomB(String nomB) { this.nomB = nomB; }

    public String getAdresseB() { return adresseB; }
    public void setAdresseB(String adresseB) { this.adresseB = adresseB; }

    public List<Livre> getLivres() { return livres; }
    public void setLivres(List<Livre> livres) { this.livres = livres; }

    public List<Adherent> getAdherents() { return adherents; }
    public void setAdherents(List<Adherent> adherents) { this.adherents = adherents; }
}