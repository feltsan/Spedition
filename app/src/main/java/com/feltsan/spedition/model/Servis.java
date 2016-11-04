package com.feltsan.spedition.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by john on 05.10.15.
 */

@ParseClassName("Servis")
public class Servis extends ParseObject implements Serializable {
    public Servis() {
        super();
    }

    public Servis(String date, String distance, String zapchast, String brend,
                  String price, String service, Truck truck) {
        super();
    }

    public void setDate(long date){
        put("time_stamp", date);
    }

    public void setDistance(String distance){
        put("distance", distance);
    }

    public void setZapchast(String zapchast){
        put("zapchast", zapchast);
    }

    public void setBrend(String brend){
        put("brend", brend);
    }

    public void setPrice(String price){
        put("price", price);
    }

    public void setService(String service){
        put("service", service);
    }

    public void setShop(String shop){
        put("shop", shop);
    }

    public void setTruck(Truck truck){
        put("truck", truck);
    }

    public void setUuidString() {
        UUID uuid = UUID.randomUUID();
        put("uuid", uuid.toString());
    }

    public String getUuidString() {
        return getString("uuid");
    }

    public static ParseQuery<Servis> getQuery() {
        return ParseQuery.getQuery(Servis.class);
    }

    public long getDate(){
        return getLong("time_stamp");
    }

    public String getDistance(){
        return getString("distance");
    }

    public String getZapchast(){
        return getString("zapchast");
    }

    public String getBrend(){
        return getString("brend");
    }

    public String getShop(){
        return getString("shop");
    }


    public String getPrice(){
        return getString("price");
    }

    public String getService(){
        return getString("service");
    }

    public Truck getTruck(){
        return (Truck) getParseObject("truck");
    }
}
