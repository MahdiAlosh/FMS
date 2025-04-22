package fms.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.OrderedHashSet;

import java.util.Set;

@Entity
@Table(name="Fahrzeug")
public class Fahrzeug {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer fahrzeugId;

    @Column(name = "Fahrzeug_LadungKap")
    private double ladungKapazitaet; // in Liter

    // für Flotte
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "flotteId")
    private Flotte flotte;

    // für Nutzer
    @JsonManagedReference
    @OneToMany(mappedBy="fahrzeug")
    private Set<Nutzer> nutzerList = new OrderedHashSet<>();
    private Set<Integer> nutzerIDList = new OrderedHashSet<>();


    // Missionen Liste
    @JsonManagedReference
    @OneToMany(mappedBy = "fahrzeug")
    private Set<Mission> missionList = new OrderedHashSet<>();
    private Set<Integer> missionIDList = new OrderedHashSet<>();

    protected Fahrzeug(){}

    public Fahrzeug(Integer fahrzeugId, double ladungKapazitaet){
        this.fahrzeugId = fahrzeugId;
        this.ladungKapazitaet = ladungKapazitaet;
    }

    @Override
    public String toString(){
        return String.format("Fahrzeug-ID: %03d, Kapazität: %.2f ", getFahrzeugId(), getLadungKapazitaet());
    }

    public Integer getFahrzeugId() {
        return fahrzeugId;
    }

    public void setFahrzeugId(Integer fahrzeugId) {
        this.fahrzeugId = fahrzeugId;
    }

    public double getLadungKapazitaet() {
        return ladungKapazitaet;
    }

    public void setLadungKapazitaet(double ladungKapazitaet) {
        this.ladungKapazitaet = ladungKapazitaet;
    }

    public Set<Nutzer> getNutzerList() {
        return nutzerList;
    }

    public void setNutzerList(Nutzer nutzer) {
        if(!nutzerIDList.contains(nutzer.getNutzerId())) {
            nutzerIDList.add(nutzer.getNutzerId());
            nutzerList.add(nutzer);
        }
    }
    // Anzahl der Nutzer zurückgeben
    public int getAnzahlNutzer() {
        return nutzerList.size();
    }

    // Getters & Setters für Flotte
    public Flotte getFlotte() {
        return flotte;
    }

    public void setFlotte(Flotte flotte) {
        this.flotte = flotte;
        flotte.setFahrzeuge(this);
    }

    // Getters & Setters für Mission
    public Set<Mission> getMissionList() {
        return missionList;
    }

    public void addMission(Mission mission) {
        if(!missionIDList.contains(mission.getMissionId())){
            missionIDList.add(mission.getMissionId());
            missionList.add(mission);
        }
    }

    // Anzahl der Mission zurückgeben
    public int getAnzahlMission() {
        return missionList.size();
    }
}
