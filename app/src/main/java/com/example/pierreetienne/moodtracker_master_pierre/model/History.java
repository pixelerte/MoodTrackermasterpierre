package com.example.pierreetienne.moodtracker_master_pierre.model;

import java.util.ArrayList;

public class History {

    private ArrayList<MoodsSave> historyList = new ArrayList<MoodsSave>();

    //constructeur
    public History() {

    }

    public void addList(MoodsSave MoodaddList){

        historyList.add(MoodaddList);


    }

    public MoodsSave getList(int i){

       return historyList.get(i);
    }

    public void removeList(int i){

        historyList.remove(i);

    }

    public int getSizeList(){

        return historyList.size();
    }

    public String toString(){

        int size = historyList.size();
        int i = 0;
        String str = " size " + historyList.size() + " // ";

        while (i < size){

           str = str + historyList.get(i).toString() + " i = " + i + " // ";
            i++;
        }

        return " toString History = " + str;
    }


}
