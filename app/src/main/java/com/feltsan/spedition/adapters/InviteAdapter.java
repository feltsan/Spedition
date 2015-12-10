package com.feltsan.spedition.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.feltsan.spedition.R;
import com.feltsan.spedition.model.Invite;

import java.util.ArrayList;


/**
 * Created by john on 11.10.15.
 */
public class InviteAdapter extends BaseAdapter {
    Context context;
    ArrayList<Invite> invite;
    private LayoutInflater inflater;

    public InviteAdapter(Context context, ArrayList<Invite> maps) {
        this.context = context;
        this.invite = maps;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return invite.size();
    }

    @Override
    public Invite getItem(int position) {
        return invite.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_invite, parent, false);
            viewHolder = new ViewHolder(convertView);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.updateView(getItem(position),context);

        return convertView;
    }


    private static class ViewHolder {
        TextView nomer, date, type;
        RelativeLayout background;


        public ViewHolder(final View _convertView) {
            initUI(_convertView);
            _convertView.setTag(this);
        }

        private void initUI(final View _convertView) {
            nomer = (TextView) _convertView.findViewById(R.id.nomer);
            date = (TextView) _convertView.findViewById(R.id.date);
            type = (TextView) _convertView.findViewById(R.id.type);
            background = (RelativeLayout) _convertView.findViewById(R.id.background);
        }

        public final void updateView(final Invite invite, Context context) {
            nomer.setText(invite.getNomer());
            date.setText(invite.getDate());

            switch (invite.getType()){
                case "Зелена карта":
                    background.setBackgroundColor(context.getResources().getColor(android.R.color.holo_green_dark));
                    type.setBackgroundColor(context.getResources().getColor(android.R.color.holo_green_dark));
                    type.setText("Карта");
                break;
                case "Сертифікат":
                    background.setBackgroundColor(context.getResources().getColor(android.R.color.white));
                    type.setBackgroundColor(context.getResources().getColor(android.R.color.white));
                    type.setText("Cертиф.");
                    break;
                case "Європротокол":
                    background.setBackgroundColor(context.getResources().getColor(android.R.color.white));
                    type.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.eu));
                    type.setText("");
                    break;
                case "Тахограф":
                    background.setBackgroundColor(context.getResources().getColor(android.R.color.white));
                    type.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.ta));
                    type.setText("");
                    break;
                case "Жовте свідоцтво":
                    background.setBackgroundColor(Color.parseColor("#fff100"));
                    type.setBackgroundColor(Color.parseColor("#fff100"));
                    type.setText("Свід.");
                    break;
                case "Страховка":
                    background.setBackgroundColor(context.getResources().getColor(android.R.color.white));
                    type.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.stra));
                    type.setText("");
                    break;
                case "Масло":
                    background.setBackgroundColor(context.getResources().getColor(android.R.color.white));
                    type.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.oil));
                    type.setText("");
                    break;
            }
        }
    }
}
