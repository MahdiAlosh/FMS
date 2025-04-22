package fms.repositories;

import fms.model.Mission;
import fms.model.Route;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RouteRepository extends CrudRepository<Route,Integer> {

    Optional<Route> findByRouteId(Integer routeId);
    List<Route> findAll();
    @Query("select r from Route r where r.startRoute = ?1 ")
    List<Route> findByStartRoute(String startRoute);

    // findByMission()
    List<Route> findByMission(Mission mission);
}
