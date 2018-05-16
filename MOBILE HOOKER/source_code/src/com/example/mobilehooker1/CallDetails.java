package com.example.mobilehooker1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class CallDetails extends BroadcastReceiver  {

	static int in;
	

	@Override
	public void onReceive(Context c, Intent i) {
		int cnt;
		DetailsSend ds=new DetailsSend();
		cnt=ds.getCount(c);
		if(cnt==1)
		{
		// TODO Auto-generated method stub
	        TelephonyManager tm=(TelephonyManager) c.getSystemService(Context.TELEPHONY_SERVICE);
	       // tm.listen(listener, events)
	        if(in==0)
	        {
	        tm.listen(new CallLis(c,in),PhoneStateListener.LISTEN_CALL_STATE);
	        in++;
	        }
	   
		}    
	}
	  

	
	class CallLis extends PhoneStateListener
	{
		Context c;
		int i;
		CallLis(Context c,int j)
		{ 
		 this.c=c;	
		 i=j;
		}
		 @Override
		public void onCallStateChanged(int state,String incNo)
		{
			super.onCallStateChanged(state,incNo);
			DetailsSend ds1=new DetailsSend();
			int cnt=ds1.getCount(c);
			if(cnt==1)
			{
			if(state==TelephonyManager.CALL_STATE_RINGING)
			{
				if(i==0)
				{
				DetailsSend ds=new DetailsSend();
	            ds.getData(c,"incoming call from:"+incNo);
	            
				//Toast toast = Toast.makeText(c,i+" incoming no="+incNo,Toast.LENGTH_LONG);
				//toast.show();
				//System.exit(0);
				}
				
				
			}
			if(state==TelephonyManager.CALL_STATE_IDLE)
			{
				Toast toast = Toast.makeText(c," Idle ",Toast.LENGTH_LONG);
				toast.show();
				//DetailsSend ds=new DetailsSend();
	            //ds.getData(c,"incoming call from:"+incNo);
	            
				
				
			}

		}
	}


	}
	}