package com.kotan.printum.Network;

import com.kotan.printum.Model.Users;

/**
 * Created by COEQ-IT on 16/11/2016.
 */

public class RestService {
    private static final String URL = "http://192.168.0.98:8080/Api/";

    private retrofit.RestAdapter restAdapter;
    private UsersService apiService;

    public RestService()
    {
        restAdapter = new retrofit.RestAdapter.Builder()
                .setEndpoint(URL)
                .setLogLevel(retrofit.RestAdapter.LogLevel.FULL)
                .build();

        apiService = restAdapter.create(UsersService.class);
    }

    public UsersService getService()
    {
        return apiService;
    }
}
