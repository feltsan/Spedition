package com.feltsan.spedition.ui.activity;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.feltsan.spedition.R;
import com.feltsan.spedition.adapters.AllDocumentsAdapter;
import com.feltsan.spedition.adapters.AllOilsAdapter;
import com.feltsan.spedition.model.Documents;
import com.feltsan.spedition.model.Oil;
import com.feltsan.spedition.until.Alarm;
import com.feltsan.spedition.until.ShPrManager;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

import java.util.List;

/**
 * Created by john on 13.10.15.
 */
public class AllOilsActivity extends AppCompatActivity {

    private ListView listView;
    private ParseQueryAdapter.QueryFactory<Oil> factory;
    private AllOilsAdapter oilsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_ois);
        findUI();
        setAdapter();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
    private void findUI(){
        listView = (ListView) findViewById(R.id.oilsList);
    }

        @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onResume() {
        super.onResume();
        oilsAdapter.loadObjects();
    }

    public void setAdapter() {
        setFactory();
        oilsAdapter = new AllOilsAdapter(this, factory);
        oilsAdapter.addOnQueryLoadListener(new ParseQueryAdapter.OnQueryLoadListener<Oil>() {
            @Override
            public void onLoading() {

            }

            @Override
            public void onLoaded(List<Oil> objects, Exception e) {

            }
        });
        listView.setAdapter(oilsAdapter);
    }

    public void setFactory() {
        factory = new ParseQueryAdapter.QueryFactory<Oil>() {
            @Override
            public ParseQuery<Oil> create() {
                ParseQuery<Oil> query = Oil.getQuery();
                query.addAscendingOrder("date");
                query.whereNotEqualTo("date", 0);
                query.whereNotEqualTo("inform", true);
                query.fromLocalDatastore();
                return query;
            }
        };
    }

}


