package com.example.freetime.foodrush;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by User on 1/1/2018.
 */

public class Einkäufe_adapter extends RecyclerView.Adapter<Einkäufe_adapter.ViewHolder> {
    private static final String TAG = "Adapter";

    private ArrayList<String> Produkte = new ArrayList<>();
    private ArrayList<String> Anzahl = new ArrayList<>();


    private Context mContext;

    public Einkäufe_adapter(ArrayList<String> produkte, ArrayList<String> anzahl, Context mContext) {
        Produkte = produkte;
        Anzahl = anzahl;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_einkaufe, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{


        TextView produkt , anzahl;



        public ViewHolder(View itemView) {
            super(itemView);
anzahl = itemView.findViewById(R.id.anzahl_kaufe);
            produkt= itemView.findViewById(R.id.produkt);

        }
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        holder.anzahl.setText(Anzahl.get(position));
        holder.produkt.setText(Produkte.get(position));




    }



    @Override
    public int getItemCount() {
        return Produkte.size();
    }
    public void scaleView() {


    }



}

