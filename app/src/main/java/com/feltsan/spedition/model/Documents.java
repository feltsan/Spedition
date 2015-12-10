package com.feltsan.spedition.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.UUID;

/**
 * Created by john on 05.10.15.
 */

@ParseClassName("Documents")
public class Documents extends ParseObject {
    public Documents() {
        super();
    }

    public Documents(int type, String end_date,String price, Truck truck) {
        super();

    }

    public void setType(int type){
        put("type", type);
    }

    public void setEndDate(long end_date){
        put("end_date", end_date);
    }

    public void setPrice(String price){
        put("price", price);
    }

    public void setTruck(Truck truck){
        put("truck", truck);
    }

    public void setUuidString() {
        UUID uuid = UUID.randomUUID();
        put("uuid", uuid.toString());
    }

    public void setInform(boolean inform){
        put("inform", inform);
    }

    public boolean isInform(){
        return getBoolean("inform");
    }

    public String getUuidString() {
        return getString("uuid");
    }

    public static ParseQuery<Documents> getQuery() {
        return ParseQuery.getQuery(Documents.class);
    }

    public int getType(){
        return getInt("type");
    }

    public long getEndDate(){
        return getLong("end_date");
    }

    public String getPrice(){
        return getString("price");
    }

    public Truck getTruck(){
        return (Truck) getParseObject("truck");
    }

}
