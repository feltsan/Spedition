package com.feltsan.spedition.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.feltsan.spedition.App;
import com.feltsan.spedition.R;
import com.feltsan.spedition.model.Documents;
import com.feltsan.spedition.model.Oil;
import com.feltsan.spedition.model.Reys;
import com.feltsan.spedition.model.Servis;
import com.feltsan.spedition.model.Truck;
import com.feltsan.spedition.ui.activity.AddActivity;
import com.feltsan.spedition.until.DateHelper;
import com.parse.DeleteCallback;
import com.parse.ParseException;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by john on 27.09.tacho.
 */
public class AddTruckFragment extends Fragment implements View.OnClickListener {
    AddActivity addActivity;
    TextView greenCartDateTruck;
    TextView certDateTruck;
    TextView europackDateTruck;
    TextView tachoDateTruck;
    TextView strachDateTruck;
    TextView greenCartDateTrailer;
    TextView certDateTrailer;
    TextView europackDateTrailer;
    TextView svidDateTrailer;
    TextView strachDateTrailer;


    EditText greenCartPriceTruck;
    EditText certPriceTruck;
    EditText europackPriceTruck;
    EditText tachoPriceTruck;
    EditText strachPriceTruck;
    EditText greenCartPriceTrailer;
    EditText certPriceTrailer;
    EditText europackPriceTrailer;
    EditText svidPriceTrailer;
    EditText strachPriceTrailer;
    EditText nomer, trailerNomer;
    boolean isEdit;

    long GCTru = 0;
    long GCTra = 0;
    long WSTru = 0;
    long WSTra = 0;
    long EPTru = 0;
    long EPTra = 0;
    long TACHO = 0;
    long YSTra = 0;
    long POLTru =0;
    long POLTra =0;


