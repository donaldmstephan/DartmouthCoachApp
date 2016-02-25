package com.dartmouth.cs.dartmouthcoach;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ProfileEdit extends AppCompatActivity {
    private TextView mCategory;
    private EditText mEditText;
    private Button mConfirm;
    private TextView mSSN, mDriveNum, mPhoneNum, mEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);

        mCategory = (TextView) findViewById(R.id.category);
        mEditText = (EditText) findViewById(R.id.editText);
        mConfirm = (Button) findViewById(R.id.comfirm);

        mSSN = (TextView) findViewById(R.id.ssn);
        mDriveNum = (TextView) findViewById(R.id.driverLicense);
        mPhoneNum = (TextView) findViewById(R.id.phoneNumber);
        mEmail = (TextView) findViewById(R.id.email);


        final int type = getIntent().getExtras().getInt("type");

        switch (type) {
            case 1:
                mCategory.setText("First Name");
                break;
            case 2:
                mCategory.setText("Last Name");
                break;
            case 3:
                mCategory.setText("Phone Number");
                break;
            case 4:
                mCategory.setText("Email");
                break;
        }


//        mEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
//                return true;
//            }
//        });

//        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
//        //显示软键盘
//        imm.showSoftInputFromInputMethod(mEditText.getWindowToken(), 0);
//

//        mEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
//            }
//        });


        mConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(getApplication(),MainActivity.class);

                back.putExtra("newInfo", mEditText.getText().toString());

                setResult(type, back);

                ProfileEdit.this.finish();
            }
        });
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        mEditText.requestFocus();
//
//        mEditText.postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
//                InputMethodManager keyboard = (InputMethodManager)
//                        getSystemService(Context.INPUT_METHOD_SERVICE);
//                keyboard.showSoftInput(mEditText, 0);
//            }
//        },200); //use 300 to make it run when coming back from lock screen
//    }
}
