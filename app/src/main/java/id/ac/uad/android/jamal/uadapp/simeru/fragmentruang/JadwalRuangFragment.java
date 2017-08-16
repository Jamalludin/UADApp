package id.ac.uad.android.jamal.uadapp.simeru.fragmentruang;

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

import id.ac.uad.android.jamal.uadapp.R;
import id.ac.uad.android.jamal.uadapp.pojo.SetJadwalDosen;
import id.ac.uad.android.jamal.uadapp.pojo.SetRuangKuliah;
import id.ac.uad.android.jamal.uadapp.simeru.fragmentdosen.JadwalDosenNgajar;

/**
 * Created by jamal on 06/07/17.
 */

public class JadwalRuangFragment extends Fragment {

    public JadwalRuangFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ruang_senin, container, false);

        List<SetRuangKuliah> setRuangKuliahs = (List<SetRuangKuliah>) getArguments().getSerializable("data");
        AdapterJadwal adapternya = new AdapterJadwal(setRuangKuliahs);
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.ruangsenin);
        RecyclerView.LayoutManager setmanager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(setmanager);
        recyclerView.setAdapter(adapternya);

        return view;

    }

    class AdapterJadwal extends RecyclerView.Adapter<AdapterJadwal.ViewJadwal>{

        List<SetRuangKuliah> isiJadwalnya;

        class ViewJadwal extends RecyclerView.ViewHolder{

            TextView kodekuliah,matakuliah,dosenkuliah,skskuliah,kelaskuliah,jamkuliah,prodi,semesterkuliah;

            public ViewJadwal(View itemView) {
                super(itemView);

                kodekuliah = (TextView)itemView.findViewById(R.id.kuliahru);
                matakuliah = (TextView)itemView.findViewById(R.id.matakuliahru);
                dosenkuliah = (TextView)itemView.findViewById(R.id.dosenru);
                skskuliah = (TextView)itemView.findViewById(R.id.skskuliahru);
                kelaskuliah = (TextView)itemView.findViewById(R.id.kelasru);
                jamkuliah = (TextView)itemView.findViewById(R.id.jamru);
                prodi = (TextView)itemView.findViewById(R.id.prodiru);
                semesterkuliah = (TextView)itemView.findViewById(R.id.semesterkuliahru);
            }
        }
        AdapterJadwal(List<SetRuangKuliah> data){
            isiJadwalnya = data;
        }

        @Override
        public int getItemCount() {
            return isiJadwalnya.size();
        }

        @Override
        public void onBindViewHolder(ViewJadwal holder, int position) {
            holder.matakuliah.setText(": "+isiJadwalnya.get(position).matakuliahru);
            holder.kodekuliah.setText(": "+isiJadwalnya.get(position).kodekuliru);
            holder.dosenkuliah.setText(": "+isiJadwalnya.get(position).dosenkuliahru);
            holder.skskuliah.setText(": "+isiJadwalnya.get(position).skskuliahru);
            holder.kelaskuliah.setText(": "+isiJadwalnya.get(position).kelaskuliahru);
            holder.jamkuliah.setText(": "+isiJadwalnya.get(position).jamkuliahru);
            holder.prodi.setText(": "+isiJadwalnya.get(position).prodi);
            holder.semesterkuliah.setText(": "+isiJadwalnya.get(position).semesterkuliahru);

        }

        @Override
        public ViewJadwal onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.card_view_jadwal_ruang, parent,false);
            ViewJadwal vi = new ViewJadwal(v);
            return vi;
        }
    }
}
