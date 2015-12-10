package com.feltsan.spedition.until;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.feltsan.spedition.App;
import com.feltsan.spedition.model.Documents;
import com.feltsan.spedition.model.Oil;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;


import java.util.ArrayList;
import java.util.List;
import android.app.*;
import android.content.*;
import android.os.*;

/**
 * Created by john on 15.10.15.
 */
public class GetInvites extends Service {
    private List<Documents> documentses;
    private List<Oil> oils;
    private  boolean prosto;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        prosto = false;
        getAllDocuments();
        return START_STICKY;
    }


    @Override
    public void onStart(Intent intent, int startId)
    {

//        getAllDocuments();
    }

    public  void getAllDocuments(){

        ParseQuery<Documents> query_doc = ParseQuery.getQuery(Documents.class);
        query_doc.whereEqualTo("inform", false);
        query_doc.whereNotEqualTo("end_date", 0);
        query_doc.fromLocalDatastore();
        query_doc.findInBackground(new FindCallback<Documents>() {
            @Override
            public void done(List<Documents> objects, ParseException e) {
                documentses = new ArrayList<>();
                for (Documents d : objects) {
                    if (d.getEndDate() <= (System.currentTimeMillis() + App.REMEMBER_DIFF)&&!d.isInform())
                        documentses.add(d);
                }

                getAllOils();
            }
        });
    }

    public void getAllOils(){

        ParseQuery<Oil> query_doc = ParseQuery.getQuery(Oil.class);
        query_doc.whereEqualTo("inform", false);
        query_doc.whereNotEqualTo("date", 0);
        query_doc.fromLocalDatastore();
        query_doc.findInBackground(new FindCallback<Oil>() {
            @Override
            public void done(List<Oil> objects, ParseException e) {
                oils = new ArrayList<>();
                for (Oil o : objects) {
                    if (o.getDate() + App.OIL_DIFF <= (System.currentTimeMillis()) &&!o.isInform())
                        oils.add(o);
                }
                if (documentses.size()!=0 || oils.size()!=0 ) {
                    if(!prosto) {
                        prosto = true;
                        new InviteManager(documentses, oils).sendInvite(getApplicationContext());
                    }
//                    documentses.clear();
//                    oils.clear();
                }


            }
        });
    }
}




