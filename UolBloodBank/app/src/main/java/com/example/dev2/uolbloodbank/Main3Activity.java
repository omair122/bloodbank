package com.example.dev2.uolbloodbank;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import Service.APIservice;
import recyclerview.RecyclerAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Main3Activity extends AppCompatActivity {
    EditText name;
    EditText email;
    EditText password;
    EditText password2;
    EditText BloodGroup;
    EditText Phone;
    Button insertdata;
    Button retro;
    ProgressDialog pdialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        insertdata = (Button) findViewById(R.id.insertdata);
        retro = (Button) findViewById(R.id.retro);
        name = (EditText) findViewById(R.id.rrname);
        email = (EditText) findViewById(R.id.rremail);
        password = (EditText) findViewById(R.id.rrpass);
        password2 = (EditText) findViewById(R.id.rrpass2);
        BloodGroup = (EditText) findViewById(R.id.rrgroup);
        Phone = (EditText) findViewById(R.id.sphone);
        pdialog = new ProgressDialog(this);
        pdialog.setMessage("please wait...!");
        pdialog.setCancelable(false);

        insertdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertUser();
            }
        });
        retro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main3Activity.this, ActivityForRecycler.class);
                startActivity(intent);
            }
        });
    }

    private void InsertUser() {
        showpdialog();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.10.9:8080/uolbloodbank/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        APIservice service = retrofit.create(APIservice.class);
        if (password.getText().toString().equals(password2.getText().toString())) {
            User user = new User();
            user.setName(name.getText().toString());
            user.setEmail(email.getText().toString());
            user.setPass(password.getText().toString());
            user.setBloodGroup(BloodGroup.getText().toString());
            user.setPhone(Phone.getText().toString());
            Call<User> call = service.InsertUser(user.getName(), user.getEmail(), user.getPass(), user.getBloodGroup(), user.getPhone());
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    hidepdialog();
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    hidepdialog();

                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "Please Enter Correct Info", Toast.LENGTH_LONG).show();
        }


    }

    private void showpdialog() {
        pdialog.show();
    }

    private void hidepdialog() {
        pdialog.hide();
    }
}
