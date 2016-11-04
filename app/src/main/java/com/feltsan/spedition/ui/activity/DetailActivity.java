package com.feltsan.spedition.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.feltsan.spedition.App;
import com.feltsan.spedition.R;
import com.feltsan.spedition.model.Truck;
import com.feltsan.spedition.ui.fragment.AllReysFragment;
import com.feltsan.spedition.ui.fragment.DetailFragment;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;


/**
 * Created by john on 02.10.15.
 */
public class DetailActivity extends AppCompatActivity {
    private int orient;
    String truckId;
    Truck truck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);


        if (getIntent().hasExtra("ID")) {
            truckId = getIntent().getExtras().getString("ID");
            getTruckOF();

        }else{
            openFragment();
        }
    }


    public void getTruckOF(){
        ParseQuery<Truck> query = ParseQuery.getQuery(Truck.class);
        query.fromLocalDatastore();
        query.whereEqualTo("uuid", truckId);
        query.getFirstInBackground(new GetCallback<Truck>() {
            @Override
            public void done(Truck object, ParseException e) {
                truck = object;
                setTitle(object.getNomer());
                openFragment();
            }
        });
    }

    public void openFragment(){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.add_container, new DetailFragment())
                .commit();
    }

    public Truck getTruck(){
        return truck;
    }



    public AllReysFragment getMyFragment(){
        return (AllReysFragment)
                getSupportFragmentManager()
                .findFragmentByTag("ALL");
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == App.REYS_CODE) {
                // Coming back from the edit view, update the view
               getMyFragment().refreshAdapter();
            }
        }
    }


}
