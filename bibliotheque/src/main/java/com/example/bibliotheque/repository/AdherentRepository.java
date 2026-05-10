package com.example.bibliotheque.repository;

import com.example.bibliotheque.entities.Adherent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdherentRepository extends JpaRepository<Adherent, Long> {
}