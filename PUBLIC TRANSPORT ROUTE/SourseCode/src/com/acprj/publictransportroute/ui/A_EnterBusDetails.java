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
import com.acprj.publictransportroute.S_EnterBusDetails;
import com.acprj.publictransportroute.ui.A_AdminLogin.GetLogin;

public class A_EnterBusDetails extends Activity{
	String buss_no="",source="",destination="",src_time="",des_time="",route_no="";
	EditText etBussNo,etSource,etDestination,etSrcTime,etDesTime,etRouteNo;
	Button btnInsertBusDetails;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a_enter_busdetails);
		 etBussNo=(EditText)findViewById(R.id.etBussNo);
		 etSource=(EditText)findViewById(R.id.etSource);
		 etDestination=(EditText)findViewById(R.id.etDestination);
		 etSrcTime=(EditText)findViewById(R.id.etSrcTime);
		 etDesTime=(EditText)findViewById(R.id.etDesTime);
		 etRouteNo=(EditText)findViewById(R.id.etRouteNo);
		 btnInsertBusDetails=(Button)findViewById(R.id.btnInsertBusDetails);
		 btnInsertBusDetails.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				buss_no=etBussNo.getText().toString();
				source=etSource.getText().toString();
				destination=etDestination.getText().toString();
				src_time=etSrcTime.getText().toString();
				des_time=etDesTime.getText().toString();
				route_no=etRouteNo.getText().toString();
				if(buss_no.length()==0){
					Toast.makeText(getApplicationContext(), "Please enter buss no.", Toast.LENGTH_SHORT).show();
				}else if(source.length()==0){
					Toast.makeText(getApplicationContext(), "Please enter source.", Toast.LENGTH_SHORT).show();
				}else if(destination.length()==0){
					Toast.makeText(getApplicationContext(), "Please enter destination.", Toast.LENGTH_SHORT).show();
				}else if(src_time.length()==0){
					Toast.makeText(getApplicationContext(), "Please enter src time.", Toast.LENGTH_SHORT).show();
				}else if(des_time.length()==0){
					Toast.makeText(getApplicationContext(), "Please enter des time.", Toast.LENGTH_SHORT).show();
				}else if(route_no.length()==0){
					Toast.makeText(getApplicationContext(), "Please enter route no.", Toast.LENGTH_SHORT).show();
				}else{
					new InsertBusDetails().execute();
				}
			}
		});
	}
	String res=null;
	ProgressDialog pd;
	public class InsertBusDetails extends AsyncTask<Void, Void, Void> {
		@Override
		protected void onPreExecute() {
			pd = new ProgressDialog(A_EnterBusDetails.this);
			pd.setMessage("Pleaase wait,data is being inserted...");
			pd.show();
		}
		@Override
		protected Void doInBackground(Void... params) {
			try{
				S_EnterBusDetails bDetails=new S_EnterBusDetails("http://publictransportroute.net63.net/PTR/insertBusDetails.php");
				res=bDetails.insertBusDetails(buss_no, source, destination, src_time, des_time, route_no);
			}catch(Exception e){}
			return null;

		}
		protected void onPostExecute(Void unused){ 
			pd.hide();
			if(res!=null){
				if(res.equalsIgnoreCase("success")){
					Toast.makeText(getApplicationContext(), "Data is inserted successfully.", Toast.LENGTH_LONG).show();
					buss_no="";source="";destination="";src_time="";des_time="";route_no="";
					etBussNo.getText().clear();
					etSource.getText().clear();
					etDestination.getText().clear();
					etSrcTime.getText().clear();
					etDesTime.getText().clear();
					etRouteNo.getText().clear();
				}
			}
		}
	}
}
