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
        String JsonURL = "http://perwalian.esy.es/api/dosen_api.php?prodi="+namaProdi;

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
                            JSONArray jsonArray = response.getJSONArray("data_dosen");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                final Data data = new Data();
                                data.setNiy(jsonObject.getString("niy"));
                                data.setNip(jsonObject.getString("nip"));
                                data.setNidn(jsonObject.getString("nidn"));
                                data.setNama(jsonObject.getString("nama"));
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
            TextView niy,nip,nidn, nama;

            public Holdernya(View itemView) {
                super(itemView);
                niy = (TextView) itemView.findViewById(R.id.niynya);
                nip = (TextView) itemView.findViewById(R.id.nipnya);
                nidn = (TextView) itemView.findViewById(R.id.nidnnya);
                nama = (TextView) itemView.findViewById(R.id.namadosen);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(JadwalDosen.this, JadwalNgajarDosen.class);
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
            holder.niy.setText(this.datas.get(position).getNiy());
            holder.nip.setText(this.datas.get(position).getNip());
            holder.nidn.setText(this.datas.get(position).getNidn());
            holder.nama.setText(this.datas.get(position).getNama());
        }

        @Override
        public int getItemCount() {
            return this.datas.size();
        }
    }

    class Data {
        String niy,nip,nidn,nama;

        public Data() {
        }

        public Data(String niy, String nip, String nidn, String nama) {
            this.niy = niy;
            this.nip = nip;
            this.nidn = nidn;
            this.nama = nama;
        }

        public String getNiy() {
            return niy;
        }

        public void setNiy(String niy) {
            this.niy = niy;
        }

        public String getNip() {
            return nip;
        }

        public void setNip(String nip) {
            this.nip = nip;
        }

        public String getNidn() {
            return nidn;
        }

        public void setNidn(String nidn) {
            this.nidn = nidn;
        }

        public String getNama() {
            return nama;
        }

        public void setNama(String nama) {
            this.nama = nama;
        }
    }
}
