package com.feltsan.spedition.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.feltsan.spedition.App;
import com.feltsan.spedition.R;
import com.feltsan.spedition.model.Servis;
import com.feltsan.spedition.ui.activity.AddActivity;
import com.feltsan.spedition.ui.activity.DetailActivity;
import com.feltsan.spedition.until.DateHelper;
import com.parse.ParseQueryAdapter;


/**
 * Created by john on 04.10.15.
 */
public class ServiceAdapter extends ParseQueryAdapter<Servis> {

    private LayoutInflater inflater;
    private DetailActivity detailActivity;

    public ServiceAdapter(Context context,
                          ParseQueryAdapter.QueryFactory<Servis> queryFactory) {
        super(context, queryFactory);

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        detailActivity = (DetailActivity) context;
    }

    @Override
    public View getItemView(final Servis service, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {
            view = inflater.inflate(R.layout.item_service, parent, false);
            holder = new ViewHolder();
            holder.date = (TextView) view.findViewById(R.id.dateService);
            holder.distance = (TextView) view.findViewById(R.id.kmService);
            holder.brend = (TextView) view.findViewById(R.id.brendService);
            holder.zapchast = (TextView) view.findViewById(R.id.zapchastService);
            holder.price = (TextView) view.findViewById(R.id.priceBrend);
            holder.service = (TextView) view.findViewById(R.id.serviceName);
            holder.layout = (RelativeLayout) view.findViewById(R.id.layout);
            holder.shop = (TextView) view.findViewById(R.id.shopService);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        TextView date = holder.date;
        TextView distance = holder.distance;
        TextView brend = holder.brend;
        TextView zapchast = holder.zapchast;
        TextView price = holder.price;
        TextView servis = holder.service;
        TextView shop = holder.shop;
        RelativeLayout layout = holder.layout;

        date.setText(DateHelper.convertLongToString(service.getDate()));
        distance.setText(service.getDistance());
        brend.setText(service.getBrend());
        zapchast.setText(service.getZapchast());
        price.setText(service.getPrice());
        shop.setText(service.getShop());
        servis.setText(service.getService());

        layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                openEditView(service);
                return false;
            }
        });

        return view;
    }

    private void openEditView(Servis servis) {
        Intent i = new Intent(detailActivity, AddActivity.class);
        i.putExtra("ID", servis.getUuidString());
        i.putExtra("CODE", App.EDIT_SERVICE_CODE);
        detailActivity.startActivityForResult(i, App.EDIT_TRUCK_CODE);
    }


    private static class ViewHolder {
        RelativeLayout layout;
        TextView date;
        TextView distance;
        TextView zapchast;
        TextView brend;
        TextView price;
        TextView service;
        TextView shop;
    }
}
