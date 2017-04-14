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
import id.ac.uad.android.jamal.uadapp.simeru.fragmentdosen.DosenJumat;
import id.ac.uad.android.jamal.uadapp.simeru.fragmentdosen.DosenKamis;
import id.ac.uad.android.jamal.uadapp.simeru.fragmentdosen.DosenRabu;
import id.ac.uad.android.jamal.uadapp.simeru.fragmentdosen.DosenSabtu;
import id.ac.uad.android.jamal.uadapp.simeru.fragmentdosen.DosenSelasa;
import id.ac.uad.android.jamal.uadapp.simeru.fragmentdosen.DosenSenin;

public class JadwalNgajarDosen extends AppCompatActivity {

    private TabLayout dosenlayout;
    private Toolbar toolbardosen;
    private ViewPager viewPagerdosen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_ngajar_dosen);

        toolbardosen = (Toolbar) findViewById(R.id.toolbardosen);
        setSupportActionBar(toolbardosen);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPagerdosen = (ViewPager)findViewById(R.id.dosenpager);
        aturViewPager(viewPagerdosen);

        dosenlayout = (TabLayout)findViewById(R.id.tabjadwaldosen);
        dosenlayout.setupWithViewPager(viewPagerdosen);
    }

    private void aturViewPager(ViewPager viewPager){
        AturAdapter setAdapter = new AturAdapter(getSupportFragmentManager());
        setAdapter.AddAdapter(new DosenSenin(),"Senin");
        setAdapter.AddAdapter(new DosenSelasa(),"Selasa");
        setAdapter.AddAdapter(new DosenRabu(),"Rabu");
        setAdapter.AddAdapter(new DosenKamis(),"Kamis");
        setAdapter.AddAdapter(new DosenJumat(),"Jumat");
        setAdapter.AddAdapter(new DosenSabtu(),"Sabtu");
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
            return listfr.get(position);
        }

        @Override
        public int getCount() {
            return listfr.size();
        }
    }
}
