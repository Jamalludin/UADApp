package id.ac.uad.android.jamal.uadapp.simeru;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import id.ac.uad.android.jamal.uadapp.R;

public class Simeru extends AppCompatActivity implements View.OnClickListener{

    private ImageView pengumuman,dosen,jadwal,ruang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simeru);
        pengumuman = (ImageView)findViewById(R.id.img_chat);
        dosen = (ImageView)findViewById(R.id.img_dosen);
        jadwal = (ImageView)findViewById(R.id.img_studi);
        ruang = (ImageView)findViewById(R.id.img_ruang);
        ruang.setOnClickListener(this);
        jadwal.setOnClickListener(this);
        pengumuman.setOnClickListener(this);
        dosen.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_chat:
                Intent pengumuman = new Intent(this, Pengumuman.class);
                this.startActivity(pengumuman);
                break;
            case R.id.img_dosen:
                Intent dosen = new Intent(this, JadwalDosen.class);
                this.startActivity(dosen);
                break;
            case R.id.img_studi:
                Intent studi = new Intent(this, JadwalKuliah.class);
                this.startActivity(studi);
                break;
            case R.id.img_ruang:
                Intent raung = new Intent(this, JadwalRuang.class);
                this.startActivity(raung);

        }
    }
}
