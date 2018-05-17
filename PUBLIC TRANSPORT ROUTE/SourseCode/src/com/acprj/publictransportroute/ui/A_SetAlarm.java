package com.acprj.publictransportroute.ui;

import java.util.HashMap;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.acprj.publictransportroute.R;

public class A_SetAlarm extends Activity {
	AutoCompleteTextView actvSource;
	String rid1;
	int i1;
	double sLat=0.0,sLng=0.0;
	Button btnSetAlarm,btnCancelAlarm;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a_set_alarm);
		btnSetAlarm=(Button)findViewById(R.id.btnSetAlarm);
		btnCancelAlarm=(Button)findViewById(R.id.btnCancelAlarm);
		btnSetAlarm.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if(sLat==0.0){
					Toast.makeText(getApplicationContext(), "Please select wakeup location.", Toast.LENGTH_SHORT).show();
				}else{
					SharedPreferences prefs = getSharedPreferences(A_SetAlarm.class.getSimpleName(), Context.MODE_PRIVATE);
					SharedPreferences.Editor editor = prefs.edit();
					editor.putString("c_lat",""+sLat);
					editor.putString("c_lng",""+sLng);
					editor.commit();
					calDistance();
				}
			}
		});
		btnCancelAlarm.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
				if (manager != null) {
					manager.cancel(pendingIntent);
					Toast.makeText(getBaseContext(), "wakeup service is stopped.", Toast.LENGTH_LONG).show();
				}      
			}
		});
		String srcDes[]=new String[A_Dashboard.mSourceDestination.size()];
		actvSource=(AutoCompleteTextView)findViewById(R.id.autoWakeUpLoc);
		for(int i=0;i<A_Dashboard.mSourceDestination.size();i++){
			HashMap<String, String> hm=A_Dashboard.mSourceDestination.get(i);
			srcDes[i]=hm.get("stop_name");
		}

		ArrayAdapter adapter = new ArrayAdapter (A_SetAlarm.this,android.R.layout.simple_list_item_1,srcDes);
		actvSource.setAdapter(adapter);
		actvSource.setThreshold(1);
		actvSource.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View arg1, int arg2,long arg3) {
				rid1 = (String) parent.getItemAtPosition(arg2);
				for(int i=0;i<A_Dashboard.mSourceDestination.size();i++){
					if(A_Dashboard.mSourceDestination.get(i).get("stop_name").equalsIgnoreCase(rid1)){
						i1=i;
						sLat=Double.parseDouble(A_Dashboard.mSourceDestination.get(i).get("lat"));
						sLng=Double.parseDouble(A_Dashboard.mSourceDestination.get(i).get("lng"));
					}
				}
				Toast.makeText(getApplicationContext(), ""+sLat+" : "+sLng, Toast.LENGTH_SHORT).show();
			}
		});

	}
	AlarmManager manager;
	PendingIntent pendingIntent;
	private void calDistance(){
		
		Intent alarmIntent = new Intent(this, R_GetLoactionReceivers.class);
		pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, 0);
		manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
		int interval = 30000*1;
		manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
		
	}
}
