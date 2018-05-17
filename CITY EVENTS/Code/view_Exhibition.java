package com.techtotos.events;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class view_Exhibition extends AppCompatActivity {



    private String TAG = viewevents.class.getSimpleName();
    private ListView lv;
    private ImageView images;
    ArrayList<HashMap<String, String>> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__exhibition);

        //Initializing the ImageView
        //images = (ImageView) findViewById(R.id.images);
        //ImageView images=(ImageView) findViewById(R.id.images);
        //Imageview images=(Imageview) findviewbyid(R.id.images);
        //Loading Image from URL


        contactList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.list);

        //adapter = new ListViewAdapter(MainActivity.this, company, purpose, desc);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent i = new Intent(view_Exhibition.this, MainActivity.class);
                startActivity(i);
            }
        });
        new view_Exhibition.GetContacts().execute();
    }

    private class GetContacts extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(view_Exhibition.this,"Json Data is downloading",Toast.LENGTH_LONG).show();


        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String url = "http://srkevents.16mb.com/naveen/get_events_exb.php";
            String jsonStr = sh.makeServiceCall(url);
            //ImageView images=(ImageView) findViewById(R.id.images);
            //Picasso.with(this).load("http://skrcse.96.lt/images/coupons/paytm.png").into(images);
            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray details = jsonObj.getJSONArray("details");

                    // looping through All details
                    for (int i = 0; i < details.length(); i++) {
                        JSONObject c = details.getJSONObject(i);
                        String company = c.getString("eventid");
                        String purpose = c.getString("eventname");
                        String desc = c.getString("eventvenue");
                        String images = c.getString("eventsdate");
                        String images1 = c.getString("eventedate");
                        String images2 = c.getString("eventtime");

                        //String gender = c.getString("gender");

                        // Phone node is JSON Object
                        //JSONObject phone = c.getJSONObject("phone");
                        //String mobile = phone.getString("mobile");
                        //String home = phone.getString("home");
                        //String office = phone.getString("office");

                        // tmp hash map for single contact
                        HashMap<String, String> contact = new HashMap<>();

                        // adding each child node to HashMap key => value
                        contact.put("eventid", company);
                        contact.put("eventname", purpose);
                        contact.put("eventvenue", desc);
                        contact.put("eventsdate", images);
                        contact.put("eventedate", images1);
                        contact.put("eventtime", images2);

                        // adding contact to contact list
                        contactList.add(contact);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                }

            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            ListAdapter adapter = new SimpleAdapter(view_Exhibition.this, contactList,
                    R.layout.list_item, new String[]{ "eventid","eventname","eventvenue","eventsdate","eventedate","eventtime"},
                    new int[]{R.id.company, R.id.purpose, R.id.desc, R.id.con, R.id.con1, R.id.con2});
            lv.setAdapter(adapter);
        }


    }
}

