package com.dartmouth.cs.dartmouthcoach;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Donald on 2/18/2016.
 */
public class PaymentSettingsFragment extends Fragment {
    private Button AddCardBtn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View returnView = inflater.inflate(R.layout.fragment_payment_settings, container, false);
        AddCardBtn = (Button)returnView.findViewById(R.id.card_add_btn);
        AddCardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), AddCardActivity.class);
                startActivity(i);
            }
        });
        return returnView;

    }
}
