package id.ac.uad.android.jamal.uadapp.simeru;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import id.ac.uad.android.jamal.uadapp.Jadwal;
import id.ac.uad.android.jamal.uadapp.R;
import id.ac.uad.android.jamal.uadapp.simeru.fragmentdosen.DosenJumat;
import id.ac.uad.android.jamal.uadapp.simeru.fragmentdosen.DosenKamis;
import id.ac.uad.android.jamal.uadapp.simeru.fragmentdosen.DosenRabu;
import id.ac.uad.android.jamal.uadapp.simeru.fragmentdosen.DosenSabtu;
import id.ac.uad.android.jamal.uadapp.simeru.fragmentdosen.DosenSelasa;
import id.ac.uad.android.jamal.uadapp.simeru.fragmentdosen.DosenSenin;
import id.ac.uad.android.jamal.uadapp.simeru.fragmentkuliah.SeninFragment;

public class JadwalNgajarDosen extends AppCompatActivity {

    private TabLayout dosenlayout;
    private Toolbar toolbardosen;
    private ViewPager viewPagerdosen;
    private RequestQueue requestQueue;
    private String niy = null;
    private String nama = null;
    Map<String,List<Jadwal>> jadwal;
    private List<String> hariStringList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_ngajar_dosen);

        niy = getIntent().getStringExtra("niy");
        nama = getIntent().getStringExtra("nama");

        requestQueue = Volley.newRequestQueue(this);

        toolbardosen = (Toolbar) findViewById(R.id.toolbardosen);
        setSupportActionBar(toolbardosen);
        getSupportActionBar().setTitle("Jadwal Dosen UAD");
        getSupportActionBar().setSubtitle(nama);

        hariStringList = new ArrayList<>();
        viewPagerdosen = (ViewPager)findViewById(R.id.dosenpager);

        dosenlayout = (TabLayout)findViewById(R.id.tabjadwaldosen);
        dosenlayout.setupWithViewPager(viewPagerdosen);


        niy = getIntent().getStringExtra("niy");
        jadwal = new HashMap<>();
        StringRequest stringRequest = new StringRequest("http://192.168.43.219/simeru/json/jadwaldosen.php?niy="+niy
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jsonObject1 = new JSONObject(response);
                    JSONObject jsonObject  = jsonObject1.getJSONObject("hasil");

                    if(jsonObject.has("Senin")){
                        JSONArray senin = jsonObject.getJSONArray("Senin");
                        List<Jadwal> jadwalList = new ArrayList<>();
                        for(int i = 0;i < senin.length();i++){
                            JSONObject object = senin.getJSONObject(i);
                            Jadwal jadwal = new Jadwal();
                            jadwal.jam = object.getString("jam");
                            jadwal.kelas = object.getString("kelas");
                            jadwal.namakul = object.getString("namakul");
                            jadwal.sks  = object.getString("sks");
                            jadwal.ruang = object.getString("ruang_idruang");
                            jadwalList.add(jadwal);
                        }
                        hariStringList.add("Senin");
                        jadwal.put("Senin",jadwalList);
                    }

                    if(jsonObject.has("Selasa")){
                        JSONArray senin = jsonObject.getJSONArray("Selasa");
                        List<Jadwal> jadwalList = new ArrayList<>();
                        for(int i = 0;i < senin.length();i++){
                            JSONObject object = senin.getJSONObject(i);
                            Jadwal jadwal = new Jadwal();
                            jadwal.jam = object.getString("jam");
                            jadwal.kelas = object.getString("kelas");
                            jadwal.namakul = object.getString("namakul");
                            jadwal.sks  = object.getString("sks");
                            jadwal.ruang = object.getString("ruang_idruang");
                            jadwalList.add(jadwal);
                        }
                        hariStringList.add("Selasa");
                        jadwal.put("Selasa",jadwalList);



                    }

                    if(jsonObject.has("Rabu")){

                        JSONArray senin = jsonObject.getJSONArray("Rabu");
                        List<Jadwal> jadwalList = new ArrayList<>();
                        for(int i = 0;i < senin.length();i++){
                            JSONObject object = senin.getJSONObject(i);
                            Jadwal jadwal = new Jadwal();
                            jadwal.jam = object.getString("jam");
                            jadwal.kelas = object.getString("kelas");
                            jadwal.namakul = object.getString("namakul");
                            jadwal.sks  = object.getString("sks");
                            jadwal.ruang = object.getString("ruang_idruang");
                            jadwalList.add(jadwal);
                        }
                        hariStringList.add("Rabu");
                        jadwal.put("Rabu",jadwalList);

                    }

                    if(jsonObject.has("Kamis")){

                        JSONArray senin = jsonObject.getJSONArray("Kamis");
                        List<Jadwal> jadwalList = new ArrayList<>();
                        for(int i = 0;i < senin.length();i++){
                            JSONObject object = senin.getJSONObject(i);
                            Jadwal jadwal = new Jadwal();
                            jadwal.jam = object.getString("jam");
                            jadwal.kelas = object.getString("kelas");
                            jadwal.namakul = object.getString("namakul");
                            jadwal.sks  = object.getString("sks");
                            jadwal.ruang = object.getString("ruang_idruang");
                            jadwalList.add(jadwal);
                        }
                        hariStringList.add("Kamis");
                        jadwal.put("Kamis",jadwalList);
                    }

                    if(jsonObject.has("Jumat")){

                        JSONArray senin = jsonObject.getJSONArray("Jumat");
                        List<Jadwal> jadwalList = new ArrayList<>();
                        for(int i = 0;i < senin.length();i++){
                            JSONObject object = senin.getJSONObject(i);
                            Jadwal jadwal = new Jadwal();
                            jadwal.jam = object.getString("jam");
                            jadwal.kelas = object.getString("kelas");
                            jadwal.namakul = object.getString("namakul");
                            jadwal.sks  = object.getString("sks");
                            jadwal.ruang = object.getString("ruang_idruang");
                            jadwalList.add(jadwal);
                        }
                        hariStringList.add("Jumat");
                        jadwal.put("Jumat",jadwalList);

                    }

                    if(jsonObject.has("Sabtu")){

                        JSONArray senin = jsonObject.getJSONArray("Sabtu");
                        List<Jadwal> jadwalList = new ArrayList<>();
                        for(int i = 0;i < senin.length();i++){
                            JSONObject object = senin.getJSONObject(i);
                            Jadwal jadwal = new Jadwal();
                            jadwal.jam = object.getString("jam");
                            jadwal.kelas = object.getString("kelas");
                            jadwal.namakul = object.getString("namakul");
                            jadwal.sks  = object.getString("sks");
                            jadwal.ruang = object.getString("ruang_idruang");
                            jadwalList.add(jadwal);
                        }
                        hariStringList.add("Sabtu");
                        jadwal.put("Sabtu",jadwalList);

                    }

                    aturViewPager(viewPagerdosen);

                }catch (Exception e){
                    Toast.makeText(JadwalNgajarDosen.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(JadwalNgajarDosen.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(stringRequest);

    }


    private void aturViewPager(ViewPager viewPager){

        AturAdapter setAdapter = new AturAdapter(getSupportFragmentManager());
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new DosenSenin());
        fragmentList.add(new DosenKamis());

        for(int i =0;i<hariStringList.size();i++){
            setAdapter.AddAdapter(fragmentList.get(i),hariStringList.get(i));
        }
        viewPager.setAdapter(setAdapter);
    }

    class AturAdapter extends FragmentStatePagerAdapter{

        private List<Fragment> listfr = new ArrayList<>();
        private List<String> hari = new ArrayList<>();

        public AturAdapter(FragmentManager fm) {
            super(fm);
        }

        public void AddAdapter (Fragment fragment,String harinya){
            listfr.add(fragment);

            hari.add(harinya);

        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return hari.get(position);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment    = listfr.get(position);
            List<Jadwal> jadwals = jadwal.get(hariStringList.get(position));
            Bundle bundle = new Bundle();
            bundle.putSerializable("data",(Serializable)jadwals);
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public int getCount() {
            return listfr.size();
        }
    }
}
