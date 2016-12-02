package com.kotan.printum.Model;

import java.io.Serializable;

/**
 * Created by Kotan@JoyDainc on 21/11/2016.
 */

public class TrollToken implements Serializable {
    private long mID;
    private String Username;
    private String Password;
    private String PasswordType;
    private String TrollTok;
    private int MImport;
    public TrollToken(){}
    public TrollToken(long id, String username, String password,String passwordType, String trollTok,int mimport ){
    mID = id;
     Username= username;
        Password =password;
            PasswordType =  passwordType;
                TrollTok = trollTok;
                    MImport= mimport;
    }
    public long getmID() {
        return mID;
    }

    public void setmID(long mID) {
        this.mID = mID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPasswordType() {
        return PasswordType;
    }

    public void setPasswordType(String passwordType) {
        PasswordType = passwordType;
    }

    public String getTrollTok() {
        return TrollTok;
    }

    public void setTrollTok(String trollTok) {
        TrollTok = trollTok;
    }
    public int getMImport() {
        return MImport;
    }

    public void setMImport(int MImport) {
        this.MImport = MImport;
    }
}
