package com.sabaos.messaging.messaging.messages.provider;

import android.app.Activity;

import com.sabaos.messaging.messaging.Utils;
import com.sabaos.messaging.messaging.api.ApiException;
import com.sabaos.messaging.messaging.api.Callback;
import com.sabaos.messaging.client.ApiClient;
import com.sabaos.messaging.client.api.ApplicationApi;
import com.sabaos.messaging.client.model.Application;

import java.util.Collections;
import java.util.List;

public class ApplicationHolder {
    private List<Application> state;
    private Runnable onUpdate;
    private Activity activity;
    private ApiClient client;

    public ApplicationHolder(Activity activity, ApiClient client) {
        this.activity = activity;
        this.client = client;
    }

    public void requestIfMissing() {
        if (state == null) {
            request();
        }
    }

    public void request() {
        client.createService(ApplicationApi.class)
                .getApps()
                .enqueue(Callback.callInUI(activity, this::onReceiveApps, this::onFailedApps));
    }

    private void onReceiveApps(List<Application> apps) {
        state = apps;
        onUpdate.run();
    }

    private void onFailedApps(ApiException e) {
        Utils.showSnackBar(activity, "Could not request applications, see logs.");
    }

    public List<Application> get() {
        return state == null ? Collections.emptyList() : state;
    }

    public void onUpdate(Runnable runnable) {
        this.onUpdate = runnable;
    }
}
