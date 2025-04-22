package fms.restservice;


import fms.model.Mission;
import fms.model.Route;
import fms.repositories.MissionRepository;
import fms.repositories.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/route")
public class RouteController {

    @Autowired
    RouteRepository routeRepository;
    @Autowired
    MissionRepository missionRepository;

    @GetMapping
    public Iterable<Route> getAllRoute(){
        return this.routeRepository.findAll();
    }

    @GetMapping("/{id}")
    Route routeById(@PathVariable Integer id){
        Optional<Route> routeOptional = this.routeRepository.findByRouteId(id);
        return routeOptional.orElse(null);
    }

    // /route/{id}
    @GetMapping("/mission/{id}")
    public List<Route> routeByMission(@PathVariable Integer id){
        Optional<Mission> missionOptional = this.missionRepository.findById(id);
        if(missionOptional.isPresent()){
            Mission mission = missionOptional.get();
            return this.routeRepository.findByMission(mission);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Route nicht gefunden");
        }
    }
}
