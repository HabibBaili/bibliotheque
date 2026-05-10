package com.example.bibliotheque.controller;

import com.example.bibliotheque.entities.Bibliotheque;
import com.example.bibliotheque.service.BibliothequeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bibliotheques")
@CrossOrigin(origins = "http://localhost:3000")
public class BibliothequeController {

    @Autowired
    private BibliothequeService bibliothequeService;

    @GetMapping
    public List<Bibliotheque> getAllBibliotheques() {
        return bibliothequeService.getAllBibliotheques();
    }

    @GetMapping("/{id}")
    public Bibliotheque getBibliothequeById(@PathVariable Long id) {
        return bibliothequeService.getBibliothequeById(id);
    }

    @PostMapping
    public Bibliotheque createBibliotheque(@RequestBody Bibliotheque bibliotheque) {
        return bibliothequeService.saveBibliotheque(bibliotheque);
    }

    @PutMapping("/{id}")
    public Bibliotheque updateBibliotheque(@PathVariable Long id, @RequestBody Bibliotheque bibliotheque) {
        return bibliothequeService.updateBibliotheque(id, bibliotheque);
    }

    @DeleteMapping("/{id}")
    public void deleteBibliotheque(@PathVariable Long id) {
        bibliothequeService.deleteBibliotheque(id);
    }
}