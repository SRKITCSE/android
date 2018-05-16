package com.example.mobilehooker1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
//import android.telephony.TelephonyManager;
//import android.widget.Toast


public class MobileTracer extends BroadcastReceiver {

	String chp;
	String users;
	
		@Override
		public void onReceive(Context context, Intent intent) {
			
			// TODO Auto-generated method stub
			 Sim si=new Sim();
			 chp=si.getData(context);
			 users=si.getUsers(context);
			 String data[]=users.split(" ");
			 
	if( !chp.equals("") && !data[0].equals("") )
	{
			 
			 
			TelephonyManager tm=(TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
	    	String Serialno=tm.getSimSerialNumber();
	    	String phno=tm.getLine1Number();
	    	String imei=tm.getDeviceId();
	    	//Toast toast11 = Toast.makeText(context," Imei:"+imei+"\nPhone no:"+phno+"\nSerial no:"+Serialno,Toast.LENGTH_LONG);
	        //toast11.show();
	        if(!Serialno.equals(chp))
	        {
	        	String msg="<<BY MOBILE HOOKER>>\n FIND SIM CHANGE\n Imei:"+imei+"\nPhone no:"+phno+"\nSerial no:"+Serialno;
	        	 SmsManager sm=SmsManager.getDefault();
	        	 for(int i=0;i<data.length;i++)
	             {
	        	 sm.sendTextMessage(data[i],null,msg,null,null);
	     		 //Toast toast = Toast.makeText(context,"MSG SEND TO "+data[i],Toast.LENGTH_LONG);
	             //toast.show();                             
	             }
	        } 
	}
	       
		}

	}


// register(userno varchar[3],number varchar[20])
// SimSerial(sim varchar[4],value varchar[20])
