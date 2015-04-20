package com.example.swathika.StepCounter;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Weather.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Weather#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Weather extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parentViewGroup,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_weather, parentViewGroup, false);
        return rootView;
    }
}