    Button save, delete;
    int year_x, mont_x, day_x;
    static final int DIALOG_ID = 0;
    private View views;
    private Truck truck=null;
    private Documents documentGCTru, documentGCTra, documentWSTru,documentWSTra,documentEPTru,documentEPTra,documentTACHO,
            documentYSTra, documentPOLTru, documentPOLTra;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        addActivity = (AddActivity) context;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_add_truck, container, false);
        isEdit = false;

        findUI(root);
        setListener();
        if(addActivity.getTruck()!=null) {
            truck = addActivity.getTruck();
            isEdit = true;
            getDoc();
            setEditData();
            saveTempDates();
            delete.setVisibility(View.VISIBLE);
        }else{
            truck = new Truck();
            truck.setUuidString();

            documentGCTru = new Documents();
            documentGCTru.setUuidString();
            documentGCTru.setInform(false);
            documentGCTru.setType(App.GCTru);

            documentGCTra = new Documents();
            documentGCTra.setUuidString();
            documentGCTra.setType(App.GCTra);
            documentGCTra.setInform(false);

            documentWSTru = new Documents();
            documentWSTru.setUuidString();
            documentWSTru.setType(App.WSTru);
            documentWSTru.setInform(false);

            documentWSTra = new Documents();
            documentWSTra.setType(App.WSTra);
            documentWSTra.setUuidString();
            documentWSTra.setInform(false);

            documentEPTru = new Documents();
            documentEPTru.setType(App.EPTru);
            documentEPTru.setUuidString();
            documentEPTru.setInform(false);

            documentEPTra = new Documents();
            documentEPTra.setUuidString();
            documentEPTra.setType(App.EPTra);
            documentEPTra.setInform(false);

            documentTACHO = new Documents();
            documentTACHO.setUuidString();
            documentTACHO.setType(App.TACHO);
            documentTACHO.setInform(false);

            documentYSTra = new Documents();
            documentYSTra.setUuidString();
            documentYSTra.setType(App.YSTra);
            documentYSTra.setInform(false);

            documentPOLTru = new Documents();
            documentPOLTru.setUuidString();
            documentPOLTru.setType(App.POLTru);
            documentPOLTru.setInform(false);

            documentPOLTra = new Documents();
            documentPOLTra.setUuidString();
            documentPOLTra.setType(App.POLTra);
            documentPOLTra.setInform(false);

        }
        return root;
    }

    private void getDoc(){
        for (Documents d:addActivity.getDocuments())
            switch (d.getType()) {
                case App.GCTru:
                    documentGCTru = d;
                    break;

                case App.GCTra:
                    documentGCTra = d;
                    break;

                case App.WSTru:
                    documentWSTru = d;
                    break;

                case App.WSTra:
                    documentWSTra = d;
                    break;

                case App.EPTru:
                    documentEPTru =d;
                    break;

                case App.EPTra:
                    documentEPTra = d;
                    break;

                case App.TACHO:
                    documentTACHO = d;
                    break;

                case App.YSTra:
                    documentYSTra = d;
                    break;

                case App.POLTru:
                    documentPOLTru = d;
                    break;

                case App.POLTra:
                    documentPOLTra = d;
                    break;

            }
    }

    private void findUI(View view) {
        greenCartDateTruck = (TextView) view.findViewById(R.id.greenCardDateTruck);
        certDateTruck = (TextView) view.findViewById(R.id.certDateTruck);
        europackDateTruck = (TextView) view.findViewById(R.id.europackDateTruck);
        tachoDateTruck = (TextView) view.findViewById(R.id.tachoDateTruck);
        strachDateTruck = (TextView) view.findViewById(R.id.policyDateTruck);

        greenCartDateTrailer = (TextView) view.findViewById(R.id.greenCardDateTrailer);
        certDateTrailer = (TextView) view.findViewById(R.id.certDateTrailer);
        europackDateTrailer = (TextView) view.findViewById(R.id.europackDateTrailer);
        svidDateTrailer = (TextView) view.findViewById(R.id.svidYellowDateTrailer);
        strachDateTrailer = (TextView) view.findViewById(R.id.strachDateTrailer);

        greenCartPriceTruck = (EditText) view.findViewById(R.id.grenCardPriceTruck);
        certPriceTruck = (EditText) view.findViewById(R.id.certPriceTruck);
        europackPriceTruck = (EditText) view.findViewById(R.id.europackPriceTruck);
        tachoPriceTruck = (EditText) view.findViewById(R.id.tachoPriceTruck);
        strachPriceTruck = (EditText) view.findViewById(R.id.strachPriceTruck);

        greenCartPriceTrailer = (EditText) view.findViewById(R.id.greenCardPriceTrailer);
        certPriceTrailer = (EditText) view.findViewById(R.id.certPriceTrailer);
        europackPriceTrailer = (EditText) view.findViewById(R.id.europackPriceTrailer);
        svidPriceTrailer = (EditText) view.findViewById(R.id.svidYellowPriceTrailer);
        strachPriceTrailer = (EditText) view.findViewById(R.id.strachCardPriceTrailer);

        nomer = (EditText) view.findViewById(R.id.nomerZnakTruck);
        trailerNomer = (EditText) view.findViewById(R.id.nomerZnakTrailer);

        save   = (Button) view.findViewById(R.id.save);
        delete = (Button) view.findViewById(R.id.delete);

    }

    private void setListener() {
        greenCartDateTruck.setOnClickListener(this);
        certDateTruck.setOnClickListener(this);
        europackDateTruck.setOnClickListener(this);
        tachoDateTruck.setOnClickListener(this);
        strachDateTruck.setOnClickListener(this);

        greenCartDateTrailer.setOnClickListener(this);
        certDateTrailer.setOnClickListener(this);
        europackDateTrailer.setOnClickListener(this);
        svidDateTrailer.setOnClickListener(this);
        strachDateTrailer.setOnClickListener(this);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTruck();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAll();
            }
        });


    }

    public void saveTruck() {

        final List<Documents> documentsList = new ArrayList<>();

        truck.setNomer(nomer.getText().toString());
        truck.setTrailerNomer(trailerNomer.getText().toString());


        documentGCTru.setEndDate(DateHelper.convertStringToLong(greenCartDateTruck.getText().toString()));
        documentGCTru.setPrice(greenCartPriceTruck.getText().toString());
        documentGCTru.setTruck(truck);

        documentGCTra.setEndDate(DateHelper.convertStringToLong(greenCartDateTrailer.getText().toString()));
        documentGCTra.setPrice(greenCartPriceTrailer.getText().toString());
        documentGCTra.setTruck(truck);

        documentWSTru.setEndDate(DateHelper.convertStringToLong(certDateTruck.getText().toString()));
        documentWSTru.setPrice(certPriceTruck.getText().toString());
        documentWSTru.setTruck(truck);

        documentWSTra.setEndDate(DateHelper.convertStringToLong(certDateTrailer.getText().toString()));
        documentWSTra.setPrice(certPriceTrailer.getText().toString());
        documentWSTra.setTruck(truck);

        documentEPTru.setEndDate(DateHelper.convertStringToLong(europackDateTruck.getText().toString()));
        documentEPTru.setPrice(europackPriceTruck.getText().toString());
        documentEPTru.setTruck(truck);

        documentEPTra.setEndDate(DateHelper.convertStringToLong(europackDateTrailer.getText().toString()));
        documentEPTra.setPrice(europackPriceTrailer.getText().toString());
        documentEPTra.setTruck(truck);

        documentTACHO.setEndDate(DateHelper.convertStringToLong(tachoDateTruck.getText().toString()));
        documentTACHO.setPrice(tachoPriceTruck.getText().toString());
        documentTACHO.setTruck(truck);

        documentYSTra.setEndDate(DateHelper.convertStringToLong(svidDateTrailer.getText().toString()));
        documentYSTra.setPrice(svidPriceTrailer.getText().toString());
        documentYSTra.setTruck(truck);

        documentPOLTru.setEndDate(DateHelper.convertStringToLong(strachDateTruck.getText().toString()));
        documentPOLTru.setPrice(strachPriceTruck.getText().toString());
        documentPOLTru.setTruck(truck);

        documentPOLTra.setEndDate(DateHelper.convertStringToLong(strachDateTrailer.getText().toString()));
        documentPOLTra.setPrice(strachPriceTrailer.getText().toString());
        documentPOLTra.setTruck(truck);

        if(isEdit)
            compare();

        documentsList.add(documentGCTru);
        documentsList.add(documentGCTra);
        documentsList.add(documentWSTru);
        documentsList.add(documentWSTra);
        documentsList.add(documentEPTru);
        documentsList.add(documentEPTra);
        documentsList.add(documentTACHO);
        documentsList.add(documentYSTra);
        documentsList.add(documentPOLTru);
        documentsList.add(documentPOLTra);

        for(Documents d:documentsList)
            d.saveEventually();
        truck.saveEventually();

        truck.pinInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {


                Documents.pinAllInBackground(documentsList, new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        getActivity().setResult(Activity.RESULT_OK);
                        getActivity().finish();

                    }
                });

            }
        });

    }

    @Override
    public void onClick(View v) {
        openDateDialog(v);

    }

    private void openDateDialog(View v){
        DialogFragment picker = new DatePickerFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(App.DATE_VIEW, v.getId());
        picker.setArguments(bundle);
        picker.show(getFragmentManager(), "datePicker");

    }




    private void setEditData() {
        nomer.setText(truck.getNomer());
        trailerNomer.setText(truck.getTrailerNomer());

        greenCartDateTruck.setText(DateHelper.convertLongToString(documentGCTru.getEndDate()));
        greenCartPriceTruck.setText(documentGCTru.getPrice());

        greenCartDateTrailer.setText(DateHelper.convertLongToString(documentGCTra.getEndDate()));
        greenCartPriceTrailer.setText(documentGCTra.getPrice());

        certDateTruck.setText(DateHelper.convertLongToString(documentWSTru.getEndDate()));
        certPriceTruck.setText(documentWSTru.getPrice());

        certDateTrailer.setText(DateHelper.convertLongToString(documentWSTra.getEndDate()));
        certPriceTrailer.setText(documentWSTra.getPrice());

        europackDateTruck.setText(DateHelper.convertLongToString(documentEPTru.getEndDate()));
        europackPriceTruck.setText(documentEPTru.getPrice());

        europackDateTrailer.setText(DateHelper.convertLongToString(documentEPTra.getEndDate()));
        europackPriceTrailer.setText(documentEPTra.getPrice());

        tachoDateTruck.setText(DateHelper.convertLongToString(documentTACHO.getEndDate()));
        tachoPriceTruck.setText(documentTACHO.getPrice());

        svidDateTrailer.setText(DateHelper.convertLongToString(documentYSTra.getEndDate()));
        svidPriceTrailer.setText(documentYSTra.getPrice());

        strachDateTruck.setText(DateHelper.convertLongToString(documentPOLTru.getEndDate()));
        strachPriceTruck.setText(documentPOLTru.getPrice());

        strachDateTrailer.setText(DateHelper.convertLongToString(documentPOLTra.getEndDate()));
        strachPriceTrailer.setText(documentPOLTra.getPrice());
    }

    public void deleteAll(){
        deleteAllDocuments();
        deleteTruck();
    }

