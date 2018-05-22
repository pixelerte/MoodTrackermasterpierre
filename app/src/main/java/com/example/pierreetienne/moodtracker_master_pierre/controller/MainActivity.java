package com.example.pierreetienne.moodtracker_master_pierre.controller;


import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.pierreetienne.moodtracker_master_pierre.R;


import static com.example.pierreetienne.moodtracker_master_pierre.R.color.banana_yellow;


@TargetApi(Build.VERSION_CODES.M)
@RequiresApi(api = Build.VERSION_CODES.M)
public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {


    private RelativeLayout mBackground;
    private ImageView mImage;
    private ImageButton mhistory;
    public ImageButton mnote;
    private GestureDetector gDetector;
    private int moodNumber = 3;

    private int[] tabBackgroundColor = {R.color.faded_red, R.color.warm_grey, R.color.cornflower_blue_65, R.color.light_sage, banana_yellow};

    private int[] image = {R.drawable.smileysad, R.drawable.smileydisappointed, R.drawable.smileynormal, R.drawable.smileyhappy, R.drawable.smileysuperappy};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gDetector = new GestureDetector(this);
        mBackground = findViewById(R.id.background);
        mImage = findViewById(R.id.imageSmailly);
        mhistory = findViewById(R.id.history);
        mnote =findViewById(R.id.note);

        mBackground.setBackgroundColor(getColor(tabBackgroundColor[moodNumber]));
        mImage.setImageResource(image[moodNumber]);

        mnote.setOnClickListener(mnoteClick);
        mhistory.setOnClickListener(mhistoryClick);

    }

    private View.OnClickListener mnoteClick = new View.OnClickListener() {
        public void onClick(View v) {



        }
    };

    private View.OnClickListener mhistoryClick = new View.OnClickListener() {
        public void onClick(View v) {


        }
    };

    //detects the interaction between user and screen

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gDetector.onTouchEvent(event);


    }



    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }


    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }


    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    //detects user fling on screen

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {

        //if user go up

        if (motionEvent.getY() < motionEvent1.getY())
        {

            //if no more mood, nothing
            if (moodNumber == 4)
            {

            }

            //if there are others moods show next moods
            else if (moodNumber < 4)
            {
                moodNumber++;

                //change background color end change moods image
                mBackground.setBackgroundColor(getColor(tabBackgroundColor[moodNumber]));

                mImage.setImageResource(image[moodNumber]);
            }
        }

        //if user go down

        else if(motionEvent.getY() > motionEvent1.getY()){

            //if no more mood, nothing
            if (moodNumber == 0){
                moodNumber = 0;

            }

            //if there are others moods show next moods
            else if(moodNumber > 0)
            {
                moodNumber--;

                //change background color end change moods image
                mBackground.setBackgroundColor(getColor(tabBackgroundColor[moodNumber]));

                mImage.setImageResource(image[moodNumber]);

                }
        }

                return false;

    }




}


