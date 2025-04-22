package fms.repositories;

import fms.model.Abteilung;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AbteilungRepository extends CrudRepository<Abteilung, Long> {
    Optional<Abteilung> findById(Long id);
    List<Abteilung> findAll();
}
