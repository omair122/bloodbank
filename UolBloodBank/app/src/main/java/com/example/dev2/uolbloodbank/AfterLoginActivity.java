package com.example.dev2.uolbloodbank;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AfterLoginActivity extends AppCompatActivity {
    User user;
    TextView name;
    TextView email;
    TextView bloodGroup;
    TextView Phone;
    Button update;
    Button List;

    String jsonString = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login);
        Intent intent = getIntent();
        jsonString = intent.getStringExtra("jsonString");
        Gson gson = new Gson();
        user = gson.fromJson(jsonString, User.class);
        name =  (TextView) findViewById(R.id.namex);
        email = (TextView) findViewById(R.id.emailx);
        bloodGroup = (TextView) findViewById(R.id.bloodx);
        Phone = (TextView) findViewById(R.id.phonex);

        update =(Button) findViewById(R.id.button2);
        name.setText(user.getName());
        email.setText(user.getEmail());
        List = (Button) findViewById(R.id.button3);
        bloodGroup.setText(user.getBloodGroup());
        Phone.setText(user.getPhone());
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(AfterLoginActivity.this,UpdateUser.class);
                intent1.putExtra("jsonString",jsonString);
                startActivity(intent1);
            }
        });
        List.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1= new Intent(AfterLoginActivity.this,CompleteListActivity.class);
                startActivity(intent1);
            }
        });



    }
}
