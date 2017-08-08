package id.ac.uad.android.jamal.uadapp.simeru.fragmentdosen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import id.ac.uad.android.jamal.uadapp.Jadwal;
import id.ac.uad.android.jamal.uadapp.R;
import id.ac.uad.android.jamal.uadapp.simeru.fragmentkuliah.SeninFragment;

/**
 * Created by jamal on 13/04/17.
 */

public class DosenSenin extends Fragment {

    List<Jadwal> jadwals = new ArrayList<>();

    public DosenSenin() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dosen_senin, container,false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        List<Jadwal> jadwals = (List<Jadwal>) getArguments().getSerializable("data");

        AdapterSenin adapternya = new AdapterSenin(jadwals);
        RecyclerView recyclerView = (RecyclerView)getActivity().findViewById(R.id.carddsnsenin);
        RecyclerView.LayoutManager setmanager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(setmanager);
        recyclerView.setAdapter(adapternya);
    }

    class AdapterSenin extends RecyclerView.Adapter<AdapterSenin.ViewSenin>{

        List<Jadwal> isiDatanya;

        class ViewSenin extends RecyclerView.ViewHolder{

            TextView matakuliah,kelas,sks,jam,ruang;

            public ViewSenin (View item){
                super(item);

                matakuliah = (TextView)item.findViewById(R.id.matkulnya);
                kelas      = (TextView)item.findViewById(R.id.kelasnya);
                sks        = (TextView)item.findViewById(R.id.sksnya);
                jam        = (TextView)item.findViewById(R.id.jamnya);
                ruang      = (TextView)item.findViewById(R.id.ruangannya);

            }
        }


        AdapterSenin(List<Jadwal> data){
            isiDatanya = data;
        }

        @Override
        public int getItemCount() {
            return isiDatanya.size();
        }

        @Override
        public ViewSenin onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.card_view_jadwal_dosennya, parent,false);
            ViewSenin vi = new ViewSenin(v);
            return vi;
        }

        @Override
        public void onBindViewHolder(ViewSenin holder, int position) {
            holder.ruang.setText(isiDatanya.get(position).ruang);
            holder.jam.setText(isiDatanya.get(position).jam);
            holder.kelas.setText(isiDatanya.get(position).kelas);
            holder.sks.setText(isiDatanya.get(position).sks);
            holder.matakuliah.setText(isiDatanya.get(position).namakul);

        }
    }
}
