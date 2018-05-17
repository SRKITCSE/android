package com.techtotos.events;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class add_hosp extends AppCompatActivity {
    EditText un, pwd, conpwd, mno, e,r;
    Spinner t;
    Button b,button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hosp);
        un = (EditText) findViewById(R.id.username);
        pwd = (EditText) findViewById(R.id.password);
        conpwd = (EditText) findViewById(R.id.repassword);
        mno = (EditText) findViewById(R.id.phone);
        e = (EditText) findViewById(R.id.email);

        r = (EditText) findViewById(R.id.city);





        b = (Button) findViewById(R.id.RegisterScreen);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String user, password, confirmpassword, email,rollnumber,mobilenumber;
                user = un.getText().toString();
                password = pwd.getText().toString();
                confirmpassword = conpwd.getText().toString();
                mobilenumber = mno.getText().toString();
                email = e.getText().toString();

                rollnumber=r.getText().toString();
                StringRequest stringRequest = new StringRequest(Request.Method.POST,"http://srkevents.16mb.com/naveen/events_hosp.php",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //Toast.makeText(register.this, response+"output", Toast.LENGTH_LONG).show();
                                Intent intent=new Intent(getApplicationContext(),addis_events.class);
                                startActivity(intent);
                                Toast.makeText(add_hosp.this,"event has been added successfuly",Toast.LENGTH_LONG).show();

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(add_hosp.this, error.toString()+"...........error", Toast.LENGTH_LONG).show();
                            }
                        })


                {
                    @Override
                    protected Map<String,String> getParams(){
                        Map<String,String> params = new HashMap<String, String>();
                        params.put("user",user);
                        params.put("password",password);
                        params.put("confirmpassword", confirmpassword);
                        params.put("mobilenumber", mobilenumber);
                        params.put("email", email);

                        params.put("rollnumber", rollnumber);
                        return params;
                    }

                };
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);

            }


        });

    }
}