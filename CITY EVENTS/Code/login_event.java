package com.techtotos.events;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class login_event extends AppCompatActivity implements View.OnClickListener {

    Button button,button1,button2;
    EditText editusername;
    EditText editpassword;

    public static final String TAG = "Login Activity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_event);
        button = (Button) findViewById(R.id.RegisterScreen);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(login_event.this,
                        register_event.class);
                startActivity(myIntent);
            }
        });

        // Capture button clicks

        button1 = (Button) findViewById(R.id.btnLogin);

        // Capture button clicks

        button2 = (Button) findViewById(R.id.reset);
        button2.setOnClickListener(this);
        button1.setOnClickListener(this);

    }
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.reset:
                editusername= (EditText) findViewById(R.id.username);
                editpassword= (EditText) findViewById(R.id.password);
                editusername.setText("");
                editpassword.setText("");
                break;
            case R.id.btnLogin:
                editusername= (EditText) findViewById(R.id.username);
                editpassword= (EditText) findViewById(R.id.password);

                String username=editusername.getText().toString();
                String password=editpassword.getText().toString();

                if(username.length()>0 && password.length()>0 ) {
//                Toast.makeText(getApplicationContext(),username +" and "+password ,Toast.LENGTH_LONG).show();
                    final String url="http://srkevents.16mb.com/naveen/login_event.php";
//                  final String url="http://www.madhutechskills.com/MobileSurvey/RegisterClient.php";
                    new login_event.AsyncHttpTask().execute(url,"username="+username+"&password="+password);

//                    editusername= (EditText) findViewById(R.id.username);
                    editpassword= (EditText) findViewById(R.id.password);
//                    editusername.setText("");
                    editpassword.setText("");

                } else {
                    //display message if text field is empty
                    Toast.makeText(getBaseContext(),"All fields are required",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
        //Toast.makeText(this,"Good",Toast.LENGTH_LONG).show();
    }

    public class AsyncHttpTask extends AsyncTask<String, Void, Integer> {

        @Override
        protected void onPreExecute() {
            setProgressBarIndeterminateVisibility(true);
        }

        @Override
        protected Integer doInBackground(String... params) {
            InputStream inputStream = null;
            Integer result = 0;
            HttpURLConnection urlConnection = null;

            try {
                /* forming th java.net.URL object */
                URL url = new URL(params[0]);

                urlConnection = (HttpURLConnection) url.openConnection();

                urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty("User-Agent","Mozilla/5.0");
                urlConnection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
                String urlParameters = params[1];
                // Send post request
                urlConnection.setDoOutput(true);
                DataOutputStream wr = new DataOutputStream(urlConnection.getOutputStream());
                wr.writeBytes(urlParameters);
                wr.flush();
                wr.close();
                int statusCode = urlConnection.getResponseCode();

                /* 200 represents HTTP OK */
                if (statusCode == 200) {

                    BufferedReader r = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = r.readLine()) != null) {
                        response.append(line);
                    }
                    Log.i(TAG, response.toString() + "   **********************************   ");
                    // parseResult(response.toString());
                    if(response.toString().equalsIgnoreCase("S")){
                        result = 1; // Successful
                    }
                    else if (response.toString().equalsIgnoreCase("N")){
                        result=0;
                    }else{
                        result=0;
                    }
//                    Toast.makeText(getApplicationContext(),result+" success",Toast.LENGTH_LONG).show();
                } else {
                    result = 0; //"Failed to fetch data!";
//                    Toast.makeText(getApplicationContext(),result+" faild",Toast.LENGTH_LONG).show();
                }

            } catch (Exception e) {
                Log.d(TAG, e.getLocalizedMessage());
            }

            return result; //"Failed to fetch data!";
        }

        @Override
        protected void onPostExecute(Integer result)
        {
            setProgressBarIndeterminateVisibility(false);
             /* Download complete. Lets update UI */
//            Toast.makeText(getApplicationContext(),"value of result is"+result,Toast.LENGTH_LONG).show();
            if (result == 1) {
                Intent intent=new Intent(getApplicationContext(),addis_events.class);
                startActivity(intent);
                Toast.makeText(login_event.this," Welcome User ",Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(login_event.this,"Incorrect Email or Password",Toast.LENGTH_LONG).show();
                Log.e(TAG, "Failed to fetch data!");
            }
        }
    }
}
