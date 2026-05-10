package com.example.bibliotheque.entities;

public enum Categorie {
    LITTERATURE("Littérature (romans, nouvelles, poésie, théâtre)"),
    RELIGIEUX("Livres religieux (exégèse, hadith, jurisprudence, théologie, comparaison des religions)"),
    PHILOSOPHIE_PSYCHOLOGIE("Philosophie et psychologie (philosophie, développement personnel, psychologie, sociologie)"),
    SCIENCES("Sciences (physique, chimie, biologie, mathématiques)"),
    TECHNOLOGIE_INFORMATIQUE("Technologie et informatique (programmation, intelligence artificielle, réseaux, cybersécurité)"),
    HISTOIRE_GEOGRAPHIE("Histoire et géographie (histoire, civilisations, géographie)"),
    ECONOMIE_GESTION("Économie et gestion (marketing, comptabilité, entrepreneuriat, gestion de projet)"),
    EDUCATIF_ACADEMIQUE("Livres éducatifs et académiques (manuels scolaires, livres universitaires, ouvrages de référence)"),
    ARTS_CULTURE("Arts et culture (dessin, musique, cinéma, design)"),
    ENFANTS("Livres pour enfants (contes, livres éducatifs, coloriage)"),
    BIOGRAPHIES_MEMOIRES("Biographies et mémoires (récits de vie, autobiographies)"),
    LOISIRS_STYLE_VIE("Loisirs et style de vie (cuisine, sport, voyage, jardinage)");

    private final String label;

    Categorie(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
