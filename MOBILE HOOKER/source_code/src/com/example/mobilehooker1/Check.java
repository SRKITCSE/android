package com.example.mobilehooker1;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
public class Check extends Activity{
	
	
private static SQLiteDatabase g;
	
	private static Context xcontext;
	Cursor res;
	String s,j;
	
		public String getData(Context context)
	{
		try
		{
			s="";
		xcontext=context.getApplicationContext();
		g=xcontext.openOrCreateDatabase("MOBILE",Context.MODE_PRIVATE,null);
		res	=g.rawQuery("Select * from CodeWord",null);
		res.moveToFirst();
		if(res!=null)
		{
			//Toast toast = Toast.makeText(context,"not null",Toast.LENGTH_LONG);
            //toast.show();
			
		do{
			
		j=new String(res.getString(res.getColumnIndex("value")));
		s=s+j+" ";
		}while(res.moveToNext());
		
		}
		
		return s;
		}
		catch(Exception e)
		{
			//Toast toast = Toast.makeText(context,"not working",Toast.LENGTH_LONG);
            //toast.show();
			return null;
			
		}
		
	}
	

}
