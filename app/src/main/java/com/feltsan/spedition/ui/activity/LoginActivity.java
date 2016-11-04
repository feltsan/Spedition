package com.feltsan.spedition.ui.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;


import android.support.v7.app.AppCompatActivity;

import com.feltsan.spedition.R;
import com.feltsan.spedition.ui.fragment.SignInFragment;
import com.feltsan.spedition.ui.fragment.SignUpFragment;


/**
 * Created by john on 26.09.tacho.
 */
public class LoginActivity extends AppCompatActivity {

    public static String PASSKEY = "passSP";
    public static String CHECKPASS = "pass";
    SharedPreferences passSP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        passSP = getPreferences(MODE_PRIVATE);

        if (passSP.getBoolean(CHECKPASS, true))
            getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new SignUpFragment())
                .commit();
        else
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new SignInFragment())
                    .commit();
    }
}
