package com.example.srkit.info;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


public class data extends AppCompatActivity {

    private ListView listView;
    private FloatingActionButton fab;

    static String[] items={"CE, Central Designing Organisation","Directorates Of Factories","Bc Commission","Commissioner of police","DGP OFFICE","Panchayati Raj, Rural Development","Engineer In Chief, Panchayati Raj","RTC Department","Transport Department","ACB Department","Department of Women Development and child Welfare","State Disaster Response And Fire Department","APIIC Deparment","Irrigation Department","Amaravathi Metro Rail Corporation","IT Department","Fisheries Department","Rasthra Pranalika Sangham","Directorate of Prosecution","Prohibition and Excise","MD, State Beverages Corporation","Enforcement Directorate","Tribal Welfare Department","Technical Education Department","Counter Intelligence","Sports Department","Commercial Taxes Department","Director of Medical Education","AP Para Medical","Department Structure of Youth Advancement","Coconut Development Board","Kapu Corporation","Warehousing Corporation","Customs Department","Income Tax Department","Agriculture Departments","Marketing Department","Commissioner of Collegiate","Minor Irrigation Department","Municipal Administration","Forest Department","AP NEDCAP"};
    int[] img={R.drawable.aplogo,R.drawable.aplogo,R.drawable.aplogo,R.drawable.aplogo,R.drawable.aplogo,R.drawable.aplogo,R.drawable.aplogo,R.drawable.aplogo,R.drawable.aplogo,R.drawable.aplogo,R.drawable.aplogo,R.drawable.aplogo,R.drawable.aplogo,R.drawable.aplogo,R.drawable.aplogo,R.drawable.aplogo,R.drawable.aplogo,R.drawable.aplogo,R.drawable.aplogo,R.drawable.aplogo,R.drawable.aplogo,R.drawable.aplogo,R.drawable.aplogo,R.drawable.aplogo,R.drawable.aplogo,R.drawable.aplogo,R.drawable.aplogo,R.drawable.aplogo,R.drawable.aplogo,R.drawable.aplogo,R.drawable.aplogo,R.drawable.aplogo,R.drawable.aplogo,R.drawable.aplogo,R.drawable.aplogo,R.drawable.aplogo,R.drawable.aplogo,R.drawable.aplogo,R.drawable.aplogo,R.drawable.aplogo,R.drawable.aplogo,R.drawable.aplogo,R.drawable.aplogo,R.drawable.aplogo,R.drawable.aplogo};
    static int pos;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        listView=(ListView) findViewById(R.id.listView);
        listView.setAdapter(new CustomAdapter(data.this,items,img));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(data.this, Give_FeedBack.class);
                startActivity(intent);
            }

        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                pos = position;

                Intent it = new Intent(data.this, FragmentDetail.class);
                startActivity(it);
            }
        });
    }

}



