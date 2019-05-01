package com.sabaos.messaging.messaging.api;

import com.sabaos.messaging.messaging.SSLSettings;
import com.sabaos.messaging.messaging.Settings;
import com.sabaos.messaging.client.ApiClient;
import com.sabaos.messaging.client.api.UserApi;
import com.sabaos.messaging.client.api.VersionApi;
import com.sabaos.messaging.client.auth.ApiKeyAuth;
import com.sabaos.messaging.client.auth.HttpBasicAuth;

public class ClientFactory {
    public static ApiClient unauthorized(
            String baseUrl, SSLSettings sslSettings) {
        return defaultClient(new String[0], baseUrl + "/", sslSettings);
    }

    public static ApiClient basicAuth(
            String baseUrl, SSLSettings sslSettings, String username, String password) {
        ApiClient client = defaultClient(new String[] {"basicAuth"}, baseUrl + "/", sslSettings);
        HttpBasicAuth auth = (HttpBasicAuth) client.getApiAuthorizations().get("basicAuth");
        auth.setUsername(username);
        auth.setPassword(password);
        return client;
    }

    public static ApiClient clientToken(String baseUrl, SSLSettings sslSettings, String token) {
        ApiClient client =
                defaultClient(new String[] {"clientTokenHeader"}, baseUrl + "/", sslSettings);
        ApiKeyAuth tokenAuth = (ApiKeyAuth) client.getApiAuthorizations().get("clientTokenHeader");
        tokenAuth.setApiKey(token);
        return client;
    }

    public static VersionApi versionApi(String baseUrl, SSLSettings sslSettings) {
        return unauthorized(baseUrl, sslSettings).createService(VersionApi.class);
    }

    public static UserApi userApiWithToken(Settings settings) {
        return clientToken(settings.url(), settings.sslSettings(), settings.token())
                .createService(UserApi.class);
    }

    private static ApiClient defaultClient(
            String[] authentications, String baseUrl, SSLSettings sslSettings) {
        ApiClient client = new ApiClient(authentications);
        CertUtils.applySslSettings(client.getOkBuilder(), sslSettings);
        client.getAdapterBuilder().baseUrl(baseUrl);
        return client;
    }
}
