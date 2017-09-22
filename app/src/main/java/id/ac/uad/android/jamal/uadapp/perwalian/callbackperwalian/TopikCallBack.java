package id.ac.uad.android.jamal.uadapp.perwalian.callbackperwalian;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import static id.ac.uad.android.jamal.uadapp.pojo.Url.url;

/**
 * Created by jamal on 22/09/17.
 */

public class TopikCallBack {

    RequestQueue requestQueue;
    String Url = url+"/simeru/perwalian/gettopik.php?iduser=";

    public TopikCallBack(Context context) {

        requestQueue = Volley.newRequestQueue(context);

    }

    public void TopikCallBack(String nim, final Addtopik addtopik){

        StringRequest stringRequest = new StringRequest(Url + nim, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    Log.e("data", response.toString());
                    addtopik.Hasil(response.toString());

                }catch (Exception e){

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(stringRequest);
    }

    public interface Addtopik{

        void Hasil (String hasil);
    }
}
