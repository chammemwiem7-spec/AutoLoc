package tn.esprit.autoloc.web.controller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.service.IEmployeService;
import java.util.List;

@RestController
@RequestMapping("/api/employes")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeController {

    IEmployeService employeService;

    @GetMapping
    public List<Employe> getAllEmployes() {
        return employeService.getAllEmployes();
    }

    @GetMapping("/{id}")
    public Employe getEmployeById(@PathVariable Long id) {
        return employeService.getEmployeById(id);
    }

    @PostMapping
    public Employe createEmploye(@RequestBody Employe employe) {
        return employeService.saveEmploye(employe);
    }

    @DeleteMapping("/{id}")
    public void deleteEmploye(@PathVariable Long id) {
        employeService.deleteEmploye(id);
    }
}