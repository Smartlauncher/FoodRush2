package com.example.freetime.foodrush;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG ="MainActivity";
private int start = 0;
    private ArrayList<String> Name = new ArrayList<>();
    private ArrayList<String> mImageUrl = new ArrayList<>();
    private ArrayList<String>Geld = new ArrayList<>();
    private List<String> Produkt = new ArrayList<>();
    private ArrayList<String> Anzahl = new ArrayList<>();
    private ArrayList<String> Wo = new ArrayList<>();
    private ArrayList<String> Wann = new ArrayList<>();
    private ArrayList<String> ID = new ArrayList<>();
    private List<String> Strings;
    private ArrayList<String> UserID = new ArrayList<>();
    private ArrayList<String> document2 = new ArrayList<>();
    private ArrayList<Boolean> Sichtbar = new ArrayList<>();
    private ArrayList<Boolean> Angenommen = new ArrayList<>();
    private SharedPreferences sharedPreferenceObj;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferenceObj=getSharedPreferences("Email" , MODE_PRIVATE);

        if (sharedPreferenceObj.getString("Email" , null) == null) {
            Intent intent = new Intent(this, RegistrierungLoginActivity.class);
            startActivity(intent);
        }
        Log.d(TAG, "Emailshared" + sharedPreferenceObj.getString("Email",null));
        FirebaseFirestore db = FirebaseFirestore.getInstance();
       if (getSharedPreferences("Mission" , MODE_PRIVATE).getBoolean("Mission", false)== false ){
        db.collection("Anzeigen")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                           @Override
                                           public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                               if (task.isSuccessful()) {

                                                   for (QueryDocumentSnapshot document : task.getResult()) {
                                                       if (document.getData().get("Name") != null &&
                                                               document.getData().get("ImgURL") != null && document.getData().get("Geld") != null &&
                                                               document.getData().get("Lebensmittel") != null &&
                                                               document.getData().get("Wo") != null && document.getData().get("Wann") != null
                                                               && document.getData().get("Angenommen") != null) {
                                                           Log.d(TAG, document.getId() + " => " + document.getData());
                                                           Name.add(document.getData().get("Name").toString());
                                                           mImageUrl.add(document.getData().get("ImgURL").toString());
                                                           Geld.add(document.getData().get("Geld").toString());
                                                           Produkt.add(document.getData().get("Lebensmittel").toString());
                                                           Wo.add(document.getData().get("Wo").toString());
                                                           Wann.add(document.getData().get("Wann").toString());
                                                       //    UserID.add(document.getData().get("UserID").toString());
                                                           Angenommen.add(Boolean.valueOf(document.getData().get("Angenommen").toString()));
document2.add(document.getId());
                                                           final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rec_main);
                                                           content_recycler_main adapter = new content_recycler_main(Name,mImageUrl,Geld,Wo,Wann, UserID,Produkt,Angenommen,document2,MainActivity.class);
                                                           recyclerView.setAdapter(adapter);
                                                           recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));


                                                       } else {
                                                           return;
                                                       }
                                                   }
                                               }
                                           }
                                       });
        }
        else{
           Toast.makeText(this, "Viel Spaß beim liefern", Toast.LENGTH_SHORT).show();
       }



        View include = (View) findViewById(R.id.navigation);
        TextView Earn =   ( TextView) include.findViewById(R.id.text_earn);
        TextView Shop  =   ( TextView) include.findViewById(R.id.text_shop);
        TextView Profile  =   ( TextView) include.findViewById(R.id.text_profile);

        Earn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Also" ,"s");
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
        Shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Also" ,"s");
                Intent intent = new Intent(MainActivity.this, ShopActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Also" ,"s");
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });



        db.collection("User")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                              Log.d("MainActivity" , "document" + document.getId());
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });






    }


}
