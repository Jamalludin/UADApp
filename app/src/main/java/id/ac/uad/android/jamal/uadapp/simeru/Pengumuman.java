package id.ac.uad.android.jamal.uadapp.simeru;

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
import id.ac.uad.android.jamal.uadapp.login.Session;
import id.ac.uad.android.jamal.uadapp.pojo.PengumumanSimeru;

import static id.ac.uad.android.jamal.uadapp.pojo.Url.url;

public class Pengumuman extends AppCompatActivity {

    private RecyclerView Rview;
    RequestQueue reqQueue;
    List<PengumumanSimeru> datanya;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengumuman);

        reqQueue = Volley.newRequestQueue(this);
        Rview = (RecyclerView) findViewById(R.id.pengumancard);
        Rview.setHasFixedSize(true);
        RecyclerView.LayoutManager mlayout = new LinearLayoutManager(this);
        Rview.setLayoutManager(mlayout);

        Session nim = new Session(this);
        String idmhs = nim.getNim();

        getData(idmhs);

    }
    public void getData(String mhsnim){

        String JsonURL = url+"/simeru/json/getpostinfo.php?nim="+mhsnim;
        JsonObjectRequest arrReq = new JsonObjectRequest(JsonURL,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        datanya = new ArrayList<>();
                        try {
                            JSONArray jsonArray = response.getJSONArray("hasil");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                PengumumanSimeru data = new PengumumanSimeru();
                                data.setMatkulps(jsonObject.getString("matakuliah_idmatakuliah"));
                                data.setKelasps(jsonObject.getString("kelas"));
                                data.setHarips(jsonObject.getString("hari"));
                                data.setJamps(jsonObject.getString("jam"));
                                data.setInfops(jsonObject.getString("keterangan"));
                                datanya.add(data);
                            }

                        } catch (JSONException e) {
                            Toast.makeText(Pengumuman.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        Adapter2 adapter = new Adapter2(datanya);
                        Rview.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Pengumuman.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        reqQueue.add(arrReq);
    }

    class Adapter2 extends RecyclerView.Adapter<Adapter2.ViewAdapter> {
        List<PengumumanSimeru> isiDatanya;

        class ViewAdapter extends RecyclerView.ViewHolder {

            TextView matkul,kls,hari,jam,info;

            ViewAdapter(View item) {
                super(item);

                matkul = (TextView) item.findViewById(R.id.matkulps);
                kls = (TextView) item.findViewById(R.id.kelaspp);
                hari = (TextView) item.findViewById(R.id.haripp);
                jam = (TextView) item.findViewById(R.id.jampp);
                info = (TextView) item.findViewById(R.id.infopp);

            }
        }

        Adapter2(List<PengumumanSimeru> data) {
            isiDatanya = data;
        }

        @Override
        public int getItemCount() {
            return isiDatanya.size();
        }

        @Override
        public ViewAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_pengumuman,
                    parent, false);
            ViewAdapter viewAdapter = new ViewAdapter(v);
            return viewAdapter;
        }

        @Override
        public void onBindViewHolder(ViewAdapter holder, int position) {
            holder.matkul.setText(isiDatanya.get(position).getMatkulps());
            holder.kls.setText(isiDatanya.get(position).getKelasps());
            holder.hari.setText(isiDatanya.get(position).getHarips());
            holder.jam.setText(isiDatanya.get(position).getJamps());
            holder.info.setText(isiDatanya.get(position).getInfops());
        }
    }

}
