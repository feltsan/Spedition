package com.feltsan.spedition.ui.fragment;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.feltsan.spedition.App;
import com.feltsan.spedition.R;
import com.feltsan.spedition.model.Documents;
import com.feltsan.spedition.model.Truck;
import com.feltsan.spedition.until.DateHelper;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;


/**
 * Created by john on 27.09.tacho.
 */
public class TruckFragment extends Fragment {

    TextView greenCartDateTruck;
    TextView certDateTruck;
    TextView europackDateTruck;
    TextView tachoDateTruck;
    TextView starchDateTruck;
    TextView greenCartDateTrailer;
    TextView certDateTrailer;
    TextView europackDateTrailer;
    TextView svidDateTrailer;
    TextView starchDateTrailer;
    TextView truckNomer, traNomer;


    TextView greenCartPriceTruck;
    TextView certPriceTruck;
    TextView europackPriceTruck;
    TextView tachoPriceTruck;
    TextView starchPriceTruck;
    TextView greenCartPriceTrailer;
    TextView certPriceTrailer;
    TextView europackPriceTrailer;
    TextView svidPriceTrailer;
    TextView starchPriceTrailer;


    String nomer, trailerNomer,     greenCartDateTru,
            certDateTru,
            europackDateTru,
            tachoDateTru,
            starchDateTru,
            greenCartDateTra,
            certDateTra,
            europackDateTra,
            svidDateTra,
            starchDateTra,


