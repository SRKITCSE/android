package com.techtotos.events;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class addevent_types extends AppCompatActivity {
Button button,button1,button2,button3,button4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addevent_types);
        button = (Button) findViewById(R.id.button);

        // Capture button clicks
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(addevent_types.this,
                        addevent.class);
                startActivity(myIntent);
            }
        });
        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class

                Intent myIntent = new Intent(addevent_types.this,
                        add_Exhibition.class);
                startActivity(myIntent);
            }
        });

        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class

                Intent myIntent = new Intent(addevent_types.this,
                        add_hosp.class);
                startActivity(myIntent);
            }
        });

        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class

                Intent myIntent = new Intent(addevent_types.this,
                        add_wp.class);
                startActivity(myIntent);
            }
        });

        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class

                Intent myIntent = new Intent(addevent_types.this,
                        add_seminar.class);
                startActivity(myIntent);
            }
        });
    }
}
