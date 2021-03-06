package id.ac.uad.android.jamal.uadapp.perwalian;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import id.ac.uad.android.jamal.uadapp.R;
import id.ac.uad.android.jamal.uadapp.login.Session;
import id.ac.uad.android.jamal.uadapp.perwalian.callbackperwalian.TranskipCallBack;
import id.ac.uad.android.jamal.uadapp.pojo.SetTranskip;

import static id.ac.uad.android.jamal.uadapp.pojo.Url.url;

public class TranskipNilai extends AppCompatActivity {

    private ListView listView;
    private List<SetTranskip> transkips;
    private float ipk;
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transkip_nilai);

        listView = (ListView) findViewById(R.id.listranskip);
        context = TranskipNilai.this;
        transkips = new ArrayList<>();

        Session nim = new Session(this);
        String getnim = nim.getNim();

        Session nama = new Session(this);
        String getnama = nama.getNama();

        getSupportActionBar().setTitle("Transkip Nilai");
        getSupportActionBar().setSubtitle(getnama + " " + "(" + getnim + ")");

        getData(getnim);
    }

    public void getData(String nim) {

        TranskipCallBack tc = new TranskipCallBack(context);
        tc.TranskipCallBack(nim, new TranskipCallBack.TransCallBack() {
            @Override
            public void Result(String result) {

                try {

                    int totalNilai = 0;
                    int totalSks = 0;
                    float jumlahnilai;

                    JSONArray jsonArray = new JSONArray(result);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject obj = jsonArray.getJSONObject(i);
                        SetTranskip transkip = new SetTranskip();
                        transkip.kodematkul = obj.getString("matakuliah_idmatakuliah");
                        transkip.namakul = obj.getString("namakul");
                        transkip.sksmatkul = obj.getString("sks");
                        transkip.nilaikul = obj.getString("nilai");

                        if (transkip.nilaikul.equals("A")) {
                            jumlahnilai = (float) (Integer.parseInt(transkip.sksmatkul) * 4.00);
                        } else if (transkip.nilaikul.equals("A-")) {
                            jumlahnilai = (float) (Integer.parseInt(transkip.sksmatkul) * 3.67);
                        } else if (transkip.nilaikul.equals("B+")) {
                            jumlahnilai = (float) (Integer.parseInt(transkip.sksmatkul) * 3.33);
                        } else if (transkip.nilaikul.equals("B")) {
                            jumlahnilai = (float) (Integer.parseInt(transkip.sksmatkul) * 3.00);
                        } else if (transkip.nilaikul.equals("B-")) {
                            jumlahnilai = (float) (Integer.parseInt(transkip.sksmatkul) * 2.67);
                        } else if (transkip.nilaikul.equals("C+")) {
                            jumlahnilai = (float) (Integer.parseInt(transkip.sksmatkul) * 2.33);
                        } else if (transkip.nilaikul.equals("C")) {
                            jumlahnilai = (float) (Integer.parseInt(transkip.sksmatkul) * 2.00);
                        } else if (transkip.nilaikul.equals("C-")) {
                            jumlahnilai = (float) (Integer.parseInt(transkip.sksmatkul) * 1.67);
                        } else if (transkip.nilaikul.equals("D+")) {
                            jumlahnilai = (float) (Integer.parseInt(transkip.sksmatkul) * 1.33);
                        } else if (transkip.nilaikul.equals("D")) {
                            jumlahnilai = (float) (Integer.parseInt(transkip.sksmatkul) * 1.00);
                        } else {
                            jumlahnilai = (float) (Integer.parseInt(transkip.sksmatkul) * 0.00);
                        }

                        totalSks += Integer.parseInt(transkip.sksmatkul);
                        totalNilai += jumlahnilai;

                        transkips.add(transkip);
                    }

                    ipk = ((float) totalNilai) / ((float) totalSks);
                    ipk = (float) Math.floor(ipk * 100) / 100;
                    ((TextView) findViewById(R.id.ipknya)).setText(String.valueOf(ipk));

                    AdapterTranskip adapterTranskip = new AdapterTranskip(transkips, TranskipNilai.this);
                    listView.setAdapter(adapterTranskip);

                } catch (Exception e) {
                    Toast.makeText(TranskipNilai.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    class AdapterTranskip extends BaseAdapter {

        List<SetTranskip> transkips;
        LayoutInflater layoutInflater;

        public AdapterTranskip(List<SetTranskip> transkipNilais, Context context) {
            this.transkips = transkipNilais;
            layoutInflater = LayoutInflater.from(context);

        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return transkips.get(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = layoutInflater.inflate(R.layout.list_transkip, parent, false);
            SetTranskip transkip = (SetTranskip) getItem(position);
            ((TextView) convertView.findViewById(R.id.matakuliah)).setText(transkip.namakul);
            ((TextView) convertView.findViewById(R.id.kode)).setText(transkip.kodematkul);
            ((TextView) convertView.findViewById(R.id.sks)).setText(transkip.sksmatkul);
            ((TextView) convertView.findViewById(R.id.nilai)).setText(transkip.nilaikul);
            return convertView;
        }

        @Override
        public int getCount() {
            return transkips.size();
        }
    }
}
