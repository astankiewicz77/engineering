package androidhive.info.materialdesign.activity;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Chronometer;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidhive.info.materialdesign.R;
import at.markushi.ui.CircleButton;

/**
 * Created by a.stankiewicz on 2015-07-28.
 */
public class HomePageFragment extends Fragment implements AdapterView.OnItemSelectedListener{

    Chronometer chronometer;
    CircleButton start,stop,mapsButton;
    Spinner spinnerActivity;
    ArrayAdapter<String> dataAdapter;
    List<String> activities;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home_page, container, false);

        ((ActionBarActivity)getActivity()).getSupportActionBar().show();

        chronometer = (Chronometer) view.findViewById(R.id.chronometer);
        start = (CircleButton)view.findViewById(R.id.buttonStart);
        stop = (CircleButton)view.findViewById(R.id.buttonStop);
        mapsButton = (CircleButton)view.findViewById(R.id.buttonMaps);
        spinnerActivity = (Spinner)view.findViewById(R.id.spinner);

        // Chronometer
        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer cArg) {
                long time = SystemClock.elapsedRealtime() - cArg.getBase();
                int h = (int) (time / 3600000);
                int m = (int) (time - h * 3600000) / 60000;
                int s = (int) (time - h * 3600000 - m * 60000) / 1000;
                String hh = h < 10 ? "0" + h : h + "";
                String mm = m < 10 ? "0" + m : m + "";
                String ss = s < 10 ? "0" + s : s + "";
                cArg.setText(hh + ":" + mm + ":" + ss);
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.stop();
            }
        });

        // Spinner
        spinnerActivity.setOnItemSelectedListener(this);

        activities = new ArrayList<>();
        activities.add("Running");
        activities.add("Cycling");

        dataAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, activities);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerActivity.setAdapter(dataAdapter);

        //Google Maps
        mapsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container_body, new MapsFragment(), "fragment_stack");
                fragmentTransaction.commit();
            }
        });


        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
        ((TextView) parent.getChildAt(0)).setTextSize(20);
        ((TextView) parent.getChildAt(0)).setGravity(Gravity.CENTER);

        String item = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}
}