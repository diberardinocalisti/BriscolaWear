package com.example.briscolawear;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.briscolawear.databinding.ActivityMainBinding;
import com.facebook.FacebookSdk;

import Login.loginClass;

import static Login.loginClass.*;

public class MainActivity extends Activity {

    private TextView mTextView;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(MainActivity.this);

        if(isFacebookLoggedIn())
            setContentView(R.layout.activity_main);
        else
            setContentView(R.layout.login_activity);
    }
}