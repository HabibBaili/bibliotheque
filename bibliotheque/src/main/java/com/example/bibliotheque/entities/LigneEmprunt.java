package com.example.bibliotheque.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class LigneEmprunt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantite;
    private boolean retourne;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "emprunt_id")
    @JsonIgnore
    private Emprunt emprunt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "livre_id")
    private Livre livre;

    public LigneEmprunt() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getQuantite() { return quantite; }
    public void setQuantite(int quantite) { this.quantite = quantite; }

    public boolean isRetourne() { return retourne; }
    public void setRetourne(boolean retourne) { this.retourne = retourne; }

    public Emprunt getEmprunt() { return emprunt; }
    public void setEmprunt(Emprunt emprunt) { this.emprunt = emprunt; }

    public Livre getLivre() { return livre; }
    public void setLivre(Livre livre) { this.livre = livre; }
}
