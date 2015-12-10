package com.feltsan.spedition.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.feltsan.spedition.R;
import com.feltsan.spedition.adapters.InviteAdapter;
import com.feltsan.spedition.model.Documents;
import com.feltsan.spedition.model.Invite;
import com.feltsan.spedition.model.Oil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by john on 11.10.15.
 */
public class InviteActivity extends AppCompatActivity {

    private ListView listView;
    private Button button;
    private List<Documents> documentses;
    private List<Oil> oils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite);


        ArrayList<Invite> invites = new ArrayList<>();

        if(this.getIntent().getParcelableArrayListExtra("arrayinvites")!=null)
            invites = this.getIntent().getParcelableArrayListExtra("arrayinvites");

        listView = (ListView) findViewById(R.id.documentsList);
        listView.setAdapter(new InviteAdapter(this, invites));

        button = (Button) findViewById(R.id.close);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



}
