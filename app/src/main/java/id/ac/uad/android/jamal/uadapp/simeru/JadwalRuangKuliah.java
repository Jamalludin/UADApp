package id.ac.uad.android.jamal.uadapp.simeru;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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

public class JadwalRuangKuliah extends AppCompatActivity {

    private RecyclerView Rview;
    String JsonURL = "";
    RequestQueue reqQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_ruang_kuliah);

        reqQueue = Volley.newRequestQueue(this);
        Rview = (RecyclerView) findViewById(R.id.ruangcard);
        Rview.setHasFixedSize(true);
        RecyclerView.LayoutManager mlayout = new LinearLayoutManager(this);
        Rview.setLayoutManager(mlayout);

        JsonObjectRequest jreq = new JsonObjectRequest(JsonURL, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                List<Ruang> ruangnya = new ArrayList<>();
                try {
                    JSONArray jsonArray = response.getJSONArray("");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Ruang data = new Ruang();
                        data.setIdruang(jsonObject.getString(""));
                        data.setRuang(jsonObject.getString(""));
                        data.setKapasitas(jsonObject.getString(""));
                        ruangnya.add(data);
                    }
                } catch (JSONException e) {
                    Toast.makeText(JadwalRuangKuliah.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                IniRuang vRuang = new IniRuang(ruangnya);
                Rview.setAdapter(vRuang);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(JadwalRuangKuliah.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        reqQueue.add(jreq);
    }
    class IniRuang extends RecyclerView.Adapter<IniRuang.Vadapter>{
        List<Ruang> isiRuang;

        class Vadapter extends RecyclerView.ViewHolder{

            TextView namaR,kapasitasR;
            public Vadapter(View itemView) {
                super(itemView);

                namaR = (TextView) itemView.findViewById(R.id.namaru);
                kapasitasR = (TextView) itemView.findViewById(R.id.kapasitasru);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent i = new Intent(JadwalRuangKuliah.this, JadwalRuangannya.class);
                        startActivity(i);

                        Toast.makeText(JadwalRuangKuliah.this, "", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
        IniRuang(List<Ruang> data){
            isiRuang = data;
        }

        @Override
        public Vadapter onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_ruang,
                    parent, false);
            Vadapter adapter = new Vadapter(v);
            return adapter;
        }

        @Override
        public void onBindViewHolder(Vadapter holder, int position) {
            holder.namaR.setText(isiRuang.get(position).getRuang());
            holder.kapasitasR.setText(isiRuang.get(position).getKapasitas());
        }

        @Override
        public int getItemCount() {
            return isiRuang.size();
        }
    }

    class Ruang{
        String idruang, ruang, kapasitas;

        public Ruang() {
        }

        public String getIdruang() {
            return idruang;
        }

        public void setIdruang(String idruang) {
            this.idruang = idruang;
        }

        public String getRuang() {
            return ruang;
        }

        public void setRuang(String ruang) {
            this.ruang = ruang;
        }

        public String getKapasitas() {
            return kapasitas;
        }

        public void setKapasitas(String kapasitas) {
            this.kapasitas = kapasitas;
        }
    }
}
