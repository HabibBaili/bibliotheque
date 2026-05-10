package com.example.bibliotheque.repository;

import com.example.bibliotheque.entities.Adherent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdherentRepository extends JpaRepository<Adherent, Long> {
    Optional<Adherent> findByEmail(String email);
    List<Adherent> findByNomContainingIgnoreCaseOrTelContainingOrEmailContainingIgnoreCase(String nom, String tel, String email);
}