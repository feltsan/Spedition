package com.feltsan.spedition.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by john on 05.10.15.
 */

@ParseClassName("Oil")
public class Oil extends ParseObject implements Serializable {
    public Oil() {
        super();
    }

    public Oil(String date, String distance, String brend, String oilFiter,
              String airFilter, String fuelFilter, String glagodel,
               String price, String service, Truck truck) {
        super();
    }

    public void setDate(long date){
        put("date", date);
    }

    public void setDistance(String distance){
        put("distance", distance);
    }

    public void setBrend(String brend){
        put("brend", brend);
    }

    public void setOilFiter(boolean oilFilter){
        put("oil_filter", oilFilter);
    }

    public void setAirFilter(boolean airFilter){
        put("air_filter", airFilter);
    }

    public void setFuelFilter(boolean fuelFilter){
        put("fuel_filter", fuelFilter);
    }

    public void setGlagoDel(boolean glagodel){
        put("glagodel", glagodel);
    }

    public void setPrice(String price){
        put("price", price);
    }

    public void setService(String service){
        put("service", service);
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

    public static ParseQuery<Oil> getQuery() {
        return ParseQuery.getQuery(Oil.class);
    }

    public long getDate(){
       return getLong("date");
    }

    public String getDistance(){
        return getString("distance");
    }

    public String getBrend(){
        return getString("brend");
    }

    public boolean getOilFiter(){
        return getBoolean("oil_filter");
    }

    public boolean getAirFilter(){
        return getBoolean("air_filter");
    }

    public boolean getFuelFilter(){
        return getBoolean("fuel_filter");
    }

    public boolean getGlagoDel(){
        return getBoolean("glagodel");
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
