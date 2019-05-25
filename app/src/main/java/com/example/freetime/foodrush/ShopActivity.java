package com.example.freetime.foodrush;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ShopActivity extends AppCompatActivity {
private int Item_selected;
private  String TAG = "Shop";
    adapter_shopping adapter2;
private Button next;
private Spinner spinCountry;
private ArrayList<String> Produkte= new ArrayList<>();
private ArrayList<String> ANzahl= new ArrayList<>();
    private ArrayList<String> array= new ArrayList<>();

private ArrayList<String> Lebensmittel = new ArrayList<>();
    private static ShopActivity instance;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_shop);
next = (Button) findViewById(R.id.button_next_produkt);
        spinCountry= (Spinner) findViewById(R.id.spinner_anzahl);//fetch the spinner from layout file
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ShopActivity.this,
                android.R.layout.simple_spinner_item, getResources()
                .getStringArray(R.array.country_array));//setting the country_array to spinner
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinCountry.setAdapter(adapter);
        spinCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int position, long id) {

                Item_selected =  Integer.parseInt(spinCountry.getItemAtPosition(position).toString());


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                Item_selected = 1;
            }
        });
next.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        EditText lebensmittel = (EditText) findViewById(R.id.input_lebensmittel);
        String item = lebensmittel.getText().toString();




//if you want to set any action you can do in this listener


        Log.d("Lebensmittel" , "Lebens" + Item_selected);
        for(int i = 0; i < Item_selected; i++){
            Lebensmittel.add(item);
            array = Lebensmittel;
            Log.d("Lebensmittel" , "Lebens" + Item_selected);

            final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rec_list_2);

             adapter2= new adapter_shopping(Lebensmittel, ShopActivity.this);
            recyclerView.setAdapter(adapter2);
            recyclerView.setLayoutManager(new LinearLayoutManager(ShopActivity.this));

        }
        lebensmittel.getText().clear();

    }
});
        Log.d("Lebensmittelssss" , "Lebenssss" +array.size());
        View include = (View) findViewById(R.id.navigation);
        TextView Earn =   ( TextView) include.findViewById(R.id.text_earn);
        TextView Shop  =   ( TextView) include.findViewById(R.id.text_shop);
        TextView Profile  =   ( TextView) include.findViewById(R.id.text_profile);
        Earn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Also" ,"s");
                Intent intent = new Intent(ShopActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
        Shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Also" ,"s");
                Intent intent = new Intent(ShopActivity.this, ShopActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Also" ,"s");
                Intent intent = new Intent(ShopActivity.this, ProfileActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });





// Add a new document with a generated ID
        Button Post = (Button) findViewById(R.id.post);
        Post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FirebaseFirestore db = FirebaseFirestore.getInstance();
                // Create a new user with a first and last name
                Map<String, Object> user = new HashMap<>();

                EditText Wann = (EditText) findViewById(R.id.input_wann_lebensmittel);
                EditText Wo = (EditText) findViewById(R.id.input_wo_lebensmittel);
                EditText Weitere = (EditText) findViewById(R.id.input_info);
                EditText Wieviel = (EditText) findViewById(R.id.input_wieviel_lebensmittel);
                user.put("Geld", Wieviel.getText().toString());
                user.put("Wann", Wann.getText().toString());
                user.put("Wo", Wo.getText().toString());
                user.put("ImgURL", getSharedPreferences("ImgURL" , MODE_PRIVATE).getString("ImgURL" , "https://www.jamf.com/jamf-nation/img/default-avatars/generic-user-purple.png"));
                user.put("UserID" , getSharedPreferences("UserID" , MODE_PRIVATE).getString("UserID",null));
                user.put("Info" , Weitere.getText().toString());
                user.put("ImgURL", getSharedPreferences("ImgURL", MODE_PRIVATE).getString("ImgURL" , "Linktonormal"));
                user.put("Angenommen" , false);
                user.put("Name" , getSharedPreferences("Name_User" , MODE_PRIVATE).getString("Name_User" , null));
                user.put("Lebensmittel" , Lebensmittel);


                db.collection("Anzeigen")
                        .add(user)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                                db.collection("Anzeigen").document().getId();
                                Toast.makeText(ShopActivity.this, "Herzlichen Glückwunsch! Ihre Anzeige ist online", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error adding document", e);
                            }
                        });
            }
        });


    }


    public void removeItem(int position) {
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rec_list_2);
Log.d("ANZAHL" , "anzahls" + Lebensmittel);

        recyclerView.removeViewAt(position);
        adapter2.notifyItemRemoved(position);
        adapter2.notifyDataSetChanged();

    }
    public static ShopActivity getInstance() {
        return instance;
    }

}
