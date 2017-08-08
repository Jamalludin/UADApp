package id.ac.uad.android.jamal.uadapp.simeru;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import id.ac.uad.android.jamal.uadapp.R;

public class Pengumuman extends AppCompatActivity {

    private RecyclerView Rview;
    String JsonURL = "http://perwalian.esy.es/api/berita_api.php";
    RequestQueue reqQueue;
    List<IsiData> datanya;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengumuman);

        reqQueue = Volley.newRequestQueue(this);
        Rview = (RecyclerView) findViewById(R.id.pengumancard);
        Rview.setHasFixedSize(true);
        RecyclerView.LayoutManager mlayout = new LinearLayoutManager(this);
        Rview.setLayoutManager(mlayout);

        getData();


    }
    public void getData(){

        JsonObjectRequest arrReq = new JsonObjectRequest(JsonURL,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        datanya = new ArrayList<>();
                        try {
                            JSONArray jsonArray = response.getJSONArray("berita");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                IsiData data = new IsiData();
                                data.setTanggal(jsonObject.getString("time"));
                                data.setPengumuman(jsonObject.getString("isi_berita"));
                                datanya.add(data);
                            }

                        } catch (JSONException e) {
                            Toast.makeText(Pengumuman.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        Adapter2 adapter = new Adapter2(datanya);
                        Rview.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Pengumuman.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        reqQueue.add(arrReq);
    }

    public String testKon(){
        getData();
        return datanya.get(0).getTanggal();
    }

    class Adapter2 extends RecyclerView.Adapter<Adapter2.ViewAdapter> {
        List<IsiData> isiDatanya;

        class ViewAdapter extends RecyclerView.ViewHolder {
            TextView tanggal, pengumuman;

            ViewAdapter(View item) {
                super(item);

                tanggal = (TextView) item.findViewById(R.id.tanggalnya);
                pengumuman = (TextView) item.findViewById(R.id.pengumumannya);
                item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(Pengumuman.this, "Pengumuman", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }

        Adapter2(List<IsiData> data) {
            isiDatanya = data;
        }

        @Override
        public int getItemCount() {
            return isiDatanya.size();
        }

        @Override
        public ViewAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_pengumuman,
                    parent, false);
            ViewAdapter viewAdapter = new ViewAdapter(v);
            return viewAdapter;
        }

        @Override
        public void onBindViewHolder(ViewAdapter holder, int position) {
            holder.tanggal.setText(isiDatanya.get(position).getTanggal());
            holder.pengumuman.setText(isiDatanya.get(position).getPengumuman());
        }
    }

    class IsiData {
        String tanggal, pengumuman;

        public IsiData() {
        }

        public IsiData(String tanggal, String pengumuman) {
            this.tanggal = tanggal;
            this.pengumuman = pengumuman;
        }

        public String getTanggal() {
            return tanggal;
        }

        public void setTanggal(String tanggal) {
            this.tanggal = tanggal;
        }

        public String getPengumuman() {
            return pengumuman;
        }

        public void setPengumuman(String pengumuman) {
            this.pengumuman = pengumuman;
        }
    }
}
