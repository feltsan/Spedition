package com.feltsan.spedition.ui.fragment;

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
import android.widget.Toast;

import com.feltsan.spedition.R;
import com.feltsan.spedition.ui.activity.AllTruckActivity;
import com.feltsan.spedition.ui.activity.LoginActivity;


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
                    startActivity(new Intent(getActivity(), AllTruckActivity.class));
                    passSP.edit().putBoolean(loginActivity.CHECKPASS, false).commit();
                    getActivity().finish();
                }
            }
        });
        return root;
    }
    public boolean chekPass(String pass){
        if (pass.equals(passSP.getString(loginActivity.PASSKEY,"")))
            return true;
        else{
            Toast.makeText(getActivity().getApplicationContext(),"Неправильно!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
