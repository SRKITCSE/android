package com.example.srkit.info;

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

public class Give_FeedBack extends AppCompatActivity {

    EditText e,f,n;
    Spinner t;
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give__feed_back);

        n = (EditText) findViewById(R.id.editText31);
        e = (EditText) findViewById(R.id.editText32);
        f = (EditText) findViewById(R.id.editText33);
        b = (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String feedback,name,email;

               name=n.getText().toString();
                email=e.getText().toString();
                feedback=f.getText().toString();
                StringRequest stringRequest = new StringRequest(Request.Method.POST,"http://srkgovt.16mb.com/aman/feedback.php",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Intent intent=new Intent(getApplicationContext(),FragmentDetail.class);
                                startActivity(intent);
                                Toast.makeText(Give_FeedBack.this,"Successfully Submitted", Toast.LENGTH_LONG).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(Give_FeedBack.this, error.toString()+"...........error", Toast.LENGTH_LONG).show();
                            }
                        })


                {
                    @Override
                    protected Map<String,String> getParams(){
                        Map<String,String> params = new HashMap<String, String>();

                        params.put("name", name);
                        params.put("email", email);
                        params.put("feedback", feedback);

                        return params;
                    }

                };
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);

            }


        });

    }
}
