package com.example.bibliotheque.controller;

import com.example.bibliotheque.entities.Adherent;
import com.example.bibliotheque.service.AdherentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/adherents")
@CrossOrigin(origins = "http://localhost:3000")
public class AdherentController {

    @Autowired
    private AdherentService adherentService;

    @GetMapping
    public List<Adherent> getAllAdherents() {
        return adherentService.getAllAdherents();
    }

    @GetMapping("/{id}")
    public Adherent getAdherentById(@PathVariable Long id) {
        return adherentService.getAdherentById(id);
    }

    @PostMapping
    public Adherent createAdherent(@Valid @RequestBody Adherent adherent) {
        return adherentService.saveAdherent(adherent);
    }

    @PutMapping("/{id}")
    public Adherent updateAdherent(@PathVariable Long id, @Valid @RequestBody Adherent adherent) {
        return adherentService.updateAdherent(id, adherent);
    }

    @GetMapping("/search")
    public List<Adherent> searchAdherents(@RequestParam String query) {
        return adherentService.searchAdherents(query);
    }

    @DeleteMapping("/{id}")
    public void deleteAdherent(@PathVariable Long id) {
        adherentService.deleteAdherent(id);
    }
}