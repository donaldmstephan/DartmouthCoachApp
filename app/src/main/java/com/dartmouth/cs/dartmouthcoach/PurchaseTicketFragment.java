package com.dartmouth.cs.dartmouthcoach;

import android.app.DatePickerDialog;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;

import java.util.Calendar;

/**
 * Created by Donald on 2/18/2016.
 */
public class PurchaseTicketFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private Spinner departure;
    private Spinner arrival;
    private Spinner airline;
    private Spinner departure_time;
    private Button dateButton;
    private Calendar mDateAndTime = Calendar.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_purchase_tickets, container, false);

        // Add button clickers
        Button purchase = (Button) v.findViewById(R.id.purchase_button);
        purchase.setOnClickListener(this);
        Button date = (Button) v.findViewById(R.id.date_button);
        date.setOnClickListener(this);

        // Get spinners
        departure = (Spinner) v.findViewById(R.id.depature_spinner);
        arrival = (Spinner) v.findViewById(R.id.arrival_spinner);
        departure_time = (Spinner) v.findViewById(R.id.departure_time_spinner);

        // Set spinner clickers
        departure.setOnItemSelectedListener(this);
        arrival.setOnItemSelectedListener(this);
        departure_time.setOnItemSelectedListener(this);

        // Create adapter for the city spinner
        ArrayAdapter<CharSequence> departure_adapter = ArrayAdapter.createFromResource(this.getActivity(), R.array.departure_type_array, android.R.layout.simple_spinner_item);
        departure_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        departure.setAdapter(departure_adapter);

        ArrayAdapter<CharSequence> time_adapter = ArrayAdapter.createFromResource(this.getActivity(), R.array.departure_time_array_zero, android.R.layout.simple_spinner_item);
        time_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        departure_time.setAdapter(time_adapter);

        dateButton = (Button) v.findViewById(R.id.date_button);
        dateButton.setText(String.format("%1$tA %1$tb %1$td %1$tY", mDateAndTime));

        // Set the other spinners to invisible
        /*city.setVisibility(View.INVISIBLE);
        destination.setVisibility(View.INVISIBLE);
        departure_time.setVisibility(View.INVISIBLE);*/

        return v;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.purchase_button:
                break;
            case R.id.date_button:
                DatePickerDialog.OnDateSetListener mDateListener = new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        mDateAndTime.set(Calendar.YEAR, year);
                        mDateAndTime.set(Calendar.MONTH, monthOfYear);
                        mDateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        dateButton.setText(String.format("%1$tA %1$tb %1$td %1$tY", mDateAndTime));
                    }
                };

                new DatePickerDialog(v.getContext(), mDateListener,
                        mDateAndTime.get(Calendar.YEAR),
                        mDateAndTime.get(Calendar.MONTH),
                        mDateAndTime.get(Calendar.DAY_OF_MONTH)).show();

                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch(parent.getId()) {
            case R.id.depature_spinner:
                if (position == 0 || position == 1 || position == 5) {
                    ArrayAdapter<CharSequence> arrival_adapter = ArrayAdapter.createFromResource(this.getActivity(), R.array.arrival_type_array_zero, android.R.layout.simple_spinner_item);
                    arrival_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    arrival.setAdapter(arrival_adapter);
                }
                else {
                    ArrayAdapter<CharSequence> arrival_adapter = ArrayAdapter.createFromResource(this.getActivity(), R.array.arrival_type_array_one, android.R.layout.simple_spinner_item);
                    arrival_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    arrival.setAdapter(arrival_adapter);
                }
                break;
        }
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Don't need
    }
}
