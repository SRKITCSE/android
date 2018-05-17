package com.ntz.newtranzzz.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ntz.newtranzzz.R;
import com.ntz.newtranzzz.api.S_Registration;

public class A_Registration extends Activity {
EditText etUserName,etPhoneNumber;
Button btnLogin,btnRegistration;
String uname="",phno="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a_registration);
		etUserName=(EditText)findViewById(R.id.etUName);
		etPhoneNumber=(EditText)findViewById(R.id.etPhoneNumber);
		btnLogin=(Button)findViewById(R.id.btnReg);
		btnLogin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				uname=etUserName.getText().toString();
				phno=etPhoneNumber.getText().toString();
				if(uname.length()==0){
					Toast.makeText(getApplicationContext(), "Please enter username", Toast.LENGTH_SHORT).show();
				}else if(phno.length()==0){
					Toast.makeText(getApplicationContext(), "Please enter phonenumber", Toast.LENGTH_SHORT).show();
				}else{
						SharedPreferences prefs = getSharedPreferences("NewTrazzz", Context.MODE_PRIVATE);
						SharedPreferences.Editor editor = prefs.edit();
						editor.putString("username",uname);
						editor.putString("phonenumber",phno);
						editor.commit();
					
					callRegService(uname,phno);
				}
			}
		});
	
	}
	private void callRegService(String uname,String pwd){
		new GetReg().execute();
	}
	String res=null;
	ProgressDialog pd;
	public class GetReg extends AsyncTask<Void, Void, Void> {
		@Override
		protected void onPreExecute() {
			pd = new ProgressDialog(A_Registration.this);
			pd.setMessage("Pleaase wait,data is being loaded...");
			pd.show();
		}
		@Override
		protected Void doInBackground(Void... params) {
			try{
				S_Registration reg=new S_Registration("http://newtrendzzz.net16.net/NTZZZ/userRegistration.php");
				res=reg.insertRegData(uname,phno,"");
			}catch(Exception e){}
			return null;

		}
		protected void onPostExecute(Void unused){ 
			pd.hide();
			if(res!=null){
				if(res.equalsIgnoreCase("success")){
					startActivity(new Intent(getApplicationContext(),A_Dashboard.class));
					finish();
				}
				else{
					Toast.makeText(getApplicationContext(), "Invalid username/phonenumber.", Toast.LENGTH_LONG).show();
				}
			}
		}
	}
}


