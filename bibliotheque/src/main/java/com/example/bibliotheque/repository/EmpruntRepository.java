package com.example.bibliotheque.repository;

import com.example.bibliotheque.entities.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {

    List<Emprunt> findByAdherent_IdA(Long adherentId);
}