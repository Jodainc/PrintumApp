package com.kotan.printum.Network;

import android.util.Log;

import com.kotan.printum.Model.Users;
import java.util.List;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.Path;
/**
 * Created by COEQ-IT on 16/11/2016.
 */
public interface UsersService  {
    @GET("/Users")
    public void getUsers(@Header("Authorization") String troll,Callback<List<Users>> callback);
    @GET("/Users/{id}")
    public void getUser(@Header("Authorization") String troll, @Path("id")Integer id, Callback<Users> callback);
}
