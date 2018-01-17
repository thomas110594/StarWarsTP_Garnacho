package com.example.thomasgarnacho.tpfinal_garnacho.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by thomasgarnacho on 10/01/2018.
 */

public class SWPeople implements Serializable, Parcelable {
    public String name;
    public String mass;
    public String height;
    public String gender;

    @SerializedName("birth_year")
    public String birthYear;

    @SerializedName("hair_color")
    public String hairColor;

    @SerializedName("skin_color")
    public String skinColor;

    @SerializedName("eye_color")
    public String eyeColor;

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<SWPeople> CREATOR = new Parcelable.Creator<SWPeople>() {
        public SWPeople createFromParcel(Parcel in) {
            return new SWPeople(in);
        }

        public SWPeople[] newArray(int size) {
            return new SWPeople[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    // write your object's data to the passed-in Parcel
    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(name);
        out.writeString(mass);
        out.writeString(height);
        out.writeString(gender);
        out.writeString(birthYear);
        out.writeString(hairColor);
        out.writeString(skinColor);
        out.writeString(eyeColor);

    }

    private SWPeople(Parcel in) {
        name = in.readString();
        mass = in.readString();
        height = in.readString();
        gender = in.readString();
        birthYear = in.readString();
        hairColor = in.readString();
        skinColor = in.readString();
        eyeColor = in.readString();
    }
}
