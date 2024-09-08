package com.us517.today.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Region implements Parcelable {
    private String _id;
    private String nameEn;
    private double longitude;
    private double tax;
    private String name;
    private double latitude;
    private String regionId;
    private List<String> slideimage;
    private List<String> promotion;
    private Logo logo;
    private List<UserAddress> dormitory;

    public List<UserAddress> getDormitory() {
        return dormitory;
    }

    public void setDormitory(List<UserAddress> dormitory) {
        this.dormitory = dormitory;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public Logo getLogo() {
        return logo;
    }

    public void setLogo(Logo logo) {
        this.logo = logo;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }



    public List<String> getSlideimage() {
        return slideimage;
    }

    public void setSlideimage(List<String> slideimage) {
        this.slideimage = slideimage;
    }

    public List<String> getPromotion() {
        return promotion;
    }

    public void setPromotion(List<String> promotion) {
        this.promotion = promotion;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this._id);
        dest.writeString(this.nameEn);
        dest.writeDouble(this.longitude);
        dest.writeDouble(this.tax);
        dest.writeString(this.name);
        dest.writeDouble(this.latitude);
        dest.writeString(this.regionId);
        dest.writeStringList(this.slideimage);
        dest.writeStringList(this.promotion);
        dest.writeParcelable(this.logo, flags);
        dest.writeTypedList(this.dormitory);
    }

    public Region() {
    }

    protected Region(Parcel in) {
        this._id = in.readString();
        this.nameEn = in.readString();
        this.longitude = in.readDouble();
        this.tax = in.readDouble();
        this.name = in.readString();
        this.latitude = in.readDouble();
        this.regionId = in.readString();
        this.slideimage = in.createStringArrayList();
        this.promotion = in.createStringArrayList();
        this.logo = in.readParcelable(Logo.class.getClassLoader());
        this.dormitory = in.createTypedArrayList(UserAddress.CREATOR);
    }

    public static final Creator<Region> CREATOR = new Creator<Region>() {
        @Override
        public Region createFromParcel(Parcel source) {
            return new Region(source);
        }

        @Override
        public Region[] newArray(int size) {
            return new Region[size];
        }
    };
}
