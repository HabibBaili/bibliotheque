package com.example.bibliotheque.controller;

import com.example.bibliotheque.entities.Adherent;
import com.example.bibliotheque.service.AdherentService;
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
    public Adherent createAdherent(@RequestBody Adherent adherent) {
        return adherentService.saveAdherent(adherent);
    }

    @PutMapping("/{id}")
    public Adherent updateAdherent(@PathVariable Long id, @RequestBody Adherent adherent) {
        return adherentService.updateAdherent(id, adherent);
    }

    @DeleteMapping("/{id}")
    public void deleteAdherent(@PathVariable Long id) {
        adherentService.deleteAdherent(id);
    }
}