package id.ac.uad.android.jamal.uadapp.simeru;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import id.ac.uad.android.jamal.uadapp.R;
import id.ac.uad.android.jamal.uadapp.simeru.fragmentruang.RuangJumat;
import id.ac.uad.android.jamal.uadapp.simeru.fragmentruang.RuangKamis;
import id.ac.uad.android.jamal.uadapp.simeru.fragmentruang.RuangRabu;
import id.ac.uad.android.jamal.uadapp.simeru.fragmentruang.RuangSabtu;
import id.ac.uad.android.jamal.uadapp.simeru.fragmentruang.RuangSelasa;
import id.ac.uad.android.jamal.uadapp.simeru.fragmentruang.RuangSenin;

public class JadwalRuangannya extends AppCompatActivity {

    private TabLayout ruanglayout;
    private Toolbar toolbarruang;
    private ViewPager viewPagerruang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_ruangannya);

        toolbarruang = (Toolbar) findViewById(R.id.toolbarruang);
        setSupportActionBar(toolbarruang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPagerruang = (ViewPager)findViewById(R.id.ruangpager);
        aturViewPager(viewPagerruang);

        ruanglayout = (TabLayout)findViewById(R.id.tabjadwalruang);
        ruanglayout.setupWithViewPager(viewPagerruang);
    }

    private void aturViewPager(ViewPager viewPager){
        AturAdapter atur = new AturAdapter(getSupportFragmentManager());
        atur.AddAdapter(new RuangSenin(),"Senin");
        atur.AddAdapter(new RuangSelasa(),"Selasa");
        atur.AddAdapter(new RuangRabu(),"Rabu");
        atur.AddAdapter(new RuangKamis(),"Kamis");
        atur.AddAdapter(new RuangJumat(),"Jumat");
        atur.AddAdapter(new RuangSabtu(),"Sabtu");
        viewPager.setAdapter(atur);
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
