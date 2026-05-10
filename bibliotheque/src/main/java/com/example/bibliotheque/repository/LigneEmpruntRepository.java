package com.example.bibliotheque.repository;

import com.example.bibliotheque.entities.LigneEmprunt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LigneEmpruntRepository extends JpaRepository<LigneEmprunt, Long> {

    List<LigneEmprunt> findByEmprunt_IdE(Long empruntId);

    List<LigneEmprunt> findByLivre_IdLivre(Long livreId);
}
