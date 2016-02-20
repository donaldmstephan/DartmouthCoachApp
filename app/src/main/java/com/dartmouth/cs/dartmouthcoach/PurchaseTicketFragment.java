package com.dartmouth.cs.dartmouthcoach;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

/**
 * Created by Donald on 2/18/2016.
 */
public class PurchaseTicketFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    Spinner state;
    Spinner city;
    Spinner destination;
    Spinner departure_time;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_purchase_tickets, container, false);

        // Add button clickers
        Button purchase = (Button) v.findViewById(R.id.purchase_button);
        purchase.setOnClickListener(this);
        Button cancel = (Button) v.findViewById(R.id.cancel_button);
        cancel.setOnClickListener(this);

        // Get spinners
        state = (Spinner) v.findViewById(R.id.state_spinner);
        city = (Spinner) v.findViewById(R.id.city_spinner);
        destination = (Spinner) v.findViewById(R.id.destination_spinner);
        departure_time = (Spinner) v.findViewById(R.id.departure_time_spinner);

        // Set spinner clickers
        /*state.setOnItemClickListener(this);
        city.setOnItemClickListener(this);
        destination.setOnItemClickListener(this);
        departure_time.setOnItemClickListener(this);*/

        // Create adapter for the city spinner
        ArrayAdapter<CharSequence> state_adapter = ArrayAdapter.createFromResource(this.getActivity(), R.array.city_type_array, android.R.layout.simple_spinner_item);
        state_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        state.setAdapter(state_adapter);

        // Set the other spinners to invisible
        city.setVisibility(View.INVISIBLE);
        destination.setVisibility(View.INVISIBLE);
        departure_time.setVisibility(View.INVISIBLE);

        return v;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.purchase_button:
                break;
            case R.id.cancel_button:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


    }
}
