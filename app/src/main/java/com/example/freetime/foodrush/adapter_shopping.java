package com.example.freetime.foodrush;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by User on 1/1/2018.
 */

public class adapter_shopping extends RecyclerView.Adapter<adapter_shopping.ViewHolder> {
    private static final String TAG = "Adapter";

    private ArrayList<String> Produkte= new ArrayList<>();




    private Context mContext;

    public adapter_shopping(ArrayList<String> produkte, Context mContext) {
        Produkte = produkte;
        this.mContext = mContext;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_shop_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
TextView Produkt;
ImageView delete;


        public ViewHolder(View itemView) {
            super(itemView);

Produkt = itemView.findViewById(R.id.produkt);
delete = itemView.findViewById(R.id.delete);
        }
    }



    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

holder.Produkt.setText(Produkte.get(position));
holder.delete.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        int newPosition = holder.getAdapterPosition();
        Log.d("thien.van","on Click onBindViewHolder");
        Produkte.remove(newPosition);
        notifyItemRemoved(newPosition);
        notifyDataSetChanged();
        notifyItemRangeChanged(newPosition, Produkte.size());




    }
});
    }


    @Override
    public int getItemCount() {
        return Produkte.size();
    }





    }





