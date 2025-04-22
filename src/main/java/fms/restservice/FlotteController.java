package fms.restservice;


import fms.model.Flotte;
import fms.repositories.FlottenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/flotte")
public class FlotteController {

    @Autowired
    FlottenRepository flottenRepository;

//    @GetMapping
//    public List<Flotte> getAll(){
//        return (List<Flotte>) flottenRepository.findAll();
//    }
    @GetMapping
    public Iterable<Flotte> getAll() {
        return this.flottenRepository.findAll();
    }

    @GetMapping("/{id}")
    public Flotte findById(@PathVariable Integer id){
        Optional<Flotte> flotteOptional = this.flottenRepository.findById(id);
        return flotteOptional.orElse(null);
    }
}
