package com.feltsan.spedition.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.feltsan.spedition.App;
import com.feltsan.spedition.R;
import com.feltsan.spedition.adapters.OilAdapter;
import com.feltsan.spedition.model.Oil;
import com.feltsan.spedition.ui.activity.AddActivity;
import com.feltsan.spedition.ui.activity.DetailActivity;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

import java.util.List;

/**
 * Created by john on 04.10.15.
 */
public class AllOilFragment extends Fragment implements View.OnClickListener,AdapterView.OnItemLongClickListener{

    private ListView oilList;
    private FloatingActionButton fab;
    private DetailActivity detailActivity;
    private OilAdapter oilAdapter;
    private ParseQueryAdapter.QueryFactory<Oil> factory;
    private TableLayout tableLayout;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        detailActivity = (DetailActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_all_oil, container, false);
        findUI(root);
        setListener();
        setAdapter();

        return root;
    }

    private void findUI(View view) {
        oilList = (ListView) view.findViewById(R.id.oilList);
        fab    = (FloatingActionButton) view.findViewById(R.id.fab);
        tableLayout = (TableLayout) view.findViewById(R.id.allOilTable);
        tableLayout.setVisibility(View.INVISIBLE);
    }

    private void setListener(){
        fab.setOnClickListener(this);
        oilList.setOnItemLongClickListener(this);
    }

    @Override
    public void onClick(View v) {
        openAddView();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//        openEditView(oilAdapter.getItem(position));
        return false;
    }



    private void openAddView() {
        Intent i = new Intent(getActivity(), AddActivity.class);
        i.putExtra("ID", detailActivity.getIntent().getExtras().getString("ID"));
        i.putExtra("CODE", App.OIL_CODE);

        startActivityForResult(i, App.OIL_CODE);
    }

    public void setAdapter() {
        setFactory();
        oilAdapter = new OilAdapter(getActivity(), factory);

        oilAdapter.addOnQueryLoadListener(new ParseQueryAdapter.OnQueryLoadListener<Oil>() {
            @Override
            public void onLoading() {

            }

            @Override
            public void onLoaded(List<Oil> objects, Exception e) {
                if (objects.size() > 0)
                    tableLayout.setVisibility(View.VISIBLE);
            }
        });

        oilList.setAdapter(oilAdapter);
    }

    public void setFactory() {
        factory = new ParseQueryAdapter.QueryFactory<Oil>() {
            @Override
            public ParseQuery<Oil> create() {
                ParseQuery<Oil> query = Oil.getQuery();
                query.include("truck");
                query.whereEqualTo("truck", detailActivity.getTruck());
                query.orderByAscending("date");
                query.fromLocalDatastore();
                return query;
            }
        };
    }

    @Override
    public void onResume() {
        super.onResume();
        oilAdapter.loadObjects();
    }

    public  void refreshAdapter(){
        oilAdapter.loadObjects();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        refreshAdapter();
    }
}


