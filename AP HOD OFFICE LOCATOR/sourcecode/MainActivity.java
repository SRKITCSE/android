package com.example.srkit.info;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
public class MainActivity extends AppCompatActivity {
Button button,button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.blogin);

        // Capture button clicks
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(MainActivity.this,
                        login.class);
                startActivity(myIntent);
            }
        });
        button1 = (Button) findViewById(R.id.breg);
        button1.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class

                Intent myIntent = new Intent(MainActivity.this,
                register.class);
                startActivity(myIntent);
            }
        });
    }
}
