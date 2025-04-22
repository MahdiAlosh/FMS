package fms.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Abteilung {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @JsonManagedReference
    @OneToMany(mappedBy = "abteilung", fetch = FetchType.LAZY)
    private List<Mitarbeiter> mitarbeiter = new ArrayList<>();

    public Abteilung(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Abteilung() {
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

    public List<Mitarbeiter> getMitarbeiter() {
        return mitarbeiter;
    }

    public void setMitarbeiter(List<Mitarbeiter> mitarbeiter) {
        this.mitarbeiter = mitarbeiter;
    }
}