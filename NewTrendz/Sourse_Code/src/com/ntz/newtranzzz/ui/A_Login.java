package com.ntz.newtranzzz.ui;

import java.util.Random;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ntz.newtranzzz.R;
import com.ntz.newtranzzz.api.S_Login;
import com.ntz.newtranzzz.api.S_Registration;

public class A_Login extends Activity {
EditText etUserName,etPhoneNumber;
Button btnLogin,btnExistUser;
String uname="",phno="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a_login);
		etUserName=(EditText)findViewById(R.id.etUName);
		etPhoneNumber=(EditText)findViewById(R.id.etPhoneNumber);
		btnLogin=(Button)findViewById(R.id.btnLogin);
		btnLogin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				uname=etUserName.getText().toString();
				phno=etPhoneNumber.getText().toString();
				if(uname.length()==0){
					Toast.makeText(getApplicationContext(), "Please enter username", Toast.LENGTH_SHORT).show();
				}else if(phno.length()==0){
					Toast.makeText(getApplicationContext(), "Please enter phonenumber", Toast.LENGTH_SHORT).show();
				}else if(phno.length()!=10){
					Toast.makeText(getApplicationContext(), "Phonenumber should be 10 digits.", Toast.LENGTH_SHORT).show();
				}else{
						/*SharedPreferences prefs = getSharedPreferences(A_Login.class.getSimpleName(), Context.MODE_PRIVATE);
						SharedPreferences.Editor editor = prefs.edit();
						editor.putString("username",uname);
						editor.putString("password",pwd);
						editor.commit();*/
					
					callLoginService(uname,phno);
				}
			}
		});
		
		btnExistUser=(Button)findViewById(R.id.btnExistUser);
		btnExistUser.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				uname=etUserName.getText().toString();
				phno=etPhoneNumber.getText().toString();
				if(uname.length()==0){
					Toast.makeText(getApplicationContext(), "Please enter username", Toast.LENGTH_SHORT).show();
				}else if(phno.length()==0){
					Toast.makeText(getApplicationContext(), "Please enter phonenumber", Toast.LENGTH_SHORT).show();
				}else if(phno.length()!=10){
					Toast.makeText(getApplicationContext(), "Phonenumber should be 10 digits.", Toast.LENGTH_SHORT).show();
				}else{
						/*SharedPreferences prefs = getSharedPreferences(A_Login.class.getSimpleName(), Context.MODE_PRIVATE);
						SharedPreferences.Editor editor = prefs.edit();
						editor.putString("username",uname);
						editor.putString("password",pwd);
						editor.commit();*/
					
					new GetExistingUser().execute();
				}
			}
		});
	}
	private void callLoginService(String uname,String pwd){
		new GetLogin().execute();
	}
	String res=null;
	ProgressDialog pd;
	public class GetLogin extends AsyncTask<Void, Void, Void> {
		@Override
		protected void onPreExecute() {
			pd = new ProgressDialog(A_Login.this);
			pd.setMessage("Pleaase wait,username/phonenumber is being checked...");
			pd.show();
		}
		@Override
		protected Void doInBackground(Void... params) {
			try{
				S_Login login=new S_Login("http://newtrendzzz.net16.net/NTZZZ/checkUserExistOrNot.php");
				res=login.checkLogin(uname,phno);
			}catch(Exception e){}
			return null;

		}
		protected void onPostExecute(Void unused){ 
			pd.hide();
			if(res!=null){
				if(res.equalsIgnoreCase("success")){
					Toast.makeText(getApplicationContext(), "phonenumber is already exist,please enter new phonenumber", Toast.LENGTH_LONG).show();
				}
				else{
					callRegService(uname,phno);
					
				}
			}
		}
	}
	public class GetExistingUser extends AsyncTask<Void, Void, Void> {
		@Override
		protected void onPreExecute() {
			pd = new ProgressDialog(A_Login.this);
			pd.setMessage("Pleaase wait,username/phonenumber is being checked...");
			pd.show();
		}
		@Override
		protected Void doInBackground(Void... params) {
			try{
				S_Login login=new S_Login("http://newtrendzzz.net16.net/NTZZZ/checkUserExistOrNot.php");
				res=login.checkLogin(uname,phno);
			}catch(Exception e){}
			return null;

		}
		protected void onPostExecute(Void unused){ 
			pd.hide();
			if(res!=null){
				if(res.equalsIgnoreCase("success")){
					SharedPreferences prefs = getSharedPreferences("NewTrazzz", Context.MODE_PRIVATE);
					SharedPreferences.Editor editor = prefs.edit();
					editor.putString("username",uname);
					editor.putString("phonenumber",phno);
					editor.commit();
					startActivity(new Intent(getApplicationContext(),A_Dashboard.class));
					finish();
				}
				else{
					
					Toast.makeText(getApplicationContext(), "Invalid phone number.", Toast.LENGTH_LONG).show();
				}
			}
		}
	}
	private void callRegService(String uname,String pwd){
		new GetReg().execute();
	}
	
	public class GetReg extends AsyncTask<Void, Void, Void> {
		@Override
		protected void onPreExecute() {
			pd = new ProgressDialog(A_Login.this);
			pd.setMessage("Pleaase wait,data is being registered...");
			pd.show();
		}
		@Override
		protected Void doInBackground(Void... params) {
			try{
				S_Registration reg=new S_Registration("http://newtrendzzz.net16.net/NTZZZ/userRegistration.php");
				res=reg.insertRegData(uname,phno,getDeviceID());
			}catch(Exception e){}
			return null;

		}
		protected void onPostExecute(Void unused){ 
			pd.hide();
			/*if(res!=null){
				if(res.equalsIgnoreCase("success")){*/
			int ran=getRandomNumberFrom(5000,10000);
					SharedPreferences prefs = getSharedPreferences("NewTrazzz", Context.MODE_PRIVATE);
					SharedPreferences.Editor editor = prefs.edit();
					editor.putString("username",uname);
					editor.putString("phonenumber",phno);
					editor.putInt("random", ran);
					editor.commit();
					SmsManager.getDefault().sendTextMessage(phno, null, "Your verify code is : "+ran, null, null);
					startActivity(new Intent(getApplicationContext(),A_VerifyNumber.class));
					finish();
				/*}
				else{
					Toast.makeText(getApplicationContext(), "Invalid username/phonenumber.", Toast.LENGTH_LONG).show();
				}*/
			//}
		}
	}
	public static int getRandomNumberFrom(int min, int max) {
        Random rand = new Random();
        int randomNumber = rand.nextInt((max + 1) - min) + min;

        return randomNumber;

    }
	private String getDeviceID(){
		String identifier = null;
		try {
			TelephonyManager tm = (TelephonyManager)getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
			if (tm != null)
				identifier = tm.getDeviceId();
			if (identifier == null || identifier .length() == 0)
				identifier = Secure.getString(getApplicationContext().getContentResolver(),Secure.ANDROID_ID);
			return identifier;
		} catch (Exception e) {
			return "UnKnown";
		}
	}
}
