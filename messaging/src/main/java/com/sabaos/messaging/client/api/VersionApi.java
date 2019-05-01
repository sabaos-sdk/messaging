package com.sabaos.messaging.client.api;

import com.sabaos.messaging.client.model.VersionInfo;

import retrofit2.Call;
import retrofit2.http.*;


public interface VersionApi {
  /**
   * Get version information.
   * 
   * @return Call&lt;VersionInfo&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @GET("version")
  Call<VersionInfo> getVersion();
    

}
