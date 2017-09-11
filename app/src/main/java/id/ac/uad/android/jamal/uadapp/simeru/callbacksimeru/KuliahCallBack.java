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
 * Created by jamal on 08/09/17.
 */

public class KuliahCallBack {

    RequestQueue reqQueue;
    String Url = url+"/simeru/json/prodi.php?idprodi=";

    public KuliahCallBack(Context context) {

        reqQueue = Volley.newRequestQueue(context);
    }

    public void KuliahCallBack(String prodi, final Callback callback){

        JsonObjectRequest arr = new JsonObjectRequest(Url+prodi, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    Log.e("data", response.toString());
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
