package com.example.takatskristof_restapi;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.takatskristof_restapi.Varosok;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ListAdapter extends ArrayAdapter {
    private Activity mContext;
    List<Varosok> varosokList;

    public ListAdapter(Activity mContext,List<Varosok> varosokList){
        super(mContext,R.layout.list_item,varosokList);
        this.mContext=mContext;
        this.varosokList=varosokList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=mContext.getLayoutInflater();
        View listItemView=inflater.inflate(R.layout.list_item,null,true);

        TextView varos=listItemView.findViewById(R.id.textViewVaros);
        TextView orszag=listItemView.findViewById(R.id.textViewOrszag);
        TextView lakossag=listItemView.findViewById(R.id.textViewLakossag);

        Varosok varosok=varosokList.get(position);

        varos.setText(varosok.getVaros());
        orszag.setText(varosok.getOrszag());
        lakossag.setText(""+varosok.getLakossag());

        return listItemView;
    }
}
