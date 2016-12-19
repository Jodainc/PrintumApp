package com.kotan.printum.Network;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.converter.GsonConverter;

/**
 * Created by Kotan@JoyDainc on 16/12/2016.
 */

public class RestRegis {
    private static final String URL = "http://192.168.0.98:8080/Api";
    private retrofit.RestAdapter restAdapterUser;
    private RegisServi apiService;
    public RestRegis()
    {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        restAdapterUser = new retrofit.RestAdapter.Builder()
                .setEndpoint(URL)
                .setLogLevel(retrofit.RestAdapter.LogLevel.FULL)
                .setConverter(new GsonConverter(gson))
                .build();
        apiService = restAdapterUser.create(RegisServi.class);
    }

    public RegisServi getService()
    {
        return apiService;
    }
}
