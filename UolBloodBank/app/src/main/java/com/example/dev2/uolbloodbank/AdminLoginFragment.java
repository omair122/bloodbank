package com.example.dev2.uolbloodbank;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class AdminLoginFragment extends Fragment {

    Button login;
    EditText email;
    EditText pass;
    View view;
    Admin userx;

    public AdminLoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_admin_login, container, false);
        login = (Button) view.findViewById(R.id.ssbutton);
        email = (EditText) view.findViewById(R.id.ssusername);
        pass = (EditText) view.findViewById(R.id.sspassword);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new adminLogin().execute(email.getText().toString(), pass.getText().toString());
            }
        });

        // Inflate the layout for this fragment
        return view;

    }

    public class adminLogin extends AsyncTask<String, Void, Boolean> {
        public int che = 0;
        int id;
        JSONArray array;
        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            dialog = ProgressDialog.show(getContext(), "Wait..", "Loading...");
        }

        @Override
        protected Boolean doInBackground(String... params) {
            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder().url("http://192.168.10.9:8080/uolbloodbank/public/admin").build();
            try {
                Thread.sleep(1000);
                Response response = okHttpClient.newCall(request).execute();
                array = new JSONArray(response.body().string());

                for (int i = 0; i < array.length(); i++) {

                    JSONObject object = array.getJSONObject(i);
//                    Toast.makeText(getContext(),object.getString("name"),Toast.LENGTH_SHORT).show();


                    Admin user = new Admin(object.getInt("id"), object.getString("name"), object.getString("email"), object.getString("password"));
                    if (user.getName().equals(params[0]) && user.getPassword().equals(params[1])) {
                        userx = user;
                        che = 1;
                    }

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (che == 1) {
                return true;
            } else
                return false;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            dialog.dismiss();
            if (result == false) {
                Toast.makeText(getContext(), "wrong username or password", Toast.LENGTH_LONG).show();
            } else if (result == true) {

                Intent i = new Intent(getContext(), CompleteListActivity.class);
                Gson gson = new Gson();
                String jsonString = gson.toJson(userx);
                i.putExtra("jsonString", jsonString);
                startActivity(i);

            }
        }
    }

}
