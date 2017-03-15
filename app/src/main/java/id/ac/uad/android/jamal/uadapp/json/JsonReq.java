package id.ac.uad.android.jamal.uadapp.json;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by jamal on 31/10/16.
 */

public class JsonReq {
    public String login(String nim, String password){
        String url = "http://perwalian.esy.es/api/login.php?nim="+nim+"&password="+password;

        try {
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            BufferedReader buf = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuffer hasil = new StringBuffer();
            while ((line = buf.readLine()) != null) {
                hasil.append(line);
            }
            buf.close();
            return hasil.toString();
        } catch (Exception e){

            return e.getMessage();

        }
    }
}
