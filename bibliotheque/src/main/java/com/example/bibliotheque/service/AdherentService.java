package com.example.bibliotheque.service;

import com.example.bibliotheque.entities.Adherent;
import com.example.bibliotheque.repository.AdherentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdherentService {

    @Autowired
    private AdherentRepository adherentRepository;

    public List<Adherent> getAllAdherents() {
        return adherentRepository.findAll();
    }

    public Adherent getAdherentById(Long id) {
        return adherentRepository.findById(id).orElse(null);
    }

    public Adherent saveAdherent(Adherent adherent) {
        return adherentRepository.save(adherent);
    }

    public Adherent updateAdherent(Long id, Adherent adherentDetails) {
        Adherent adherent = adherentRepository.findById(id).orElse(null);
        if (adherent != null) {
            adherent.setNom(adherentDetails.getNom());
            adherent.setPrenom(adherentDetails.getPrenom());
            adherent.setAdresse(adherentDetails.getAdresse());
            adherent.setTel(adherentDetails.getTel());
            adherent.setBibliotheque(adherentDetails.getBibliotheque());
            return adherentRepository.save(adherent);
        }
        return null;
    }

    public void deleteAdherent(Long id) {
        adherentRepository.deleteById(id);
    }
}