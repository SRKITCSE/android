package com.acprj.publictransportroute.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
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
import com.acprj.publictransportroute.S_SourceDestinations;
public class A_Dashboard extends Activity {
	//Spinner spRoutes,spSource,spDestination;
	Button btnSubmit,btnViewDirection,btnSetAlarm,btnAdminLogin;
	String rid1,rid2;
	String mRouteID=null;
	double sLat,sLng,dLat,dLng;
	int sourceIndex=-1,destIndex=-1;
	private AutoCompleteTextView actvSource,actvDestination;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a_dashboard);
		btnSubmit=(Button)findViewById(R.id.btnGetBuses);
		btnViewDirection=(Button)findViewById(R.id.btnViewDirection);
		btnSetAlarm=(Button)findViewById(R.id.btnSetAlarm);
		btnAdminLogin=(Button)findViewById(R.id.btnAdminLogin);
		actvSource=(AutoCompleteTextView)findViewById(R.id.autoCTSource);
		actvSource.setThreshold(1);
		actvSource.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View arg1, int arg2,long arg3) {
				rid1 = (String) parent.getItemAtPosition(arg2);
				for(int i=0;i<mSourceDestination.size();i++){
					if(mSourceDestination.get(i).get("stop_name").equalsIgnoreCase(rid1)){
						sourceIndex=i;
						sLat=Double.parseDouble(mSourceDestination.get(i).get("lat"));
						sLng=Double.parseDouble(mSourceDestination.get(i).get("lng"));
					}
				}
				//Toast.makeText(getApplicationContext(), ""+ind, Toast.LENGTH_SHORT).show();
			}
		});
		actvDestination=(AutoCompleteTextView)findViewById(R.id.autoDTSource);
		actvDestination.setThreshold(1);
		actvDestination.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View arg1, int arg2,long arg3) {
				rid2 = (String) parent.getItemAtPosition(arg2);
				for(int i=0;i<mSourceDestination.size();i++){
					if(mSourceDestination.get(i).get("stop_name").equalsIgnoreCase(rid2)){
						destIndex=i;
						dLat=Double.parseDouble(mSourceDestination.get(i).get("lat"));
						dLng=Double.parseDouble(mSourceDestination.get(i).get("lng"));
					}
				}
			}
		});

		btnSubmit.setEnabled(false);
		btnSubmit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(sourceIndex!=-1&&destIndex!=-1){
					if(mSourceDestination.get(sourceIndex).get("rid").equalsIgnoreCase(mSourceDestination.get(destIndex).get("rid"))){
						Intent intent=new Intent(getApplicationContext(),A_Busses.class);
						intent.putExtra("mRouteID", mSourceDestination.get(sourceIndex).get("rid"));
						startActivity(intent);
					}else{
						Toast.makeText(getApplicationContext(), "Sorry no route busses are available", Toast.LENGTH_SHORT).show();
					}
				}else{
					Toast.makeText(getApplicationContext(), "Please select soure and destination.", Toast.LENGTH_SHORT).show();
				}
			}
		});

		btnSetAlarm.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(getApplicationContext(),A_SetAlarm.class);
				startActivity(intent);
			}
		});
		btnAdminLogin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(getApplicationContext(),A_AdminLogin.class);
				startActivity(intent);
			}
		});
		
		btnViewDirection.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(sourceIndex!=-1&&destIndex!=-1){
				Intent intent=new Intent(getApplicationContext(),A_View_BusDirectionsMap.class);
				intent.putExtra("sLat", sLat);
				intent.putExtra("sLng", sLng);
				intent.putExtra("dLat", dLat);
				intent.putExtra("dLng", dLng);
				startActivity(intent);
				}else{
					Toast.makeText(getApplicationContext(), "Please select soure and destination.", Toast.LENGTH_SHORT).show();
				}
			}
		});
		new GetSourceDest().execute();
	}

	ProgressDialog pb;
	ArrayList<HashMap<String, String>> mRoutes = new ArrayList<HashMap<String, String>>();
	public static ArrayList<HashMap<String, String>> mSourceDestination = new ArrayList<HashMap<String, String>>();

	private class GetSourceDest extends AsyncTask<Void, Void, Void>{
		@Override
		protected void onPreExecute() {
			pb=new ProgressDialog(A_Dashboard.this);
			pb.setTitle("Please wait,data is being loaded.");
			pb.show();
		}
		@Override
		protected Void doInBackground(Void... arg0) {
			try{
				//S_SourceDestinations rs=new S_SourceDestinations("http://telugucenimaentertainment.com/Project/PTR/getStopDetails.php");
				S_SourceDestinations rs=new S_SourceDestinations("http://www.publictransportroute.net63.net/PTR/getStopDetails.php");
				mSourceDestination.clear();
				mSourceDestination=rs.getSourceDestinations(mRouteID);
				return null;
			}catch(Exception e){
				return null;
			}
		}
		protected void onPostExecute(Void unused){
			String srcDes[]=new String[mSourceDestination.size()];

			List<String> menus = new ArrayList<String>();
			for(int i=0;i<mSourceDestination.size();i++){
				HashMap<String, String> hm=mSourceDestination.get(i);
				menus.add(hm.get("stop_name"));
				srcDes[i]=hm.get("stop_name");
			}

			ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(A_Dashboard.this,android.R.layout.simple_spinner_item, menus);
			dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			ArrayAdapter adapter = new ArrayAdapter (A_Dashboard.this,android.R.layout.simple_list_item_1,srcDes);
			actvSource.setAdapter(adapter);
			actvDestination.setAdapter(adapter);
			pb.hide();
			btnSubmit.setEnabled(true);
		}
	}
}
