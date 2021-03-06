package id.ac.uad.android.jamal.uadapp.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import id.ac.uad.android.jamal.uadapp.MainActivity;
import id.ac.uad.android.jamal.uadapp.R;
import id.ac.uad.android.jamal.uadapp.json.JsonReq;

public class Login extends AppCompatActivity {
    protected EditText nim, pass;
    protected Button masuk;
    protected ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        nim = (EditText) findViewById(R.id.input_nim);
        pass = (EditText) findViewById(R.id.input_password);
        masuk = (Button) findViewById(R.id.btn_signup);

        masuk = (Button)findViewById(R.id.btn_signup);
        masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = nim.getText().toString();
                String passw = pass.getText().toString();

                new LoginApp(username,passw).execute();
            }
        });

        if(new Session(getApplicationContext()).cekLogin() == true){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }
    }

    class LoginApp extends AsyncTask<Void,Void,String> {

        private String pass,username;

        public LoginApp(String username, String pass){
            this.pass = pass;
            this.username = username;
        }

        @Override
        protected String doInBackground(Void... voids) {

            String token = FirebaseInstanceId.getInstance().getToken();
            JsonReq json = new JsonReq();
            String hasil = json.login(this.username, this.pass, token);
            return hasil;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            pDialog.dismiss();

            try{
                JSONObject object = new JSONObject(s);
                String hasil = object.getString("hasil");
                if(hasil.equals("sukses")){
                    JSONObject jsonArray = object.getJSONObject("data");
                    new Session(getApplicationContext()).buatLogin(this.username,
                            this.pass,
                            jsonArray.getString("idprogram_studi"),
                            jsonArray.getString("dosenwali"),
                            jsonArray.getString("dosen_niynipnidn"),
                            jsonArray.getString("namaprodi"),
                            jsonArray.getString("nama"));

                    Intent masuk = new Intent(Login.this, MainActivity.class);
                    startActivity(masuk);

                    Toast.makeText(Login.this, "Selamat Datang ", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(Login.this, "Invalid NIM or Password", Toast.LENGTH_SHORT).show();
                }
            }catch (Exception e){
                Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Login.this);
            pDialog.setMessage("Masuk Aplikasi...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
    }
}
