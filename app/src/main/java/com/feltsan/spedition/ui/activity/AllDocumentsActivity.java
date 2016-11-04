package com.feltsan.spedition.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.support.v4.app.NavUtils;

import com.feltsan.spedition.R;
import com.feltsan.spedition.adapters.AllDocumentsAdapter;
import com.feltsan.spedition.model.Documents;
import com.feltsan.spedition.until.Alarm;
import com.feltsan.spedition.until.ShPrManager;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;


import java.util.List;

/**
 * Created by john on 13.10.15.
 */
public class AllDocumentsActivity extends AppCompatActivity {

    private ListView listView;
    private ParseQueryAdapter.QueryFactory<Documents> factory;
    private AllDocumentsAdapter documentsAdapter;
    boolean isRunService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_documents);
        findUI();
        setAdapter();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
    private void findUI(){
        listView = (ListView) findViewById(R.id.documentsList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        isRunService = ShPrManager.getRunService(this);
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my, menu);
        MenuItem item = menu.findItem(R.id.action_remember);

        if (isRunService) {
            item.setTitle("Вкл.");
            item.setIcon(R.drawable.switch_active);
        } else {
            item.setTitle("Викл.");
            item.setIcon(R.drawable.switch_inactive);
        }
        return true;
    }


        @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                break;

            case R.id.action_remember:
                isRunService = ShPrManager.getRunService(this);
                if(isRunService){
                    //change your view and sort it by Alphabet
                    item.setIcon(R.drawable.switch_inactive);
                    item.setTitle("Викл.");
                    new Alarm().CancelAlarm(this);
                    ShPrManager.setRunService(this,false);
                }else{
                    //change your view and sort it by Date of Birth
                    item.setIcon(R.drawable.switch_active);
                    item.setTitle("Вкл.");
                    new Alarm().SetAlarm(this);
                    ShPrManager.setRunService(this,true);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onResume() {
        super.onResume();
        documentsAdapter.loadObjects();
    }

    public void setAdapter() {
        setFactory();
        documentsAdapter = new AllDocumentsAdapter(this, factory);
        documentsAdapter.addOnQueryLoadListener(new ParseQueryAdapter.OnQueryLoadListener<Documents>() {
            @Override
            public void onLoading() {

            }

            @Override
            public void onLoaded(List<Documents> objects, Exception e) {

            }
        });
        listView.setAdapter(documentsAdapter);
    }

    public void setFactory() {
        factory = new ParseQueryAdapter.QueryFactory<Documents>() {
            @Override
            public ParseQuery<Documents> create() {
                ParseQuery<Documents> query = Documents.getQuery();
                query.addAscendingOrder("end_date");
                query.whereNotEqualTo("end_date", 0);
                query.fromLocalDatastore();
                return query;
            }
        };
    }

}


