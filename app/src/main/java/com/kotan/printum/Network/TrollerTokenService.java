package com.kotan.printum.Network;
import com.kotan.printum.Model.Kento;
import com.kotan.printum.Model.TrollToken;
import com.kotan.printum.Model.Users;
import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.Path;
import retrofit.mime.TypedString;
import retrofit2.Call;

/**
 * Created by Kotan@JoyDainc on 21/11/2016.
 */
public interface TrollerTokenService {
    @FormUrlEncoded
    @POST("/token")
    public void addKento(@Field(value = "username", encodeValue = false) String names,Callback<Kento> callback);
    //
    // public void addKento(@Body String body, Callback<Kento> callback);
}