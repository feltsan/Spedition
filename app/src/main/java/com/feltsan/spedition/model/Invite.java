package com.feltsan.spedition.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by john on 11.10.15.
 */
public class Invite implements Parcelable{
    String nomer, type, date;

    public Invite(String nomer, String type, String date) {
        this.nomer = nomer;
        this.type = type;
        this.date = date;
    }

    public Invite() {
    }

    public String getNomer() {
        return nomer;
    }

    public void setNomer(String nomer) {
        this.nomer = nomer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return nomer +" " + type + " " +
                 date ;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeStringArray(new String[] {this.nomer,
                this.date,
                this.type});
    }
    public static final Creator CREATOR = new Creator() {
        public Invite createFromParcel(Parcel in) {
            return new Invite(in);
        }

        public Invite[] newArray(int size) {
            return new Invite[size];
        }
    };


    public Invite(Parcel in){
        String[] data = new String[3];

        in.readStringArray(data);
        this.nomer = data[0];
        this.date = data[1];
        this.type = data[2];
    }
}
