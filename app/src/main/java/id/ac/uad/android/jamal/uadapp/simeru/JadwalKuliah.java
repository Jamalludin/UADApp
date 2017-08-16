package id.ac.uad.android.jamal.uadapp.simeru;

import android.content.Context;
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
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import id.ac.uad.android.jamal.uadapp.R;
import id.ac.uad.android.jamal.uadapp.pojo.SetJadwalKuliah;
import id.ac.uad.android.jamal.uadapp.login.Session;
import id.ac.uad.android.jamal.uadapp.simeru.fragmentjadwalkuliah.JadwalKuliahFragment;

import static id.ac.uad.android.jamal.uadapp.pojo.Url.url;

public class JadwalKuliah extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabKuliah;
    private ViewPager viewKuliah;
    private RequestQueue requestQueue;
    public static Context contek;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_kuliah);

        Session namaprodi = new Session(this);
        String prodi = namaprodi.getNamaprodi();

        contek = JadwalKuliah.this;
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Jadwal Perkuliahan UAD");
        getSupportActionBar().setSubtitle(prodi);

        viewKuliah = (ViewPager) findViewById(R.id.viewpager);
        tabKuliah = (TabLayout) findViewById(R.id.tabs);

        requestQueue = Volley.newRequestQueue(this);

        getData();
    }

    public void getData(){

        final Session prodi = new Session(this);
        String kuliah = prodi.getProdi();
        Toast.makeText(contek, kuliah, Toast.LENGTH_SHORT).show();
        StringRequest req = new StringRequest(url + "/simeru/json/prodi.php?idprodi="+kuliah, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                List<String> haristring = new ArrayList<>();
                List<Fragment> hariFragment = new ArrayList<>();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject hasil      = jsonObject.getJSONObject("hasil");
                    Iterator<String> stringIterator = hasil.keys();

                    while(stringIterator.hasNext()){
                        haristring.add(stringIterator.next());
                    }
                    for(String s : haristring){
                        JSONArray jsonHari = hasil.getJSONArray(s);
                        List<SetJadwalKuliah> jadwalKuliahs = new ArrayList<>();
                        for(int i = 0; i <  jsonHari.length(); i++){
                            JSONObject object = jsonHari.getJSONObject(i);
                            SetJadwalKuliah setJadwalKuliah = new SetJadwalKuliah();
                            setJadwalKuliah.kodekuliah     = object.getString("idmatakuliah");
                            setJadwalKuliah.matakuliah     = object.getString("namakul");
                            setJadwalKuliah.dosenkuliah    = object.getString("namadosen");
                            setJadwalKuliah.skskuliah      = object.getString("sks");
                            setJadwalKuliah.kelaskuliah    = object.getString("kelas");
                            setJadwalKuliah.jamkuliah      = object.getString("jam");
                            setJadwalKuliah.ruangkuliah    = object.getString("ruang_idruang");
                            setJadwalKuliah.semesterkuliah = object.getString("semester");
                            jadwalKuliahs.add(setJadwalKuliah);
                        }

                        Fragment fragment = new JadwalKuliahFragment();
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("data",(Serializable) jadwalKuliahs);
                        fragment.setArguments(bundle);
                        hariFragment.add(fragment);
                    }
                    SetAdapter viewpagerAdapter = new SetAdapter(getSupportFragmentManager(),
                            haristring,hariFragment);
                    viewKuliah.setAdapter(viewpagerAdapter);
                    tabKuliah.setupWithViewPager(viewKuliah);

                } catch (Exception e) {
                    Toast.makeText(JadwalKuliah.this, "error "+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(JadwalKuliah.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(req);
    }
    class SetAdapter extends FragmentStatePagerAdapter {

        List<Fragment> listfr = new ArrayList<>();
        List<String> harikul;

        public SetAdapter(FragmentManager fm, List<String> hari, List<Fragment> fragments) {
            super(fm);
            this.harikul = hari;
            this.listfr = fragments;
        }


        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return harikul.get(position);
        }

        @Override
        public int getCount() {
            return listfr.size();
        }

        @Override
        public Fragment getItem(int position) {
            return listfr.get(position);
        }

    }

}
