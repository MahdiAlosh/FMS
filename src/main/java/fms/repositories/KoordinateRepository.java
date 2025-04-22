package fms.repositories;

import fms.model.Koordinate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface KoordinateRepository extends CrudRepository<Koordinate, Long> {

    List<Koordinate> findAll();
}
