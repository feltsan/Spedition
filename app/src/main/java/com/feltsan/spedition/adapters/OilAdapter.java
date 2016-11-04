package com.feltsan.spedition.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.feltsan.spedition.App;
import com.feltsan.spedition.R;
import com.feltsan.spedition.model.Oil;
import com.feltsan.spedition.ui.activity.AddActivity;
import com.feltsan.spedition.ui.activity.DetailActivity;
import com.feltsan.spedition.until.DateHelper;
import com.parse.ParseQueryAdapter;


/**
 * Created by john on 04.10.15.
 */
public class OilAdapter extends ParseQueryAdapter<Oil> {

    private LayoutInflater inflater;
    private DetailActivity detailActivity;

    public OilAdapter(Context context,
                      ParseQueryAdapter.QueryFactory<Oil> queryFactory) {
        super(context, queryFactory);

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        detailActivity = (DetailActivity) context;
    }

    @Override
    public View getItemView(final Oil oil, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {
            view = inflater.inflate(R.layout.item_oil, parent, false);
            holder = new ViewHolder();
            holder.layout = (RelativeLayout) view.findViewById(R.id.layout);
            holder.date = (TextView) view.findViewById(R.id.dateOil);
            holder.distance = (TextView) view.findViewById(R.id.kmOil);
            holder.brend = (TextView) view.findViewById(R.id.oilBrend);
            holder.oilF = (CheckBox) view.findViewById(R.id.oilFilterCB);
            holder.airF = (CheckBox) view.findViewById(R.id.airFilterCB);
            holder.fuelF = (CheckBox) view.findViewById(R.id.fuelFilterCB);
            holder.glagodel = (CheckBox) view.findViewById(R.id.glagodelCB);
            holder.price = (TextView) view.findViewById(R.id.priceOil);
            holder.service = (TextView) view.findViewById(R.id.serviceOil);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        TextView date = holder.date;
        TextView distance = holder.distance;
        TextView brend = holder.brend;
        CheckBox oilF = holder.oilF;
        CheckBox airF = holder.airF;
        CheckBox fuelF = holder.fuelF;
        CheckBox glagodel = holder.glagodel;
        TextView price = holder.price;
        TextView service = holder.service;
        RelativeLayout layout = holder.layout;

        date.setText(DateHelper.convertLongToString(oil.getDate()));
        distance.setText(oil.getDistance());
        brend.setText(oil.getBrend());
        oilF.setChecked(oil.getOilFiter());
        airF.setChecked(oil.getAirFilter());
        fuelF.setChecked(oil.getFuelFilter());
        glagodel.setChecked(oil.getGlagoDel());
        price.setText(oil.getPrice());
        service.setText(oil.getService());

        layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                openEditView(oil);
                return false;
            }
        });

        return view;
    }

    private void openEditView(Oil oil) {
        Intent i = new Intent(detailActivity, AddActivity.class);
        i.putExtra("ID", oil.getUuidString());
        i.putExtra("CODE", App.EDIT_OIL_CODE);
        detailActivity.startActivityForResult(i, App.EDIT_TRUCK_CODE);
    }


    private static class ViewHolder {
        RelativeLayout layout;
        TextView date;
        TextView distance;
        TextView brend;
        CheckBox oilF;
        CheckBox airF;
        CheckBox fuelF;
        CheckBox glagodel;
        TextView price;
        TextView service;
    }
}