package com.example.freetime.foodrush;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {
    private static final String TAG = "ProfileActivity";
    private TextView positiv;
    private ImageView Settings;
    private TextView City;
    private TextView Name;
    private TextView age;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private TextView number;
    private TextView email;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        boolean connected = false;
        positiv = (TextView) findViewById(R.id.profile_positiv);
        Settings = (ImageView) findViewById(R.id.settings);
        City = (TextView) findViewById(R.id.city_profile);
        Name = (TextView) findViewById(R.id.name_profile);
        age = (TextView) findViewById(R.id.age_profile);
        number = (TextView) findViewById(R.id.number_profile);
        email = (TextView) findViewById(R.id.email_profile);

        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        }

        else{
            connected = false;}
            Log.d(TAG , "connectedconn" + connected );
        if (connected == true) {
            db.collection("User")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Log.d(TAG, "ddddd" + document.getId() + " => " + document.getData());


                                            //positiv.setText(document.getData().get("Bewertung_positiv").toString());
                                          //  Log.d("Profile" , "document22" + document.getData().get("Bewertung_positiv"));

                                            City.setText(document.getData().get("Stadt").toString());


                                           number.setText(document.getData().get("Telefonnummer").toString());
                                           Log.d("Profile" , "document22" + document.getData().get("Telefonnummer"));


                                            Name.setText(document.getData().get("Name").toString());



                                            email.setText(document.getData().get("Email").toString());


                                            age.setText(document.getData().get("Alter").toString());







                                }
                            } else {
                                Log.w(TAG, "Error getting documents.", task.getException());
                            }
                        }
                    });
        }
        else{
            Toast.makeText(this, "Tut mir leid aber um die neusten Daten von deinem Profil zu empfangen brauchen wir Internet", Toast.LENGTH_SHORT).show();
        }

        View include = (View) findViewById(R.id.navigation);
        TextView Earn =   ( TextView) include.findViewById(R.id.text_earn);
        TextView Shop  =   ( TextView) include.findViewById(R.id.text_shop);
        TextView Profile  =   ( TextView) include.findViewById(R.id.text_profile);

        Earn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Also" ,"s");
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
        Shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Also" ,"s");
                Intent intent = new Intent(ProfileActivity.this, ShopActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Also" ,"s");
                Intent intent = new Intent(ProfileActivity.this, ProfileActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
        SharedPreferences.Editor edit =getSharedPreferences("Mission" , Context.MODE_PRIVATE).edit();
        edit.putBoolean("Mission",false );
        edit.apply();


    }
}
