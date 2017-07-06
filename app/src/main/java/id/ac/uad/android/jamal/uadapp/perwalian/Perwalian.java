package id.ac.uad.android.jamal.uadapp.perwalian;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import id.ac.uad.android.jamal.uadapp.R;

public class Perwalian extends AppCompatActivity implements View.OnClickListener {

    ImageView chat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perwalian);

        chat = (ImageView) findViewById(R.id.img_chat);
        chat.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_chat:
                Intent chat = new Intent(this, ChatDosen.class);
                startActivity(chat);
        }
    }
}
