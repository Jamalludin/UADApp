package id.ac.uad.android.jamal.uadapp.json;

import android.content.SharedPreferences;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import id.ac.uad.android.jamal.uadapp.pojo.Url;

/**
 * Created by jamal on 31/10/16.
 */

public class JsonReq {

    private String nim,password;
    private SharedPreferences preferences;

    public JsonReq() {
    }

    public JsonReq(String nim, String password) {
        this.nim = nim;
        this.password = password;
    }

    public String removeToken(String token){

        try {

            String url = Url.url+"/simeru/keluar.php";
            URL obj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.setRequestMethod("POST");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("token="+token);
            conn.setDoOutput(true);
            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            out.writeBytes(stringBuilder.toString());
            out.flush();
            out.close();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null){
                sb.append(line);
            }

            bufferedReader.close();
            return sb.toString();

        }catch (Exception e){

            return e.getMessage();

        }
    }

    public String login(String nim, String password, String token){
        String url = Url.url+"/simeru/json/mhs_login.php";

        try {
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setConnectTimeout(4000);
            connection.setReadTimeout(4000);
            connection.setRequestMethod("POST");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("nim="+nim);
            stringBuilder.append("&password="+password);
            stringBuilder.append("&token="+token);
            connection.setDoOutput(true);
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            out.writeBytes(stringBuilder.toString());
            out.flush();
            out.close();

            BufferedReader buf = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuffer stringBuffer = new StringBuffer();
            while ((line = buf.readLine()) !=null){
                stringBuffer.append(line);
            }
            buf.close();
            return stringBuffer.toString();

        } catch (Exception e){

            return e.getMessage();

        }
    }
}
