package id.ac.uad.android.jamal.uadapp.simeru.fragmentkuliah;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import id.ac.uad.android.jamal.uadapp.simeru.Pengumuman;

/**
 * Created by jamal on 15/03/17.
 */

public class SeninFragment extends Fragment {

    public SeninFragment (){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_senin, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String url = "";
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Mohon Tunggu Sebentar ...");
        progressDialog.setMessage("Sebentar ...");

        RecyclerView.LayoutManager setmanager = new LinearLayoutManager(getActivity().getApplicationContext());
        final RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.cardsenin);
        recyclerView.setLayoutManager(setmanager);

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

        JsonObjectRequest arrayReq = new JsonObjectRequest(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                List<Datanya> dataya = new ArrayList<>();
                try {
                    JSONArray jsonArray = response.getJSONArray("kuliah");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Datanya data = new Datanya();
                        dataya.add(data);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Adapternya adapter = new Adapternya(dataya);
                recyclerView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(arrayReq);
    }

    class Adapternya extends RecyclerView.Adapter<Adapternya.ViewAdapter>{
        List<Datanya> isiDatanya;

        class ViewAdapter extends RecyclerView.ViewHolder{

            ViewAdapter(View item) {
                super(item);

            }
        }
        Adapternya(List<Datanya> data){
            isiDatanya = data;
        }

        @Override
        public int getItemCount() {
            return 0;
        }

        @Override
        public ViewAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(ViewAdapter holder, int position) {

        }
    }

    class Datanya {

    }
}
