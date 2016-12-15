package com.kotan.printum.Model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Kotan@JoyDainc on 05/12/2016.
 */

public class CertiModel implements Parcelable {
    private static final String KEY_C8CODIGO = "C8Codigo";
    private static final String KEY_C8EPP = "c8epp";
    private static final String KEY_C8MASINFO = "c8mASiNFO";
    private static final String KEY_C8PROTECCION = "C8pROTECCION";
    public long Id;
    String C8Codigo;
    String c8epp;
    String c8mASiNFO;
    String C8pROTECCION;
    public CertiModel(String c8Codigo, String c8epp, String c8mASiNFO, String c8pROTECCION) {
        this.C8Codigo = c8Codigo;
        this.c8epp = c8epp;
        this.c8mASiNFO = c8mASiNFO;
        this.C8pROTECCION = c8pROTECCION;
    }
    protected CertiModel(Parcel in) {
        C8Codigo = in.readString();
        c8epp = in.readString();
        c8mASiNFO = in.readString();
        C8pROTECCION = in.readString();
    }
    public static final Creator<CertiModel> CREATOR = new Creator<CertiModel>() {
        @Override
        public CertiModel createFromParcel(Parcel in) {
            return new CertiModel(in);
        }

        @Override
        public CertiModel[] newArray(int size) {
            return new CertiModel[size];
        }
    };
    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getC8Codigo() {
        return C8Codigo;
    }

    public String getC8epp() {
        return c8epp;
    }

    public String getC8mASiNFO() {
        return c8mASiNFO;
    }

    public String getC8pROTECCION() {
        return C8pROTECCION;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_C8CODIGO, C8Codigo);
        bundle.putString(KEY_C8EPP, c8epp);
        bundle.putString(KEY_C8MASINFO, c8mASiNFO);
        bundle.putString(KEY_C8PROTECCION, C8pROTECCION);
        dest.writeBundle(bundle);
    }

}

