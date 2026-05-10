package com.example.bibliotheque.service;

import com.example.bibliotheque.entities.Bibliotheque;
import com.example.bibliotheque.repository.BibliothequeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BibliothequeService {

    @Autowired
    private BibliothequeRepository bibliothequeRepository;

    public List<Bibliotheque> getAllBibliotheques() {
        return bibliothequeRepository.findAll();
    }

    public Bibliotheque getBibliothequeById(Long id) {
        return bibliothequeRepository.findById(id).orElse(null);
    }

    public Bibliotheque saveBibliotheque(Bibliotheque bibliotheque) {
        return bibliothequeRepository.save(bibliotheque);
    }

    public Bibliotheque updateBibliotheque(Long id, Bibliotheque bibliothequeDetails) {
        Bibliotheque bibliotheque = bibliothequeRepository.findById(id).orElse(null);
        if (bibliotheque != null) {
            bibliotheque.setNomB(bibliothequeDetails.getNomB());
            bibliotheque.setAdresseB(bibliothequeDetails.getAdresseB());
            return bibliothequeRepository.save(bibliotheque);
        }
        return null;
    }

    public void deleteBibliotheque(Long id) {
        bibliothequeRepository.deleteById(id);
    }
}