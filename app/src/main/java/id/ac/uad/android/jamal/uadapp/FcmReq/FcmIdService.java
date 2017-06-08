package id.ac.uad.android.jamal.uadapp.FcmReq;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by jamal on 08/06/17.
 */

public class FcmIdService extends FirebaseInstanceIdService {

    private static final String TAG = "FcmIDService";

    @Override
    public void onTokenRefresh() {

        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);
        kirimkeserver(refreshedToken);
    }

    private void kirimkeserver(String token){

    }

}
