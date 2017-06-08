package id.ac.uad.android.jamal.uadapp.simeru.fragmentkuliah;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

/**
 * Created by jamal on 15/03/17.
 */

public class SelasaFragment extends Fragment {

    public SelasaFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_selasa, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String[] p = new Session(getContext()).getProdi().split(" ");
        String namaProdi = TextUtils.join("%20",p);

        String url = "http://perwalian.esy.es/api/kuliah.php?hari=selasa&prodi="+namaProdi;

        RecyclerView.LayoutManager setmanager = new LinearLayoutManager(getActivity().getApplicationContext());
        final RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.cardselasa);
        recyclerView.setLayoutManager(setmanager);

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

        JsonObjectRequest arrayReq = new JsonObjectRequest(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                List<Datanya> dataya = new ArrayList<>();
                try {
                    JSONArray jsonArray = response.getJSONArray("kuliah");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        final Datanya data = new Datanya();
                        data.setKode(jsonObject.getString("kode"));
                        data.setMatakuliah(jsonObject.getString("matakuliah"));
                        data.setKelas(jsonObject.getString("kelas"));
                        data.setSks(jsonObject.getString("sks"));
                        data.setJam(jsonObject.getString("jam"));
                        data.setSemester(jsonObject.getString("semester"));
                        data.setDosen(jsonObject.getString("dosen"));
                        data.setRuang(jsonObject.getString("ruang"));
                        dataya.add(data);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Adapternya adapter = new Adapternya(dataya);
                recyclerView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(arrayReq);
    }

    class Adapternya extends RecyclerView.Adapter<Adapternya.ViewAdapter>{

        List<Datanya> isiDatanya;

        class ViewAdapter extends RecyclerView.ViewHolder{
            TextView kode,matakuliah,kelas,sks,jam,semester,dosen,ruang;

            public ViewAdapter(View itemView) {
                super(itemView);
                kode = (TextView)itemView.findViewById(R.id.kuliahnya);
                matakuliah = (TextView)itemView.findViewById(R.id.matkulnya);
                kelas = (TextView)itemView.findViewById(R.id.kelasnya);
                sks = (TextView)itemView.findViewById(R.id.sksnya);
                jam = (TextView)itemView.findViewById(R.id.jamnya);
                semester = (TextView)itemView.findViewById(R.id.semesternya);
                dosen = (TextView)itemView.findViewById(R.id.namadosen);
                ruang = (TextView)itemView.findViewById(R.id.ruangannya);
            }
        }

        Adapternya(List<Datanya> data){
            isiDatanya = data;
        }

        @Override
        public ViewAdapter onCreateViewHolder(ViewGroup parent, int viewType) {

            View v = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.card_view_jadwal_kuliah, parent,false);
            ViewAdapter holder = new ViewAdapter(v);
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewAdapter holder, int position) {
            holder.kode.setText(this.isiDatanya.get(position).getKode());
            holder.matakuliah.setText(this.isiDatanya.get(position).getMatakuliah());
            holder.kelas.setText(this.isiDatanya.get(position).getKelas());
            holder.sks.setText(this.isiDatanya.get(position).getSks());
            holder.jam.setText(this.isiDatanya.get(position).getJam());
            holder.semester.setText(this.isiDatanya.get(position).getSemester());
            holder.dosen.setText(this.isiDatanya.get(position).getDosen());
            holder.ruang.setText(this.isiDatanya.get(position).getRuang());

        }

        @Override
        public int getItemCount() {
            return this.isiDatanya.size();
        }
    }

    class Datanya{
        String kode,matakuliah,kelas,sks,jam,semester,dosen,ruang;

        public Datanya() {
        }

        public Datanya(String kode, String matakuliah, String kelas, String sks, String jam, String semester, String dosen, String ruang) {
            this.kode = kode;
            this.matakuliah = matakuliah;
            this.kelas = kelas;
            this.sks = sks;
            this.jam = jam;
            this.semester = semester;
            this.dosen = dosen;
            this.ruang = ruang;
        }

        public String getKode() {
            return kode;
        }

        public void setKode(String kode) {
            this.kode = kode;
        }

        public String getMatakuliah() {
            return matakuliah;
        }

        public void setMatakuliah(String matakuliah) {
            this.matakuliah = matakuliah;
        }

        public String getKelas() {
            return kelas;
        }

        public void setKelas(String kelas) {
            this.kelas = kelas;
        }

        public String getSks() {
            return sks;
        }

        public void setSks(String sks) {
            this.sks = sks;
        }

        public String getJam() {
            return jam;
        }

        public void setJam(String jam) {
            this.jam = jam;
        }

        public String getSemester() {
            return semester;
        }

        public void setSemester(String semester) {
            this.semester = semester;
        }

        public String getDosen() {
            return dosen;
        }

        public void setDosen(String dosen) {
            this.dosen = dosen;
        }

        public String getRuang() {
            return ruang;
        }

        public void setRuang(String ruang) {
            this.ruang = ruang;
        }
    }
}
