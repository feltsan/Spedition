package com.feltsan.spedition.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by john on 05.10.15.
 */

@ParseClassName("Reys")
public class Reys extends ParseObject implements Serializable {
    public Reys() {
        super();
    }

    public Reys(String date, String start, String finish, String client,
               String price, boolean confirm, Truck truck) {
        super();
    }

    public void setDate(long date){
        put("time_stamp", date);
    }

    public void setStart(String start){
        put("start", start);
    }

    public void setFinish(String finish){
        put("finish", finish);
    }

    public void setClient(String client){
        put("client", client);
    }

    public void setPrice(String price){
        put("price", price);
    }

    public void setConfirm(boolean confirm){
        put("confirm", confirm);
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

    public String getUuidString() {
        return getString("uuid");
    }

    public static ParseQuery<Reys> getQuery() {
        return ParseQuery.getQuery(Reys.class);
    }

    public long getDate(){
        return getLong("time_stamp");
    }

    public String getStart(){
        return getString("start");
    }

    public String getFinish(){
        return getString("finish");
    }

    public String getClient(){
        return getString("client");
    }

    public boolean getConfirm(){
        return getBoolean("confirm");
    }

    public String getPrice(){
        return getString("price");
    }

    public String getService(String service){
        return getString("service");
    }

    public Truck getTruck(){
        return (Truck) getParseObject("truck");
    }
}
