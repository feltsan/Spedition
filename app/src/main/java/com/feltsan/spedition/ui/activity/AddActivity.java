package com.feltsan.spedition.ui.activity;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.feltsan.spedition.App;
import com.feltsan.spedition.R;
import com.feltsan.spedition.model.Documents;
import com.feltsan.spedition.model.Oil;
import com.feltsan.spedition.model.Reys;
import com.feltsan.spedition.model.Servis;
import com.feltsan.spedition.model.Truck;
import com.feltsan.spedition.ui.fragment.AddOilFragment;
import com.feltsan.spedition.ui.fragment.AddReysFragment;
import com.feltsan.spedition.ui.fragment.AddServiceFragment;
import com.feltsan.spedition.ui.fragment.AddTruckFragment;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;


import java.util.List;

/**
 * Created by john on 02.10.15.
 */
public class AddActivity extends AppCompatActivity {

    private int code = -1;
    String id = null;
    Truck truck;
    Reys reys;
    Oil oil;
    Servis servis;
    List<Documents> documents;
    List<Reys> reyses;
    List<Oil> oils;
    List<Servis> servises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        if (getIntent().hasExtra("CODE")) {
            code = getIntent().getExtras().getInt("CODE");
            if (getIntent().hasExtra("ID")) {
                id = getIntent().getExtras().getString("ID");
                switchFragment();
            } else
                openFragment(new AddTruckFragment());
        }
    }

    public void getTruckOF(final Fragment fragment){
        ParseQuery<Truck> query = ParseQuery.getQuery(Truck.class);
        query.fromLocalDatastore();
        query.whereEqualTo("uuid", id);
        query.getFirstInBackground(new GetCallback<Truck>() {
            @Override
            public void done(Truck object, ParseException e) {
                truck = object;
                setTitle(object.getNomer());
                openFragment(fragment);
            }
        });
    }

    public void getAllOfTruck(){
        ParseQuery<Truck> query = ParseQuery.getQuery(Truck.class);
        query.fromLocalDatastore();
        query.whereEqualTo("uuid", id);
        query.getFirstInBackground(new GetCallback<Truck>() {
            @Override
            public void done(Truck object, ParseException e) {
                truck = object;
                setTitle(object.getNomer());
                getAllService(truck);
            }
        });
    }

    public void getReysOF(final Fragment fragment){
        ParseQuery<Reys> query = ParseQuery.getQuery(Reys.class);
        query.fromLocalDatastore();
        query.whereEqualTo("uuid",  id);
        query.getFirstInBackground(new GetCallback<Reys>() {
            @Override
            public void done(Reys object, ParseException e) {
                reys = object;
                truck = object.getTruck();
                setTitle(reys.getTruck().getNomer());
                openFragment(fragment);
            }
        });
    }

    public void getOilOF(final Fragment fragment){
        ParseQuery<Oil> query = ParseQuery.getQuery(Oil.class);
        query.fromLocalDatastore();
        query.whereEqualTo("uuid",  id);
        query.getFirstInBackground(new GetCallback<Oil>() {
            @Override
            public void done(Oil object, ParseException e) {
                oil = object;
                truck = object.getTruck();
                setTitle(oil.getTruck().getNomer());
                openFragment(fragment);
            }
        });
    }

    public void getServiceOF(final Fragment fragment){
        ParseQuery<Servis> query = ParseQuery.getQuery(Servis.class);
        query.fromLocalDatastore();
        query.whereEqualTo("uuid",  id);
        query.getFirstInBackground(new GetCallback<Servis>() {
            @Override
            public void done(Servis object, ParseException e) {
                servis = object;
                truck = object.getTruck();
                setTitle(servis.getTruck().getNomer());
                openFragment(fragment);
            }
        });
    }

    private void openFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.add_container, fragment)
                .commit();
    }
    public void switchFragment(){
        switch (code){
            case App.NEW_TRUCK_CODE:
                openFragment(new AddTruckFragment());
                break;
            case App.REYS_CODE:
                getTruckOF(new AddReysFragment());
                break;
            case App.OIL_CODE:
                getTruckOF(new AddOilFragment());
                break;
            case App.SERVICE_CODE:
                getTruckOF(new AddServiceFragment());
                break;
            case App.EDIT_TRUCK_CODE:
                getAllOfTruck();
                break;
            case App.EDIT_REYS_CODE:
                getReysOF(new AddReysFragment());
                break;
            case App.EDIT_OIL_CODE:
                getOilOF(new AddOilFragment());
                break;
            case App.EDIT_SERVICE_CODE:
                getServiceOF(new AddServiceFragment());
                break;
        }

    }

    public Truck getTruck(){
        return truck;
    }

    public Reys getReys(){
        return reys;
    }

    public Oil getOil(){
        return oil;
    }

    public Servis getServis(){
        return servis;
    }

    public List<Documents> getDocuments(){
        return documents;
    }

    public List<Oil> getOils(){
        return oils;
    }

    public List<Reys> getReyses(){
        return reyses;
    }

    public List<Servis> getServices(){
        return servises;
    }

    public void getAllDocumets(Truck truck){
        ParseQuery<Documents> query = ParseQuery.getQuery(Documents.class);
        query.fromLocalDatastore();
        query.whereEqualTo("truck",truck);
        query.findInBackground(new FindCallback<Documents>() {
            public void done(List<Documents> itemList, ParseException e) {
                if (e == null) {
                    documents = itemList;
                    openFragment(new AddTruckFragment());
                } else {

                }
            }
        });
    }

    public void getAllreys(final Truck truck){
        ParseQuery<Reys> query = ParseQuery.getQuery(Reys.class);
        query.fromLocalDatastore();
        query.whereEqualTo("truck",truck);
        query.findInBackground(new FindCallback<Reys>() {
            public void done(List<Reys> itemList, ParseException e) {
                if (e == null) {
                    reyses = itemList;
                    getAllDocumets(truck);
                } else {

                }
            }
        });
    }

    public void getAllOil(final Truck truck){
        ParseQuery<Oil> query = ParseQuery.getQuery(Oil.class);
        query.fromLocalDatastore();
        query.whereEqualTo("truck",truck);
        query.findInBackground(new FindCallback<Oil>() {
            public void done(List<Oil> itemList, ParseException e) {
                if (e == null) {
                    oils = itemList;
                    getAllreys(truck);
                } else {

                }
            }
        });
    }

    public void getAllService(final Truck truck){
        ParseQuery<Servis> query = ParseQuery.getQuery(Servis.class);
        query.fromLocalDatastore();
        query.whereEqualTo("truck",truck);
        query.findInBackground(new FindCallback<Servis>() {
            public void done(List<Servis> itemList, ParseException e) {
                if (e == null) {
                    servises = itemList;
                    getAllOil(truck);
                } else {

                }
            }
        });
    }



}
