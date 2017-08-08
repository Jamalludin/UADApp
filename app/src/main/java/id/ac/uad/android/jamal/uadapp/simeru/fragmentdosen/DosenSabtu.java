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

import java.util.List;

import id.ac.uad.android.jamal.uadapp.Jadwal;
import id.ac.uad.android.jamal.uadapp.R;

/**
 * Created by jamal on 13/04/17.
 */

public class DosenSabtu extends Fragment {
    public DosenSabtu() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dosen_sabtu, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        List<Jadwal> jadwals = (List<Jadwal>) getArguments().getSerializable("data");

        AdapterSabtu adapterSabtu = new AdapterSabtu(jadwals);
        RecyclerView recyclerView = (RecyclerView)getActivity().findViewById(R.id.carddsnsabtu);
        RecyclerView.LayoutManager setmanager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(setmanager);
        recyclerView.setAdapter(adapterSabtu);
    }

    class AdapterSabtu extends RecyclerView.Adapter<AdapterSabtu.ViewSabtu>{

        List<Jadwal> isiDatanya;

        class ViewSabtu extends RecyclerView.ViewHolder{

            TextView matakuliah,kelas,sks,jam,ruang;

            public ViewSabtu(View itemView) {
                super(itemView);

            matakuliah = (TextView)itemView.findViewById(R.id.matkulnya);
                kelas      = (TextView)itemView.findViewById(R.id.kelasnya);
                sks        = (TextView)itemView.findViewById(R.id.sksnya);
                jam        = (TextView)itemView.findViewById(R.id.jamnya);
                ruang      = (TextView)itemView.findViewById(R.id.ruangannya);

            }
        }

        AdapterSabtu(List<Jadwal> data){
            isiDatanya = data;
        }

        @Override
        public void onBindViewHolder(ViewSabtu holder, int position) {

            holder.ruang.setText(isiDatanya.get(position).ruang);
            holder.jam.setText(isiDatanya.get(position).jam);
            holder.kelas.setText(isiDatanya.get(position).kelas);
            holder.sks.setText(isiDatanya.get(position).sks);
            holder.matakuliah.setText(isiDatanya.get(position).namakul);
        }

        @Override
        public ViewSabtu onCreateViewHolder(ViewGroup parent, int viewType) {

            View v = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.card_view_jadwal_dosennya, parent,false);
            ViewSabtu vi = new ViewSabtu(v);
            return vi;
        }

        @Override
        public int getItemCount() {
            return isiDatanya.size();
        }
    }
}
