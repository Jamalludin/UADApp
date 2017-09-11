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
 * Created by jamal on 07/09/17.
 */

public class RuangCallBack {

    String JsonURL = url+"/simeru/json/jadwalruang.php?ruang=";
    RequestQueue reqQueue;

    public RuangCallBack(Context context) {

        reqQueue = Volley.newRequestQueue(context);
    }

    public void RuangCallBack (String ruang, final Callback callback){
        JsonObjectRequest arr = new JsonObjectRequest(JsonURL+ruang, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    callback.Result(response.toString());

                } catch (Exception e) {

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        reqQueue.add(arr);

    }

    public interface Callback{
        void Result (String result);
    }
}
