package com.feltsan.spedition.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.feltsan.spedition.App;
import com.feltsan.spedition.R;
import com.feltsan.spedition.model.Documents;
import com.feltsan.spedition.until.DateHelper;
import com.parse.ParseQueryAdapter;


/**
 * Created by john on 13.10.15.
 */
public class AllDocumentsAdapter extends ParseQueryAdapter<Documents> {

    private LayoutInflater inflater;
    private Context context;

    public AllDocumentsAdapter(Context context, QueryFactory<Documents> queryFactory) {
        super(context, queryFactory);
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
    }

    @Override
    public View getItemView(final Documents documents, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {
            view = inflater.inflate(R.layout.item_documets, parent, false);
            holder = new ViewHolder();
            holder.date = (TextView) view.findViewById(R.id.date);
            holder.nomer = (TextView) view.findViewById(R.id.nomer);
            holder.type = (TextView) view.findViewById(R.id.type);
            holder.layout = (RelativeLayout) view.findViewById(R.id.background);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        TextView date = holder.date;
        TextView nomer = holder.nomer;
        TextView type = holder.type;
        RelativeLayout layout = holder.layout;

        date.setText(DateHelper.convertLongToString(documents.getEndDate()));


        switch (documents.getType()) {
            case App.GCTru:
                layout.setBackgroundColor(context.getResources().getColor(android.R.color.holo_green_dark));
                type.setBackgroundColor(context.getResources().getColor(android.R.color.holo_green_dark));
                nomer.setText(documents.getTruck().getNomer());
                type.setText("Карта");
                break;

            case App.GCTra:
                layout.setBackgroundColor(context.getResources().getColor(android.R.color.holo_green_dark));
                type.setBackgroundColor(context.getResources().getColor(android.R.color.holo_green_dark));
                type.setText("Карта");
                nomer.setText(documents.getTruck().getTrailerNomer());
                break;

            case App.WSTru:
                layout.setBackgroundColor(context.getResources().getColor(android.R.color.white));
                type.setBackgroundColor(context.getResources().getColor(android.R.color.white));
                type.setText("Сертиф.");
                nomer.setText(documents.getTruck().getNomer());
                break;

            case App.WSTra:
                layout.setBackgroundColor(context.getResources().getColor(android.R.color.white));
                type.setBackgroundColor(context.getResources().getColor(android.R.color.white));
                type.setText("Сертиф.");
                nomer.setText(documents.getTruck().getTrailerNomer());
                break;

            case App.EPTru:
                layout.setBackgroundColor(context.getResources().getColor(android.R.color.white));
                type.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.eu));
                type.setText("");
                nomer.setText(documents.getTruck().getNomer());
                break;

            case App.EPTra:
                layout.setBackgroundColor(context.getResources().getColor(android.R.color.white));
                type.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.eu));
                type.setText("");
                nomer.setText(documents.getTruck().getTrailerNomer());
                break;

            case App.TACHO:
                layout.setBackgroundColor(context.getResources().getColor(android.R.color.white));
                type.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.ta));
                type.setText("");
                nomer.setText(documents.getTruck().getNomer());
                break;

            case App.YSTra:
                layout.setBackgroundColor(Color.parseColor("#fff100"));
                type.setBackgroundColor(Color.parseColor("#fff100"));
                type.setText("Свід.");
                nomer.setText(documents.getTruck().getTrailerNomer());
                break;

            case App.POLTru:
                layout.setBackgroundColor(context.getResources().getColor(android.R.color.white));
                type.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.stra));
                type.setText("");
                nomer.setText(documents.getTruck().getNomer());
                break;

            case App.POLTra:
                layout.setBackgroundColor(context.getResources().getColor(android.R.color.white));
                type.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.stra));
                type.setText("");
                nomer.setText(documents.getTruck().getTrailerNomer());
                break;
        }


        return view;
    }



    private static class ViewHolder {
        RelativeLayout layout;
        TextView date;
        TextView type;
        TextView nomer;
    }
}

