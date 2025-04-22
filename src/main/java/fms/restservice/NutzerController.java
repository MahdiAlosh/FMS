package fms.restservice;


import fms.model.Nutzer;
import fms.repositories.NutzerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/nutzern")
public class NutzerController {

    @Autowired
    NutzerRepository nutzerRepository;

    @GetMapping
    public Iterable<Nutzer> getAll(){
        return  this.nutzerRepository.findAll();
    }

    @GetMapping("/{id}")
    public Nutzer findById(@PathVariable Integer id){
        Optional<Nutzer> routeOptional = this.nutzerRepository.findById(id);
        return routeOptional.orElse(null);
    }
}
