package com.dartmouth.cs.dartmouthcoach;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Donald on 2/18/2016.
 */



public class ProfileFragment extends Fragment {

    private ImageView mPortrait;
    private TextView mUserName, mAccount, mSSN, mDriveNum, mPhoneNum, mEmail;
    private TextView mTitle1, mTitle2,  mTitle3,  mTitle4,  mTitle5;
    private ImageView mBack1, mBack2, mLine1, mLine2;

    public Intent edit;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_profile,container,false );


        mUserName = (TextView) view.findViewById(R.id.userName);
        mAccount = (TextView) view.findViewById(R.id.account);
        mSSN = (TextView) view.findViewById(R.id.ssn);
        mDriveNum = (TextView) view.findViewById(R.id.driverLicense);
        mPhoneNum = (TextView) view.findViewById(R.id.phoneNumber);
        mEmail = (TextView) view.findViewById(R.id.email);

        mTitle1 = (TextView) view.findViewById(R.id.title1);
        mTitle2 = (TextView) view.findViewById(R.id.title2);
        mTitle3 = (TextView) view.findViewById(R.id.title3);
        mTitle4 = (TextView) view.findViewById(R.id.title4);
        mTitle5 = (TextView) view.findViewById(R.id.title5);

//        mBack1 = (ImageView) view.findViewById(R.id.back1);
//        mBack2 = (ImageView) view.findViewById(R.id.back2);
        mLine1 = (ImageView) view.findViewById(R.id.line1);
        mLine2 = (ImageView) view.findViewById(R.id.line2);

        mPortrait = (ImageView) view.findViewById(R.id.portrait);

//        从fragment转到activity
        mSSN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit = new Intent(getActivity(), ProfileEdit.class);
                edit.putExtra("type", 1);
                startActivityForResult(edit, 1);
            }
        });

        mDriveNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit = new Intent(getActivity().getApplicationContext(), ProfileEdit.class);
                edit.putExtra("type", 2);
                startActivityForResult(edit,2);
            }
        });

        mPhoneNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit = new Intent(getActivity().getApplicationContext(), ProfileEdit.class);
                edit.putExtra("type",3);
                startActivityForResult(edit,3);
            }
        });

        mEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit = new Intent(getActivity().getApplicationContext(), ProfileEdit.class);
                edit.putExtra("type",4);
                startActivityForResult(edit,4);
            }
        });


        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        String newinfo = " ";

        if (data!=null) {
            newinfo = data.getStringExtra("newInfo");
        }


        if(newinfo!=null) {
            switch (resultCode) {
                case 1:
                    mSSN.setText(newinfo);
                    mUserName.setText(newinfo);
                    break;
                case 2:
                    mDriveNum.setText(newinfo);
                    break;
                case 3:
                    mPhoneNum.setText(newinfo);
                    break;
                case 4:
                    mEmail.setText(newinfo);
                    break;
            }
        }
    }


}
