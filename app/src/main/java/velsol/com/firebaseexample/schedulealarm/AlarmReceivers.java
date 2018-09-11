package velsol.com.firebaseexample.schedulealarm;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

/**
 * Created by Velsol 170016 on 9/5/2018.
 */

public class AlarmReceivers extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        NotificationCompat.Builder b = new NotificationCompat.Builder(context);
        b.setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setContentTitle("title")
                .setContentText("message")
                .setContentInfo("INFO");

        NotificationManager nm = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(1, b.build());
    }
}
