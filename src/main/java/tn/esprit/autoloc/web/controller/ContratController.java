package tn.esprit.autoloc.web.controller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.service.IContratService;
import java.util.List;

@RestController
@RequestMapping("/api/contrats")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ContratController {

    IContratService contratService;

    @GetMapping
    public List<Contrat> getAllContrats() {
        return contratService.getAllContrats();
    }

    @GetMapping("/{id}")
    public Contrat getContratById(@PathVariable Long id) {
        return contratService.getContratById(id);
    }

    @PostMapping
    public Contrat createContrat(@RequestBody Contrat contrat) {
        return contratService.saveContrat(contrat);
    }

    @DeleteMapping("/{id}")
    public void deleteContrat(@PathVariable Long id) {
        contratService.deleteContrat(id);
    }
}