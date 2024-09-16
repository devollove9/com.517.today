package com.us517.today.model;

import android.os.Parcel;
import android.os.Parcelable;

public class UserAddress implements Parcelable {

    private String userId;
    private String firstName;
    private String lastName;
    private String street;
    private String room;
    private String city;
    private String state;
    private String zip;
    private String phone;
    private String addressId;
    private Boolean disabled ;
    private String type;
    private String name;
    private String nameEn;
    private String label;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }


    @Override
    public String toString() {
        return "UserAddress{" +
                "userId='" + userId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", street='" + street + '\'' +
                ", room='" + room + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                ", phone='" + phone + '\'' +
                ", addressId='" + addressId + '\'' +
                ", disabled=" + disabled +
                ", type=" + type +
                '}';
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userId);
        dest.writeString(this.firstName);
        dest.writeString(this.lastName);
        dest.writeString(this.street);
        dest.writeString(this.room);
        dest.writeString(this.city);
        dest.writeString(this.state);
        dest.writeString(this.zip);
        dest.writeString(this.phone);
        dest.writeString(this.addressId);
        dest.writeValue(this.disabled);
        dest.writeValue(this.type);
        dest.writeString(this.name);
        dest.writeString(this.nameEn);
        dest.writeString(this.label);
    }

    public UserAddress() {
    }

    protected UserAddress(Parcel in) {
        this.userId = in.readString();
        this.firstName = in.readString();
        this.lastName = in.readString();
        this.street = in.readString();
        this.room = in.readString();
        this.city = in.readString();
        this.state = in.readString();
        this.zip = in.readString();
        this.phone = in.readString();
        this.addressId = in.readString();
        this.disabled = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.type = in.readString();
        this.name = in.readString();
        this.nameEn = in.readString();
    }

    public static final Parcelable.Creator<UserAddress> CREATOR = new Creator<UserAddress>() {
        @Override
        public UserAddress createFromParcel(Parcel source) {
            return new UserAddress(source);
        }

        @Override
        public UserAddress[] newArray(int size) {
            return new UserAddress[size];
        }
    };
}
