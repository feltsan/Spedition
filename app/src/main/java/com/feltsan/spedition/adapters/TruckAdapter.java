package com.feltsan.spedition.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.feltsan.spedition.R;
import com.feltsan.spedition.model.Truck;
import com.parse.ParseQueryAdapter;


/**
 * Created by john on 04.10.15.
 */
public class TruckAdapter extends ParseQueryAdapter<Truck> {

    private LayoutInflater inflater;

    public TruckAdapter(Context context,
                           ParseQueryAdapter.QueryFactory<Truck> queryFactory) {
        super(context, queryFactory);

       inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getItemView(Truck truck, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {
            view = inflater.inflate(R.layout.grid_simple,parent, false);
            holder = new ViewHolder();
            holder.truckNomer = (TextView) view
                    .findViewById(R.id.truck_nomer);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        TextView truckNomer = holder.truckNomer;
        truckNomer.setText(truck.getNomer());

        return view;
    }


    private static class ViewHolder {
    TextView truckNomer;
}
}

