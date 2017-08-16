package id.ac.uad.android.jamal.uadapp.simeru.fragmentjadwalkuliah;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import id.ac.uad.android.jamal.uadapp.R;
import id.ac.uad.android.jamal.uadapp.pojo.SetJadwalKuliah;

/**
 * Created by jamal on 15/03/17.
 */

public class JadwalKuliahFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vi = inflater.inflate(R.layout.fragment_senin, container, false);

        List<SetJadwalKuliah> setJadwalKuliahs = (List<SetJadwalKuliah>) getArguments().getSerializable("data");
        AdapterKuliahSenin adapterKuliahSenin = new AdapterKuliahSenin(setJadwalKuliahs);
        RecyclerView recyclerView = (RecyclerView)vi.findViewById(R.id.cardsenin);
        RecyclerView.LayoutManager setmanager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(setmanager);
        recyclerView.setAdapter(adapterKuliahSenin);

        return vi;
    }

    class AdapterKuliahSenin extends RecyclerView.Adapter<AdapterKuliahSenin.ViewSenin>{

        List<SetJadwalKuliah> isiDatanya;

        class ViewSenin extends RecyclerView.ViewHolder{

            TextView kodekuliah,matakuliah,dosenkuliah,skskuliah,kelaskuliah,jamkuliah,ruangkuliah,semesterkuliah;

            public ViewSenin (View item){
                super(item);

                matakuliah  = (TextView)item.findViewById(R.id.matakuliah);
                kodekuliah  = (TextView)item.findViewById(R.id.kuliahnya);
                dosenkuliah = (TextView)item.findViewById(R.id.dosen);
                skskuliah   = (TextView)item.findViewById(R.id.skskuliahnya);
                kelaskuliah = (TextView)item.findViewById(R.id.kelas);
                jamkuliah = (TextView)item.findViewById(R.id.Jam);
                ruangkuliah = (TextView)item.findViewById(R.id.ruang);
                semesterkuliah = (TextView)item.findViewById(R.id.semesterkuliahnya);

            }
        }

        AdapterKuliahSenin(List<SetJadwalKuliah> data){
            isiDatanya = data;
        }

        @Override
        public void onBindViewHolder(ViewSenin holder, int position) {

            holder.matakuliah.setText(": "+isiDatanya.get(position).matakuliah);
            holder.kodekuliah.setText(": "+isiDatanya.get(position).kodekuliah);
            holder.dosenkuliah.setText(": "+isiDatanya.get(position).dosenkuliah);
            holder.skskuliah.setText(": "+isiDatanya.get(position).skskuliah);
            holder.kelaskuliah.setText(": "+isiDatanya.get(position).kelaskuliah);
            holder.jamkuliah.setText(": "+isiDatanya.get(position).jamkuliah);
            holder.ruangkuliah.setText(": "+isiDatanya.get(position).ruangkuliah);
            holder.semesterkuliah.setText(": "+isiDatanya.get(position).semesterkuliah);

        }

        @Override
        public ViewSenin onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.card_view_jadwal_kuliah, parent,false);
            ViewSenin vi = new ViewSenin(v);
            return vi;
        }

        @Override
        public int getItemCount() {
            return isiDatanya.size();
        }
    }
}
