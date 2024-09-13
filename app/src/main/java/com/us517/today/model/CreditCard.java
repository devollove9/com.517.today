package com.us517.today.model;

import android.os.Parcel;
import android.os.Parcelable;

public class CreditCard implements Parcelable {
    private String userId;
    private String paymentId;
    private String number;
    private String expire;
    private String cvv;
    private String zip;
    private Boolean disabled;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getExpire() {
        return expire;
    }

    public void setExpire(String expire) {
        this.expire = expire;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userId);
        dest.writeString(this.paymentId);
        dest.writeString(this.number);
        dest.writeString(this.expire);
        dest.writeString(this.cvv);
        dest.writeString(this.zip);
        dest.writeValue(this.disabled);
        dest.writeString(this.type);
    }

    public CreditCard() {
    }

    protected CreditCard(Parcel in) {
        this.userId = in.readString();
        this.paymentId = in.readString();
        this.number = in.readString();
        this.expire = in.readString();
        this.cvv = in.readString();
        this.zip = in.readString();
        this.type = in.readString();
        this.disabled = (Boolean) in.readValue(Boolean.class.getClassLoader());
    }

    public static final Creator<CreditCard> CREATOR = new Creator<CreditCard>() {
        public CreditCard createFromParcel(Parcel source) {
            return new CreditCard(source);
        }

        public CreditCard[] newArray(int size) {
            return new CreditCard[size];
        }
    };
}
