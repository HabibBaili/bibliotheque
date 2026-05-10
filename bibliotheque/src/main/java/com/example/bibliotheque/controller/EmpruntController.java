package com.example.bibliotheque.controller;

import com.example.bibliotheque.dto.EmpruntDTO;
import com.example.bibliotheque.entities.Emprunt;
import com.example.bibliotheque.service.EmpruntService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emprunts")
@CrossOrigin(origins = "http://localhost:3000")
public class EmpruntController {

    @Autowired
    private EmpruntService empruntService;

    @GetMapping
    public List<Emprunt> getAllEmprunts() {
        return empruntService.getAllEmprunts();
    }

    @GetMapping("/{id}")
    public Emprunt getEmpruntById(@PathVariable Long id) {
        return empruntService.getEmpruntById(id);
    }

    @PostMapping
    public Emprunt createEmprunt(@RequestBody EmpruntDTO empruntDTO) {
        return empruntService.createEmprunt(empruntDTO);
    }

    @PutMapping("/{id}")
    public Emprunt updateEmprunt(@PathVariable Long id, @RequestBody Emprunt emprunt) {
        return empruntService.updateEmprunt(id, emprunt);
    }

    @DeleteMapping("/{id}")
    public void deleteEmprunt(@PathVariable Long id) {
        empruntService.deleteEmprunt(id);
    }

    @GetMapping("/adherent/{adherentId}")
    public List<Emprunt> getEmpruntsByAdherent(@PathVariable Long adherentId) {
        return empruntService.getEmpruntsByAdherent(adherentId);
    }

    @PutMapping("/{id}/retour")
    public Emprunt retournerEmprunt(@PathVariable Long id) {
        return empruntService.retournerEmprunt(id);
    }
}