package com.kotan.printum.Model;

import com.google.android.gms.appindexing.Thing;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Kotan@JoyDainc on 16/12/2016.
 */

public class Users1  implements Serializable {

    @SerializedName("UserId")
    int UserId;
    @SerializedName("cedula")
    int cedula;
    @SerializedName("CompanyId")
    int CompanyId;
    @SerializedName("nit_Number")
    int nit_Number;
    @SerializedName("CityId")
    int CityId;
    @SerializedName("LastName")
    String LastName;
    @SerializedName("DepartmentId")
    int DeparmentId;
    @SerializedName("UserName")
    String UserName;
    @SerializedName("User_Photo")
    String User_Photo;
    @SerializedName("FirstName")
    String FirtsName;
    @SerializedName("UserAddress")
    String UserAddress;
    @SerializedName("UserPhone")
    String UserPhone;
    public Users1(){


    }
    public Users1( String name,String las ,String pho,String dir,String fir ,String userPhone,int cedula,int nit_Number ) {
        this.UserId = 0;
        this.cedula = cedula;
        this.CompanyId = 1;
        this.CityId = 1;
        this.nit_Number = nit_Number;
        this.LastName = las;
        this.DeparmentId=1;
        this.UserName = name;
        this.User_Photo = pho;
        this.FirtsName =fir;
        this.UserPhone= userPhone;
        this.UserAddress= dir;
    }

}
