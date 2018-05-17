package com.acprj.publictransportroute.ui;

import java.util.ArrayList;
import java.util.HashMap;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.acprj.publictransportroute.R;
import com.acprj.publictransportroute.S_BusDetails;

public class A_Busses extends Activity {
	LinearLayout llBusses;
	String mRouteID;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a_busses);
		llBusses=(LinearLayout)findViewById(R.id.llBusses);
		mRouteID=getIntent().getExtras().getString("mRouteID");
		new GetBusses().execute();
	}
	ProgressDialog pd1;
	ArrayList<HashMap<String, String>> alBusses=null;
	public class GetBusses extends AsyncTask<Void, Void, Void> {
		@Override
		protected void onPreExecute() {
			pd1 = new ProgressDialog(A_Busses.this);
			pd1.setMessage("Please wait, Image is being Loaded");
			pd1.show();
		}
		@Override
		protected Void doInBackground(Void... params) {
			//
			S_BusDetails busses=new S_BusDetails("http://www.publictransportroute.net63.net/PTR/getBusDetails.php");
			//S_BusDetails busses=new S_BusDetails("http://telugucenimaentertainment.com/Project/PTR/getBusDetails.php");
			alBusses=busses.getBusDetails(mRouteID);
			return null;
		}
		protected void onPostExecute(Void unused){ 
			pd1.hide();
			//Toast.makeText(getApplicationContext(), ""+alBusses.size(), Toast.LENGTH_SHORT).show();
			for(int i=0;i<alBusses.size();i++){
				final HashMap<String, String> hm=alBusses.get(i);
				View hiddenInfo = getLayoutInflater().inflate(R.layout.row_buses, llBusses, false);
				TextView tvBusNo=(TextView)hiddenInfo.findViewById(R.id.tvBusNo);
				tvBusNo.setText(hm.get("busno"));
				TextView tvSource=(TextView)hiddenInfo.findViewById(R.id.tvSource);
				tvSource.setText(hm.get("source"));
				TextView tvDestination=(TextView)hiddenInfo.findViewById(R.id.tvDestination);
				tvDestination.setText(hm.get("destination"));
				TextView tvSrcTime=(TextView)hiddenInfo.findViewById(R.id.tvSrcTime);
				tvSrcTime.setText(hm.get("SrcTime"));
				TextView tvDestTime=(TextView)hiddenInfo.findViewById(R.id.tvDestTime);
				tvDestTime.setText(hm.get("DestTime"));
				llBusses.addView(hiddenInfo);
			}
		}
	}
}
