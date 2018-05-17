package com.acprj.publictransportroute.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.acprj.publictransportroute.R;
import com.acprj.publictransportroute.S_Login;

public class A_AdminLogin extends Activity {
	EditText etUName,etPassword;
	Button btnLogin;
	String uname,password;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a_admin_login);
		etUName=(EditText)findViewById(R.id.etUName);
		etPassword=(EditText)findViewById(R.id.etPassword);
		btnLogin=(Button)findViewById(R.id.btnLogin);
		btnLogin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				uname=etUName.getText().toString();
				password=etPassword.getText().toString();
				if(uname.length()==0){
					Toast.makeText(getApplicationContext(), "Please enter username.", Toast.LENGTH_SHORT).show();
				}else if(password.length()==0){
					Toast.makeText(getApplicationContext(), "Please enter password.", Toast.LENGTH_SHORT).show();
				}else{
					new GetLogin().execute();
				
				}
			}
		});
	}
	String res=null;
	ProgressDialog pd;
	public class GetLogin extends AsyncTask<Void, Void, Void> {
		@Override
		protected void onPreExecute() {
			pd = new ProgressDialog(A_AdminLogin.this);
			pd.setMessage("Pleaase wait,username/password is being checked...");
			pd.show();
		}
		@Override
		protected Void doInBackground(Void... params) {
			try{
				S_Login login=new S_Login("http://www.publictransportroute.net63.net/PTR/checkUserExistOrNot.php");
				res=login.checkLogin(uname,password);
			}catch(Exception e){}
			return null;

		}
		protected void onPostExecute(Void unused){ 
			pd.hide();
			if(res!=null){
				if(res.equalsIgnoreCase("success")){
					startActivity(new Intent(A_AdminLogin.this,A_EnterDetails.class));
				}
				else{
					Toast.makeText(getApplicationContext(), "please enter username and password.", Toast.LENGTH_LONG).show();
				}
			}
		}
	}
}
