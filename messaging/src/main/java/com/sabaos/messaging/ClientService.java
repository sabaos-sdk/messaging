package com.sabaos.messaging;

import android.app.IntentService;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.NotificationCompat;
import android.util.Log;


public class ClientService extends IntentService {

    public ClientService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        // This is necessary for service to run, otherwise Android will destroy it after 5 secs.
        NotificationManager notificationManager;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "SABA_CHANNEL";
            String description = "SABA_CHANNEL_DESCRIPTION";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            String channelId = "SABA_CHANNEL_ID";
            NotificationChannel channel = new NotificationChannel(channelId, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), channelId)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("Saba Notification")
                    .setContentText("Initializing")
                    .setAutoCancel(false);
            startForeground(1, builder.build());
        } else {
            String channelId = "SABA_CHANNEL";
            NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), channelId)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("Saba Notification")
                    .setContentText("Initializing")
                    .setAutoCancel(false);
            startForeground(1, builder.build());
        }

        Log.i("serviceStarted", "now");
        SharedPref sharedPref = new SharedPref(getApplicationContext());
        final Bundle bundle = intent.getExtras();
        String type = bundle.getString("type");
        if (type.equalsIgnoreCase("registerApp")) {

            String packageName = bundle.getString("app");
            Log.i("app", packageName);
            if (packageName.equalsIgnoreCase(getApplicationContext().getPackageName())) {

                sharedPref.saveData("SabaToken", bundle.getString("token"));
                Log.i("received token", "from saba app");
                Log.i("token received", sharedPref.loadData("SabaToken"));
                String marketToken = bundle.getString("token");
                Log.i("intentMessage", marketToken);

                //send token to app server
                if (sharedPref.loadData("isRegisteredInAppServer").equalsIgnoreCase("empty")) {

                    sharedPref.saveData("isRegisteredInAppServer", "false");
                }
                Intent appIntent = new Intent();
                appIntent.putExtra("type", "appServerRegister");
                appIntent.putExtra("token", sharedPref.loadData("SabaToken"));
                appIntent.putExtra("app", getApplicationContext().getPackageName());
                appIntent.setComponent(new ComponentName("com.sabaos.mdmcontroller", "com.sabaos.mdmcontroller.MyIntentService"));
                if (Build.VERSION.SDK_INT >= 26) {
                    Log.i("sent market Token", "to app server");
                    getApplicationContext().startForegroundService(appIntent);
                } else getApplicationContext().startService(appIntent);
            }
        }

        if (type.equalsIgnoreCase("appServerRegister")) {
            if (bundle.getString("result").equalsIgnoreCase("success")) {
                Log.i("MDM parent", "received Token successfully");
                sharedPref.saveData("isRegisteredInAppServer", "true");
            }
        }
        if (type.equalsIgnoreCase("push")) {
            Log.i("push", bundle.getString("data"));
            String data = bundle.getString("data");
            handleMessage(data);
        }

    }

    public void handleMessage(String data){


    }

}
