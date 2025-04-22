//package fms.myRobotsimulator;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import javax.swing.*;
//import java.awt.*;
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//
//public class MissionGUI_2 extends JFrame {
//
//    public MissionGUI_2(int fahrzeugId) {
//        setTitle("Simple GUI Example");
//        setSize(900, 600);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        // Fahrzeugdaten von der REST-API abrufen und in der GUI anzeigen
//        List<ObjectPanel_2> objectPanels = getFahrzeugeVonApi(fahrzeugId);
//        setLayout(null); // Absolute Positionierung verwenden
//        for (ObjectPanel_2 panel : objectPanels) {
//            add(panel);
//            Thread thread = new Thread(new MovingObject_2(panel));
//            thread.start();
//        }
//        setVisible(true);
//    }
//
//    private List<ObjectPanel_2> getFahrzeugeVonApi(int fahrzeugId) {
//        List<ObjectPanel_2> objectPanels = new ArrayList<>();
//        try {
//            URL url = new URL("http://127.0.0.1:8080/fahrzeuge"); // Abrufen aller Fahrzeuge
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("GET");
//
//            int responseCode = conn.getResponseCode();
//            if (responseCode != 200) {
//                System.out.println("Fehler beim Abrufen der Fahrzeugdaten.");
//                return objectPanels;
//            }
//            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//            String inputLine;
//            StringBuilder content = new StringBuilder();
//            while ((inputLine = in.readLine()) != null) {
//                content.append(inputLine);
//            }
//            in.close();
//            conn.disconnect();
//            // Parsen der JSON-Daten mit Jackson
//            ObjectMapper objectMapper = new ObjectMapper();
//            JsonNode jsonArray = objectMapper.readTree(content.toString());
//
//            boolean found = false;
//            for (JsonNode jsonObject : jsonArray) {
//                int id = jsonObject.get("fahrzeugId").asInt();
//                if (id == fahrzeugId) {
//                    found = true;
//                    // Beispiel-Koordinaten und zufällige Farbe
//                    int x = 50; // Beispielkoordinate, um die Panels nicht zu überlappen
//                    int y = 50; // Beispielkoordinate
//                    if (fahrzeugId == 1) {
//                        ObjectPanel_2 panel = new ObjectPanel_2(Color.ORANGE, 50, getHeight() / 2 - 50, getWidth() - 100, getHeight() / 2 - 50);
//                        objectPanels.add(panel);
//                    } else if (fahrzeugId == 2) {
//                        ObjectPanel_2 panel = new ObjectPanel_2(Color.GREEN, 50, getHeight() / 2 - 50, getWidth() - 100, getHeight() / 2 - 50);
//                        objectPanels.add(panel);
//                    } else if (fahrzeugId == 3) {
//                        ObjectPanel_2 panel = new ObjectPanel_2(Color.BLUE, 50, getHeight() / 2 - 50, getWidth() - 100, getHeight() / 2 - 50);
//                        objectPanels.add(panel);
//                    } else {
//                        ObjectPanel_2 panel = new ObjectPanel_2(Color.RED, 50, getHeight() / 2 - 50, getWidth() - 100, getHeight() / 2 - 50);
//                        objectPanels.add(panel);
//                    }
//                    break;
//                }
//            }
//            if (!found) {
//                System.out.println("Fahrzeug mit ID " + fahrzeugId + " nicht gefunden.");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return objectPanels;
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                int fahrzeugId = 1; // Beispiel-Fahrzeug-ID
//                new MissionGUI_2(fahrzeugId);
//            }
//        });
//    }
//}
//
//class ObjectPanel_2 extends JPanel {
//    private Color color;
//    private int startX;
//    private int startY;
//    private int endX;
//    private int endY;
//
//    public ObjectPanel_2(Color color, int startX, int startY, int endX, int endY) {
//        this.color = color;
//        this.startX = startX;
//        this.startY = startY;
//        this.endX = endX;
//        this.endY = endY;
//        setSize(50, 50);
//        setLocation(startX, startY);
//    }
//
//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        g.setColor(color);
//        g.fillRect(0, 0, getWidth(), getHeight());
//    }
//
//    public int getEndX() {
//        return endX;
//    }
//
////    public int getEndY() {
////        return endY;
////    }
//}
//
//class MovingObject_2 implements Runnable {
//    private ObjectPanel_2 objectPanel;
//
//    public MovingObject_2(ObjectPanel_2 objectPanel) {
//        this.objectPanel = objectPanel;
//    }
//
//    @Override
//    public void run() {
//        int dx = 2; // Geschwindigkeit in x-Richtung
//        int dy = 0; // Keine Bewegung in y-Richtung, da das Fahrzeug horizontal bewegt wird
//        while (true) {
//            Point currentPosition = objectPanel.getLocation();
//            int newX = currentPosition.x + dx;
//            int newY = currentPosition.y + dy;
//
//            // Überprüfen, ob das Objekt das Endziel erreicht hat
//            if (newX >= objectPanel.getEndX()) {
//                newX = objectPanel.getEndX();
//                // Beenden der Schleife, wenn das Ziel erreicht ist
//                break;
//            }
//
//            objectPanel.setLocation(newX, newY);
//
//            // Kurze Pause, um die Bewegung zu verlangsamen
//            try {
//                Thread.sleep(10); // Wartezeit in Millisekunden (hier 10 Millisekunden)
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
//
////TODO: nach der Bewegung des Fahrzeugs bezüglich der PointListe aus Route
////TODO: whärend der Bewegung des Fahrs. solle bsw. alle 2 Sek. aktuelle Positione(x,y) gepostet bzw. ausgeben lassen!
