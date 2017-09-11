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

public class PengumumanCallBack {

    RequestQueue requestQueue;
    String Url = url+"/simeru/json/getpostinfo.php?nim=";

    public PengumumanCallBack(Context context) {

        requestQueue = Volley.newRequestQueue(context);
    }

    public void PengumumanCallBack(String nimmhs, final PngumumanCallback pngumumanCallback){

        JsonObjectRequest obj = new JsonObjectRequest(Url + nimmhs, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    Log.e("data", response.toString());
                    pngumumanCallback.Result(response.toString());

                } catch (Exception e) {

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(obj);

    }

    public interface PngumumanCallback{

        void Result (String result);
    }
}
