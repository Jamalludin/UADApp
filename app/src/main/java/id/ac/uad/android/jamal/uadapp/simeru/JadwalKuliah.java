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

import java.util.ArrayList;
import java.util.List;

import id.ac.uad.android.jamal.uadapp.R;
import id.ac.uad.android.jamal.uadapp.login.Session;
import id.ac.uad.android.jamal.uadapp.simeru.fragmentkuliah.JumatFragment;
import id.ac.uad.android.jamal.uadapp.simeru.fragmentkuliah.KamisFragment;
import id.ac.uad.android.jamal.uadapp.simeru.fragmentkuliah.RabuFragment;
import id.ac.uad.android.jamal.uadapp.simeru.fragmentkuliah.SabtuFragment;
import id.ac.uad.android.jamal.uadapp.simeru.fragmentkuliah.SelasaFragment;
import id.ac.uad.android.jamal.uadapp.simeru.fragmentkuliah.SeninFragment;

public class JadwalKuliah extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_kuliah);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        BaruAdapter adapter = new BaruAdapter(getSupportFragmentManager());
        adapter.tambahFragment(new SeninFragment(), "Senin");
        adapter.tambahFragment(new SelasaFragment(), "Selasa");
        adapter.tambahFragment(new RabuFragment(), "Rabu");
        adapter.tambahFragment(new KamisFragment(), "Kamis");
        adapter.tambahFragment(new JumatFragment(), "Jumat");
        adapter.tambahFragment(new SabtuFragment(), "Sabtu");
        viewPager.setAdapter(adapter);
    }

    class BaruAdapter extends FragmentStatePagerAdapter {

        private List<Fragment> listfr = new ArrayList<>();
        private List<String> judul = new ArrayList<>();

        public BaruAdapter(FragmentManager manager) {
            super(manager);
        }

        public void tambahFragment(Fragment fragment, String judulnya) {
            listfr.add(fragment);
            judul.add(judulnya);
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return judul.get(position);
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
