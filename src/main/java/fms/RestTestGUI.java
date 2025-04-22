package fms;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RestTestGUI {

    public static void main(String[] args){
        getFahrzeugeVonApi();
    }

    private static void getFahrzeugeVonApi() {
//        List<ObjectPanel> objectPanels = new ArrayList<>();
        try {
            URL url = new URL("http://127.0.0.1:8080/fahrzeuge/4"); // Beispiel-URL
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
