package com.feltsan.spedition.until;

import com.feltsan.spedition.App;
import com.feltsan.spedition.model.Documents;
import com.feltsan.spedition.model.Oil;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by john on 15.10.15.
 */
public class InviteGetter{
    List<Documents> documentses;
    List<Oil> oils;
    FindeCallback findeCallback;

    public InviteGetter(FindeCallback findeCallback) {
        this.findeCallback = findeCallback;
    }

    public interface FindeCallback {
        void onTaskCompleted(List<Documents> documentses, List<Oil> oils);
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
                for (Documents d: objects){
                    if(d.getEndDate()<=(System.currentTimeMillis()+ App.REMEMBER_DIFF))
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
                for (Oil o: objects){
                    if(o.getDate() + App.OIL_DIFF>=(System.currentTimeMillis()))
                        oils.add(o);
                }

                findeCallback.onTaskCompleted(documentses, oils);
            }
        });
    }
}
