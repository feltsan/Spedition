package com.feltsan.spedition.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.feltsan.spedition.App;
import com.feltsan.spedition.R;
import com.feltsan.spedition.model.Documents;
import com.feltsan.spedition.model.Oil;
import com.feltsan.spedition.until.DateHelper;
import com.parse.ParseException;
import com.parse.ParseQueryAdapter;


/**
 * Created by john on 13.10.15.
 */
public class AllOilsAdapter extends ParseQueryAdapter<Oil> {

    private LayoutInflater inflater;
    private Context context;

    public AllOilsAdapter(Context context, QueryFactory<Oil> queryFactory) {
        super(context, queryFactory);
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
    }

    @Override
    public View getItemView(final Oil oil, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {
            view = inflater.inflate(R.layout.item_oils, parent, false);
            holder = new ViewHolder();
            holder.nomer = (TextView) view.findViewById(R.id.nomerAllOils);
            holder.date = (TextView) view.findViewById(R.id.dateAllOils);
            holder.brend = (TextView) view.findViewById(R.id.brendAllOils);
            holder.distance = (TextView) view.findViewById(R.id.distanceAllOils);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        TextView date = holder.date;
        TextView nomer = holder.nomer;
        TextView brend = holder.brend;
        TextView distance = holder.distance;

        String nomertr = "";
        try {
            nomertr =oil.getTruck().fetchIfNeeded().getString("nomer");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        nomer.setText(nomertr);
        date.setText(DateHelper.convertLongToString(oil.getDate()));
        brend.setText(oil.getBrend());
        distance.setText(oil.getDistance());

        return view;
    }

    private static class ViewHolder {
        TextView nomer;
        TextView date;
        TextView brend;
        TextView distance;
    }
}

