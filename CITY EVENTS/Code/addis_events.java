package com.techtotos.events;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class addis_events extends AppCompatActivity {
Button button,button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addis_events);
        button = (Button) findViewById(R.id.button);

        // Capture button clicks
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(addis_events.this,
                        viewevent_types.class);
                startActivity(myIntent);
            }
        });
        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class

                Intent myIntent = new Intent(addis_events.this,
                        addevent_types.class);
                startActivity(myIntent);
            }
        });
    }
}
