package id.ac.uad.android.jamal.uadapp.fcmReq;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import id.ac.uad.android.jamal.uadapp.R;
import id.ac.uad.android.jamal.uadapp.perwalian.BeritaDosenWali;
import id.ac.uad.android.jamal.uadapp.perwalian.ChatDosen;
import id.ac.uad.android.jamal.uadapp.perwalian.TopikPerwalian;
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
        String idtopik = remoteMessage.getData().get("idtopik");

        if (jenis.equals("comment")){
            String id = remoteMessage.getData().get("idtopik");
            if (ChatDosen.isCommentActive == true && ChatDosen.idCommentActive.equals(id)){
                Intent intent = new Intent("Comment");
                String comment = remoteMessage.getData().get("message");
                String username = remoteMessage.getData().get("username");
                intent.putExtra("comment",comment);
                intent.putExtra("username", username);
                LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this);
                localBroadcastManager.sendBroadcast(intent);
            }else{
                KirimPesan(pesan,judul,jenis,idtopik);
            }

        }else {
            KirimPesan(pesan,judul,jenis,idtopik);
        }
    }

    private void KirimPesan(String pesan, String judul,String jenis,String idtopik){
        Intent intent;
        if(jenis.equals("perwalian")){
            intent = new Intent(this, BeritaDosenWali.class);
        }else if (jenis.equals("chat")){
            intent = new Intent(this, TopikPerwalian.class);
        } else if (jenis.equals("comment")){
            intent = new Intent(this, ChatDosen.class);
            intent.putExtra("idtopik",idtopik);
        } else{
            intent = new Intent(this, Pengumuman.class);
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.ic_launcher);
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
