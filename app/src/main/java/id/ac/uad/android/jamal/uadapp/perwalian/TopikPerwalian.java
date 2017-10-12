package id.ac.uad.android.jamal.uadapp.perwalian;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import id.ac.uad.android.jamal.uadapp.R;
import id.ac.uad.android.jamal.uadapp.login.Session;
import id.ac.uad.android.jamal.uadapp.perwalian.callbackperwalian.TopikCallBack;
import id.ac.uad.android.jamal.uadapp.perwalian.callbackperwalian.TranskipCallBack;
import id.ac.uad.android.jamal.uadapp.pojo.SetTopik;
import id.ac.uad.android.jamal.uadapp.pojo.SetTranskip;

public class TopikPerwalian extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<SetTopik> topik;
    public static Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topik_perwalian);
        context = TopikPerwalian.this;

        getSupportActionBar().setTitle("Catatan Perwalian");
        getSupportActionBar().setSubtitle("Dosen Wali : "+new Session(this).getDosenwali());

        recyclerView = (RecyclerView) findViewById(R.id.topikcard);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mlayout = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mlayout);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(TopikPerwalian.this, TambahTopikPerwalian.class);
                startActivity(i);

            }
        });

        Session nimmhs = new Session(this);
        String idnya = nimmhs.getIdDosen();

        getData(idnya);
    }

    public void getData(String idnya){

        TopikCallBack tb = new TopikCallBack(context);
        tb.TopikCallBack(idnya, new TopikCallBack.Addtopik() {
            @Override
            public void Hasil(String hasil) {

                topik = new ArrayList<>();

                try {

                    JSONArray jsonArray = new JSONArray(hasil);
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        SetTopik st = new SetTopik();
                        st.setIdtopik(jsonObject.getString("idtopik"));
                        st.setJamtopik(jsonObject.getString("jam"));
                        st.setInfotopik(jsonObject.getString("topik"));
                        topik.add(st);
                    }
                }catch (Exception e){

                }

                Tambah tambah = new Tambah(topik);
                recyclerView.setAdapter(tambah);

            }
        });
    }

    class Tambah extends RecyclerView.Adapter<Tambah.ViewTambah>{

        List<SetTopik> isidatanya;

        class ViewTambah extends RecyclerView.ViewHolder{

            TextView idtopik,jam,info;

            public ViewTambah(View itemView) {
                super(itemView);

                jam = (TextView)itemView.findViewById(R.id.tanggaltopik);
                info = (TextView)itemView.findViewById(R.id.topiknya);
                idtopik =(TextView)itemView.findViewById(R.id.idtopik);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent i = new Intent(TopikPerwalian.this, ChatDosen.class);
                        i.putExtra("idtopik",idtopik.getText().toString());
                        i.putExtra("topik", info.getText().toString());
                        startActivity(i);


                    }
                });

            }
        }

        Tambah(List<SetTopik> data){

            isidatanya = (data);
        }

        @Override
        public int getItemCount() {
            return isidatanya.size();
        }

        @Override
        public void onBindViewHolder(ViewTambah holder, int position) {

            holder.jam.setText(isidatanya.get(position).getJamtopik());
            holder.info.setText(isidatanya.get(position).getInfotopik());
            holder.idtopik.setText(isidatanya.get(position).getIdtopik());

        }

        @Override
        public ViewTambah onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_topik_perwalian,
                    parent, false);
            ViewTambah vt = new ViewTambah(view);

            return vt;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData(new Session(this).getIdDosen());
    }
}
