package com.example.student.summative;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class Credits extends AppCompatActivity {

    private ImageButton btnImage1;
    private RatingBar rtingBar;
    private TextView txtvw2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
        btnImage1 = (ImageButton) findViewById(R.id.btnImage1);
        rtingBar = (RatingBar) findViewById(R.id.rtingBar);
        txtvw2 = (TextView) findViewById(R.id.txtvw2);
        ratingBarListener();
    }

    public void ratingBarListener (){

        rtingBar.setOnRatingBarChangeListener(
                new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                        txtvw2.setText( "Thanks for the rating of " +(String.valueOf(rating)));
                    }
                }
        );

    }

    public void mainMenu(View vw){

        Intent a= new Intent(Credits.this, mainMenu.class);
        startActivity(a);

    }

}


//https://www.youtube.com/watch?v=0MrPs4yk9pU rating bars
//