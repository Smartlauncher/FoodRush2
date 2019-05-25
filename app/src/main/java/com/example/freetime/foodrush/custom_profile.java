package com.example.freetime.foodrush;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class custom_profile extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_profile);


        Intent intent = getIntent();
        String Name = intent.getStringExtra("Name");
        String Alter = intent.getStringExtra("Alter");
        String Stadt = intent.getStringExtra("Stadt");
        String Punkte = intent.getStringExtra("Punkte");
        String Img = intent.getStringExtra("Img");

        TextView Name_t = (TextView) findViewById(R.id.profile_custom_name);
        TextView Alter_t = (TextView) findViewById(R.id.profile_custom_alter);
        TextView Stadt_t = (TextView) findViewById(R.id.profile_custom_stadt);
        TextView Punkte_t = (TextView) findViewById(R.id.profile_custom_points);

        Name_t.setText(Name);
        Alter_t.setText(Alter);
        Stadt_t.setText(Stadt);
        Punkte_t.setText(Punkte);



    }
}
