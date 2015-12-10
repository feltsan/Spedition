package com.feltsan.spedition.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.feltsan.spedition.App;
import com.feltsan.spedition.R;
import com.feltsan.spedition.adapters.TruckAdapter;
import com.feltsan.spedition.model.Truck;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

public class AllTruckActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    private FloatingActionButton fba;
    private GridView gridView;
    private ParseQueryAdapter.QueryFactory<Truck> factory;
    private TruckAdapter truckAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findUi();
        setListener();
        setAdapter();



    }

    public void findUi() {
        gridView = (GridView) findViewById(R.id.grid);
        fba = (FloatingActionButton) findViewById(R.id.fab);
    }

    @Override
    protected void onResume() {
        super.onResume();
            truckAdapter.loadObjects();
    }

    public void setAdapter() {
        setFactory();
        truckAdapter = new TruckAdapter(this, factory);
        gridView.setAdapter(truckAdapter);
    }

    public void setFactory() {
        factory = new ParseQueryAdapter.QueryFactory<Truck>() {
            @Override
            public ParseQuery<Truck> create() {
                ParseQuery<Truck> query = Truck.getQuery();
                query.orderByAscending("createdAt");
                query.fromLocalDatastore();
                return query;
            }
        };
    }

    public void setListener() {
        fba.setOnClickListener(this);
        gridView.setOnItemClickListener(this);
        gridView.setOnItemLongClickListener(this);
    }


    private void openAddView() {
        Intent i = new Intent(this, AddActivity.class);
//        i.putExtra("ID", todo.getUuidString());
        i.putExtra("CODE", App.NEW_TRUCK_CODE);

        startActivityForResult(i, App.NEW_TRUCK_CODE);
    }

    private void openDetailView(Truck truck) {
        Intent i = new Intent(this, DetailActivity.class);
        i.putExtra("ID", truck.getUuidString());
        i.putExtra("CODE", App.DETAIL_TRUCK_CODE);
        startActivityForResult(i, App.DETAIL_TRUCK_CODE);
    }

    private void openEditView(Truck truck) {
        Intent i = new Intent(this, AddActivity.class);
        i.putExtra("ID", truck.getUuidString());
        i.putExtra("CODE", App.EDIT_TRUCK_CODE);
        startActivityForResult(i, App.EDIT_TRUCK_CODE);
    }

    private void openAllDocumentsView() {
        Intent i = new Intent(this, AllDocumentsActivity.class);
        startActivityForResult(i, App.DETAIL_TRUCK_CODE);
    }

    @Override
    public void onClick(View v) {
        openAddView();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Truck truck = truckAdapter.getItem(position);
        openDetailView(truck);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
                truckAdapter.loadObjects();

    }


    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        openEditView(truckAdapter.getItem(position));
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
        openAllDocumentsView();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}