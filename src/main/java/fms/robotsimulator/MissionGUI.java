package fms.robotsimulator;

import fms.model.Mission;

import java.util.Set;

public class MissionGUI extends MovingObject{
    private Mission mission;


    public MissionGUI(ObjectPanel objectPanel, Mission mission) {
        super(objectPanel);
        this.mission = mission;
    }

    @Override
    public void run() {

        int dx = 2; // Geschwindigkeit in x-Richtung
        int dy = 1; // Geschwindigkeit in y-Richtung

        while(true){
            // Aktuelle Position des Objekts aktualisieren
            Set<Mission> missions = mission.getFahrzeug().getMissionList();
            for (Mission m : missions) {

            }
//            int newX = currentPosition.x + dx;
//            int newY = currentPosition.y + dy;

        }

//        super.run();
    }
}
