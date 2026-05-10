package com.example.bibliotheque.service;

import com.example.bibliotheque.entities.Categorie;
import com.example.bibliotheque.entities.Livre;
import com.example.bibliotheque.repository.LivreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivreService {

    @Autowired
    private LivreRepository livreRepository;

    public List<Livre> getAllLivres() {
        return livreRepository.findAll();
    }

    public List<Livre> getLivresByCategorie(Categorie categorie) {
        return livreRepository.findByCategorie(categorie);
    }

    public Livre getLivreById(Long id) {
        return livreRepository.findById(id).orElse(null);
    }

    public Livre saveLivre(Livre livre) {
        livre.setQuantiteDisponible(livre.getQuantite());
        return livreRepository.save(livre);
    }

    public Livre updateLivre(Long id, Livre livreDetails) {
        Livre livre = livreRepository.findById(id).orElse(null);
        if (livre != null) {
            livre.setTitre(livreDetails.getTitre());
            livre.setAuteur(livreDetails.getAuteur());
            livre.setIsbn(livreDetails.getIsbn());
            livre.setAnneePublication(livreDetails.getAnneePublication());
            
            int diff = livreDetails.getQuantite() - livre.getQuantite();
            livre.setQuantite(livreDetails.getQuantite());
            livre.setQuantiteDisponible(livre.getQuantiteDisponible() + diff);

            livre.setBibliotheque(livreDetails.getBibliotheque());
            livre.setCategorie(livreDetails.getCategorie());
            return livreRepository.save(livre);
        }
        return null;
    }

    public void deleteLivre(Long id) {
        livreRepository.deleteById(id);
    }
}