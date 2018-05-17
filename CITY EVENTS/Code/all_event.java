package com.techtotos.events;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class all_event extends AppCompatActivity {
Button button,button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_event);
        button = (Button) findViewById(R.id.blogin);

        // Capture button clicks
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(all_event.this,
                        login_event.class);
                startActivity(myIntent);
            }
        });
        button1 = (Button) findViewById(R.id.breg);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class

                Intent myIntent = new Intent(all_event.this,
                        register_event.class);
                startActivity(myIntent);
            }
        });
    }
}
