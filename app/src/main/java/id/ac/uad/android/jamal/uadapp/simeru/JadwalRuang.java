package id.ac.uad.android.jamal.uadapp.simeru;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import id.ac.uad.android.jamal.uadapp.R;

public class JadwalRuang extends AppCompatActivity {

    private RecyclerView Rview;
    String JsonURL = "http://perwalian.esy.es/api/kampus.php";
    RequestQueue reqQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_ruang);

        reqQueue = Volley.newRequestQueue(this);
        Rview = (RecyclerView) findViewById(R.id.cardkampus);
        Rview.setHasFixedSize(true);
        RecyclerView.LayoutManager mlayout = new LinearLayoutManager(this);
        Rview.setLayoutManager(mlayout);

        JsonObjectRequest arryReq = new JsonObjectRequest(JsonURL, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                List<Kampus> kampusnya = new ArrayList<>();
                try {
                    JSONArray jsonArray = response.getJSONArray("kampus");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Kampus data = new Kampus();
                        data.setId(jsonObject.getString("id"));
                        data.setNama(jsonObject.getString("nama"));
                        data.setAlamat(jsonObject.getString("alamat"));
                        kampusnya.add(data);
                    }
                } catch (JSONException e) {
                    Toast.makeText(JadwalRuang.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                IniKampus adapter = new IniKampus(kampusnya);
                Rview.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(JadwalRuang.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        reqQueue.add(arryReq);

    }

    class IniKampus extends RecyclerView.Adapter<IniKampus.ViewAdapter>{
        List<Kampus> isikampusnya;

        class ViewAdapter extends RecyclerView.ViewHolder{
            TextView namakmps,alamatkmps;

            public ViewAdapter(View itemView) {
                super(itemView);

                namakmps = (TextView)itemView.findViewById(R.id.namakmps);
                alamatkmps = (TextView)itemView.findViewById(R.id.alamatkmps);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(JadwalRuang.this, JadwalRuangKuliah.class);
                        startActivity(i);

                        Toast.makeText(getApplicationContext(),namakmps.getText().toString(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
        IniKampus(List<Kampus> data){
            isikampusnya = data;
        }

        @Override
        public ViewAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_kampus,
                    parent, false);
           ViewAdapter vAdapter = new ViewAdapter(v);
            return vAdapter;
        }

        @Override
        public void onBindViewHolder(ViewAdapter holder, int position) {
            holder.namakmps.setText(isikampusnya.get(position).getNama());
            holder.alamatkmps.setText(isikampusnya.get(position).getAlamat());
        }

        @Override
        public int getItemCount() {
            return isikampusnya.size();
        }
    }

    class Kampus {
        String nama, alamat, id;

        public Kampus() {
        }


        public String getNama() {
            return nama;
        }

        public void setNama(String nama) {
            this.nama = nama;
        }

        public String getAlamat() {
            return alamat;
        }

        public void setAlamat(String alamat) {
            this.alamat = alamat;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
