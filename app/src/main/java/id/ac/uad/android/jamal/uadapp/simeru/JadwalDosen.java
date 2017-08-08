package id.ac.uad.android.jamal.uadapp.simeru;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import id.ac.uad.android.jamal.uadapp.R;
import id.ac.uad.android.jamal.uadapp.login.Session;

public class JadwalDosen extends AppCompatActivity {

    RequestQueue requestQueue;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_dosen);


        String[] p = new Session(JadwalDosen.this).getProdi().split(" ");
        String namaProdi = TextUtils.join("%20",p);

        String JsonURL = "http://192.168.43.219/simeru/json/prodidosen.php?prodi="+namaProdi;

        requestQueue = Volley.newRequestQueue(this);

        recyclerView = (RecyclerView) findViewById(R.id.buatcardiview);
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        JsonObjectRequest arrayRequest = new JsonObjectRequest(JsonURL,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        List<Data> datas = new ArrayList<>();
                        try {
                            JSONArray jsonArray = response.getJSONArray("hasil");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                final Data data = new Data();
                                data.setNiynipnidn(jsonObject.getString("niynipnidn"));
                                data.setNama(jsonObject.getString("namadosen"));
                                datas.add(data);
                            }
                        } catch (JSONException e) {
                            Toast.makeText(JadwalDosen.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        Adapter adapter = new Adapter(datas);
                        recyclerView.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(JadwalDosen.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(arrayRequest);
    }

    class Adapter extends RecyclerView.Adapter<Adapter.Holdernya> {
        private List<Data> datas;

        Adapter(List<Data> datas) {
            this.datas = datas;
        }

        class Holdernya extends RecyclerView.ViewHolder {
            TextView niynipnidn, nama;

            public Holdernya(View itemView) {
                super(itemView);
                niynipnidn = (TextView) itemView.findViewById(R.id.iddsn);
                nama = (TextView) itemView.findViewById(R.id.namadosen);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent i = new Intent(JadwalDosen.this, JadwalNgajarDosen.class);
                        i.putExtra("niy",niynipnidn.getText().toString());
                        i.putExtra("nama",nama.getText().toString());
                        startActivity(i);

                        Toast.makeText(getApplicationContext() ,nama.getText().toString(),
                                Toast.LENGTH_SHORT).show();

                    }
                });
            }
        }

        @Override
        public Holdernya onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.card_view_jadwal_dosen, parent, false);
            Holdernya holdernya = new Holdernya(v);

            return holdernya;
        }

        @Override
        public void onBindViewHolder(Holdernya holder, int position) {
            holder.niynipnidn.setText(this.datas.get(position).getNiynipnidn());
            holder.nama.setText(this.datas.get(position).getNama());
        }

        @Override
        public int getItemCount() {
            return this.datas.size();
        }
    }

    class Data {
        String niynipnidn,nama;

        public Data() {
        }

        public Data(String niynipnidn, String nama) {
            this.niynipnidn = niynipnidn;
            this.nama = nama;
        }

        public String getNiynipnidn() {
            return niynipnidn;
        }

        public void setNiynipnidn(String niynipnidn) {
            this.niynipnidn = niynipnidn;
        }

        public String getNama() {
            return nama;
        }

        public void setNama(String nama)
        {
            this.nama = nama;
        }
    }
}
