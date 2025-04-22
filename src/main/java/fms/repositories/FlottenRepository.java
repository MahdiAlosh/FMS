package fms.repositories;

import fms.model.Flotte;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface FlottenRepository extends CrudRepository<Flotte, Integer> {

    Flotte findByName(String name);
    Flotte findByFlotteId(Integer flotteId);
    List<Flotte> findAll();
    @Query("select f from Flotte f where f.dienstOrt = ?1 ")
    List<Flotte> findByDienstOrt(String dienstOrt);
}
