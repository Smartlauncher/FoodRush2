package com.example.freetime.foodrush;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RegistrierungLoginActivity extends AppCompatActivity {
    private static final String TAG = "Regi";
    private SharedPreferences.Editor edit_login;
    private SharedPreferences.Editor edit;
    private Boolean ausgefüllt ;
    private SharedPreferences.Editor Email_edit;
     EditText Name;
     EditText Geburtsdatum;
     EditText Email;
     EditText Telefonnummer;
     private Spinner Alter;
     EditText Stadt;
     EditText Passwort;
    private SharedPreferences.Editor Name_edit;
    private SharedPreferences.Editor Password_edit;
    private SharedPreferences.Editor Alter_edit;
    private SharedPreferences.Editor UserID_edit;
    private SharedPreferences Erste;
    private SharedPreferences ImgURL;
    private SharedPreferences.Editor ImgURL_edit;
    private SharedPreferences Login;
    private SharedPreferences Email_shared;
    private SharedPreferences Alter_shared;
    private String se;
    private SharedPreferences Name_shared;
    private SharedPreferences UserID_shared;
    private SharedPreferences Password_shared;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrierungloginactivity);
        Erste = getSharedPreferences("Erste" , MODE_PRIVATE);
        ImgURL = getSharedPreferences("ImgURL" , MODE_PRIVATE);
        UserID_shared= getSharedPreferences("UserID" , MODE_PRIVATE);
        Alter_shared = getSharedPreferences("Alter" , MODE_PRIVATE);
        Login = getSharedPreferences("Login" , MODE_PRIVATE);
Name_shared = getSharedPreferences("Name_User" , MODE_PRIVATE);

        Email_shared = getSharedPreferences("Email" , MODE_PRIVATE);
        Password_shared = getSharedPreferences("Passwort" , MODE_PRIVATE);
        edit = getSharedPreferences("Erste" , MODE_PRIVATE).edit();




        if ( Email_shared.getString("Email" , null) == null){


            Button Beitreten = (Button) findViewById(R.id.beitreten);
           Name = (EditText) findViewById(R.id.input_name);
           Geburtsdatum = (EditText) findViewById(R.id.input_geburtsdatum);
           Email = (EditText) findViewById(R.id.input_email);
           Telefonnummer = (EditText) findViewById(R.id.input_telefonnummer);
           Stadt = (EditText) findViewById(R.id.input_stadt);
           Passwort = (EditText) findViewById(R.id.input_pass);
            Alter = (Spinner) findViewById(R.id.spinner_alter);
            List<String> list = new ArrayList<String>();
            list.add("14");
            list.add("15");
            list.add("16");
            list.add("17");
            list.add("18");
            list.add("19");
            list.add("20");
            list.add("21");
            list.add("22");
            list.add("23");
            list.add("24");
            list.add("25");
            list.add("26");
            list.add("27");
            list.add("28");
            list.add("29");
            list.add("30");
            list.add("31");
            list.add("32");
            list.add("33");
            list.add("34");
            list.add("35");


            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            Alter.setAdapter(dataAdapter);

           Alter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
               @Override
               public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    se = Alter.getItemAtPosition(position).toString();
               }

               @Override
               public void onNothingSelected(AdapterView<?> parent) {

               }
           });



            Beitreten.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    List<EditText> validationList = new ArrayList();


                        validationList.add(Name);
                        validationList.add(Geburtsdatum);
                    validationList.add(Passwort);
                        validationList.add(Email);
                        validationList.add(Telefonnummer);
                        validationList.add(Stadt);


if (TextUtils.isEmpty(Name.getText().toString())|| TextUtils.isEmpty(Geburtsdatum.getText().toString())  || TextUtils.isEmpty(Passwort.getText().toString()) || TextUtils.isEmpty(Email.getText().toString()) || TextUtils.isEmpty(Telefonnummer.getText().toString()) || TextUtils.isEmpty(Stadt.getText().toString())){
    ausgefüllt = false;
    Toast.makeText(RegistrierungLoginActivity.this, "Sieht so aus als hättest du nicht alle Felder ausgefüllt", Toast.LENGTH_SHORT).show();
}
else{
    ausgefüllt = true;
}



                            if (ausgefüllt) {
                                String pass_a = Passwort.getText().toString();
                                String Name_a = Name.getText().toString();
                                String Geburtsdatum_a = Geburtsdatum.getText().toString();
                                String Email_a = Email.getText().toString();
                                String Telefonnummer_a = Telefonnummer.getText().toString();
                                String Stadt_a = Stadt.getText().toString();
                                String newPass_a = sha256(pass_a);
                                // Create a new user with a first and last name
                                Map<String, Object> user = new HashMap<>();
                                user.put("Aufträge_online", false);
                                user.put("Aufträge_gesamt", 0);
                                user.put("Abzeichen", null);
                                user.put("Stadt", Stadt_a);
                                user.put("Passwort", newPass_a);
                                user.put("UserID" , null);
                                user.put("Alter" , se);
                                user.put("ImgURL" , "https://www.jamf.com/jamf-nation/img/default-avatars/generic-user-purple.png");
                                user.put("Punkte", 0);
                                user.put("Name", Name_a);
                                user.put("Geburtsdatum", Geburtsdatum_a);
                                user.put("Email", Email_a);
                                user.put("Telefonnummer", Telefonnummer_a);
                                final FirebaseFirestore db = FirebaseFirestore.getInstance();
                                Email_edit = getSharedPreferences("Email", MODE_PRIVATE).edit();
                                Email_edit.putString("Email", Email_a);
                                Email_edit.apply();
                                ImgURL_edit = getSharedPreferences("ImgURL" , MODE_PRIVATE).edit();
                                ImgURL_edit.putString("ImgURL" , "https://www.jamf.com/jamf-nation/img/default-avatars/generic-user-purple.png");
                                ImgURL_edit.apply();

                                Name_edit = getSharedPreferences("Name_User" , MODE_PRIVATE).edit();
                                Name_edit.putString("Name_User" , Name_a);
                                Name_edit.apply();
                                Password_edit = getSharedPreferences("Passwort", MODE_PRIVATE).edit();
                                Password_edit.putString("Passwort", newPass_a);
                                Password_edit.apply();
// Add a new document with a generated ID
                                db.collection("User")
                                        .add(user)
                                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                            @Override
                                            public void onSuccess(DocumentReference documentReference) {
                                                Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());

                                                        db.collection("User")
                                                        .document(documentReference.getId())
                                                        .update("UserID", documentReference.getId());
                                                        UserID_edit = getSharedPreferences("UserID" , MODE_PRIVATE).edit();
                                                        UserID_edit.putString(documentReference.getId(),null);
                                                        UserID_edit.apply();

                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Log.w(TAG, "Error adding document", e);
                                            }
                                        });
                                Toast.makeText(RegistrierungLoginActivity.this, "Registriert! Herzlichen Glückwunsch", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegistrierungLoginActivity.this,MainActivity.class);
                                startActivity(intent);
                                finish();


                            }



        }

            });


        }



        else{

String email = Email_shared.getString("Email" , null);
String Passwort = Password_shared.getString("Passwort" , null);

if (Passwort == null  ){
    return;
}
if (email == null){
return;
}
else{
    Intent intent = new Intent(this , LoginActivity.class);
    startActivity(intent);
    finish();
}


            //Login credentials loading if not there then start from the beginning










        }


















    }




    public static String sha256(String base) {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

}
