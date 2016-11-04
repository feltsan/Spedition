package com.feltsan.spedition.ui.fragment;

import android.app.ProgressDialog;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.feltsan.spedition.R;
import com.feltsan.spedition.model.Documents;
import com.feltsan.spedition.model.Oil;
import com.feltsan.spedition.model.Reys;
import com.feltsan.spedition.model.Servis;
import com.feltsan.spedition.model.Truck;
import com.feltsan.spedition.ui.activity.AllTruckActivity;
import com.feltsan.spedition.ui.activity.LoginActivity;
import com.feltsan.spedition.until.ShPrManager;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.List;


/**
 * Created by john on 26.09.tacho.
 */
public class SignInFragment extends Fragment {
    EditText pass;
    SharedPreferences passSP;
    LoginActivity loginActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        loginActivity = (LoginActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.sign_in_fragment, container, false);

        passSP = getActivity().getPreferences(Context.MODE_PRIVATE);
        pass = (EditText) root.findViewById(R.id.editName);
        Button ok = (Button) root.findViewById(R.id.button);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chekPass(pass.getText().toString())) {
                   if(!ShPrManager.getDownloaded(getContext()))
                       loadFromParse();
                    else
                       openMainActvivity();
                }
            }
        });
        return root;
    }
    public boolean chekPass(String pass){
        if (pass.equals(passSP.getString(loginActivity.PASSKEY, "")))
            return true;
        else{
            Toast.makeText(getActivity().getApplicationContext(),"Неправильно!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
    ProgressDialog dialog;

    private void loadFromParse(){
       if (checkInternetConnection()){
           dialog = ProgressDialog.show(getContext(), "Загрузка даних", "...", true);
           loadTrucks();
       }else
           Toast.makeText(getActivity(),"Не підключений до інтернету!", Toast.LENGTH_SHORT).show();
    }

    public void loadTrucks(){
        ParseQuery<Truck> query = Truck.getQuery();
        query.findInBackground(new FindCallback<Truck>() {
            @Override
            public void done(List<Truck> objects, ParseException e) {
                if (e == null) {
                    ParseObject.pinAllInBackground((List<Truck>) objects,
                            new SaveCallback() {
                                @Override
                                public void done(ParseException e) {
                                    if (e == null) {
                                        loadOils();
                                    } else {
                                        Toast.makeText(getContext(),
                                                "Помилка збереження: "
                                                        + e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } else
                    Toast.makeText(getContext(),
                            "Помилка завантаження: "
                                    + e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void loadOils(){
        ParseQuery<Oil> query = Oil.getQuery();
        query.findInBackground(new FindCallback<Oil>() {
            @Override
            public void done(List<Oil> objects, ParseException e) {
                if (e == null) {
                    ParseObject.pinAllInBackground((List<Oil>) objects,
                            new SaveCallback() {
                                @Override
                                public void done(ParseException e) {
                                    if (e == null) {
                                        loadDocuments();
                                    } else {
                                        Toast.makeText(getContext(),
                                                "Помилка збереження: "
                                                        + e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }else
                    Toast.makeText(getContext(),
                            "Помилка завантаження: "
                                    + e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void loadDocuments(){
        ParseQuery<Documents> query = Documents.getQuery();
        query.findInBackground(new FindCallback<Documents>() {
            @Override
            public void done(List<Documents> objects, ParseException e) {
                if (e == null) {
                    ParseObject.pinAllInBackground((List<Documents>) objects,
                            new SaveCallback() {
                                @Override
                                public void done(ParseException e) {
                                    if (e == null) {
                                        loadReyses();
                                    } else {
                                        Toast.makeText(getContext(),
                                                "Помилка збереження: "
                                                        + e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }else
                    Toast.makeText(getContext(),
                            "Помилка завантаження: "
                                    + e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void loadReyses(){

        ParseQuery<Reys> query = Reys.getQuery();
        query.findInBackground(new FindCallback<Reys>() {
            @Override
            public void done(List<Reys> objects, ParseException e) {
                if (e == null) {
                    ParseObject.pinAllInBackground((List<Reys>) objects,
                            new SaveCallback() {
                                @Override
                                public void done(ParseException e) {
                                    if (e == null) {
                                        loadServices();
                                    } else {
                                        Toast.makeText(getContext(),
                                                "Помилка збереження: "
                                                        + e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }else
                    Toast.makeText(getContext(),
                            "Помилка завантаження: "
                                    + e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void loadServices(){
        ParseQuery<Servis> query = Servis.getQuery();
        query.findInBackground(new FindCallback<Servis>() {
            @Override
            public void done(List<Servis> objects, ParseException e) {
                if (e == null) {
                    ParseObject.pinAllInBackground((List<Servis>) objects,
                            new SaveCallback() {
                                @Override
                                public void done(ParseException e) {
                                    if (e == null) {
                                        openMainActvivity();
                                        ShPrManager.setDownloaded(getActivity(),true);
                                    } else {
                                        Toast.makeText(getContext(),
                                                "Помилка збереження: "
                                                        + e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                    dialog.dismiss();
                }else
                    Toast.makeText(getContext(),
                            "Помилка завантаження: "
                                    + e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void openMainActvivity(){
        startActivity(new Intent(getActivity(), AllTruckActivity.class));
        passSP.edit().putBoolean(loginActivity.CHECKPASS, false).commit();
        getActivity().finish();
    }

    private boolean checkInternetConnection(){
        ConnectivityManager connectivity = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null)
        {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED)
                    {
                        return true;
                    }
        }
        return false;
    }
}
