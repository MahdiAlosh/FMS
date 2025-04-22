package fms.myRobotsimulator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fms.model.Mission;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;
import java.util.*;

public class MissionGUI extends JFrame {

    public MissionGUI() { // oder mit parameter "fahrzeugId"
        setTitle("Simple GUI Example");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Fahrzeugdaten von der REST-API abrufen und in der GUI anzeigen
        List<ObjectPanel_2> objectPanels = getFahrzeugeVonApi();
        setLayout(null); // Absolute Positionierung verwenden
        for (ObjectPanel_2 panel : objectPanels) {
            add(panel);
        }
        if (!objectPanels.isEmpty()) {
            ObjectPanel_2 firstPanel = objectPanels.get(0);
            firstPanel.setVisible(true);
            Thread thread = new Thread(new MovingObject_2(firstPanel, objectPanels, this));
            thread.start();
        }
//        for (ObjectPanel_2 panel : objectPanels) {
//            add(panel);
//            if(objectPanels.indexOf(panel) == 1){
//                Thread thread = new Thread(new MovingObject_2(panel, objectPanels));
//                thread.start();
//            }
//        }
        setVisible(true);
    }

    private List<ObjectPanel_2> getFahrzeugeVonApi() {
        Scanner eingabe = new Scanner(System.in);
        List<ObjectPanel_2> objectPanels = new ArrayList<>();
        try {
            URL url = new URL("http://127.0.0.1:8080/fahrzeuge"); // Abrufen aller Fahrzeuge
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                System.out.println("Fehler beim Abrufen der Fahrzeugdaten.");
                return objectPanels;
            }
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder content = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            conn.disconnect();
            // Parsen der JSON-Daten mit Jackson
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonArray = objectMapper.readTree(content.toString());
            // Ausgeben des Arrayes auf die Konsole
            System.out.println(jsonArray);
            System.out.println("Fahrzeug-ID einegeben: ");
            int fahrzeugId = eingabe.nextInt(); // Beispiel-Fahrzeug-ID

//            URL url2 = new URL("http://127.0.0.1:8080/missionen"); // Abrufen aller Missionen
//            HttpURLConnection conn2 = (HttpURLConnection) url2.openConnection();
//            conn2.setRequestMethod("GET");
//            int responseCode2 = conn2.getResponseCode();
//            if (responseCode2 != 200) {
//                System.out.println("Fehler beim Abrufen der Missiondaten.");
//                return objectPanels;
//            }
//            BufferedReader in2 = new BufferedReader(new InputStreamReader(conn2.getInputStream()));
//            StringBuilder content2 = new StringBuilder();
//            String inputLine2;
//            while ((inputLine2 = in2.readLine()) != null) {
//                content2.append(inputLine2);
//            }
//            in2.close();
//            conn2.disconnect();
//            // Parsen der JSON-Daten mit Jackson
//            ObjectMapper objectMapper2 = new ObjectMapper();
//            JsonNode jsonArray2 = objectMapper2.readTree(content2.toString());
//            // Ausgeben des Arrayes auf die Konsole
//            System.out.println(jsonArray2);
//            System.out.println("Mission-ID einegeben: ");
//            int missionId = eingabe.nextInt(); // Beispiel-Mission-ID
            boolean found = false;
//            for (JsonNode jsonObject : jsonArray) {
//                JsonNode fahrzeugIdNode = jsonObject.get("fahrzeugId");
//                if (fahrzeugIdNode != null && fahrzeugIdNode.asInt() == fahrzeugId) {
//                    for (JsonNode json : jsonArray2) {
//                        JsonNode missionIdNode = json.get("missionId");
//                        if (missionIdNode != null && missionIdNode.asInt() == missionId) {
//                            found = true;
//                            // Abrufen der Routendaten
//                            URL routeUrl = new URL("http://127.0.0.1:8080/route/mission/" + missionId);
//                            HttpURLConnection routeConn = (HttpURLConnection) routeUrl.openConnection();
//                            routeConn.setRequestMethod("GET");
//                            int routeResponseCode = routeConn.getResponseCode();
//                            if (routeResponseCode != 200) {
//                                System.out.println("Fehler beim Abrufen der Fahrzeugdaten.");
//                            }
//                            BufferedReader routeIn = new BufferedReader(new InputStreamReader(routeConn.getInputStream()));
//                            StringBuilder routeContent = new StringBuilder();
//                            String routeInputLine;
//                            while ((routeInputLine = routeIn.readLine()) != null) {
//                                routeContent.append(routeInputLine);
//                            }
//                            routeIn.close();
//                            routeConn.disconnect();
//                            JsonNode routeArray = objectMapper.readTree(routeContent.toString());
//                            if (!routeArray.isEmpty()) {
//                                JsonNode routeObject = routeArray.get(0); // Nehmen wir die erste Route für das Beispiel
//                                JsonNode pointList = routeObject.get("pointList");
//                                JsonNode startPoint = pointList.get(0);
//                                JsonNode endPoint = pointList.get(pointList.size() - 1);
//                                int startX = startPoint.get("x").asInt();
//                                int startY = startPoint.get("y").asInt();
//                                int endX = endPoint.get("x").asInt();
//                                int endY = endPoint.get("y").asInt();
//
//                                Color color = switch (fahrzeugId) { // Alternative Variante für Switch
//                                    case 1 -> Color.ORANGE;
//                                    case 2 -> Color.GREEN;
//                                    case 3 -> Color.BLUE;
//                                    default -> Color.RED;
//                                };
//                                ObjectPanel_2 panel = new ObjectPanel_2(color, startX, startY, endX, endY);
//                                objectPanels.add(panel);
//                            } else {
//                                System.out.println("Keine Route für Mission gefunden.");
//                            }
//                            break;
//                        }
//                    }
//                }
//            }
            for(JsonNode jsonObject : jsonArray){
                JsonNode fahrzeugIdNode = jsonObject.get("fahrzeugId");
                if(fahrzeugIdNode != null && fahrzeugIdNode.asInt() == fahrzeugId){
                    found = true;
                    // Abrufen der Missionen für das gewählte Fahrzeug
                    JsonNode missionList = jsonObject.get("missionList");
                    if(missionList.isEmpty()){
                        System.out.println("Fahrzeug mit ID: " + fahrzeugId + " hat keine Missionen zu verarbeiten!");
                    }
                    // Missionen in eine Liste einlesen => parsen
                    List<Mission> missions = new ArrayList<>();
                    for(JsonNode mission : missionList){
                        Mission m = new Mission();
                        m.setMissionId(mission.get("missionId").asInt());
                        m.setMissionName(mission.get("missionName").asText());
                        m.setStartM(LocalDate.parse(mission.get("startM").asText()));
                        m.setEndM(LocalDate.parse(mission.get("endM").asText()));
                        missions.add(m);
                    }

                    // Missionen sortieren
                    missions.sort(Comparator.comparingInt(Mission::getMissionId));
                    for(Mission mission : missions){
                        //int missionId = mission.get("missionId").asInt();
                        int missionId = mission.getMissionId();
                        // Abrufen der Routendaten
                        URL routeUrl = new URL("http://127.0.0.1:8080/route/mission/" + missionId);
                        HttpURLConnection routeConn = (HttpURLConnection) routeUrl.openConnection();
                        routeConn.setRequestMethod("GET");
                        int routeResponseCode = routeConn.getResponseCode();
                        if (routeResponseCode != 200) {
                            System.out.println("Fehler beim Abrufen der Fahrzeugdaten.");
                            return objectPanels;
                        }
                        BufferedReader routeIn = new BufferedReader(new InputStreamReader(routeConn.getInputStream()));
                        StringBuilder routeContent = new StringBuilder();
                        String routeInputLine;
                        while ((routeInputLine = routeIn.readLine()) != null) {
                            routeContent.append(routeInputLine);
                        }
                        routeIn.close();
                        routeConn.disconnect();
                        JsonNode routeArray = objectMapper.readTree(routeContent.toString());
                        if (!routeArray.isEmpty()) {
                            JsonNode routeObject = routeArray.get(0); // Nehmen wir die erste Route für das Beispiel
                            JsonNode pointList = routeObject.get("pointList");
                            JsonNode startPoint = pointList.get(0);
                            JsonNode endPoint = pointList.get(pointList.size() - 1);
                            int startX = startPoint.get("x").asInt();
                            int startY = startPoint.get("y").asInt();
                            int endX = endPoint.get("x").asInt();
                            int endY = endPoint.get("y").asInt();

                            Color color = switch (fahrzeugId) { // Alternative Variante für Switch
                                case 1 -> Color.ORANGE;
                                case 2 -> Color.GREEN;
                                case 3 -> Color.BLUE;
                                default -> Color.RED;
                            };
                            ObjectPanel_2 panel = new ObjectPanel_2(color, startX, startY, endX, endY);
                            panel.setMissionId(missionId);
                            panel.setVisible(false); // Verstecke das Panel zunächst
                            objectPanels.add(panel);
                        } else {
                            System.out.println("Keine Route für Mission gefunden.");
                        }
                    }
                }
            }
            if (!found) {
                System.out.println("Fahrzeug mit ID " + fahrzeugId + " nicht gefunden.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objectPanels;
    }

    //***************** Für Post-Request *****************
    public static void sendKoordinateen(int x, int y) {
        try {
            URL url = new URL("http://127.0.0.1:8080/koordinaten"); // URL des neuen API-Endpoints
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setDoOutput(true);

            String jsonInputString = "{\"x\": " + x + ", \"y\": " + y + "}";

            try(OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                System.out.println("Hinzugefügt!");
            } else {
                System.out.println("Fehler beim Senden der Koordinaten: " + responseCode);
            }

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //***************** Für Post-Request *****************

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MissionGUI();
            }
        });
    }
}

