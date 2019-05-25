package com.example.freetime.foodrush;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Welcome_Screen extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        TextView font = (TextView) findViewById(R.id.font);


        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Raleway-MediumItalic.ttf");

        font.setTypeface(custom_font);
        Thread timer= new Thread()
        {
            public void run()
            {
                try
                {
                    //Display for 2 seconds
                    sleep(2000);
                }
                catch (InterruptedException e)
                {
                    // TODO: handle exception
                    e.printStackTrace();
                }
                finally
                {
                    //Goes to Activity  StartingPoint.java(STARTINGPOINT)
                    Intent openstartingpoint=new Intent(Welcome_Screen.this, MainActivity.class);
                    startActivity(openstartingpoint);
                    finish();
                }
            }
        };
        timer.start();
    }


    //Destroy Welcome_screen.java after it goes to next activity
    @Override
    protected void onPause()
    {
        // TODO Auto-generated method stub
        super.onPause();
        finish();

    }

    }

