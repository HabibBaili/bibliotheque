package com.example.bibliotheque.service;

import com.example.bibliotheque.dto.EmpruntDTO;
import com.example.bibliotheque.entities.Adherent;
import com.example.bibliotheque.entities.Emprunt;
import com.example.bibliotheque.entities.Livre;
import com.example.bibliotheque.repository.AdherentRepository;
import com.example.bibliotheque.repository.EmpruntRepository;
import com.example.bibliotheque.repository.LivreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmpruntService {

    @Autowired
    private EmpruntRepository empruntRepository;

    @Autowired
    private LivreRepository livreRepository;

    @Autowired
    private AdherentRepository adherentRepository;

    public List<Emprunt> getAllEmprunts() {
        return empruntRepository.findAll();
    }

    public Emprunt getEmpruntById(Long id) {
        return empruntRepository.findById(id).orElse(null);
    }

    @Transactional
    public Emprunt createEmprunt(EmpruntDTO dto) {
        Livre livre = livreRepository.findById(dto.getLivreId()).orElse(null);
        Adherent adherent = adherentRepository.findById(dto.getAdherentId()).orElse(null);

        if (livre == null || adherent == null) {
            System.out.println("❌ Livre ou Adhérent non trouvé !");
            return null;
        }

        if (!livre.verifierDisponibilite()) {
            throw new RuntimeException("Livre indisponible");
        }

        livre.decrementerStock();
        livreRepository.save(livre);

        Emprunt emprunt = new Emprunt();
        emprunt.setLivre(livre);
        emprunt.setAdherent(adherent);
        emprunt.setDateE(dto.getDateE());
        emprunt.setDateRetourPrevue(dto.getDateRetourPrevue());
        emprunt.setDateRetourEffective(dto.getDateRetourEffective());

        return empruntRepository.save(emprunt);
    }

    @Transactional
    public Emprunt updateEmprunt(Long id, Emprunt empruntDetails) {
        Emprunt emprunt = empruntRepository.findById(id).orElse(null);
        if (emprunt != null) {
            emprunt.setDateE(empruntDetails.getDateE());
            emprunt.setDateRetourPrevue(empruntDetails.getDateRetourPrevue());
            emprunt.setDateRetourEffective(empruntDetails.getDateRetourEffective());
            return empruntRepository.save(emprunt);
        }
        return null;
    }

    @Transactional
    public void deleteEmprunt(Long id) {
        empruntRepository.deleteById(id);
    }

    public List<Emprunt> getEmpruntsByAdherent(Long adherentId) {
        return empruntRepository.findByAdherent_IdA(adherentId);
    }

    @Transactional
    public Emprunt retournerEmprunt(Long id) {
        Emprunt emprunt = empruntRepository.findById(id).orElse(null);
        if (emprunt != null && emprunt.getDateRetourEffective() == null) {
            emprunt.setDateRetourEffective(java.time.LocalDate.now());
            Livre livre = emprunt.getLivre();
            if (livre != null) {
                livre.incrementerStock();
                livreRepository.save(livre);
            }
            return empruntRepository.save(emprunt);
        }
        return null;
    }
}