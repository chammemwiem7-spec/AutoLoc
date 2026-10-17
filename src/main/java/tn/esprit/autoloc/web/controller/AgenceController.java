package tn.esprit.autoloc.web.controller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.service.IAgenceService;
import java.util.List;

@RestController
@RequestMapping("/agences")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AgenceController {

    IAgenceService agenceService;

    @GetMapping
    public List<Agence> getAllAgences() {
        return agenceService.getAllAgences();
    }

    @GetMapping("/{id}")
    public Agence getAgenceById(@PathVariable Long id) {
        return agenceService.getAgenceById(id);
    }

    @PostMapping
    public Agence createAgence(@RequestBody Agence agence) {
        return agenceService.saveAgence(agence);
    }
    @PutMapping("/{id}")
    public Agence updateAgence(@PathVariable Long id, @RequestBody Agence agence) {
        return agenceService.saveAgence(agence);
    }
    @DeleteMapping("/{id}")
    public void deleteAgence(@PathVariable Long id) {
        agenceService.deleteAgence(id);
    }
}