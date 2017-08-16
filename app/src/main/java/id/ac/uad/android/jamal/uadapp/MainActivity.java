package id.ac.uad.android.jamal.uadapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import id.ac.uad.android.jamal.uadapp.login.Login;
import id.ac.uad.android.jamal.uadapp.login.Session;
import id.ac.uad.android.jamal.uadapp.perwalian.Perwalian;
import id.ac.uad.android.jamal.uadapp.simeru.Simeru;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView perwalian,simeru,tentang,keluar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        perwalian = (ImageView) findViewById(R.id.img_perwalian);
        simeru =(ImageView)findViewById(R.id.img_simeru);
        tentang =(ImageView)findViewById(R.id.img_tentang);
        keluar = (ImageView)findViewById(R.id.img_keluar);
        perwalian.setOnClickListener(this);
        simeru.setOnClickListener(this);
        tentang.setOnClickListener(this);
        keluar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_perwalian:
                Intent perwalian = new Intent(this, Perwalian.class);
                startActivity(perwalian);
                break;
            case R.id.img_simeru:
                Intent simeru = new Intent(this, Simeru.class);
                startActivity(simeru);
                break;
            case R.id.img_tentang:
                Intent tentang = new Intent(this, Tentang.class);
                startActivity(tentang);
                break;
            case R.id.img_keluar:
                Session keluar = new Session(this);
                keluar.keluar();
                Intent back = new Intent(this, Login.class);
                startActivity(back);
                finish();
                break;
        }
    }
}
