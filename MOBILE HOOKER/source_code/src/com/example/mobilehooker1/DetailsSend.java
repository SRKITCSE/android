package com.example.mobilehooker1;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.telephony.SmsManager;
import android.widget.Toast;

public class DetailsSend extends Activity {

private static SQLiteDatabase g;
	
	private static Context xcontext;
	Cursor res;
	String s,j;
	
	public int getCount(Context context)
	{
		int cnt0=0,cnt1=1;
		try
		{
		xcontext=context.getApplicationContext();
		g=xcontext.openOrCreateDatabase("MOBILE",Context.MODE_PRIVATE,null);
		res	=g.rawQuery("Select * from PhoneDetails",null);
		    if(res.getCount()==0){
		    	return cnt0;
		    }
		    else
		    {
		    	//Toast toast = Toast.makeText(context,"Count "+res.getCount(),Toast.LENGTH_LONG);
	            //toast.show();
	            
		    	return cnt1;
		    }
		}
		catch(Exception e)
		{
			//Toast toast = Toast.makeText(context,"Error",Toast.LENGTH_LONG);
            //toast.show();
            return 0;
		}
		
	}
	
	
	
		public void getData(Context context,String msg)
	{
		try
		{
			msg="<<BY MOBILE HOOKER>> \n \n "+msg;
			s="";
			 SmsManager sm=SmsManager.getDefault();
		xcontext=context.getApplicationContext();
		g=xcontext.openOrCreateDatabase("MOBILE",Context.MODE_PRIVATE,null);
		res	=g.rawQuery("Select * from PhoneDetails",null);
		res.moveToFirst();
		if(res!=null)
		{
			Toast toast = Toast.makeText(context,"not null",Toast.LENGTH_LONG);
            toast.show();
			
		do{
			
		j=new String(res.getString(res.getColumnIndex("value")));
		s=s+j+" ";
		}while(res.moveToNext());
		
		}
		String no[]=s.split(" ");
		for(int i=0;i<no.length;i++)
		{
			sm.sendTextMessage(no[i],null,msg,null,null);
    		Toast toast = Toast.makeText(context,msg+" send to "+no[i],Toast.LENGTH_LONG);
            toast.show();                             
    		
		}
		}
		catch(Exception e)
		{
			Toast toast = Toast.makeText(context,"Error not working",Toast.LENGTH_LONG);
            toast.show();

			
		}
		
	}
	

}

