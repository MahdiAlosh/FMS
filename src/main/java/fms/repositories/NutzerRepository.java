package fms.repositories;

import fms.model.Nutzer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NutzerRepository extends CrudRepository<Nutzer, Integer> {

    Nutzer findByNutzerId(Integer nutzerId);
    List<Nutzer> findAll();

    @Query("select n from Nutzer n where n.name = ?1 ")
    List<Nutzer> findByName(String name);
}
