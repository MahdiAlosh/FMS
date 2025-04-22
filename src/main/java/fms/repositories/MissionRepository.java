package fms.repositories;

import fms.model.Fahrzeug;
import fms.model.Mission;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MissionRepository extends CrudRepository<Mission,Integer> {

    Optional<Mission> findByMissionId(Integer missionId);
    List<Mission> findAll();
    @Query("select m from Mission m where m.missionName = ?1 ")
    List<Mission> findByMissionName(String missionName);

    // findByFahrzeug()
    List<Mission> findByFahrzeug(Fahrzeug fahrzeug);
}
