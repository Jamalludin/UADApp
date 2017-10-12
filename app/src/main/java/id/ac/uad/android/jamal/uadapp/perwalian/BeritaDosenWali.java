package id.ac.uad.android.jamal.uadapp.perwalian;

import android.content.Context;
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
import id.ac.uad.android.jamal.uadapp.perwalian.callbackperwalian.BeritaCallBack;
import id.ac.uad.android.jamal.uadapp.pojo.SetDindingDosen;
import id.ac.uad.android.jamal.uadapp.simeru.Pengumuman;

import static id.ac.uad.android.jamal.uadapp.pojo.Url.url;

public class BeritaDosenWali extends AppCompatActivity {

    private RecyclerView Rview;
    List<SetDindingDosen> datanya;
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berita_dosen_wali);

        context = BeritaDosenWali.this;
        Session dsn = new Session(this);
        String namadsn = dsn.getDosenwali();
        getSupportActionBar().setTitle("Pengumuman Dosen Wali");
        getSupportActionBar().setSubtitle(namadsn);

        Rview = (RecyclerView) findViewById(R.id.buatberita);
        Rview.setHasFixedSize(true);
        RecyclerView.LayoutManager mlayout = new LinearLayoutManager(this);
        Rview.setLayoutManager(mlayout);

        Session iddsn = new Session(this);
        String nid = iddsn.getIdDosen();
        getData(nid);
    }

    public void getData(String dosen){


        BeritaCallBack bc = new BeritaCallBack(context);
        bc.BeritaCallBack(dosen, new BeritaCallBack.Beritadsn() {
            @Override
            public void Result(String result) {

                datanya = new ArrayList<>();
                try {

                    JSONObject jo = new JSONObject(result);
                    JSONArray jsonArray = jo.getJSONArray("hasil");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        SetDindingDosen dsn = new SetDindingDosen();
                        dsn.setPengumuman(jsonObject.getString("info"));
                        dsn.setJamdinding(jsonObject.getString("jam"));
                        datanya.add(dsn);
                    }
                } catch (Exception e) {

                }

                DindingDsn adapter = new DindingDsn(datanya);
                Rview.setAdapter(adapter);

            }
        });
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
