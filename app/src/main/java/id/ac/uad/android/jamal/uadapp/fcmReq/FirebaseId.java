package id.ac.uad.android.jamal.uadapp.fcmReq;

import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by jamal on 27/07/17.
 */

public class FirebaseId extends FirebaseInstanceIdService {

    private static final String TAG = "FirebaseId";

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        SharedPreferences sharedPreferences = getSharedPreferences("akun",MODE_APPEND);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        String token = FirebaseInstanceId.getInstance().getToken();
        editor.putString("token",token);
        editor.commit();
        Log.d(TAG, "Refreshed Token: "+token);
    }
}
