package velsol.com.firebaseexample;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFbMsg extends FirebaseMessagingService
{
    public MyFbMsg()
    {
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage)
    {
        super.onMessageReceived(remoteMessage);
        messages(remoteMessage.getNotification().getBody());
    }


    public void messages(String remoteMessage)
    {
        Intent ss=new Intent(this,MainActivity.class);
        ss.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pi=PendingIntent.getActivity(this,0,ss,PendingIntent.FLAG_ONE_SHOT);
        Uri duri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder m=new NotificationCompat.Builder(this);
        m.setContentTitle("hello");
        m.setContentText(remoteMessage);
        m.setAutoCancel(true);

        NotificationManager nm=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(0,m.build());
    }
}
