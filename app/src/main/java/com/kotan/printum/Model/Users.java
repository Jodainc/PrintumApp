package com.kotan.printum.Model;

import com.kotan.printum.Ui.Dao.DaoTroller;

/**
 * Created by COEQ-IT on 16/11/2016.
 */

public class Users {
    public long UserId;
    public String UserName;
    public String FirstName;
    public String LastName;
    public String UserPhone;
    public String UserAddress;
    public String UserPhoto;
    public int DepartmentId;
    public int CityId;
    public int CompanyId;
    public TrollToken trollToken;
    public byte[] image;
    public long getUserId() {
        return UserId;
    }
    public void setUserId(long userId) {
        UserId = userId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getUserPhone() {
        return UserPhone;
    }

    public void setUserPhone(String userPhone) {
        UserPhone = userPhone;
    }

    public String getUserAddress() {
        return UserAddress;
    }

    public void setUserAddress(String userAddress) {
        UserAddress = userAddress;
    }

    public String getUserPhoto() {
        return UserPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        UserPhoto = userPhoto;
    }

    public int getDepartmentId() {
        return DepartmentId;
    }

    public void setDepartmentId(int departmentId) {
        DepartmentId = departmentId;
    }

    public int getCityId() {
        return CityId;
    }

    public void setCityId(int cityId) {
        CityId = cityId;
    }

    public int getCompanyId() {
        return CompanyId;
    }

    public void setCompanyId(int companyId) {
        CompanyId = companyId;
    }

    public TrollToken getTrollToken() {
        return trollToken;
    }

    public void setTrollToken(TrollToken trollToken) {
        this.trollToken = trollToken;
    }
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }




}
