package fms.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name="Nutzer")
public class Nutzer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer nutzerId;

    @Column(name = "Nutzer_name")
    private String name;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "fahrzeug_id")
    private Fahrzeug fahrzeug;


    protected Nutzer(){}

    public Nutzer(Integer nutzerId, String name){
        this.nutzerId = nutzerId;
        this.name = name;
    }

    @Override
    public String toString(){
        return String.format("Nuter-ID: %03d, Name: %s", getNutzerId(), getName());
    }

    public Integer getNutzerId() {
        return nutzerId;
    }

    public void setNutzerId(Integer nutzerId) {
        this.nutzerId = nutzerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Fahrzeug getFahrzeug() {
        return fahrzeug;
    }

    public void setFahrzeug(Fahrzeug fahrzeug) {
        this.fahrzeug = fahrzeug;
        fahrzeug.setNutzerList(this);
    }
}
