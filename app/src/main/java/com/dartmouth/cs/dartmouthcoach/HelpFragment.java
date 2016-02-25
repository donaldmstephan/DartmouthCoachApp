package com.dartmouth.cs.dartmouthcoach;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class HelpFragment extends Fragment {
    Button webBtn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View returnView = inflater.inflate(R.layout.fragment_help, container, false);
        webBtn = (Button) returnView.findViewById(R.id.web_btn);
        webBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://dartmouthcoach.com/mobi");
                Intent it = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(it);
            }
        });
        return returnView;
    }
}

