package com.example.bibliotheque.repository;

import com.example.bibliotheque.entities.Categorie;
import com.example.bibliotheque.entities.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivreRepository extends JpaRepository<Livre, Long> {
    List<Livre> findByCategorie(Categorie categorie);
}