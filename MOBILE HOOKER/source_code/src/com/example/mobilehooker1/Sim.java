package com.example.mobilehooker1;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

class Sim extends Activity
{
	String s1="",s2="";
	SQLiteDatabase MH;
	Cursor res;
	public String getUsers(Context c)
	{
		try
		{
			MH=c.openOrCreateDatabase("MOBILE",Context.MODE_PRIVATE,null);
			res	=MH.rawQuery("Select * from register",null);
			res.moveToFirst();
			if(res!=null)
			{
				
			do{
				
			String j1=new String(res.getString(res.getColumnIndex("number")));
			s2=s2+j1+" ";
			}while(res.moveToNext());
			//Toast toast = Toast.makeText(c,"not null "+s2,Toast.LENGTH_LONG);
	        //toast.show();	 	  
			}
			return s2;
			
		}
		catch(Exception e)
		{
			//Toast toast = Toast.makeText(c,"ERROR IN USER",Toast.LENGTH_LONG);
	        //toast.show();	 	  
			return "haii";
		}
		

	}
	
	
	public String getData(Context c)
	{
		try
		{
			MH=c.openOrCreateDatabase("MOBILE",Context.MODE_PRIVATE,null);
			res	=MH.rawQuery("Select * from SimSerial where sim='sim'",null);
			res.moveToFirst();
			s1=new String(res.getString(res.getColumnIndex("value")));
			
			  return s1;
		}
		catch(Exception e)
		{
			
			return "haii";
		}
		
	}
}