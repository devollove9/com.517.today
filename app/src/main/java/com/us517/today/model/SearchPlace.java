package com.us517.today.model;

import android.os.Parcel;
import android.os.Parcelable;

public class SearchPlace implements Parcelable {

    private String primaryText;
    private String secondaryText;
    private String placeId;
    private int distanceMeters;
    private String fullText;

    public String getFullText() {
        return fullText;
    }

    public void setFullText(String s) {
        this.fullText = s;
    }

    public String getPrimaryText() {
        return primaryText;
    }

    public void setPrimaryText(String s) {
        this.primaryText = s;
    }

    public String getSecondaryText() {
        return secondaryText;
    }

    public void setSecondaryText(String s) {
        this.secondaryText = s;
    }

    public String getPlaceId() {return placeId;}

    public void setPlaceId(String id) {this.placeId = id;}

    public int getDistance() {return distanceMeters;}

    public void setDistance(int d) {this.distanceMeters = d;}

    @Override
    public String toString() {
        return "SearchPlace{" +
                "primaryText='" + primaryText + '\'' +
                ", secondaryText='" + secondaryText + '\'' +
                ", placeId='" + placeId + '\'' +
                ", distanceMeters='" + distanceMeters + '\'' +
                ", fullText='" + fullText +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.fullText);
        dest.writeString(this.primaryText);
        dest.writeString(this.secondaryText);
        dest.writeString(this.placeId);
        dest.writeValue(this.distanceMeters);
    }

    public SearchPlace() {
    }

    protected SearchPlace(Parcel in) {
        this.fullText = in.readString();
        this.secondaryText = in.readString();
        this.placeId = in.readString();
        this.primaryText = in.readString();
        this.distanceMeters = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<SearchPlace> CREATOR = new Creator<SearchPlace>() {
        @Override
        public SearchPlace createFromParcel(Parcel source) {
            return new SearchPlace(source);
        }

        @Override
        public SearchPlace[] newArray(int size) {
            return new SearchPlace[size];
        }
    };
}
