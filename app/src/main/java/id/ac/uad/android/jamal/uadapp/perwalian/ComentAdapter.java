package id.ac.uad.android.jamal.uadapp.perwalian;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import id.ac.uad.android.jamal.uadapp.R;
import id.ac.uad.android.jamal.uadapp.login.Session;
import id.ac.uad.android.jamal.uadapp.pojo.ComentPojo;

/**
 * Created by jamal on 07/10/17.
 */

public class ComentAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private List<ComentPojo> comentPojos;
    private Context context;

    public ComentAdapter(Context context, List<ComentPojo> comentPojos){

        this.context = context;
        this.comentPojos = comentPojos;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return comentPojos.size();
    }

    @Override
    public Object getItem(int position) {
        return comentPojos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = layoutInflater.inflate(R.layout.layout_koment, parent, false);

        TextView komentKiri = (TextView)convertView.findViewById(R.id.comentkiri);
        TextView komentKanan = (TextView)convertView.findViewById(R.id.comentkanan);
        TextView usernameKanan = (TextView)convertView.findViewById(R.id.usernamekanan);
        TextView usernameKiri = (TextView)convertView.findViewById(R.id.username);

        RelativeLayout rKiri = (RelativeLayout)convertView.findViewById(R.id.chatkiri);
        RelativeLayout rKanan = (RelativeLayout)convertView.findViewById(R.id.chatKanan);

        String idUser = new Session(context).getNim();
        ComentPojo comentPojo = (ComentPojo)getItem(position);

        if (idUser.equals(comentPojo.id_user)){

            rKiri.setVisibility(View.INVISIBLE);
            usernameKanan.setText(comentPojo.username);
            komentKanan.setText(comentPojo.coment);
        }else {

            rKanan.setVisibility(View.INVISIBLE);
            usernameKiri.setText(comentPojo.username);
            komentKiri.setText(comentPojo.coment);
        }

        return convertView;

    }
}
