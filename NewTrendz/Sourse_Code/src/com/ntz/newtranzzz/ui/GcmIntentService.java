package com.ntz.newtranzzz.ui;

/* http://techlovejump.in/2013/11/android-push-notification-using-google-cloud-messaging-gcm-php-google-play-service-library/
 * techlovejump.in
 * tutorial link
 * 
 *  */
import org.json.JSONObject;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.ContactsContract;
import android.support.v4.app.NotificationCompat;
import android.text.SpannableStringBuilder;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.ntz.newtranzzz.R;

public class GcmIntentService extends IntentService{
	Context context;
	public static final int NOTIFICATION_ID = 1;
	public static String MESSAGE = null;
	private NotificationManager mNotificationManager;
	NotificationCompat.Builder builder;
	public static final String TAG = "GCM Demo";

	public GcmIntentService() {
		super("GcmIntentService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Bundle extras = intent.getExtras();
		String msg = intent.getStringExtra("message");
		GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
		String messageType = gcm.getMessageType(intent);

		if (!extras.isEmpty()) {

			if (GoogleCloudMessaging.
					MESSAGE_TYPE_SEND_ERROR.equals(messageType)) {
				sendNotification("Send error: " + extras.toString());
			} else if (GoogleCloudMessaging.
					MESSAGE_TYPE_DELETED.equals(messageType)) {
				sendNotification("Deleted messages on server: " +
						extras.toString());
				// If it's a regular GCM message, do some work.
			} else if (GoogleCloudMessaging.
					MESSAGE_TYPE_MESSAGE.equals(messageType)) {
				// This loop represents the service doing some work.
				for (int i=0; i<5; i++) {
					Log.i(TAG, "Working... " + (i+1)
							+ "/5 @ " + SystemClock.elapsedRealtime());
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
					}
				}
				Log.i(TAG, "Completed work @ " + SystemClock.elapsedRealtime());
				// Post notification of received message.
				//sendNotification("Received: " + extras.toString());
				sendNotification(msg);
				//CustomNotification(msg);
				Log.i(TAG, "Received: " + extras.toString());
			}
		}
		GcmBroadcastReceiver.completeWakefulIntent(intent);
	}
	JSONObject json;
	Intent mIntent;
	private void sendNotification(String msg) {
		String itemID="";
		String username="";
		String product_name="";
		String phno="";
		
		try{
			json = new JSONObject(msg);
			//message = json.getString("name");
			itemID = json.getString("itemID");
			username= json.getString("username");
			product_name=json.getString("product_name");
			phno=json.getString("phno");
			username=getNameByPhono(phno,username);
		}catch(Exception e){}
		mNotificationManager = (NotificationManager)
				this.getSystemService(Context.NOTIFICATION_SERVICE);
		mIntent = new Intent(this, A_ProductDetails.class);
		mIntent.putExtra("itemID", itemID);
		mIntent.putExtra("username", username);
		mIntent.putExtra("product_name", product_name);
		
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0,mIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		// Uri sound = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.sound);

		Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
		NotificationCompat.Builder mBuilder =new NotificationCompat.Builder(this)
		.setSmallIcon(R.drawable.ic_launcher)
		.setSound(uri)
		.setContentTitle(username)
		.setStyle(new NotificationCompat.BigTextStyle()
		.bigText(product_name))
		.setAutoCancel(true)
		.setContentText(product_name);
		Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
		mBuilder.setLargeIcon(bm);
		mBuilder.setContentIntent(contentIntent);
		mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
	}
    private String getNameByPhono(String phno,String username){
    	String mName=null;
    	Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null,null, null);
    	while (phones.moveToNext()) {
			String name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
			String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
			if(phoneNumber.equals(phno)){
				mName=name;
				break;
			}
    	}
    	
    	if(mName==null){
    		mName=username;
    	}
    	return mName;
    }
	
	public void showNotification(SpannableStringBuilder sb) {
		PendingIntent contentIntent;
		// CharSequence text=" Satnam Shri vaheguru...You have a new sabad to read.";
		CharSequence title="title";
		NotificationManager	nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
		Notification notification = new Notification(R.drawable.ic_launcher, sb, System.currentTimeMillis());
		notification.flags = Notification.FLAG_AUTO_CANCEL;
		notification.defaults |= Notification.DEFAULT_SOUND;
		notification.defaults |= Notification.DEFAULT_LIGHTS;
		notification.defaults |= Notification.DEFAULT_VIBRATE;
		Intent showIntent =new Intent(this,AppSplashScreen.class);
		showIntent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
		contentIntent = PendingIntent.getActivity(this, 0,
				showIntent, 0); 
		notification.setLatestEventInfo(this,sb, sb, contentIntent);
		nm.notify(1, notification);
	}

}