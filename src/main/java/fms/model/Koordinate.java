package fms.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Koordinaten")
public class Koordinate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int x;
    private int y;

    // Das Problem tritt auf, weil Hibernate eine Standardkonstruktor für die Entitätsklasse Koordinate benötigt,
    // um Objekte korrekt instanziieren zu können.
    public Koordinate(){}
    // Standardkonstruktor hinzugefügt: Der Standardkonstruktor public Koordinate() {} wird von Hibernate verwendet,
    // um Instanzen der Entität zu erstellen.

    // Konstruktor
    public Koordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Getter und Setter
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Koordinate{" + "x=" + x + ", y=" + y + '}';
    }
}

