package com.feltsan.spedition.ui.fragment;

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

import com.feltsan.spedition.App;
import com.feltsan.spedition.R;
import com.feltsan.spedition.model.Servis;
import com.feltsan.spedition.ui.activity.AddActivity;
import com.feltsan.spedition.until.DateHelper;
import com.parse.ParseException;
import com.parse.SaveCallback;


/**
 * Created by john on 04.10.15.
 */
public class AddServiceFragment extends Fragment implements View.OnClickListener {

    private View view;
    private TextView date;
    private EditText distance, zapchast, brend, price, service, shop;
    private Button save, delete;
    private AddActivity addActivity;
    private Servis servis = null;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        addActivity = (AddActivity) context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_service, container, false);
        findUI(view);
        setListener();

        if(savedInstanceState!=null){
            servis = (Servis) savedInstanceState.getSerializable("Service");
            setEditData();
            delete.setVisibility(View.VISIBLE);
        }else if(addActivity.getServis()!=null) {
            servis = addActivity.getServis();
            setEditData();
            delete.setVisibility(View.VISIBLE);
        }else {
            servis = new Servis();
            servis.setUuidString();
            servis.setTruck(addActivity.getTruck());
        }

        return view;
    }

    public void findUI(View view) {
        date = (TextView) view.findViewById(R.id.dateService);
        distance = (EditText) view.findViewById(R.id.kmService);
        zapchast = (EditText) view.findViewById(R.id.zapchastService);
        price = (EditText) view.findViewById(R.id.priceService);
        brend = (EditText) view.findViewById(R.id.brendService);
        service = (EditText) view.findViewById(R.id.serviceName);
        save = (Button) view.findViewById(R.id.save);
        delete = (Button) view.findViewById(R.id.delete);
        shop = (EditText) view.findViewById(R.id.shopService);
    }

    public void setListener() {
        save.setOnClickListener(this);
        delete.setOnClickListener(this);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDateDialog(v);
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.save:
                save();
                break;

            case R.id.delete:
                deleteService();
                break;

        }

    }

    private void openDateDialog(View v){
        DialogFragment picker = new DatePickerFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(App.DATE_VIEW, v.getId());
        picker.setArguments(bundle);
        picker.show(getFragmentManager(), "datePicker");
    }

    public void save(){
        servis.setDate(DateHelper.convertStringToLong(date.getText().toString()));
        servis.setDistance(distance.getText().toString());
        servis.setBrend(brend.getText().toString());
        servis.setZapchast(zapchast.getText().toString());
        servis.setPrice(price.getText().toString());
        servis.setShop(shop.getText().toString());
        servis.setService(service.getText().toString());
        servis.saveEventually();
        servis.pinInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                getActivity().finish();
            }
        });
    }

    private void setEditData() {
        date.setText(DateHelper.convertLongToString(servis.getDate()));
        distance.setText(servis.getDistance());
        brend.setText(servis.getBrend());
        zapchast.setText(servis.getZapchast());
        price.setText(servis.getPrice());
        shop.setText(servis.getShop());
        service.setText(servis.getService());
    }

    public void deleteService(){
        servis.deleteEventually();
        addActivity.finish();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("Service", servis);
    }

}
