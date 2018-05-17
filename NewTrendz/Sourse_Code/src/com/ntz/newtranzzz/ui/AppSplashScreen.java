package com.ntz.newtranzzz.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.ntz.newtranzzz.R;
import com.ntz.newtranzzz.api.S_LatestItem;
import com.ntz.newtranzzz.api.S_Login;


public class AppSplashScreen extends Activity{
	public static final String EXTRA_MESSAGE = "message";
	public static final String PROPERTY_REG_ID = "registration_id";
	private static final String PROPERTY_APP_VERSION = "appVersion";
	private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
	// please enter your sender id
	String SENDER_ID = "424374278138";
	Context context;
	String regid;
	static final String TAG = "GCMDemo";
	GoogleCloudMessaging gcm;
	ConnectionDetector cd;
	private StringBuilder mResposeData = new StringBuilder();
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		try{
		new GetLatest().execute();
		}catch(Exception e){}
		context = getApplicationContext();
		cd=new ConnectionDetector(this);
		if(cd.isConnectingToInternet()){
			if(checkPlayServices()){
				gcm = GoogleCloudMessaging.getInstance(this);
				regid = getRegistrationId(context);
				if(regid.isEmpty()){
					new RegisterBackground().execute();
				}
			}
		}

		final int ScreenDisplay = 3000;
		Thread t1=new Thread(){
			int wait1=0;
			public void run(){
				try{
					while(wait1<=ScreenDisplay )
					{
						sleep(100);
						wait1+=100;						
					}
				}
				catch(Exception e){
					e.printStackTrace();
				}
				finally{	
					SharedPreferences prefs = getSharedPreferences("NewTrazzz", Context.MODE_PRIVATE);
					String str=prefs.getString("username", "default");
					if(str.equals("default")){
					startActivity(new Intent(getApplicationContext(),A_Login.class));
					finish();
					}else{
						startActivity(new Intent(getApplicationContext(),A_Dashboard.class));
						finish();
					}
					/*SharedPreferences prefs = getSharedPreferences(A_Login.class.getSimpleName(), Context.MODE_PRIVATE);
					String uname=prefs.getString("username", "nodata");
					if(uname.equalsIgnoreCase("nodata")){
						startActivity(new Intent(AppSplashScreen.this,A_Login.class));
						finish();
					}else{
						startActivity(new Intent(getApplicationContext(),FA_Dashboard.class));
						finish();
					}*/
				}
			}
		};
		t1.start();
	}
	@Override
	protected void onResume(){
		super.onResume();
		if(cd.isConnectingToInternet()){
			checkPlayServices();
		}else{
			Toast.makeText(getApplicationContext(), "Please check your internet connection.", Toast.LENGTH_SHORT).show();
		}

	}

	private boolean checkPlayServices() {
		int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
		if (resultCode != ConnectionResult.SUCCESS) {
			if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
				GooglePlayServicesUtil.getErrorDialog(resultCode, this,
						PLAY_SERVICES_RESOLUTION_REQUEST).show();
			} else {
				Log.i(TAG, "This device is not supported.");
				finish();
			}
			return false;
		}
		return true;
	}
	@SuppressLint("NewApi")
	private String getRegistrationId(Context context) {
		final SharedPreferences prefs = getGCMPreferences(context);
		String registrationId = prefs.getString(PROPERTY_REG_ID, "");
		if (registrationId.isEmpty()) {
			Log.i(TAG, "Registration not found.");
			return "";
		}

		int registeredVersion = prefs.getInt(PROPERTY_APP_VERSION, Integer.MIN_VALUE);
		int currentVersion = getAppVersion(context);
		if (registeredVersion != currentVersion) {
			Log.i(TAG, "App version changed.");
			return "";
		}
		return registrationId;
	}

	private SharedPreferences getGCMPreferences(Context context) {

		return getSharedPreferences(AppSplashScreen.class.getSimpleName(),
				Context.MODE_PRIVATE);
	}

	private static int getAppVersion(Context context) {
		try {
			PackageInfo packageInfo = context.getPackageManager()
					.getPackageInfo(context.getPackageName(), 0);
			return packageInfo.versionCode;
		} catch (NameNotFoundException e) {
			// should never happen
			throw new RuntimeException("Could not get package name: " + e);
		}
	}
	class RegisterBackground extends AsyncTask<String,String,String>{

		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			String msg = "";
			try {
				if (gcm == null) {
					gcm = GoogleCloudMessaging.getInstance(context);
				}
				regid = gcm.register(SENDER_ID);
				msg = "Dvice registered, registration ID=" + regid;
				sendRegistrationIdToBackend();
				// Persist the regID - no need to register again.
				storeRegistrationId(context, regid);
			} catch (IOException ex) {
				msg = "Error :" + ex.getMessage();
			}
			return msg;
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
		@Override
		protected void onPostExecute(String msg) {
			//Toast.makeText(getApplicationContext(), ""+mResposeData.toString(), Toast.LENGTH_SHORT).show();

		}
		private void sendRegistrationIdToBackend() {
			// Your implementation here.

			String url = "http://newtrendzzz.net16.net/NTZZZ/GCM/getRegDevices.php";
			List<NameValuePair> params = new ArrayList<NameValuePair>();

			params.add(new BasicNameValuePair("regid", regid));
			params.add(new BasicNameValuePair("device_id", getDeviceID()));
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);
			try {
				httpPost.setEntity(new UrlEncodedFormEntity(params));
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				HttpResponse response = httpClient.execute(httpPost);
				HttpEntity entity = response.getEntity();
				BufferedReader buf = new BufferedReader(new InputStreamReader(entity.getContent()));

				String line = null;
				while ((line = buf.readLine()) != null) {
					mResposeData.append(line+"\n");
				}
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}     
		}

		private void storeRegistrationId(Context context, String regId) {
			final SharedPreferences prefs = getGCMPreferences(context);
			int appVersion = getAppVersion(context);
			Log.i(TAG, "Saving regId on app version " + appVersion);
			SharedPreferences.Editor editor = prefs.edit();
			editor.putString(PROPERTY_REG_ID, regId);
			editor.putInt(PROPERTY_APP_VERSION, appVersion);
			editor.commit();
		}
	}
	public static ArrayList<HashMap<String, String>> mLatest = new ArrayList<HashMap<String, String>>();
	public class GetLatest extends AsyncTask<Void, Void, Void> {
		@Override
		protected void onPreExecute() {
			
		}
		@Override
		protected Void doInBackground(Void... params) {
			try{
				S_LatestItem latest=new S_LatestItem("http://newtrendzzz.net16.net/NTZZZ/getLatestItem.php");
				mLatest=latest.getProductDetails("");
			}catch(Exception e){}
			return null;

		}
		protected void onPostExecute(Void unused){ 
		}
	}
}
