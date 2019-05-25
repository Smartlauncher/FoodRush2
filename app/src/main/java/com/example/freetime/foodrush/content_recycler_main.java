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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by User on 1/1/2018.
 */

public class content_recycler_main extends RecyclerView.Adapter<content_recycler_main.ViewHolder> {
    private static final String TAG = "Adapter";
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ArrayList<String> Name = new ArrayList<>();
    private ArrayList<String> mImageUrl = new ArrayList<>();
    private ArrayList<String>Geld = new ArrayList<>();
    private List<String> Produkt = new ArrayList<>();

    private ArrayList<String>Lebensmittel = new ArrayList<>();
    private ArrayList<String> Wo = new ArrayList<>();
    private ArrayList<String> Wann = new ArrayList<>();
    private int test = 0;
    private ArrayList<String> UserID = new ArrayList<>();
    private SharedPreferences Name_User;

    private ArrayList<String> Produkte = new ArrayList<>();
    private  String Name_String;
    private ArrayList<Boolean> Angenommen = new ArrayList<>();
    private ArrayList<String> document3 = new ArrayList<>();


    private Context mContext;

    public content_recycler_main(ArrayList<String> name, ArrayList<String> mImageUrl, ArrayList<String> geld, ArrayList<String> wo, ArrayList<String> wann, ArrayList<String> userID, List<String> produkte, ArrayList<Boolean> angenommen, ArrayList<String> document2, Class<MainActivity> mContext) {
        Name = name;
        this.mImageUrl = mImageUrl;
        Geld = geld;

        Wo = wo;
        Wann = wann;
        this.test = test;
        UserID = userID;



        Angenommen = angenommen;
        this.document3 = document2;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item_earn, parent, false);
        ViewHolder holder = new ViewHolder(view);
Name_User = mContext.getSharedPreferences("Name_User" , Context.MODE_PRIVATE);
Name_String = Name_User.getString("Name_User" , null);

        return holder;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

CircleImageView pic;
         SharedPreferences shared2 = mContext.getSharedPreferences("Mission" , Context.MODE_PRIVATE);
RecyclerView rec;
        final RecyclerView recyclerView;
        TextView title , anzahl , produkt , geld , ort ,wann;

        Spinner spinner;
                ImageView accept;
        RelativeLayout parentLayout;


        public ViewHolder(View itemView) {
            super(itemView);
            wann = itemView.findViewById(R.id.custom_wann);
            accept = itemView.findViewById(R.id.accept);
            title= itemView.findViewById(R.id.custom_name);
            geld = itemView.findViewById(R.id.custom_geld);
            ort = itemView.findViewById(R.id.custom_wo);
         //   date = itemView.findViewById(R.id.content_time);
           recyclerView = (RecyclerView) itemView.findViewById(R.id.rec_list);
pic = itemView.findViewById(R.id.img_main);
        spinner = (Spinner) itemView.findViewById(R.id.drop_items);
            parentLayout = itemView.findViewById(R.id.parent_main);

        }
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");




int size = Produkt.size();
        db.collection("Anzeigen")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {


                                List<String> list = (List<String>) document.get("Lebensmittel");
                                for (String item : list) {
                                    Log.d("TAG", item);

                                    Produkte.add(item);

                                    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                                            mContext, android.R.layout.simple_spinner_item,  Produkte);
                                    spinnerArrayAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
// Spinner spinYear = (Spinner)findViewById(R.id.spin);
                                    holder.spinner.setAdapter(spinnerArrayAdapter);
                                }


                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });



        holder.geld.setText(Geld.get(position));
holder.wann.setText(Wann.get(position));
holder.ort.setText(Wo.get(position));






        holder.title.setText(Name.get(position));
     //   holder.date.setText(Wann.get(position));
        Picasso.get().load(mImageUrl.get(position)).fit().centerCrop().into(holder.pic);


        holder.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.collection("Anzeigen").document(document3.get(position)).delete();

                final Animation myAnim = AnimationUtils.loadAnimation(mContext, R.anim.bounce);
                // Use bounce interpolator with amplitude 0.1 and frequency 15
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.1, 15);
                myAnim.setInterpolator(interpolator);

