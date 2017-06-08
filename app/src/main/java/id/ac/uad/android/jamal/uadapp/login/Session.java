package id.ac.uad.android.jamal.uadapp.login;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by jamal on 09/11/16.
 */

public class Session {
    private Context context;
   public Session(Context context){
        this.context = context;
    }

    public void buatLogin(String username,String password,String prodi){
        SharedPreferences preferences = this.context.getSharedPreferences("login",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("nim",username);
        editor.putString("prodi",prodi);
        editor.putString("password",password);
        editor.commit();
    }

    public boolean cekLogin(){
        SharedPreferences preferences = this.context.getSharedPreferences("login",Context.MODE_PRIVATE);
        if(preferences.getString("nim",null) != null){
            return  true;
        }else return false;
    }

    public String getProdi(){
        SharedPreferences preferences = this.context.getSharedPreferences("login",Context.MODE_PRIVATE);
        return preferences.getString("prodi",null);
    }

    public void keluar(){
        SharedPreferences preferences = this.context.getSharedPreferences("login",Context.MODE_PRIVATE);
        SharedPreferences.Editor e = preferences.edit();
        e.clear().commit();
    }

    public String getNim(){
        SharedPreferences preferences = this.context.getSharedPreferences("login",Context.MODE_PRIVATE);
        return preferences.getString("nim",null);
    }

    public String getPassword(){
        SharedPreferences preferences = this.context.getSharedPreferences("login",Context.MODE_PRIVATE);
        return preferences.getString("password",null);
    }
}
