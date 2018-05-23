package com.example.pierreetienne.moodtracker_master_pierre.controller;


import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.pierreetienne.moodtracker_master_pierre.R;


import static com.example.pierreetienne.moodtracker_master_pierre.R.color.banana_yellow;


@TargetApi(Build.VERSION_CODES.M)
@RequiresApi(api = Build.VERSION_CODES.M)
public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {


    private static final String TAG = "activity_main";
    private RelativeLayout mBackground;
    private ImageView mImage;
    private ImageButton mhistory;
    public ImageButton mnoteIcone;
    private GestureDetector gDetector;
    private int moodNumber = 3;
    private String noteUser = "";
    private SharedPreferences mPreferences;

    private int[] tabBackgroundColor = {R.color.faded_red, R.color.warm_grey, R.color.cornflower_blue_65, R.color.light_sage, banana_yellow};

    private int[] image = {R.drawable.smileysad, R.drawable.smileydisappointed, R.drawable.smileynormal, R.drawable.smileyhappy, R.drawable.smileysuperhappy};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            moodNumber = savedInstanceState.getInt("my_mood");
        }



        gDetector = new GestureDetector(this);
        mBackground = findViewById(R.id.background);

        mImage = findViewById(R.id.imageSmailly);
        mhistory = findViewById(R.id.history);
        mnoteIcone =findViewById(R.id.note);

        mBackground.setBackgroundColor(getColor(tabBackgroundColor[moodNumber]));
        mImage.setImageResource(image[moodNumber]);

        mnoteIcone.setOnClickListener(mnoteClick);
        mhistory.setOnClickListener(mhistoryClick);

        mPreferences = getPreferences(MODE_PRIVATE);


    }

    //save moodNumber

    @Override
    protected void onSaveInstanceState(Bundle stateSaved) {
        super.onSaveInstanceState(stateSaved);
        stateSaved.putInt("my_mood", moodNumber);
        Log.i(TAG, "onSaveInstanceState: ");

    }

    //Restore moodNumber

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // restore saved values
        moodNumber = savedInstanceState.getInt("my_mood");
        Log.i(TAG, "onRestoreInstanceState: ");

    }



        //detect click on the button note

        private View.OnClickListener mnoteClick = new View.OnClickListener() {
            public void onClick(View v) {


                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("add comments");


                //create area text input
                final EditText input = new EditText(MainActivity.this);
                input.setInputType(InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE);
                builder.setView(input);

                //save comment user
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        noteUser= input.getText().toString();

                    }
                });

                //cancel, no saving comment
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();

        }
    };


    private View.OnClickListener mhistoryClick = new View.OnClickListener() {
        public void onClick(View v) {

            Log.i(TAG, "onClick: mhistoryClick");


        }
    };

    //detects the interaction between user and screen

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        System.err.print("var moodNumber = " + moodNumber);

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

            //if there are others moods show next moods
            if (moodNumber < 4)
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



        Log.i(TAG, "var mood number " + moodNumber );



                return false;

    }




}


