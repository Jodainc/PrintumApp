package com.kotan.printum.Model;

/**
 * Created by Kotan@JoyDainc on 21/11/2016.
 */

public class TrollToken {
    private int mID;
    private String Username;
    private String Password;
    private int PasswordType;
    private String TrollTok;
    private int MImport;
    public TrollToken(int id, String username, String password,int passwordType, String trollTok,int mimport ){
    mID = id;
     Username= username;
        Password =password;
            PasswordType =  passwordType;
                TrollTok = trollTok;
                    MImport= mimport;
    }
    public int getmID() {
        return mID;
    }

    public void setmID(int mID) {
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

    public int getPasswordType() {
        return PasswordType;
    }

    public void setPasswordType(int passwordType) {
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
