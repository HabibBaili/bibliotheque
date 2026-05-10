package com.example.bibliotheque.controller;

import com.example.bibliotheque.entities.Categorie;
import com.example.bibliotheque.entities.Livre;
import com.example.bibliotheque.service.LivreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/livres")
@CrossOrigin(origins = "http://localhost:3000")
public class LivreController {

    @Autowired
    private LivreService livreService;

    @GetMapping
    public List<Livre> getAllLivres(@RequestParam(required = false) Categorie categorie) {
        if (categorie != null) {
            return livreService.getLivresByCategorie(categorie);
        }
        return livreService.getAllLivres();
    }

    @GetMapping("/categories")
    public Categorie[] getCategories() {
        return Categorie.values();
    }

    @GetMapping("/{id}")
    public Livre getLivreById(@PathVariable Long id) {
        return livreService.getLivreById(id);
    }

    @PostMapping
    public Livre createLivre(@RequestBody Livre livre) {
        return livreService.saveLivre(livre);
    }

    @PutMapping("/{id}")
    public Livre updateLivre(@PathVariable Long id, @RequestBody Livre livre) {
        return livreService.updateLivre(id, livre);
    }

    @DeleteMapping("/{id}")
    public void deleteLivre(@PathVariable Long id) {
        livreService.deleteLivre(id);
    }
}