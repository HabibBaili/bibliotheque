package com.example.bibliotheque.repository;

import com.example.bibliotheque.entities.Bibliotheque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BibliothequeRepository extends JpaRepository<Bibliotheque, Long> {
}