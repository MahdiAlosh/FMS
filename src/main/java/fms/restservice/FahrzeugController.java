package fms.restservice;


import fms.model.Fahrzeug;
import fms.repositories.FahrzeugRepository;
import fms.repositories.MissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/fahrzeuge")
public class FahrzeugController {

    @Autowired
    FahrzeugRepository fahrzeugRepository;
    @Autowired
    MissionRepository missionRepository;

//    @GetMapping
//    public List<Fahrzeug> getAllMitarbeiter() {
//        return (List<Fahrzeug>) fahrzeugRepository.findAll();
//    }
    // Read
    @GetMapping
    public Iterable<Fahrzeug> fahrzeuge(){
        return  this.fahrzeugRepository.findAll();
    }

    // Read by id
    @GetMapping("/{id}")
    public Fahrzeug fahrzeugById(@PathVariable Integer id){
        // "findById" hat mit findByFahrzeugId in der Rerpository nichts zu tun
        Optional<Fahrzeug> fahrzeugOptional = this.fahrzeugRepository.findById(id);
        return fahrzeugOptional.orElse(null);
    }

    // Create
    @PostMapping("/fahrzeuge")
    Fahrzeug newFahrzeug(@RequestBody Fahrzeug newFahrzeug){
        return this.fahrzeugRepository.save(newFahrzeug);
    }

    // Delete
    @DeleteMapping("/fahrzeuge/{id}")
    void deleteFahhzeug(@PathVariable Integer id){
        fahrzeugRepository.deleteById(id);
    }
}
