package fms.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Mitarbeiter {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "abteilung_id")
    private Abteilung abteilung;

    public Mitarbeiter() {
    }

    public Mitarbeiter(Long id, String name, Abteilung abteilung) {
        this.id = id;
        this.name = name;
        this.abteilung = abteilung;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Abteilung getAbteilung() {
        return abteilung;
    }

    public void setAbteilung(Abteilung abteilung) {
        this.abteilung = abteilung;
    }
}