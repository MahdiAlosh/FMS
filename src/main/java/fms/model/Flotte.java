package fms.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.OrderedHashSet;

import java.util.Set;

@Entity
@Table(name="Flotte")
public class Flotte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer flotteId;
    @Column(name = "Flotte_name")
    private String name;
    @Column(name = "Flotte_dienstOrt")
    private String dienstOrt;

    //@OneToMany(mappedBy="flotte",fetch = FetchType.EAGER)
    @JsonManagedReference
    @OneToMany(mappedBy="flotte")
    private Set<Fahrzeug> fahrzeugeList=new OrderedHashSet<>();
    private Set<Integer> fahrzeugeIDList=new OrderedHashSet<>();


   //*************************
    protected Flotte(){}

    // Anzahl der Fahrzeuge automatisch ermittelt
    public Flotte(Integer flotteId,String name, String dienstOrt){
        this.flotteId = flotteId;
        this.name = name;
        this.dienstOrt=dienstOrt;
    }

    // Anzahl der Fahrzeuge in einer Flotte
    public int getAnzahlFahrzeuge() {
        return fahrzeugeList.size();
    }

    @Override
    public String toString(){
        return String.format("Flotten-ID: %03d, Name der Flotte: %s, Dienstsort: %s", getFlotteId(),getName(),getDienstOrt());
    }

    public void setFahrzeuge(Fahrzeug fahrzeuge) {
        if(!fahrzeugeIDList.contains(fahrzeuge.getFahrzeugId())){
            fahrzeugeIDList.add(fahrzeuge.getFahrzeugId());
            fahrzeugeList.add(fahrzeuge);
        }
    }

    public Set<Fahrzeug> getFahrzeugeList() {
        return fahrzeugeList;
    }
//    public Set<Fahrzeug> getFahrzeuge() {
//        return fahrzeugeList;
//    }

    public Integer getFlotteId() {
        return flotteId;
    }

    public void setFlotteId(Integer flotteId) {
        this.flotteId = flotteId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDienstOrt() {
        return dienstOrt;
    }

    public void setDienstOrt(String dienstOrt) {
        this.dienstOrt = dienstOrt;
    }
}
