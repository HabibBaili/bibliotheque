package com.example.bibliotheque.service;

import com.example.bibliotheque.dto.EmpruntDTO;
import com.example.bibliotheque.dto.LigneEmpruntDTO;
import com.example.bibliotheque.entities.Adherent;
import com.example.bibliotheque.entities.Emprunt;
import com.example.bibliotheque.entities.LigneEmprunt;
import com.example.bibliotheque.entities.Livre;
import com.example.bibliotheque.repository.AdherentRepository;
import com.example.bibliotheque.repository.EmpruntRepository;
import com.example.bibliotheque.repository.LigneEmpruntRepository;
import com.example.bibliotheque.repository.LivreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpruntService {

    private static final int MAX_BOOKS_PER_LOAN = 10;
    private static final int MAX_QTY_SAME_BOOK = 2;

    @Autowired
    private EmpruntRepository empruntRepository;

    @Autowired
    private LivreRepository livreRepository;

    @Autowired
    private AdherentRepository adherentRepository;

    @Autowired
    private LigneEmpruntRepository ligneEmpruntRepository;

    public List<Emprunt> getAllEmprunts() {
        return empruntRepository.findAll();
    }

    public Emprunt getEmpruntById(Long id) {
        return empruntRepository.findById(id).orElse(null);
    }

    @Transactional
    public Emprunt createEmprunt(EmpruntDTO dto) {
        // Validate adherent
        Adherent adherent = adherentRepository.findById(dto.getAdherentId()).orElse(null);
        if (adherent == null) {
            throw new RuntimeException("Adhérent non trouvé");
        }

        // Validate lignes
        if (dto.getLignes() == null || dto.getLignes().isEmpty()) {
            throw new RuntimeException("L'emprunt doit contenir au moins un livre");
        }

        if (dto.getLignes().size() > MAX_BOOKS_PER_LOAN) {
            throw new RuntimeException("Maximum " + MAX_BOOKS_PER_LOAN + " livres par emprunt");
        }

        // Create the emprunt
        Emprunt emprunt = new Emprunt();
        emprunt.setAdherent(adherent);
        emprunt.setDateE(dto.getDateE());
        emprunt.setDateRetourPrevue(dto.getDateRetourPrevue());
        emprunt.setStatut("EN_COURS");

        // Validate and create each line
        for (LigneEmpruntDTO ligneDTO : dto.getLignes()) {
            // Validate quantity per same book
            if (ligneDTO.getQuantite() > MAX_QTY_SAME_BOOK) {
                throw new RuntimeException("Maximum " + MAX_QTY_SAME_BOOK + " exemplaires du même livre");
            }

            if (ligneDTO.getQuantite() <= 0) {
                throw new RuntimeException("La quantité doit être supérieure à 0");
            }

            Livre livre = livreRepository.findById(ligneDTO.getLivreId()).orElse(null);
            if (livre == null) {
                throw new RuntimeException("Livre non trouvé (ID: " + ligneDTO.getLivreId() + ")");
            }

            if (!livre.verifierDisponibilite(ligneDTO.getQuantite())) {
                throw new RuntimeException("Quantité insuffisante pour \"" + livre.getTitre()
                        + "\" (disponible: " + livre.getQuantiteDisponible()
                        + ", demandé: " + ligneDTO.getQuantite() + ")");
            }

            // Decrement stock
            livre.decrementerStock(ligneDTO.getQuantite());
            livreRepository.save(livre);

            // Create line
            LigneEmprunt ligne = new LigneEmprunt();
            ligne.setLivre(livre);
            ligne.setQuantite(ligneDTO.getQuantite());
            ligne.setRetourne(false);
            emprunt.addLigne(ligne);
        }

        return empruntRepository.save(emprunt);
    }

    @Transactional
    public Emprunt retournerEmprunt(Long id) {
        Emprunt emprunt = empruntRepository.findById(id).orElse(null);
        if (emprunt == null) {
            throw new RuntimeException("Emprunt non trouvé");
        }

        if ("RETOURNE".equals(emprunt.getStatut())) {
            throw new RuntimeException("Cet emprunt a déjà été retourné");
        }

        // Return all non-returned lines
        for (LigneEmprunt ligne : emprunt.getLignes()) {
            if (!ligne.isRetourne()) {
                ligne.setRetourne(true);
                Livre livre = ligne.getLivre();
                livre.incrementerStock(ligne.getQuantite());
                livreRepository.save(livre);
            }
        }

        emprunt.setDateRetourEffective(LocalDate.now());
        emprunt.setStatut("RETOURNE");
        return empruntRepository.save(emprunt);
    }

    @Transactional
    public Emprunt retournerLigne(Long empruntId, Long ligneId) {
        Emprunt emprunt = empruntRepository.findById(empruntId).orElse(null);
        if (emprunt == null) {
            throw new RuntimeException("Emprunt non trouvé");
        }

        LigneEmprunt ligne = emprunt.getLignes().stream()
                .filter(l -> l.getId().equals(ligneId))
                .findFirst()
                .orElse(null);

        if (ligne == null) {
            throw new RuntimeException("Ligne d'emprunt non trouvée");
        }

        if (ligne.isRetourne()) {
            throw new RuntimeException("Ce livre a déjà été retourné");
        }

        // Return this line
        ligne.setRetourne(true);
        Livre livre = ligne.getLivre();
        livre.incrementerStock(ligne.getQuantite());
        livreRepository.save(livre);

        // Update emprunt status
        boolean allReturned = emprunt.getLignes().stream().allMatch(LigneEmprunt::isRetourne);
        if (allReturned) {
            emprunt.setStatut("RETOURNE");
            emprunt.setDateRetourEffective(LocalDate.now());
        } else {
            emprunt.setStatut("RETOURNE_PARTIELLEMENT");
        }

        return empruntRepository.save(emprunt);
    }

    @Transactional
    public void deleteEmprunt(Long id) {
        Emprunt emprunt = empruntRepository.findById(id).orElse(null);
        if (emprunt != null) {
            // Restore stock for non-returned lines
            for (LigneEmprunt ligne : emprunt.getLignes()) {
                if (!ligne.isRetourne()) {
                    Livre livre = ligne.getLivre();
                    livre.incrementerStock(ligne.getQuantite());
                    livreRepository.save(livre);
                }
            }
            empruntRepository.deleteById(id);
        }
    }

    public List<Emprunt> getEmpruntsByAdherent(Long adherentId) {
        return empruntRepository.findByAdherent_IdA(adherentId);
    }

    public List<Emprunt> getEmpruntsEnRetard() {
        return empruntRepository.findAll().stream()
                .filter(Emprunt::isEnRetard)
                .collect(Collectors.toList());
    }

    @Transactional
    public Emprunt updateEmprunt(Long id, Emprunt empruntDetails) {
        Emprunt emprunt = empruntRepository.findById(id).orElse(null);
        if (emprunt != null) {
            emprunt.setDateE(empruntDetails.getDateE());
            emprunt.setDateRetourPrevue(empruntDetails.getDateRetourPrevue());
            return empruntRepository.save(emprunt);
        }
        return null;
    }
}