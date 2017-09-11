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

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import id.ac.uad.android.jamal.uadapp.pojo.SetJadwalDosen;
import id.ac.uad.android.jamal.uadapp.R;
import id.ac.uad.android.jamal.uadapp.simeru.callbacksimeru.DosenCallBack;
import id.ac.uad.android.jamal.uadapp.simeru.fragmentdosen.JadwalDosenNgajar;

public class JadwalNgajarDosen extends AppCompatActivity {

    private TabLayout dosenlayout;
    private Toolbar toolbardosen;
    private ViewPager viewPagerdosen;
    private RequestQueue requestQueue;
    private String niy = null;
    private String nama = null;
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_ngajar_dosen);

        context = JadwalNgajarDosen.this;
        nama = getIntent().getStringExtra("nama");

        toolbardosen = (Toolbar) findViewById(R.id.toolbardosen);
        setSupportActionBar(toolbardosen);
        getSupportActionBar().setTitle("Jadwal Dosen UAD");
        getSupportActionBar().setSubtitle(nama);


        viewPagerdosen = (ViewPager)findViewById(R.id.dosenpager);
        dosenlayout = (TabLayout)findViewById(R.id.tabjadwaldosen);

        getData();
    }

    public void getData(){

        niy = getIntent().getStringExtra("niy");
        DosenCallBack dc = new DosenCallBack(context);

        dc.DosenCallBack(niy, new DosenCallBack.DsnCallback() {
            @Override
            public void Result(String result) {
                List<String> haristring = new ArrayList<>();
                List<Fragment> hariFragment = new ArrayList<>();

                try {

                    JSONObject jsonObject = new JSONObject(result);
                    JSONObject hasil = jsonObject.getJSONObject("hasil");
                    Iterator<String> stringIterator = hasil.keys();

                    while (stringIterator.hasNext()) {
                        haristring.add(stringIterator.next());
                    }

                    for (String s : haristring) {

                        JSONArray jsonHari = hasil.getJSONArray(s);
                        List<SetJadwalDosen> jadwalDosens = new ArrayList<>();

                        for (int i = 0; i < jsonHari.length(); i++) {

                            JSONObject object = jsonHari.getJSONObject(i);
                            SetJadwalDosen jadwalDosen = new SetJadwalDosen();
                            jadwalDosen.namakul = object.getString("namakul");
                            jadwalDosen.kelas = object.getString("kelas");
                            jadwalDosen.sks = object.getString("sks");
                            jadwalDosen.jam = object.getString("jam");
                            jadwalDosen.ruang = object.getString("ruang_idruang");

                            jadwalDosens.add(jadwalDosen);
                        }

                        Fragment fragment = new JadwalDosenNgajar();
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("data", (Serializable) jadwalDosens);
                        fragment.setArguments(bundle);
                        hariFragment.add(fragment);
                    }

                    AturAdapter viewpagerAdapter = new AturAdapter(getSupportFragmentManager(),
                            haristring, hariFragment);
                    viewPagerdosen.setAdapter(viewpagerAdapter);
                    dosenlayout.setupWithViewPager(viewPagerdosen);

                } catch (Exception e) {

                }
            }
        });
    }

    class AturAdapter extends FragmentStatePagerAdapter{

        private List<Fragment> listfr = new ArrayList<>();
        private List<String> hari;

        public AturAdapter(FragmentManager fm, List<String> hari, List<Fragment> fragments) {
            super(fm);
            this.hari = hari;
            this.listfr = fragments;

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

            return listfr.get(position);
        }

        @Override
        public int getCount() {
            return listfr.size();
        }
    }
}
