package com.sabaos.messaging.client.api;


import com.sabaos.messaging.client.model.User;
import com.sabaos.messaging.client.model.UserPass;
import com.sabaos.messaging.client.model.UserWithPass;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.*;

public interface UserApi {
  /**
   * Create a user.
   * 
   * @param body the user to add (required)
   * @return Call&lt;User&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("user")
  Call<User> createUser(
          @retrofit2.http.Body UserWithPass body
  );

  /**
   * Return the current user.
   *
   * @return Call&lt;User&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @GET("current/user")
  Call<User> currentUser();


  /**
   * Deletes a user.
   *
   * @param id the user id (required)
   * @return Call&lt;Void&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @DELETE("user/{id}")
  Call<Void> deleteUser(
          @retrofit2.http.Path("id") Integer id
  );

  /**
   * Get a user.
   *
   * @param id the user id (required)
   * @return Call&lt;User&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @GET("user/{id}")
  Call<User> getUser(
          @retrofit2.http.Path("id") Integer id
  );

  /**
   * Return all users.
   *
   * @return Call&lt;List&lt;User&gt;&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @GET("user")
  Call<List<User>> getUsers();


  /**
   * Update the password of the current user.
   *
   * @param body the user (required)
   * @return Call&lt;Void&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("current/user/password")
  Call<Void> updateCurrentUser(
          @retrofit2.http.Body UserPass body
  );

  /**
   * Update a user.
   *
   * @param id the user id (required)
   * @param body the updated user (required)
   * @return Call&lt;User&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("user/{id}")
  Call<User> updateUser(
          @retrofit2.http.Path("id") Integer id, @retrofit2.http.Body UserWithPass body
  );

}
