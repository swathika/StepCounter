package com.example.swathika.StepCounter;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;


public  class StepcounterActivity extends FragmentActivity {
    stepcounter Sstepcounter;
    Timecounter counter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stepcounter);
        Sstepcounter=new stepcounter();
        counter=new Timecounter();

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.container2,Sstepcounter);
        transaction.add(R.id.container1,counter);
        transaction.commit();

    }
}



