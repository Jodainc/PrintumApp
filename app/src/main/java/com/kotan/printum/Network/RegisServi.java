package com.kotan.printum.Network;

import com.kotan.printum.Model.Kento;
import com.kotan.printum.Model.Users1;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.POST;
import retrofit2.Call;


/**
 * Created by Kotan@JoyDainc on 16/12/2016.
 */

public interface RegisServi {
    @POST("/Users")
    void  createUser(@Body Users1 user, Callback<Users1> cb);

}
