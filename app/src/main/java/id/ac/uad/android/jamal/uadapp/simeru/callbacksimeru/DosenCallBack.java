package id.ac.uad.android.jamal.uadapp.simeru.callbacksimeru;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import static id.ac.uad.android.jamal.uadapp.pojo.Url.url;

/**
 * Created by jamal on 10/09/17.
 */

public class DosenCallBack {

    RequestQueue requestQueue;
    String Url = url+"/simeru/json/jadwaldosen.php?niy=";

    public DosenCallBack(Context context) {

        requestQueue = Volley.newRequestQueue(context);
    }

    public void DosenCallBack(String prodi, final DsnCallback dosenCallBack){

        JsonObjectRequest jobj = new JsonObjectRequest(Url + prodi, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    Log.e("data" , response.toString());
                    dosenCallBack.Result(response.toString());

                } catch (Exception e) {

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(jobj);
    }

    public interface DsnCallback{
        void Result (String result);
    }
}
