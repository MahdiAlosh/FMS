package fms.restservice;

import fms.model.Mitarbeiter;
import fms.repositories.MitarbeiterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mitarbeiter")
public class MitarbeiterController {

    @Autowired
    MitarbeiterRepository mitarbeiterRepository;

    @GetMapping
    public List<Mitarbeiter> getAllMitarbeiter() {
        return (List<Mitarbeiter>) mitarbeiterRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mitarbeiter getMitarbeiterById(@PathVariable Long id) {
        Optional<Mitarbeiter> mitarbeiterOptional = mitarbeiterRepository.findById(id);
        return mitarbeiterOptional.orElse(null);
    }
}

