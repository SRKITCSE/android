package com.ntz.newtranzzz.ui;

import com.ntz.newtranzzz.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class A_VerifyNumber extends Activity{//btnVerify
	EditText editText1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a_verify_number);
		 editText1=(EditText)findViewById(R.id.editText1);
		 Button btnVerify=(Button)findViewById(R.id.btnVerify);
		 btnVerify.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			SharedPreferences prefs = getSharedPreferences("NewTrazzz", Context.MODE_PRIVATE);
			int ran=prefs.getInt("random", 0);
			int vry=Integer.parseInt(editText1.getText().toString());
			
				if(vry==ran){
				startActivity(new Intent(getApplicationContext(),A_Dashboard.class));
				finish();
				}else{
					Toast.makeText(getApplicationContext(), "Invalid verify code.", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
}
