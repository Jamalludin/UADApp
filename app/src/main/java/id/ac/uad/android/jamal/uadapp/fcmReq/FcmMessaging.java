package id.ac.uad.android.jamal.uadapp.fcmReq;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import id.ac.uad.android.jamal.uadapp.R;
import id.ac.uad.android.jamal.uadapp.perwalian.BeritaDosenWali;
import id.ac.uad.android.jamal.uadapp.perwalian.ChatDosen;
import id.ac.uad.android.jamal.uadapp.simeru.Pengumuman;

/**
 * Created by jamal on 27/07/17.
 */

public class FcmMessaging extends FirebaseMessagingService {

    private static final String TAG = "FirebaseMsgService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.d(TAG, "Form : "+remoteMessage.getFrom());

        if (remoteMessage.getData().size()>0){
            Log.d(TAG, "Message data payload: " + remoteMessage.getFrom());
        }
        if (remoteMessage.getNotification() != null){
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }
        String pesan = remoteMessage.getData().get("message");
        String judul = remoteMessage.getData().get("title");
        String jenis = remoteMessage.getData().get("jenis");
        KirimPesan(pesan,judul,jenis);
    }

    private void KirimPesan(String pesan, String judul,String jenis){
        Intent intent;
        if(jenis.equals("perwalian")){
            intent = new Intent(this, BeritaDosenWali.class);
        }else{
            intent = new Intent(this, Pengumuman.class);
        }
        intent.putExtra("pesan",pesan);
        intent.putExtra("title",judul);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle(judul);
        builder.setContentText(pesan)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 ,builder.build());
    }

    @Override
    public void onMessageSent(String s) {
        super.onMessageSent(s);
        Log.d("Pesan Terkirim","Pesan Berhasil Terkirim");
    }

    @Override
    public void onSendError(String s, Exception e) {
        super.onSendError(s, e);
        Log.d("Gagal Terkirim",e.getMessage());
    }
}
