package fms.repositories;

import fms.model.Fahrzeug;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface FahrzeugRepository extends CrudRepository<Fahrzeug, Integer> {

    Optional<Fahrzeug> findByFahrzeugId(Integer fahrzeugId);
    List<Fahrzeug> findAll();
    @Query("select f from Fahrzeug f where f.ladungKapazitaet >= ?1 ")
    List<Fahrzeug> findByLadungKapazitaetGreaterThan(double ladungKapazitaet);

/*    // findByMission()
    List<Fahrzeug> findByMission(Mission mission);*/
}
