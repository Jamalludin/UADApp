package id.ac.uad.android.jamal.uadapp.perwalian;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import id.ac.uad.android.jamal.uadapp.R;
import id.ac.uad.android.jamal.uadapp.login.Session;
import id.ac.uad.android.jamal.uadapp.pojo.Url;

import static id.ac.uad.android.jamal.uadapp.pojo.Url.url;

public class TambahTopikPerwalian extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_topik_perwalian);

        getSupportActionBar().setTitle("Tambah Topik Bimbingan");

        ((Button)findViewById(R.id.tambahtopik)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String info = ((EditText)findViewById(R.id.isitopik)).getText().toString();

                if (info.equals("")){
                    Toast.makeText(TambahTopikPerwalian.this, "Mohon Isi Topik", Toast.LENGTH_SHORT).show();
                }else {

                    postInfo(new Session(getApplicationContext()).getNim(),info);
                }
            }
        });
    }

    public void postInfo(final String iduser, final String info){

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String post = url+"/simeru/perwalian/posttopik.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, post, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(TambahTopikPerwalian.this, "Topik Telah Terkirim", Toast.LENGTH_SHORT).show();
                onBackPressed();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> data = new HashMap<>();
                data.put("iduser", iduser);
                data.put("info", info);
                return data;
            }
        };

        requestQueue.add(stringRequest);

    }
}
