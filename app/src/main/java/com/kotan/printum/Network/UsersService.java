package com.kotan.printum.Network;

import android.util.Log;

import com.kotan.printum.Model.Users;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.PATCH;
import retrofit.http.Path;
import retrofit.*;
import  retrofit.http.GET;

/**
 * Created by COEQ-IT on 16/11/2016.
 */

public interface UsersService  {

    @GET("/Users")
    public void getUsers(Callback<List<Users>> callback);


    @GET("/Users/{id}")
    public void getUser(@Path("id")Integer id, Callback<Users> callback);
}
