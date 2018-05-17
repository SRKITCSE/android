package com.acprj.publictransportroute.ui;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import com.acprj.publictransportroute.R;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;
import com.acprj.publictransportroute.S_GetCurrentLocation;

public class R_GetLoactionReceivers extends BroadcastReceiver {
	Context mContext;
	private LocationManager locManager;

	
	@Override
	public void onReceive(Context arg0, Intent arg1) {
		mContext=arg0;
		locManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
		GetLatLngInLab();//Lab Testing
		GetLatLngInRealTime(arg0);
	}
	private void GetLatLngInLab(){
		new GetLocation().execute();
	}
	private void GetLatLngInRealTime(Context arg0){
		GPSTracker gps = new GPSTracker(arg0);
		// check if GPS enabled     
        if(gps.canGetLocation()){
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            calculateDes(arg0,latitude,longitude);   
        }else{
            gps.showSettingsAlert();
        }
	}
	
	ArrayList<HashMap<String, String>> mCurrentLoc=null;
	public class GetLocation extends AsyncTask<Void, Void, Void> {
		@Override
		protected void onPreExecute() {
			
		}
		@Override
		protected Void doInBackground(Void... params) {
			S_GetCurrentLocation cLoc=new S_GetCurrentLocation("http://www.publictransportroute.net63.net/PTR/getCurrentMobileLocation.php");
			//S_GetCurrentLocation cLoc=new S_GetCurrentLocation("http://telugucenimaentertainment.com/Project/PTR/getCurrentMobileLocation.php");
			mCurrentLoc=cLoc.getCurrentLoc();
			return null;
		}
		protected void onPostExecute(Void unused){
			HashMap<String, String> hm=mCurrentLoc.get(0);
			double lat=Double.parseDouble(hm.get("c_lat").toString());
			double loc=Double.parseDouble(hm.get("c_lng").toString());
			calculateDes(mContext,lat,loc);
		}
	}
	private void calculateDes(Context context,double latitude,double longitude){
		Location locationA = new Location("point A");     
		locationA.setLatitude(latitude); 
		locationA.setLongitude(longitude);
		Location locationB = new Location("point B");
		SharedPreferences prefs = context.getSharedPreferences(A_SetAlarm.class.getSimpleName(), Context.MODE_PRIVATE);
		double lat=Double.parseDouble(prefs.getString("c_lat","0.0").toString());
		double lng=Double.parseDouble(prefs.getString("c_lng","0.0").toString());
		locationB.setLatitude(lat); 
		locationB.setLongitude(lng);
		float distance = (float)(locationA.distanceTo(locationB))/1000 ;
		DecimalFormat _numberFormat= new DecimalFormat("#0.000");
		String tempDis=_numberFormat.format(distance);
		distance=Float.parseFloat(tempDis);
		if(distance<=0.500){
			Toast.makeText(context, "You are near to your destinys."+distance, Toast.LENGTH_SHORT).show();
			playSound(distance);
			 //Notify("Public Transport Route",distance+" away from your destination.");
			//closeDialog();
			//context.startActivity(new Intent(context,A_WakeAlaram.class));
			
		}else{
			Toast.makeText(context, "You are so long to your destinys.You are away from "+distance+"Killometers.", Toast.LENGTH_SHORT).show();
		}
	}
	String provider;
	private LocationListener locListener;
	private void locationByGPS(){
		locListener = new MyLocationListener();
		try {
			network_enabled = locManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
		} catch (Exception ex) {
		}
		// exceptions will be thrown if provider is not permitted.
		try {
			gps_enabled = locManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
		} catch (Exception ex) {
		}
		
		if (gps_enabled) {
			//provider="gps_enabled";
			locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locListener);
		}
		if (network_enabled) {
			//provider="network_enabled";
			locManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locListener);
		}
	}
	private boolean gps_enabled = false;
	private boolean network_enabled = false;
	double lng,lat;
	class MyLocationListener implements LocationListener {
		@Override
		public void onLocationChanged(Location location) {
			if (location != null) {

				// This needs to stop getting the location data and save the battery power.
				locManager.removeUpdates(locListener); 
				//String londitude = "Londitude: " + location.getLongitude();
				lng=location.getLongitude();
				//String latitude = "Latitude: " + location.getLatitude();
				lat=location.getLatitude();
				Toast.makeText(mContext, "lat : "+lat+" :: lng"+lng, Toast.LENGTH_SHORT).show();
			}
		}

		@Override
		public void onProviderDisabled(String provider) {

		}

		@Override
		public void onProviderEnabled(String provider) {

		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {

		}
	}
	private void closeDialog(){
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
		alertDialogBuilder.setMessage("Do you want to close app?");
		alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				
			}
		});
		alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {

			}
		});
		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();
	}
	MediaPlayer mediaPlayer;
	private void playSound(double distance){
		Uri defaultRingtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

		MediaPlayer mediaPlayer = new MediaPlayer();
		try {
		      mediaPlayer.setDataSource(mContext, defaultRingtoneUri);
		      mediaPlayer.setAudioStreamType(AudioManager.STREAM_NOTIFICATION);
		      mediaPlayer.prepare();
		      mediaPlayer.setOnCompletionListener(new OnCompletionListener() {
		         @Override
		         public void onCompletion(MediaPlayer mp)
		         {
		            mp.release();
		         }
		      });
		  mediaPlayer.start();
		  Notify("Public Transport Route",distance+" away from your destination.");
		} catch (IllegalArgumentException e) {
		 e.printStackTrace();
		} catch (SecurityException e) {
		 e.printStackTrace();
		} catch (IllegalStateException e) {
		 e.printStackTrace();
		} catch (IOException e) {
		 e.printStackTrace();
		}
	}
	@SuppressWarnings("deprecation")
	 private void Notify(String notificationTitle, String notificationMessage) {
	  NotificationManager notificationManager = (NotificationManager) mContext.getSystemService(mContext.NOTIFICATION_SERVICE);
	  @SuppressWarnings("deprecation")
	  Notification notification = new Notification(R.drawable.ic_launcher,
	    "New Message", System.currentTimeMillis());

	   Intent notificationIntent = new Intent(mContext, A_Dashboard.class);
	  PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0,
	    notificationIntent, 0);

	   notification.setLatestEventInfo(mContext, notificationTitle,
	    notificationMessage, pendingIntent);
	  notificationManager.notify(9999, notification);
	 }
}
