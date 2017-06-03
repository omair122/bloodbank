package com.example.dev2.uolbloodbank;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CompleteListActivity extends AppCompatActivity {
    User userx;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_list);
        listView =(ListView)findViewById(R.id.ls);
        new Listing().execute();
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//        });

    }
    public class Listing extends AsyncTask<String,Void,Boolean> {
        public int che = 1;
        ListAdapter arrayAdapter;

        int id;
        JSONArray array;

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected Boolean doInBackground(String... params) {
            ArrayList<User> arrayx = new ArrayList<User>();
            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder().url("http://192.168.10.16:8080/uolbloodbank/public/donor").build();
            try {
                Thread.sleep(1000);
                Response response = okHttpClient.newCall(request).execute();
                array = new JSONArray(response.body().string());

                for (int i = 0; i < array.length(); i++) {

                    JSONObject object = array.getJSONObject(i);
                    User user = new User(object.getInt("id"), object.getString("name"), object.getString("email"), object.getString("password"), object.getString("BloodGroup"),object.getString("Phone"));
                    arrayx.add(user);
                }
                arrayAdapter = new ListAdapter(getApplicationContext(), R.layout.mylist, arrayx);
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
            if (result == false) {
                Toast.makeText(getApplicationContext(), "wrong username or password", Toast.LENGTH_LONG).show();
            } else if (result == true) {
                listView.setAdapter(arrayAdapter);

//                Intent i = new Intent(getContext(), AfterLoginActivity.class);
//                Gson gson = new Gson();
//                String jsonString = gson.toJson(userx);
//                i.putExtra("jsonString", jsonString);
//                startActivity(i);

            }
        }
    }
    public class ListAdapter extends ArrayAdapter<User> {



        private List<User> users;

        public ListAdapter(Context context, int resource, List<User> users) {

            super(context, resource, users);

            this.users = users;


        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View v = convertView;

            TextView name = null;
            TextView email = null;
            TextView blood = null;
            ImageView img = null;
            if (v == null) {

                LayoutInflater vi;
                vi = LayoutInflater.from(getContext());
                v = vi.inflate(R.layout.mylist, null);


                name = (TextView) v.findViewById(R.id.tvname);
                email = (TextView) v.findViewById(R.id.tvemail);
                blood = (TextView) v.findViewById(R.id.tvblood);
                img = (ImageView) v.findViewById(R.id.img);
            }

            User user = users.get(position);

            if (user != null) {

                if (name != null) {

                    name.setText( "Name:                  " + user.getName());
                    email.setText("Email:                   "+user.getEmail());
                    blood.setText("Blood Group:       "+user.getBloodGroup());
                    img.setImageResource(R.drawable.pic5);


                }

            }
            return v;

        }
    }
}
