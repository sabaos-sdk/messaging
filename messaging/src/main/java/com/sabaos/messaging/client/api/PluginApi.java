package com.sabaos.messaging.client.api;


import com.sabaos.messaging.client.model.PluginConf;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.*;

public interface PluginApi {
  /**
   * Disable a plugin.
   * 
   * @param id the plugin id (required)
   * @return Call&lt;Void&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("plugin/{id}/disable")
  Call<Void> disablePlugin(
          @retrofit2.http.Path("id") Integer id
  );

  /**
   * Enable a plugin.
   *
   * @param id the plugin id (required)
   * @return Call&lt;Void&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("plugin/{id}/enable")
  Call<Void> enablePlugin(
          @retrofit2.http.Path("id") Integer id
  );

  /**
   * Get YAML configuration for Configurer plugin.
   *
   * @param id the plugin id (required)
   * @return Call&lt;Object&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @GET("plugin/{id}/config")
  Call<Object> getPluginConfig(
          @retrofit2.http.Path("id") Integer id
  );

  /**
   * Get display info for a Displayer plugin.
   *
   * @param id the plugin id (required)
   * @return Call&lt;String&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @GET("plugin/{id}/display")
  Call<String> getPluginDisplay(
          @retrofit2.http.Path("id") Integer id
  );

  /**
   * Return all plugins.
   *
   * @return Call&lt;List&lt;PluginConf&gt;&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @GET("plugin")
  Call<List<PluginConf>> getPlugins();


  /**
   * Update YAML configuration for Configurer plugin.
   *
   * @param id the plugin id (required)
   * @return Call&lt;Void&gt;
   */
  @Headers({
    "Content-Type:application/x-yaml"
  })
  @POST("plugin/{id}/config")
  Call<Void> updatePluginConfig(
          @retrofit2.http.Path("id") Integer id
  );

}
