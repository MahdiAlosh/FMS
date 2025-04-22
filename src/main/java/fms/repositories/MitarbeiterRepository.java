package fms.repositories;

import fms.model.Mitarbeiter;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MitarbeiterRepository extends CrudRepository<Mitarbeiter, Long> {
    Optional<Mitarbeiter> findById(Long id);
    List<Mitarbeiter> findAll();
}
