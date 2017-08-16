package id.ac.uad.android.jamal.uadapp.perwalian;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import id.ac.uad.android.jamal.uadapp.R;
import id.ac.uad.android.jamal.uadapp.login.Session;

public class Perwalian extends AppCompatActivity implements View.OnClickListener {

    ImageView chat,mhs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perwalian);

        Session namamhs = new Session(this);
        String nama = namamhs.getNama();

        Session prodi = new Session(this);
        String prodimhs = prodi.getNamaprodi();

        getSupportActionBar().setTitle("Perwalian Mahasiswa");
        getSupportActionBar().setSubtitle(nama+" "+prodimhs);

        chat = (ImageView) findViewById(R.id.img_chat);
        chat.setOnClickListener(this);

        mhs = (ImageView) findViewById(R.id.img_mhs);
        mhs.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_chat:
                Intent chat = new Intent(this, ChatDosen.class);
                startActivity(chat);
                break;
            case R.id.img_mhs:
                Intent mhs = new Intent(this, TranskipNilai.class);
                startActivity(mhs);
                break;
        }
    }
}
