package fms.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Mission")
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer missionId;
    @Column(name = "Mission_name")
    private String missionName;
    @Column(name = "Mission_start")
    private LocalDate startM;
    @Column(name = "Mission_end")
    private LocalDate endM;

    // für Fahrzeug
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "fahrzeug_id")
    private Fahrzeug fahrzeug;

    // für Route
    @JsonBackReference
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "route_id", referencedColumnName = "routeId")
    private Route route;

    // ***************************************
    public Mission(){}
    public Mission(Integer missionId, String missionName, LocalDate startM, LocalDate endM){
        this.missionId = missionId;
        this.missionName = missionName;
        this.startM = startM;
        this.endM = endM;
    }

    @Override
    public String toString(){
        return String.format("Mission-ID: %03d, Missionsname: %s, Start: %s, Ende: %s", getMissionId(), getMissionName(), getStartM().toString(), getEndM().toString());
    }

    public Integer getMissionId() {
        return missionId;
    }

    public void setMissionId(Integer missionId) {
        this.missionId = missionId;
    }

    public String getMissionName() {
        return missionName;
    }

    public void setMissionName(String missionName) {
        this.missionName = missionName;
    }

    public LocalDate getStartM() {
        return startM;
    }

    public void setStartM(LocalDate startM) {
        this.startM = startM;
    }

    public LocalDate getEndM() {
        return endM;
    }

    public void setEndM(LocalDate endM) {
        this.endM = endM;
    }

    // Getters & Setters für Mission
    public Fahrzeug getFahrzeug() {
        return fahrzeug;
    }

    public void setFahrzeug(Fahrzeug fahrzeug) {
        this.fahrzeug = fahrzeug;
        fahrzeug.addMission(this);
    }

    // Getters & Setters für Route
    public Route getRoute() {
        return route;
    }
    public void setRoute(Route route) {
        this.route = route;
    }
}


