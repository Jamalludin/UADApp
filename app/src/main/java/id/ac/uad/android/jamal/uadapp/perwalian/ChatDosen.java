package id.ac.uad.android.jamal.uadapp.perwalian;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import id.ac.uad.android.jamal.uadapp.R;
import id.ac.uad.android.jamal.uadapp.login.Session;
import id.ac.uad.android.jamal.uadapp.pojo.ComentPojo;

import static id.ac.uad.android.jamal.uadapp.pojo.Url.url;

public class ChatDosen extends AppCompatActivity {

    private TextView topik;
    private ListView balasan;
    private String topiknya, idtopiknya;
    private EditText isiPesan;
    public static boolean isCommentActive = false;
    public static String idCommentActive = null;
    private List<ComentPojo> comentPojos = new ArrayList<>();
    private ComentAdapter comentAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_dosen);

        getSupportActionBar().setTitle("Dosen Pembimbing Akademik");
        getSupportActionBar().setSubtitle(new Session(this).getDosenwali());
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_person_black_24dp);

        topiknya = getIntent().getStringExtra("topik");
        idtopiknya = getIntent().getStringExtra("idtopik");

        idCommentActive = idtopiknya;

        topik = (TextView)findViewById(R.id.topik);
        topik.setText(topiknya);

        balasan = (ListView)findViewById(R.id.isichat);

        isiPesan = (EditText)findViewById(R.id.inputmsg);

        getComent(idtopiknya);

        FloatingActionButton kirim = (FloatingActionButton)findViewById(R.id.fab);
        kirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String balasannya = isiPesan.getText().toString();

                isiPesan.setText("");

                if (balasannya.equals("")){
                    Snackbar.make(v, "Replace with your messages", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }else {
                    ComentPojo comentPojo = new ComentPojo();
                    comentPojo.coment = balasannya;
                    comentPojo.id_topik = "";
                    comentPojo.id_user = new Session(getApplicationContext()).getNim();
                    comentPojo.idComent = "";
                    comentPojo.role = "";
                    String username = new Session(getApplicationContext()).getNama();
                    comentPojo.username = username;
                    comentPojos.add(comentPojo);
                    comentAdapter.notifyDataSetChanged();
                    balasan.smoothScrollToPosition(comentPojos.size() - 1);
                    kirimPesan(balasannya, idtopiknya);
                }
            }
        });

        isCommentActive = true;
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, new IntentFilter("Comment"));
    }

    public void kirimPesan(final String pesan, final String idtopik){

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String post = url+"/simeru/perwalian/postComment.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, post, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> data = new HashMap<>();

                String nimmhs = new Session(getApplicationContext()).getNim();
                String nama = new Session(getApplicationContext()).getNama();

                data.put("comment", pesan);
                data.put("id_topik", idtopik);
                data.put("role", "mahasiswa");
                data.put("id_user",nimmhs);
                data.put("username", nama);

                return data;
            }
        };

        requestQueue.add(stringRequest);

    }

    public void getComent(final String idtopi){

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(url + "/simeru/perwalian/getComment.php?idtopik=" + idtopi,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i<jsonArray.length(); i++){

                                ComentPojo comentPojo = new ComentPojo();
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                comentPojo.coment = jsonObject.getString("comment");
                                comentPojo.id_topik = idtopi;
                                comentPojo.id_user = jsonObject.getString("id_user");
                                comentPojo.idComent = jsonObject.getString("id");
                                comentPojo.role = jsonObject.getString("role");
                                comentPojo.username = jsonObject.getString("username");
                                comentPojos.add(comentPojo);

                            }

                            comentAdapter  = new ComentAdapter(getApplicationContext(), comentPojos);
                            balasan.setAdapter(comentAdapter);

                        }catch (Exception e){
                            Toast.makeText(ChatDosen.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ChatDosen.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(stringRequest);
    }

    @Override
    protected void onResume() {
        super.onResume();
        isCommentActive = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        isCommentActive = false;
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            ComentPojo comentPojo = new ComentPojo();
            comentPojo.username = intent.getStringExtra("username");
            comentPojo.coment = intent.getStringExtra("comment");
            comentPojos.add(comentPojo);
            comentAdapter.notifyDataSetChanged();
            balasan.smoothScrollToPosition(comentPojos.size());
            Toast.makeText(context, String.valueOf(comentPojos.size()                                     ), Toast.LENGTH_SHORT).show();
        }
    };
}
