package com.example.bibliotheque.service;

import com.example.bibliotheque.entities.Adherent;
import com.example.bibliotheque.repository.AdherentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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
        if (adherentRepository.findByEmail(adherent.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email déjà utilisé");
        }
        return adherentRepository.save(adherent);
    }

    public Adherent updateAdherent(Long id, Adherent adherentDetails) {
        Adherent adherent = adherentRepository.findById(id).orElse(null);
        if (adherent != null) {
            // Check if email is being changed and if new email is already taken
            Optional<Adherent> existingWithEmail = adherentRepository.findByEmail(adherentDetails.getEmail());
            if (existingWithEmail.isPresent() && !existingWithEmail.get().getIdA().equals(id)) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Email déjà utilisé");
            }

            adherent.setNom(adherentDetails.getNom());
            adherent.setPrenom(adherentDetails.getPrenom());
            adherent.setAdresse(adherentDetails.getAdresse());
            adherent.setTel(adherentDetails.getTel());
            adherent.setEmail(adherentDetails.getEmail());
            adherent.setBibliotheque(adherentDetails.getBibliotheque());
            return adherentRepository.save(adherent);
        }
        return null;
    }

    public List<Adherent> searchAdherents(String query) {
        return adherentRepository.findByNomContainingIgnoreCaseOrTelContainingOrEmailContainingIgnoreCase(query, query, query);
    }

    public void deleteAdherent(Long id) {
        adherentRepository.deleteById(id);
    }
}