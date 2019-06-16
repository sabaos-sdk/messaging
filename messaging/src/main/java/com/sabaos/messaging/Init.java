package com.sabaos.messaging;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

public class Init {

    public void startSaba(Context context) {

        SharedPref sharedPref = new SharedPref(context);
        if (sharedPref.loadData("SabaToken").equalsIgnoreCase("empty")) {
            Toast.makeText(context, "market is running for the first time", Toast.LENGTH_LONG).show();
            Log.i("market is Running", "for the first time");
            Intent intent = new Intent();
            intent.putExtra("type", "registerApp");
            intent.putExtra("app", context.getPackageName());
            intent.setComponent(new ComponentName("com.sabaos.saba", "com.sabaos.saba.service.MyIntentService"));
            if (Build.VERSION.SDK_INT >= 26) {
                context.startForegroundService(intent);
            } else {
                context.startService(intent);
            }
        } else {

            Log.i("already have", new SharedPref(context).loadData("SabaToken"));
        }
        if (sharedPref.loadData("isRegisteredInAppServer").equalsIgnoreCase("false")) {
            Log.i("resending token", "to app server");
            Intent appIntent = new Intent();
            appIntent.putExtra("type", "appServerRegister");
            appIntent.putExtra("token", sharedPref.loadData("SabaToken"));
            appIntent.putExtra("app", context.getPackageName());
            appIntent.setComponent(new ComponentName("com.sabaos.mdmcontroller", "com.sabaos.mdmcontroller.MyIntentService"));
            if (Build.VERSION.SDK_INT >= 26) {
                Log.i("sent Token", " for " + context.getPackageName() + " to app server");
                context.startForegroundService(appIntent);
            } else context.startService(appIntent);
        }
    }
}
