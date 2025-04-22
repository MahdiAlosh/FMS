package fms.restservice;

import fms.model.Koordinate;
import fms.repositories.KoordinateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/koordinaten")
public class KoordinateController {
    @Autowired
    KoordinateRepository koordinateRepository;

    @GetMapping
    public List<Koordinate> getAllAbteilungen() {
        return (List<Koordinate>)koordinateRepository.findAll();
    }

    @PostMapping
    public Koordinate saveKoordinaten(@RequestBody Koordinate koordinate) {
        return this.koordinateRepository.save(koordinate);
    }
//    @PostMapping
//    public ResponseEntity<Koordinate> saveKoordinaten(@RequestBody Koordinate koordinate) {
//        Koordinate savedKoordinate = koordinateRepository.save(koordinate);
//        return ResponseEntity.ok(savedKoordinate);
//    }
}
