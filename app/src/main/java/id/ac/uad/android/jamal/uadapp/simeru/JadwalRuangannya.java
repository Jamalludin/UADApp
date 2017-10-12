package id.ac.uad.android.jamal.uadapp.simeru;

import android.content.Context;
import android.content.Intent;
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

import id.ac.uad.android.jamal.uadapp.R;
import id.ac.uad.android.jamal.uadapp.pojo.SetRuangKuliah;
import id.ac.uad.android.jamal.uadapp.simeru.callbacksimeru.RuangCallBack;
import id.ac.uad.android.jamal.uadapp.simeru.fragmentruang.JadwalRuangFragment;

public class JadwalRuangannya extends AppCompatActivity {

    private TabLayout ruanglayout;
    private Toolbar toolbarruang;
    private ViewPager viewPagerruang;
    private String ruang = null;
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_ruangannya);

        context = JadwalRuangannya.this;

        toolbarruang = (Toolbar) findViewById(R.id.truang);
        setSupportActionBar(toolbarruang);

        viewPagerruang = (ViewPager)findViewById(R.id.ruangpager);
        ruanglayout = (TabLayout)findViewById(R.id.tabruang);

        ruang = getIntent().getStringExtra("idruang");
        getSupportActionBar().setTitle("Ruang : "+ruang);

        getData();
    }

    public void getData() {

        RuangCallBack rc = new RuangCallBack(context);

        rc.RuangCallBack(ruang, new RuangCallBack.Callback() {
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
                        JSONArray jsonArrayHari = hasil.getJSONArray(s);
                        List<SetRuangKuliah> jadwaSetRuangKuliahs = new ArrayList<>();
                        for (int i = 0; i < jsonArrayHari.length(); i++) {
                            JSONObject object = jsonArrayHari.getJSONObject(i);
                            SetRuangKuliah setJadwalKuliah = new SetRuangKuliah();
                            setJadwalKuliah.kodekuliru = object.getString("idmatakuliah");
                            setJadwalKuliah.matakuliahru = object.getString("namakul");
                            setJadwalKuliah.dosenkuliahru = object.getString("namadosen");
                            setJadwalKuliah.skskuliahru = object.getString("sks");
                            setJadwalKuliah.kelaskuliahru = object.getString("kelas");
                            setJadwalKuliah.jamkuliahru = object.getString("jam");
                            setJadwalKuliah.prodi = object.getString("namaprodi");
                            setJadwalKuliah.semesterkuliahru = object.getString("semester");
                            jadwaSetRuangKuliahs.add(setJadwalKuliah);
                        }

                        Fragment fragment = new JadwalRuangFragment();
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("data", (Serializable) jadwaSetRuangKuliahs);
                        fragment.setArguments(bundle);
                        hariFragment.add(fragment);
                    }

                    AturAdapter viewpagerAdapter = new AturAdapter(getSupportFragmentManager(),
                            haristring, hariFragment);
                    viewPagerruang.setAdapter(viewpagerAdapter);
                    ruanglayout.setupWithViewPager(viewPagerruang);

                } catch (Exception e) {

                }
            }
        });

    }

    class AturAdapter extends FragmentStatePagerAdapter{

        private List<Fragment> listfr = new ArrayList<>();
        private List<String> hari = new ArrayList<>();

        public AturAdapter(FragmentManager fm, List<String> hari, List<Fragment> fragments) {
            super(fm);
            this.hari = hari;
            this.listfr = fragments;
        }


        @Override
        public int getCount() {
            return listfr.size();
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
    }
}
