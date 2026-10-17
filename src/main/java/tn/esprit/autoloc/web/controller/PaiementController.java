package tn.esprit.autoloc.web.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Paiement;
import tn.esprit.autoloc.service.IPaiementService;

import java.util.List;

@RestController
@RequestMapping("/paiements")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PaiementController {

    IPaiementService paiementService;

    // 1. Lister tous les paiements (GET)
    @GetMapping
    public List<Paiement> getAllPaiements() {
        return paiementService.getAllPaiements(); // Adapte si c'est findAll() dans ton service
    }

    // 2. Consulter un paiement par ID (GET)
    @GetMapping("/{id}")
    public Paiement getPaiementById(@PathVariable Long id) {
        return paiementService.getPaiementById(id); // Adapte si c'est findById(id) dans ton service
    }
}