package id.ac.uad.android.jamal.uadapp.perwalian;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.iid.FirebaseInstanceId;

import id.ac.uad.android.jamal.uadapp.R;

public class ChatDosen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_dosen);

        String token = FirebaseInstanceId.getInstance().getToken();

        ((TextView)findViewById(R.id.inputmsg)).setText(token);
        Log.d("token", FirebaseInstanceId.getInstance().getToken());
    }
}
