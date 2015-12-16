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
import com.feltsan.spedition.model.Reys;
import com.feltsan.spedition.ui.activity.AddActivity;
import com.feltsan.spedition.ui.activity.DetailActivity;
import com.feltsan.spedition.until.DateHelper;
import com.parse.ParseQueryAdapter;

import java.util.Date;


/**
 * Created by john on 04.10.15.
 */
public class ReysAdapter extends ParseQueryAdapter<Reys> {

    private LayoutInflater inflater;

    int pos=0;
    private DetailActivity detailActivity;

    public ReysAdapter(Context context,
                        ParseQueryAdapter.QueryFactory<Reys> queryFactory) {
        super(context, queryFactory);

        detailActivity = (DetailActivity) context;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public Reys getItem(int index) {
        pos =index+1;
        return super.getItem(index);

    }

    @Override
    public View getItemView(final Reys reys, View view, ViewGroup parent) {
        ViewHolder holder;

        if (view == null) {
            view = inflater.inflate(R.layout.item_reys,parent, false);
            holder = new ViewHolder();
            holder.layout = (RelativeLayout) view.findViewById(R.id.layout);
            holder.date = (TextView) view.findViewById(R.id.reysDate);
            holder.start = (TextView) view.findViewById(R.id.startpoint);
            holder.finish = (TextView) view.findViewById(R.id.finishPoint);
            holder.client = (TextView) view.findViewById(R.id.client);
            holder.price = (TextView) view.findViewById(R.id.priceSpedition);
            holder.confirm = (CheckBox) view.findViewById(R.id.confirmReys);
            holder.nomer =  (TextView) view.findViewById(R.id.nomer);
//            pos++;
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        super.getItemView(reys, view, parent);

        TextView date = holder.date;
        TextView start = holder.start;
        TextView finish = holder.finish;
        TextView client = holder.client;
        TextView price = holder.price;
        final CheckBox confirm = holder.confirm;
        RelativeLayout layout = holder.layout;
        TextView nomer = holder.nomer;

        date.setText(DateHelper.convertLongToString(reys.getDate()));
        start.setText(reys.getStart());
        finish.setText(reys.getFinish());
        client.setText(reys.getClient());
        price.setText(reys.getPrice());
        nomer.setText(String.valueOf(pos));

        confirm.setChecked(reys.getConfirm());
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reys.setConfirm(confirm.isChecked());
                reys.saveInBackground();
            }
        });
        layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                openEditView(reys);
                return false;
            }
        });


        return view;
    }



    private void openEditView(Reys reys) {
        Intent i = new Intent(detailActivity, AddActivity.class);
        i.putExtra("ID", reys.getUuidString());
        i.putExtra("CODE", App.EDIT_REYS_CODE);
        detailActivity.startActivityForResult(i, App.EDIT_TRUCK_CODE);
    }

    private static class ViewHolder {
        RelativeLayout layout;
        TextView date;
        TextView nomer;
        TextView start;
        TextView finish;
        TextView client;
        TextView price;
        CheckBox confirm;
    }

}
