package com.feltsan.spedition.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by john on 05.10.15.
 */

@ParseClassName("Truck")
public class Truck extends ParseObject {
    public Truck() {
        super();
    }

    public Truck(String nomer, String tr_nomer) {
        super();
        setNomer(nomer);
        setTrailerNomer(tr_nomer);
    }

    public void setNomer(String nomer){
        put("nomer", nomer);
    }

    public void setTrailerNomer(String tr_nomer){
        put("trailer_nomer", tr_nomer);
    }

    public void setUuidString() {
        UUID uuid = UUID.randomUUID();
        put("uuid", uuid.toString());
    }

    public String getUuidString() {
        return getString("uuid");
    }

    public static ParseQuery<Truck> getQuery() {
        return ParseQuery.getQuery(Truck.class);
    }

    public String getNomer(){
        return getString("nomer");
    }

    public String getTrailerNomer(){
        return getString("trailer_nomer");
    }


}
