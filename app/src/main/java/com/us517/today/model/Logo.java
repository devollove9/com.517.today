package com.us517.today.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Logo implements Parcelable ,Serializable {

    private static final long serialVersionUID = 1L;
    private String web;
    private String phone;
    private String mini;
    public String getWeb() {
        return web;
    }
    public void setWeb(String web) {
        this.web = web;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getMini() {
        return mini;
    }
    public void setMini(String mini) {
        this.mini = mini;
    }
    @Override
    public String toString() {
        return "Logo [web=" + web + ", phone=" + phone + ", mini=" + mini + "]";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.web);
        dest.writeString(this.phone);
        dest.writeString(this.mini);
    }

    public Logo() {
    }

    protected Logo(Parcel in) {
        this.web = in.readString();
        this.phone = in.readString();
        this.mini = in.readString();
    }

    public static final Creator<Logo> CREATOR = new Creator<Logo>() {
        public Logo createFromParcel(Parcel source) {
            return new Logo(source);
        }

        public Logo[] newArray(int size) {
            return new Logo[size];
        }
    };
}
