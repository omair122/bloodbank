package com.example.dev2.uolbloodbank;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UpdateUser extends AppCompatActivity {
    User user;
    EditText name;
    EditText email;
    EditText oldxpass;
    EditText password;
    EditText password2;
    EditText BloodGroup;
    EditText Phone;

    Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);
        Intent intent = getIntent();
        String jsonString = intent.getStringExtra("jsonString");
        Gson gson = new Gson();
        user = gson.fromJson(jsonString, User.class);
        name = (EditText) findViewById(R.id.rrname);
        email = (EditText) findViewById(R.id.rremail);
        oldxpass = (EditText) findViewById(R.id.oldpass);
        password = (EditText) findViewById(R.id.rrpass);
        password2 = (EditText) findViewById(R.id.rrpass2);
        BloodGroup = (EditText) findViewById(R.id.rrgroup);
        Phone = (EditText) findViewById(R.id.sxphone);

        update = (Button) findViewById(R.id.submit);
        name.setText(user.getName());
        email.setText(user.getEmail());
        BloodGroup.setText(user.getBloodGroup());
        Phone.setText(user.getPhone());

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (oldxpass.getText().toString().equals(user.getPass()) && password.getText().toString().equals(password2.getText().toString())) {
                    new UserUpdate().execute(name.getText().toString(),email.getText().toString(),password.getText().toString(),BloodGroup.getText().toString(),Phone.getText().toString());
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Something is Wrong",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public class UserUpdate extends AsyncTask<String, Void, Boolean> {
        int chek = 0, nchek = 0;

        @Override
        protected Boolean doInBackground(String... params) {


            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url("http://192.168.10.9:8080/uolbloodbank/public/donor").build();
            try {
                Thread.sleep(100);
                Response response = client.newCall(request).execute();
                JSONArray array = new JSONArray(response.body().string());

                for (int i = 0; i < array.length(); i++) {

                    JSONObject object = array.getJSONObject(i);

                    User userx = new User(object.getInt("id"), object.getString("name"), object.getString("email"), object.getString("password"), object.getString("BloodGroup"),object.getString("Phone"));

                    if (userx.getEmail().equals(params[1])) {
                        chek = userx.getId();
                        RequestBody fx = new FormBody.Builder()
                                .add("name", params[0])
                                .add("email", params[1])
                                .add("password", params[2])
                                .add("BloodGroup", params[3])
                                .add("Phone", params[4])
                                .build();
                        Request req1 = new Request.Builder()
                                .url("http://192.168.10.9:8080/uolbloodbank/public/donor/"+chek)
                                .put(fx).build();
                        try {
                            Thread.sleep(100);
                            Response hi = client.newCall(req1).execute();

                            if (hi.body() != null) {
                                nchek = 1;
                            }
                        } catch (IOException | InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }


            } catch (IOException | JSONException | InterruptedException e) {
                e.printStackTrace();
            }
            if (nchek == 1) {
                return true;
            } else
                return false;


        }

        protected void onPostExecute(Boolean result) {

            if (result == false) {
                Toast.makeText(getApplicationContext(), "Record not updated due to unknown reason", Toast.LENGTH_LONG).show();
            } else if (result == true) {
                name.setText(null);
                email.setText(null);
                oldxpass.setText(null);
                password.setText(null);
                password2.setText(null);
                BloodGroup.setText(null);
                Phone.setText(null);

                Toast.makeText(getApplicationContext(), "Record updated successfully", Toast.LENGTH_LONG).show();
                Intent intent1= new Intent(UpdateUser.this,CompleteListActivity.class);
                startActivity(intent1);
            }
        }
    }

    }
