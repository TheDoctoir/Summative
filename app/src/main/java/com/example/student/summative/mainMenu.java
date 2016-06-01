package com.example.student.summative;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class mainMenu extends AppCompatActivity {

    ImageButton btnDataBase;
    ImageButton btnCredits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        btnDataBase = (ImageButton) findViewById(R.id.btnDataBase);
        btnCredits = (ImageButton) findViewById(R.id.btnCredits);

    }


    public void goToCredits(View vw){

        Intent c= new Intent(mainMenu.this, Credits.class);
        startActivity(c);

    }


    public void goToDataBase(View vw){

        Intent b= new Intent(mainMenu.this, MainActivity.class);
        startActivity(b);

    }


}
