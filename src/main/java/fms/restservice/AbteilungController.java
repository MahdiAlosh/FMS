package fms.restservice;

import fms.model.Abteilung;
import fms.repositories.AbteilungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/abteilungen")
public class AbteilungController {

    @Autowired
    AbteilungRepository abteilungRepository;

    @GetMapping
    public List<Abteilung> getAllAbteilungen() {
        return (List<Abteilung>)abteilungRepository.findAll();
    }

    @GetMapping("/{id}")
    public Abteilung getAbteilungById(@PathVariable Long id) {
        Optional<Abteilung> abteilungOptional = abteilungRepository.findById(id);
        return abteilungOptional.orElse(null);
    }

}
