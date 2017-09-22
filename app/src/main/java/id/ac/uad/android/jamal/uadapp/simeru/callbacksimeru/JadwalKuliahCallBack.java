package id.ac.uad.android.jamal.uadapp.simeru.callbacksimeru;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import static id.ac.uad.android.jamal.uadapp.pojo.Url.url;

/**
 * Created by jamal on 23/09/17.
 */

public class JadwalKuliahCallBack {

    RequestQueue requestQueue;
    String conn = url+"/simeru/json/prodi.php?idprodi=";

    public JadwalKuliahCallBack (Context context){

        requestQueue = Volley.newRequestQueue(context);
    }

    public void JadwalKuliahCallBack(String prodi, final Kuliah kuliah){

        JsonObjectRequest arr = new JsonObjectRequest(conn + prodi, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    kuliah.Result(response.toString());

                }catch (Exception e){

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(arr);

    }

    public interface Kuliah{

        void Result (String result);
    }
}
