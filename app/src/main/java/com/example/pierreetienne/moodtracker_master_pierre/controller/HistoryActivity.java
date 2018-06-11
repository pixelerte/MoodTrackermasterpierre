package com.example.pierreetienne.moodtracker_master_pierre.controller;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pierreetienne.moodtracker_master_pierre.R;
import com.example.pierreetienne.moodtracker_master_pierre.model.History;
import com.google.gson.Gson;

import static com.example.pierreetienne.moodtracker_master_pierre.R.color.banana_yellow;

public class HistoryActivity extends AppCompatActivity implements View.OnClickListener{

    private String TAG = "activity_main";
    private int i = 0;
    private History history;
    private int[] tabBackgroundColor = {R.color.faded_red, R.color.warm_grey, R.color.cornflower_blue_65, R.color.light_sage, banana_yellow};
    private RelativeLayout[] tabLayoutClass;
    private TextView[] tabTextClass;
    private ImageView[] tabImageClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);


        RelativeLayout[] tabLayout = {
                findViewById(R.id.layout0),
                findViewById(R.id.layout1),
                findViewById(R.id.layout2),
                findViewById(R.id.layout3),
                findViewById(R.id.layout4),
                findViewById(R.id.layout5),
                findViewById(R.id.layout6)};

        Button[] tabButton = {
                findViewById(R.id.button0),
                findViewById(R.id.button1),
                findViewById(R.id.button2),
                findViewById(R.id.button3),
                findViewById(R.id.button4),
                findViewById(R.id.button5),
                findViewById(R.id.button6)};

        TextView[] tabText = {
                findViewById(R.id.comment0),
                findViewById(R.id.comment1),
                findViewById(R.id.comment2),
                findViewById(R.id.comment3),
                findViewById(R.id.comment4),
                findViewById(R.id.comment5),
                findViewById(R.id.comment6)};

        ImageView[] tabImage = {
                findViewById(R.id.imageView0),
                findViewById(R.id.imageView1),
                findViewById(R.id.imageView2),
                findViewById(R.id.imageView3),
                findViewById(R.id.imageView4),
                findViewById(R.id.imageView5),
                findViewById(R.id.imageView6)};



        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        Gson gson = new Gson();
        String LoadHistoryJson = mPreferences.getString("HistoryList", "");
        History HistoryList = gson.fromJson(LoadHistoryJson, History.class);

        tabLayoutClass = tabLayout;
        history = HistoryList;
        tabImageClass = tabImage;
        tabTextClass = tabText;


        if (HistoryList != null) {

            while (HistoryList.getSizeList() > i) {

                tabButton[i].setOnClickListener(this);
                tabButton[i].setTag(i);
                setLayout(i);
                i++;

            }
        }
        else {

            Toast.makeText(this," No mood saved detected ",
                    Toast.LENGTH_LONG).show();

        }

    }


    //calculate the Layout size for the different Moods (horizontal)
    public int getsizeX(int moodNumber){

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int size = 0;

        switch (moodNumber){

            case 0:

                size = metrics.widthPixels - metrics.widthPixels * 80 / 100;

                break;

            case 1:

                size = metrics.widthPixels - metrics.widthPixels * 60 / 100;;

           break;

            case 2:

                size = metrics.widthPixels - metrics.widthPixels * 40 / 100;;

                break;

            case 3:

                size = metrics.widthPixels - metrics.widthPixels * 20 / 100;;

                break;

            case 4:

                size = metrics.widthPixels;

                break;



        }



        return size;
        }


    //calculate the Layout size for vertical (screen / 7)

    public int getsizeY(){

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int size = metrics.heightPixels / 7;


         return size;
         }

    public void setLayout(int l){

        if (history != null) {
            tabLayoutClass[l].getLayoutParams().width = getsizeX(history.getList(l).getMoodsNumber());
            tabLayoutClass[l].getLayoutParams().height = getsizeY();
            tabLayoutClass[l].setBackgroundColor(getResources().getColor(tabBackgroundColor[history.getList(l).getMoodsNumber()]));
            tabTextClass[l].setVisibility(View.VISIBLE);
        if (history.getList(l).getComment() != null) {
            tabImageClass[l].setVisibility(View.VISIBLE);
        }
        }

        }

    @Override
    public void onClick(View view) {

        if (history.getSizeList() > 0) {


            Log.i(TAG, "onClick: Button" + view.getTag());

            if (history.getList((int) view.getTag()).getComment() != null) {

                Toast.makeText(view.getContext(), history.getList((int) view.getTag()).getComment(),
                        Toast.LENGTH_SHORT).show();

            }

            }



    }
}
