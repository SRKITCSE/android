package com.acprj.publictransportroute.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.acprj.publictransportroute.R;

public class A_EnterDetails extends Activity {
	Button btnBusdetails,btnRouteDetails;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a_enterdetails);
		btnBusdetails=(Button)findViewById(R.id.btnBusdetails);
		btnRouteDetails=(Button)findViewById(R.id.btnRouteDetails);
		btnBusdetails.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(A_EnterDetails.this,A_EnterBusDetails.class));	
			}
		});
		btnRouteDetails.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(A_EnterDetails.this,A_RouteDetails.class));
			}
		});
	}
}