//    public void deleteAllReys(){
//        Reys.deleteAllInBackground(addActivity.getReyses(), new DeleteCallback() {
//            @Override
//            public void done(ParseException e) {
//                deleteAllOil();
//            }
//
//        });
//
//    }

    public void deleteAllDocuments(){

        for (Documents d : addActivity.getDocuments())
            d.deleteEventually();

        for (Reys r : addActivity.getReyses())
            r.deleteEventually();

        for (Oil o : addActivity.getOils())
            o.deleteEventually();

        for (Servis s : addActivity.getServices())
            s.deleteEventually();

//        Documents.deleteAllInBackground(addActivity.getDocuments(), new DeleteCallback() {
//            @Override
//            public void done(ParseException e) {
//                deleteAllReys();
//            }
//        });
    }

//    public void deleteAllOil(){
//        Oil.deleteAllInBackground(addActivity.getOils(), new DeleteCallback() {
//            @Override
//            public void done(ParseException e) {
//                deleteAllService();
//            }
//        });
//    }
//
//    public void deleteAllService(){
//        Servis.deleteAllInBackground(addActivity.getServices(),  new DeleteCallback() {
//            @Override
//            public void done(ParseException e) {
//
//            }
//
//            });

//    }

    public void deleteTruck(){
        truck.deleteEventually();
        addActivity.finish();
    }

    public void saveTempDates(){
        GCTru = documentGCTru.getEndDate();
        GCTra = documentGCTra.getEndDate();
        WSTru = documentWSTru.getEndDate();
        WSTra = documentWSTra.getEndDate();
        EPTru = documentEPTru.getEndDate();
        EPTra = documentEPTra.getEndDate();
        TACHO = documentTACHO.getEndDate();
        YSTra = documentYSTra.getEndDate();
        POLTru = documentPOLTru.getEndDate();
        POLTra = documentPOLTra.getEndDate();
    }
    public void compare(){
        if(GCTru != DateHelper.convertStringToLong(greenCartDateTruck.getText().toString()))
            documentGCTru.setInform(false);

        if(GCTra != DateHelper.convertStringToLong(greenCartDateTrailer.getText().toString()))
            documentGCTra.setInform(false);

        if(WSTru != DateHelper.convertStringToLong(certDateTruck.getText().toString()))
            documentWSTru.setInform(false);

        if(WSTra != DateHelper.convertStringToLong(certDateTrailer.getText().toString()))
            documentWSTra.setInform(false);

        if(EPTru != DateHelper.convertStringToLong(europackDateTruck.getText().toString()))
            documentEPTru.setInform(false);

        if(EPTra != DateHelper.convertStringToLong(europackDateTrailer.getText().toString()))
            documentEPTra.setInform(false);

        if(TACHO != DateHelper.convertStringToLong(tachoDateTruck.getText().toString()))
            documentTACHO.setInform(false);

        if(YSTra != DateHelper.convertStringToLong(svidDateTrailer.getText().toString()))
            documentYSTra.setInform(false);

        if(POLTru != DateHelper.convertStringToLong(strachDateTruck.getText().toString()))
            documentPOLTru.setInform(false);

        if(POLTra != DateHelper.convertStringToLong(strachDateTrailer.getText().toString()))
            documentPOLTra.setInform(false);
    }

}
