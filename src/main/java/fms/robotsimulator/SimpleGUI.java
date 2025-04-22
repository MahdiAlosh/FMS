package fms.robotsimulator;

import javax.swing.*;
import java.awt.*;

public class SimpleGUI extends JFrame {

    public SimpleGUI() {
        setTitle("Simple GUI Example");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Erstellen und Hinzufügen von beweglichen grafischen Objekten zur GUI
        ObjectPanel objectPanel1 = new ObjectPanel(Color.RED);
        ObjectPanel objectPanel2 = new ObjectPanel(Color.BLUE);
        ObjectPanel objectPanel3 = new ObjectPanel(Color.GREEN);

//        add(fahrzeug);
        add(objectPanel1);
        add(objectPanel2);
        add(objectPanel3);

        // Starten der Bewegung der Objekte
//        Thread threadFahrzeug = new Thread(new MovingObject(fahrzeug));
//        threadFahrzeug.start();
        Thread thread1 = new Thread(new MovingObject(objectPanel1));
        thread1.start();
        Thread thread2 = new Thread(new MovingObject(objectPanel2));
        thread2.start();
        Thread thread3 = new Thread(new MovingObject(objectPanel3));
        thread3.start();

        // Layout der GUI aktualisieren
        setLayout(new FlowLayout());

        setVisible(true);
    }

    public static void main(String[] args) {
        // Starten der GUI-Anwendung
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SimpleGUI();
            }
        });
    }
}