    greenCartPriceTru,
            certPriceTru,
            europackPriceTru,
            tachoPriceTru,
            starchPriceTru,
            greenCartPriceTra,
            certPriceTra,
            europackPriceTra,
            svidPriceTra,
            starchPriceTra;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_truck, container, false);

        findUI(root);
        setData();


        return root;
    }

    private void findUI(View view) {
        truckNomer = (TextView) view.findViewById(R.id.nomerZnakTruck);
        traNomer = (TextView) view.findViewById(R.id.nomerZnakTrailer);
        greenCartDateTruck = (TextView) view.findViewById(R.id.greenCardDateTruck);
        certDateTruck = (TextView) view.findViewById(R.id.certDateTruck);
        europackDateTruck = (TextView) view.findViewById(R.id.europackDateTruck);
        tachoDateTruck = (TextView) view.findViewById(R.id.tachoDateTruck);
        starchDateTruck = (TextView) view.findViewById(R.id.policyDateTruck);

        greenCartDateTrailer = (TextView) view.findViewById(R.id.greenCardDateTrailer);
        certDateTrailer = (TextView) view.findViewById(R.id.certDateTrailer);
        europackDateTrailer = (TextView) view.findViewById(R.id.europackDateTrailer);
        svidDateTrailer = (TextView) view.findViewById(R.id.svidYellowDateTrailer);
        starchDateTrailer = (TextView) view.findViewById(R.id.strachDateTrailer);

        greenCartPriceTruck = (TextView) view.findViewById(R.id.grenCardPriceTruck);
        certPriceTruck = (TextView) view.findViewById(R.id.certPriceTruck);
        europackPriceTruck = (TextView) view.findViewById(R.id.europackPriceTruck);
        tachoPriceTruck = (TextView) view.findViewById(R.id.tachoPriceTruck);
        starchPriceTruck = (TextView) view.findViewById(R.id.strachPriceTruck);

        greenCartPriceTrailer = (TextView) view.findViewById(R.id.greenCardPriceTrailer);
        certPriceTrailer = (TextView) view.findViewById(R.id.certPriceTrailer);
        europackPriceTrailer = (TextView) view.findViewById(R.id.europackPriceTrailer);
        svidPriceTrailer = (TextView) view.findViewById(R.id.svidYellowPriceTrailer);
        starchPriceTrailer = (TextView) view.findViewById(R.id.strachCardPriceTrailer);


    }

    private void setData(){
        String truckId = getActivity().getIntent().getStringExtra("ID");
        ParseQuery<Truck> query = ParseQuery.getQuery(Truck.class);
// First try to find from the cache and only then go to network
//        query.setCachePolicy(ParseQuery.CachePolicy.CACHE_ONLY); // or CACHE_ONLY
// Execute the query to find the object with ID
        query.fromLocalDatastore();
        query.whereEqualTo("uuid", truckId);
        query.getFirstInBackground(new GetCallback<Truck>() {
            @Override
            public void done(Truck object, ParseException e) {
                getAllDocuments(object);
            }
        });


    }

    public void getAllDocuments(final Truck truck){

        ParseQuery<Documents> query_doc = ParseQuery.getQuery(Documents.class);
        query_doc.whereEqualTo("truck",truck);
        query_doc.fromLocalDatastore();
        query_doc.findInBackground(new FindCallback<Documents>() {
            @Override
            public void done(List<Documents> objects, ParseException e) {
                for (Documents documents : objects) {
                    switch (documents.getType()) {
                        case App.GCTru:
                            greenCartDateTru = DateHelper.convertLongToString(documents.getEndDate());
                            greenCartPriceTru = documents.getPrice();
                            break;

                        case App.GCTra:
                            greenCartDateTra =DateHelper.convertLongToString(documents.getEndDate());
                            greenCartPriceTra = documents.getPrice();
                            break;

                        case App.WSTru:
                            certDateTru = DateHelper.convertLongToString(documents.getEndDate());
                            certPriceTru = documents.getPrice();
                            break;

                        case App.WSTra:
                            certDateTra =DateHelper.convertLongToString(documents.getEndDate());
                            certPriceTra = documents.getPrice();
                            break;

                        case App.EPTru:
                            europackDateTru = DateHelper.convertLongToString(documents.getEndDate());
                            europackPriceTru = documents.getPrice();
                            break;

                        case App.EPTra:
                            europackDateTra = DateHelper.convertLongToString(documents.getEndDate());
                            europackPriceTra = documents.getPrice();
                            break;

                        case App.TACHO:
                            tachoDateTru = DateHelper.convertLongToString(documents.getEndDate());
                            tachoPriceTru = documents.getPrice();
                            break;

                        case App.YSTra:
                            svidDateTra = DateHelper.convertLongToString(documents.getEndDate());
                            svidPriceTra = documents.getPrice();
                            break;

                        case App.POLTru:
                            starchDateTru = DateHelper.convertLongToString(documents.getEndDate());
                            starchPriceTru = documents.getPrice();
                            break;

                        case App.POLTra:
                            starchDateTra =DateHelper.convertLongToString(documents.getEndDate());
                            starchPriceTra = documents.getPrice();
                            break;

                    }

                }

                nomer = truck.getNomer();
                trailerNomer = truck.getTrailerNomer();

                truckNomer.setText(nomer);
                traNomer.setText(trailerNomer);

                greenCartDateTruck.setText(greenCartDateTru);
                certDateTruck.setText(certDateTru);
                europackDateTruck.setText(europackDateTru);
                tachoDateTruck.setText(tachoDateTru);
                starchDateTruck.setText(starchDateTru);

                greenCartDateTrailer.setText(greenCartDateTra);
                certDateTrailer.setText(certDateTra);
                europackDateTrailer.setText(europackDateTra);
                svidDateTrailer.setText(svidDateTra);
                starchDateTrailer.setText(starchDateTra);

                greenCartPriceTruck.setText(greenCartPriceTru);
                certPriceTruck.setText(certPriceTru);
                europackPriceTruck.setText(europackPriceTru);
                tachoPriceTruck.setText(tachoPriceTru);
                starchPriceTruck.setText(starchPriceTru);

                greenCartPriceTrailer.setText(greenCartPriceTra);
                certPriceTrailer.setText(certPriceTra);
                europackPriceTrailer.setText(europackPriceTra);
                svidPriceTrailer.setText(svidPriceTra);
                starchPriceTrailer.setText(starchPriceTra);
            }
        });
    }

}
