package fms.restservice;


import fms.model.Fahrzeug;
import fms.model.Mission;
import fms.repositories.FahrzeugRepository;
import fms.repositories.MissionRepository;
import fms.repositories.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/missionen")
public class MissionController {

    @Autowired
    MissionRepository missionRepository;

    @Autowired
    FahrzeugRepository fahrzeugRepository;
    @Autowired
    RouteRepository routeRepository;

    // zum Testen
    @GetMapping("/hi")
    public String firstApp() {
        return "Hello in Missionen";
    }

    // Read
    @GetMapping
    public Iterable<Mission> missionen() {
        return this.missionRepository.findAll();
    }
    // Read by id
    @GetMapping("/{id}")
    Mission missionById(@PathVariable Integer id) {
        Optional<Mission> missionOptional = this.missionRepository.findById(id);
        return missionOptional.orElse(null);
    }
    // /fahrzeug/{id}
    @GetMapping("/fahrzeug/{id}")
    public List<Mission> missionByFahrzeug(@PathVariable Integer id){
        Optional<Fahrzeug> fahrzeugOptional = this.fahrzeugRepository.findById(id);
        if(fahrzeugOptional.isPresent()) {
            Fahrzeug fahrzeug = fahrzeugOptional.get();
            return this.missionRepository.findByFahrzeug(fahrzeug);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Mission nicht gefunden");
        }
    }

    // Create
    @PostMapping("/missionen")
    Mission newMission(@RequestBody Mission newMission) {
        return this.missionRepository.save(newMission);
    }

    // Delete
    @DeleteMapping("/missionen/{id}")
    void deleteMission(@PathVariable Integer id) {
        missionRepository.deleteById(id);
    }

}
