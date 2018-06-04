package com.example.pierreetienne.moodtracker_master_pierre.controller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.pierreetienne.moodtracker_master_pierre.model.History;
import com.example.pierreetienne.moodtracker_master_pierre.model.MoodsSave;
import com.google.gson.Gson;

import static android.content.ContentValues.TAG;
import static android.support.v4.content.ContextCompat.startActivity;


public class AlarmeMoodsClock extends BroadcastReceiver {




    @Override
    public void onReceive(Context context, Intent intent) {

        Log.i(TAG, "onReceive: ");

        History HistoryList = loadHistory(context);
        MoodsSave userMoodSave = loadMood(context);

        if(HistoryList == null){

            HistoryList = new History();
            Log.i(TAG, "onReceive: New List");

        }

        HistoryList.addList(userMoodSave);


        if (HistoryList.getSizeList() > 7){

            HistoryList.removeList(0);
            Log.i(TAG, "onReceive: remove 0");

            }

        saveHistory(context, HistoryList);
        resetMood(context, userMoodSave);

        }



    protected void saveHistory(Context context, History HistoryList){

        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        Gson gson = new Gson();

        SharedPreferences.Editor prefsEditor = mPreferences.edit();
        String SaveHistoryJson = gson.toJson(HistoryList);
        prefsEditor.putString("HistoryList", SaveHistoryJson);
        prefsEditor.apply();
        Log.i(TAG, "onReceive: AlarmeMoodsClock" + HistoryList.toString());

    }

    protected History loadHistory(Context context){

        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        Gson gson = new Gson();

        String LoadHistoryJson = mPreferences.getString("HistoryList", "");
        History HistoryList = gson.fromJson(LoadHistoryJson, History.class);

        return HistoryList;
    }

    protected MoodsSave loadMood(Context context){

        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String jsonMood = mPreferences.getString("PrefMoodUserSave", "");
        MoodsSave userMoodSave = gson.fromJson(jsonMood, MoodsSave.class);

        return userMoodSave;
        }

    protected void resetMood(Context context, MoodsSave userMoodSave){

        userMoodSave.setComment("");
        userMoodSave.setMoodsNumber(3);

        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        SharedPreferences.Editor prefsEditor = mPreferences.edit();
        String SaveHistoryJson = gson.toJson(userMoodSave);
        prefsEditor.putString("PrefMoodUserSave", SaveHistoryJson);
        prefsEditor.apply();

        Log.i(TAG, "resetMood: " + userMoodSave.getComment() + " ; " + userMoodSave.getMoodsNumber());
        }



}