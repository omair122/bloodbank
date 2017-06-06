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

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {

    EditText name;
    EditText email;
    EditText password;
    EditText password2;
    EditText BloodGroup;
    EditText Phone;

    Button signup;
    User user;
    View view;
    String toJSON;
    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        name = (EditText) view.findViewById(R.id.rrname);
        email = (EditText) view.findViewById(R.id.rremail);
        password = (EditText) view.findViewById(R.id.rrpass);
        password2 = (EditText) view.findViewById(R.id.rrpass2);
        BloodGroup = (EditText) view.findViewById(R.id.rrgroup);
        Phone = (EditText) view.findViewById(R.id.sphone);

        signup = (Button) view.findViewById(R.id.submit);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText() != null && email.getText() != null && password.getText() != null && password2.getText() != null && BloodGroup.getText() != null && Phone.getText() != null ){
                    if(password.getText().toString().equals(password2.getText().toString())){
                        new DonorSignup().execute(name.getText().toString(),email.getText().toString(),password.getText().toString(),BloodGroup.getText().toString(),Phone.getText().toString());
                    }
                    else
                    {
                        Toast.makeText(getContext(), "Password Does not match!",Toast.LENGTH_SHORT).show();
                    }
                }
                else
                    Toast.makeText(getContext(), "Please Fill Required Field",Toast.LENGTH_SHORT).show();

            }
        });



        return view;
    }


    public class DonorSignup extends AsyncTask<String,Void,Boolean> {
        public int che =0;
        ProgressDialog dialog;
        @Override
        protected void onPreExecute(){
            dialog = ProgressDialog.show(getContext(),"Wait..","Loading...");
        }
        @Override
        protected Boolean doInBackground(String... params) {
            OkHttpClient okHttpClient = new OkHttpClient();
            FormBody formbody = new FormBody.Builder()
                    .add("name",params[0])
                    .add("email",params[1])
                    .add("password",params[2])
                    .add("BloodGroup",params[3])
                    .add("Phone",params[4])
                    .build();
            Request request = new Request.Builder().url("http://192.168.10.9:8080/uolbloodbank/public/donor").post(formbody).build();

            try {
                Thread.sleep(1000);
                Response response = okHttpClient.newCall(request).execute();
                if(response.body()!=null)
                {
                    che=1;
                }

            } catch (IOException |  InterruptedException e ) {
                e.printStackTrace();
            }

            if (che==1)
            {
                return true;
            }
            else
                return false;


        }
        @Override
        protected void onPostExecute(Boolean result) {
            dialog.dismiss();
            if(result==false)
            {
                Toast.makeText(getContext(),"wrong username or password",Toast.LENGTH_LONG).show();
            }
            else if(result==true)
            {
                name.setText(null);
                email.setText(null);
                password.setText(null);
                password2.setText(null);
                BloodGroup.setText(null);
                Phone.setText(null);
                Toast.makeText(getContext(),"Data sent Successfully",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getContext(),MainActivity.class);
                startActivity(intent);



            }
        }
    }

}