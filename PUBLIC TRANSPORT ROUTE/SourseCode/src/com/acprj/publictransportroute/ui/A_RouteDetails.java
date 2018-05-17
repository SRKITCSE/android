package com.acprj.publictransportroute.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.acprj.publictransportroute.R;
import com.acprj.publictransportroute.S_EnterRouteDetails;

public class A_RouteDetails extends Activity{
	String stop_name="",route_no="",lat="",lng="";
	EditText etStopName,etRouteNo,etLat,etLng;
	Button btnInsertRouteDetails;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a_enterroutedetails);
		etStopName=(EditText)findViewById(R.id.etStopName);
		etRouteNo=(EditText)findViewById(R.id.etRouteNo);
		etLat=(EditText)findViewById(R.id.etLat);
		etLng=(EditText)findViewById(R.id.etLng);
		 
		 btnInsertRouteDetails=(Button)findViewById(R.id.btnInsertRouteDetails);
		 btnInsertRouteDetails.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				stop_name=etStopName.getText().toString();
				route_no=etRouteNo.getText().toString();
				lat=etLat.getText().toString();
				lng=etLng.getText().toString();
				if(stop_name.length()==0){
					Toast.makeText(getApplicationContext(), "Please enter stop name.", Toast.LENGTH_SHORT).show();
				}else if(route_no.length()==0){
					Toast.makeText(getApplicationContext(), "Please enter route no.", Toast.LENGTH_SHORT).show();
				}else if(lat.length()==0){
					Toast.makeText(getApplicationContext(), "Please enter lat.", Toast.LENGTH_SHORT).show();
				}else if(lng.length()==0){
					Toast.makeText(getApplicationContext(), "Please enter src lng.", Toast.LENGTH_SHORT).show();
				}else{
					new InsertRouteDetails().execute();
				}
			}
		});
	}
	String res=null;
	ProgressDialog pd;
	public class InsertRouteDetails extends AsyncTask<Void, Void, Void> {
		@Override
		protected void onPreExecute() {
			pd = new ProgressDialog(A_RouteDetails.this);
			pd.setMessage("Pleaase wait,data is being inserted...");
			pd.show();
		}
		@Override
		protected Void doInBackground(Void... params) {
			try{
				S_EnterRouteDetails rDetails=new S_EnterRouteDetails("http://publictransportroute.net63.net/PTR/insertRouteDetails.php");
				res=rDetails.insertRouteDetails(stop_name, route_no,lat, lng);
			}catch(Exception e){}
			return null;

		}
		protected void onPostExecute(Void unused){ 
			pd.hide();
			if(res!=null){
				if(res.equalsIgnoreCase("success")){
					Toast.makeText(getApplicationContext(), "Data is inserted successfully.", Toast.LENGTH_LONG).show();
					stop_name="";route_no="";lat="";lng="";
					etStopName.getText().clear();
					etRouteNo.getText().clear();
					etLat.getText().clear();
					etLng.getText().clear();
				}
			}
		}
	}
}