//SharedPreferences.Editor edit = mContext.getSharedPreferences("Mission" , Context.MODE_PRIVATE).edit();
//edit.putBoolean("Mission",true );
//edit.apply();



                FirebaseFirestore db = FirebaseFirestore.getInstance();
                // Create a new user with a first and last name



// Add a new document with a generated ID
            //    db.collection("Anzeigen").document(ID.get(position))
            //            .update("Angenommen" , true);
            //    // Create a new user with a first and last name
            //    Map<String, Object>  Anzeigen= new HashMap<>();
            //    Anzeigen.put("Angenommen", true);
            //    Anzeigen.put("Geld", Geld.get(position));
            //    Anzeigen.put("ID", ID.get(position));
            //    Anzeigen.put("ImgURL", mImageUrl.get(position));
            //    Anzeigen.put("Name" , Name.get(position));
            //    Anzeigen.put("UserID" , UserID.get(position));
            //    Anzeigen.put("Wann" , Wann.get(position));
            //    Anzeigen.put("Wo" , Wo.get(position));
            //    Anzeigen.put("Anzahl" , Anzahl.get(position));
            //    Anzeigen.put("Lebensmittel" , Produkt.get(position));
//
            //    Anzeigen.put("Name_User" , Name_String );

// Add a new document with a generated ID
             //  db.collection("Angenommen")
             //          .add(Anzeigen)
             //          .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
             //              @Override
             //              public void onSuccess(DocumentReference documentReference) {
             //                  Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
             //              }
             //          })
             //          .addOnFailureListener(new OnFailureListener() {
             //              @Override
             //              public void onFailure(@NonNull Exception e) {
             //                  Log.w(TAG, "Error adding document", e);
             //              }
             //          });
             //  db.collection("Anzeigen").document(ID.get(position)).delete();

                holder.accept.startAnimation(myAnim);
                Picasso.get().load("https://st2.depositphotos.com/5266903/11960/v/950/depositphotos_119601376-stock-illustration-ok-tick-stroke-vector-icon.jpg").fit().centerCrop().into(holder.accept);
               // setInterpolator(AnimationUtils.loadInterpolator(mContext,
                //        R.anim.bounce));
            }
        });



holder.pic.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        final Intent intent = new Intent(mContext , custom_profile.class);
        mContext.startActivity(intent);
        final ActivityOptionsCompat option = ActivityOptionsCompat
                .makeSceneTransitionAnimation((Activity) mContext, holder.pic, "example_transition");
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("User")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                if (document.getId() == UserID.get(position)){
                                    intent.putExtra("Name",document.getData().get("Name").toString() );
                                    intent.putExtra("Alter",document.getData().get("Alter").toString() );
                                    intent.putExtra("Stadt",document.getData().get("Stadt").toString() );
                                    intent.putExtra("Punkte",document.getData().get("Punkte").toString() );
                                    intent.putExtra("Img",document.getData().get("Img").toString() );
                                    ActivityCompat.startActivity(mContext, intent, option.toBundle());
                                }
                                else{
                                    Toast.makeText(mContext, "User nicht gefunden", Toast.LENGTH_SHORT).show();
                                }
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });

    }
});




    }



    @Override
    public int getItemCount() {
        return Name.size();
    }




    public class MyBounceInterpolator implements android.view.animation.Interpolator {
        private double mAmplitude = 1;
        private double mFrequency = 10;

        public MyBounceInterpolator(double amplitude, double frequency) {
            mAmplitude = amplitude;
            mFrequency = frequency;
        }

        public float getInterpolation(float time) {
            return (float) (-1 * Math.pow(Math.E, -time / mAmplitude) *
                    Math.cos(mFrequency * time) + 1);
        }
    }



}

