package id.ac.uad.android.jamal.uadapp.perwalian.callbackperwalian;

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
 * Created by jamal on 22/09/17.
 */

public class BeritaCallBack {

    RequestQueue requestQueue;
    String koneksi = url+"/simeru/json/dindingperwalian.php?niy=";

    public BeritaCallBack(Context context) {

        requestQueue = Volley.newRequestQueue(context);
    }

    public void BeritaCallBack(String niydsn, final Beritadsn beritadsn){

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(koneksi+niydsn, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    Log.e("data", response.toString());
                    beritadsn.Result(response.toString());

                }catch (Exception e){

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(jsonObjectRequest);

    }

    public interface Beritadsn{

        void Result (String result);
    }
}
