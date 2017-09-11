package id.ac.uad.android.jamal.uadapp.perwalian.callbackperwalian;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import id.ac.uad.android.jamal.uadapp.perwalian.TranskipNilai;

import static id.ac.uad.android.jamal.uadapp.pojo.Url.url;

/**
 * Created by jamal on 11/09/17.
 */

public class TranskipCallBack {

    RequestQueue requestQueue;
    String Url = url+"/simeru/transkip.php?nim=";

    public TranskipCallBack(Context context) {

        requestQueue = Volley.newRequestQueue(context);
    }

    public void TranskipCallBack (String nimnya, final TransCallBack transCallBack){

        StringRequest str = new StringRequest(Url + nimnya, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    Log.e("data", response.toString());
                    transCallBack.Result(response.toString());

                } catch (Exception e) {

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(str);
    }

    public interface TransCallBack{

        void Result (String result);
    }
}
