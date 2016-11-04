package com.feltsan.spedition.ui.fragment;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.feltsan.spedition.R;
import com.feltsan.spedition.ui.activity.LoginActivity;


/**
 * Created by john on 26.09.tacho.
 */
public class  SignUpFragment extends Fragment {
    EditText pass;
    EditText repass;
    LoginActivity loginActivity;
    SharedPreferences passSP;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        loginActivity = (LoginActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.sign_up_fragment,container,false);
        passSP = getActivity().getPreferences(Context.MODE_PRIVATE);

        Button ok = (Button) root.findViewById(R.id.button);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkPass())
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, new SignInFragment())
                    .commit();
            }
        });

        pass = (EditText) root.findViewById(R.id.editName);
        repass = (EditText) root.findViewById(R.id.editName2);


        return root;
    }
    public boolean checkPass(){

        if(pass.getText().length()==0 || repass.getText().length()==0 ){
            repass.setError("Заповни поля!");
//            Toast.makeText(getActivity().getApplicationContext(),"Заповни поля!",Toast.LENGTH_SHORT).show();
            return  false;
        }else{
            if (pass.getText().toString().equals(repass.getText().toString())) {
                savePass(pass.getText().toString());
                return true;
            }
            else
                Toast.makeText(getActivity().getApplicationContext(),"Різні паролі!",Toast.LENGTH_SHORT).show();
                return false;
        }
    }
    public void savePass(String pass){
            passSP
                .edit()
                .putString(loginActivity.PASSKEY, pass)
                .commit();
    }
}
