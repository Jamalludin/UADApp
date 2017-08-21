package id.ac.uad.android.jamal.uadapp.perwalian;

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
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import id.ac.uad.android.jamal.uadapp.R;
import id.ac.uad.android.jamal.uadapp.login.Session;
import id.ac.uad.android.jamal.uadapp.pojo.SetDindingDosen;
import id.ac.uad.android.jamal.uadapp.simeru.Pengumuman;

import static id.ac.uad.android.jamal.uadapp.pojo.Url.url;

public class BeritaDosenWali extends AppCompatActivity {

    private RecyclerView Rview;
    RequestQueue reqQueue;
    List<SetDindingDosen> datanya;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berita_dosen_wali);

        Session dsn = new Session(this);
        String namadsn = dsn.getDosenwali();
        getSupportActionBar().setTitle("Pengumuman Dosen Wali");
        getSupportActionBar().setSubtitle(namadsn);

        reqQueue = Volley.newRequestQueue(this);
        Rview = (RecyclerView) findViewById(R.id.buatberita);
        Rview.setHasFixedSize(true);
        RecyclerView.LayoutManager mlayout = new LinearLayoutManager(this);
        Rview.setLayoutManager(mlayout);

        Session iddsn = new Session(this);
        String nid = iddsn.getIdDosen();
        getData(nid);
    }

    public void getData(String dosen){

        String JsonUrl = url+"/simeru/json/dindingperwalian.php?niy="+dosen;
        JsonObjectRequest jreq = new JsonObjectRequest(JsonUrl, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                datanya = new ArrayList<>();
                try {
                    JSONArray jsonArray = response.getJSONArray("hasil");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        SetDindingDosen dsn = new SetDindingDosen();
                        dsn.setJamdinding(jsonObject.getString("jam"));
                        dsn.setPengumuman(jsonObject.getString("informasi"));
                        datanya.add(dsn);
                    }
                } catch (Exception e) {
                    Toast.makeText(BeritaDosenWali.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                DindingDsn adapter = new DindingDsn(datanya);
                Rview.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(BeritaDosenWali.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        reqQueue.add(jreq);
    }

    class DindingDsn extends RecyclerView.Adapter<DindingDsn.ViewDinding> {
        List<SetDindingDosen> isidatanya;

        class ViewDinding extends RecyclerView.ViewHolder{
            TextView jamnya, isinya;

            public ViewDinding(View itemView) {
                super(itemView);

                jamnya = (TextView)itemView.findViewById(R.id.tanggalnyadsn);
                isinya = (TextView)itemView.findViewById(R.id.pengumumannyadsn);

            }
        }

        DindingDsn(List<SetDindingDosen> data){
            isidatanya = data;
        }

        @Override
        public int getItemCount() {
            return isidatanya.size();
        }

        @Override
        public void onBindViewHolder(ViewDinding holder, int position) {
            holder.jamnya.setText(isidatanya.get(position).getJamdinding());
            holder.isinya.setText(isidatanya.get(position).getPengumuman());

        }

        @Override
        public ViewDinding onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_berita_dosen_wali,
                    parent, false);
            ViewDinding viewDinding = new ViewDinding(v);
            return viewDinding;
        }
    }
}
