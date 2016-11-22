package com.kotan.printum.Network;

/**
 * Created by COEQ-IT on 16/11/2016.
 */

public class RestService {
    private static final String URL = "http://192.168.0.98:8080/Api";
    private retrofit.RestAdapter restAdapterUser;
    private UsersService apiService;
    public RestService()
    {
        restAdapterUser = new retrofit.RestAdapter.Builder()
                .setEndpoint(URL)
                .setLogLevel(retrofit.RestAdapter.LogLevel.FULL)
                .build();
        apiService = restAdapterUser.create(UsersService.class);
    }
    public UsersService getService()
    {
        return apiService;
    }
}
