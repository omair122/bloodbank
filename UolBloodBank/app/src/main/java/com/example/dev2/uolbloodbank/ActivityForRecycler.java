package com.example.dev2.uolbloodbank;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import Service.APIservice;
import recyclerview.RecyclerAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActivityForRecycler extends AppCompatActivity {
    RecyclerView recyclerview;
    RecyclerAdapter recyclerAdapter;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_recycler);

        getUsers();
    }

    private void getUsers() {

        try {
            Thread.sleep(2000);
            Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.10.9:8080/uolbloodbank/")
                    .addConverterFactory(GsonConverterFactory.create()).build();
            APIservice service = retrofit.create(APIservice.class);
            Call<List<User>> call = service.getDonors();
            call.enqueue(new Callback<List<User>>() {
                @Override
                public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                    List<User> users = response.body();

                    linearLayoutManager = new LinearLayoutManager(ActivityForRecycler.this);
                    recyclerview = (RecyclerView) findViewById(R.id.recyclex);
                    recyclerview.setLayoutManager(linearLayoutManager);
                    recyclerAdapter = new RecyclerAdapter(users);
                    recyclerview.setAdapter(recyclerAdapter);

                }

                @Override
                public void onFailure(Call<List<User>> call, Throwable t) {
                    Log.d("failed", "failed");
                }
            });
        } catch (Exception e) {

        }

    }


}
