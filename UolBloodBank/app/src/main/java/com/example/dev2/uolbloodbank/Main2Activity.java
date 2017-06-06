package com.example.dev2.uolbloodbank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Main2Activity extends AppCompatActivity {
    SignUpFragment signUpFragment;
    LoginFragment loginFragment;
    AdminLoginFragment adminLoginFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        String msg = intent.getStringExtra("msg");
        if(msg.equals("signin")){
            loginFragment = new LoginFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.activity_main2, loginFragment);
            fragmentTransaction.commit();
        }
        else if(msg.equals("signup")){
            signUpFragment = new SignUpFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.activity_main2, signUpFragment);
            fragmentTransaction.commit();
        }
        else if(msg.equals("admin")){
            adminLoginFragment = new AdminLoginFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.activity_main2, adminLoginFragment);
            fragmentTransaction.commit();
        }


    }
}