class ObjectPanel_2 extends JPanel {
    private Color color;
    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private int missionId;

    public ObjectPanel_2(Color color, int startX, int startY, int endX, int endY) {
        this.color = color;
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        setSize(50, 50);
        setLocation(startX, startY);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    public int getMissionId() {
        return missionId;
    }
    public void setMissionId(int missionId) {
        this.missionId = missionId;
    }
    public int getStartY() {
        return startY;
    }
    public int getStartX() {
        return startX;
    }
        public int getEndX() {
        return endX;
    }
    public int getEndY() {
        return endY;
    }
}

class MovingObject_2 implements Runnable {
    private ObjectPanel_2 objectPanel;
    private List<ObjectPanel_2> objectPanels;
    private MissionGUI gui;

    public MovingObject_2(ObjectPanel_2 objectPanel, List<ObjectPanel_2> objectPanels, MissionGUI gui) {
        this.objectPanel = objectPanel;
        this.objectPanels = objectPanels;
        this.gui = gui;
    }

    @Override
    public void run() {
        int dx = (objectPanel.getEndX() - objectPanel.getLocation().x) > 0 ? 2 : -2; //Geschwindigkeit in X-Richtung
        int dy = (objectPanel.getEndY() - objectPanel.getLocation().y) > 0 ? 2 : -2; //Geschwindigkeit in Y-Richtung

        boolean firstPrint = false;
        boolean secondPrint = false;

        while (true) {
            Point currentPosition = objectPanel.getLocation();

            if(objectPanel.getStartX() == objectPanel.getLocation().x && objectPanel.getStartY() == objectPanel.getLocation().y){
                System.out.println("Startkoordinate: (" + objectPanel.getStartX() + ", " + objectPanel.getStartY() + ")");
                MissionGUI.sendKoordinateen(objectPanel.getStartX(), objectPanel.getStartY());
            }

            int newX = currentPosition.x + dx;
            int newY = currentPosition.y + dy;

            // Gesamtdistanz berechnen
            int totalDistanceX = Math.abs(objectPanel.getEndX() - objectPanel.getLocation().x);
            int totalDistanceY = Math.abs(objectPanel.getEndY() - objectPanel.getLocation().y);

            // Überprüfen, ob das Objekt das Endziel erreicht hat
            if ((dx > 0 && newX >= objectPanel.getEndX()) || (dx < 0 && newX <= objectPanel.getEndX())) {
                //dx > 0: Dieser Teil der Bedingung prüft, ob das Objekt sich nach rechts bewegt
                //dx < 0: ob das Objekt sich nach links bewegt
                //Überprüft, ob die aktuelle X-Koordinate (newX) des Objekts größer oder gleich der End-X-Koordinate ist.
                //Das bedeutet, dass das Objekt sein Ziel in positiver X-Richtung erreicht hat oder überschritten hat.
                newX = objectPanel.getEndX();
            }
            if ((dy > 0 && newY >= objectPanel.getEndY()) || (dy < 0 && newY <= objectPanel.getEndY())) {
                newY = objectPanel.getEndY();
            }
            objectPanel.setLocation(newX, newY);

            // Live Koordinaten ausgeben
            int currentDistanceX = Math.abs(newX - objectPanel.getStartX());
            int currentDistanceY = Math.abs(newY - objectPanel.getStartY());
            double progressX = (double) currentDistanceX / totalDistanceX;
            double progressY = (double) currentDistanceY / totalDistanceY;
            double progress = Math.min(progressX, progressY);

            if (!firstPrint && progress >= 0.3) {
                System.out.println("Erste Zwischenkoordinate: (" + newX + ", " + newY + ")");
                MissionGUI.sendKoordinateen(newX, newY);
                firstPrint = true;
            }

            if (!secondPrint && progress >= 0.7) {
                System.out.println("Zweite Zwischenkoordinate: (" + newX + ", " + newY + ")");
                MissionGUI.sendKoordinateen(newX, newY);
                secondPrint = true;
            }

            // Beenden der Schleife, wenn das Ziel erreicht ist
            if (newX == objectPanel.getEndX() && newY == objectPanel.getEndY()) {
                System.out.println("Endkoordinate: (" + newX + ", " + newY + ")");
                MissionGUI.sendKoordinateen(newX, newY);
                int currentIndex = objectPanels.indexOf(objectPanel);
                if(objectPanels.size() > 1 && currentIndex < objectPanels.size()-1){
                    objectPanel.setVisible(false); // Verstecke das Panel nach der Mission
                    startNextMission();
                }else if(currentIndex == objectPanels.size()-1){
                    objectPanel.setVisible(true);
                }
                break;
            }
            // Kurze Pause, um die Bewegung zu verlangsamen
            try {
                Thread.sleep(10); // Wartezeit in Millisekunden (hier 10 Millisekunden)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private void startNextMission() {
        int currentIndex = objectPanels.indexOf(objectPanel);
        if (currentIndex + 1 < objectPanels.size()) { // d.h. die 2 Mission starten, size = 2. erste Mission wird erst gestartet danach diese Funk.
            ObjectPanel_2 nextPanel = objectPanels.get(currentIndex + 1);// wird aufgerufen => direkt mit der 2ten wietermachen!
            nextPanel.setVisible(true);
            Thread nextThread = new Thread(new MovingObject_2(nextPanel, objectPanels, gui));
            nextThread.start();
        }
    }
}

