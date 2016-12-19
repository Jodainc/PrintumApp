package com.kotan.printum.EventBus;

import android.app.Application;
import android.content.Context;
public class MyApplication extends Application {
    private static MyApplication sInstance;
    private String someVariable;
    private int idUser;


    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public String getSomeVariable() {
        return someVariable;
    }

    public void setSomeVariable(String someVariable) {
        this.someVariable = someVariable;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    public static MyApplication getInstance() {
        return sInstance;
    }
    public static Context getAppContext() {
        return sInstance.getApplicationContext();
    }
}
