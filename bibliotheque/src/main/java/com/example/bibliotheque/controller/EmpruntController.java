package com.example.bibliotheque.controller;

import com.example.bibliotheque.dto.EmpruntDTO;
import com.example.bibliotheque.entities.Emprunt;
import com.example.bibliotheque.service.EmpruntService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public ResponseEntity<?> createEmprunt(@RequestBody EmpruntDTO empruntDTO) {
        try {
            Emprunt emprunt = empruntService.createEmprunt(empruntDTO);
            return ResponseEntity.ok(emprunt);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
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
    public ResponseEntity<?> retournerEmprunt(@PathVariable Long id) {
        try {
            Emprunt emprunt = empruntService.retournerEmprunt(id);
            return ResponseEntity.ok(emprunt);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    @PutMapping("/{id}/retour/ligne/{ligneId}")
    public ResponseEntity<?> retournerLigne(@PathVariable Long id, @PathVariable Long ligneId) {
        try {
            Emprunt emprunt = empruntService.retournerLigne(id, ligneId);
            return ResponseEntity.ok(emprunt);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    @GetMapping("/en-retard")
    public List<Emprunt> getEmpruntsEnRetard() {
        return empruntService.getEmpruntsEnRetard();
    }
}