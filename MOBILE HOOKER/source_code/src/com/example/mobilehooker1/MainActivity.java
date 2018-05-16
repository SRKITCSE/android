package com.example.mobilehooker1;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnTouchListener{
	String TAG=MainActivity.class.getSimpleName();
	float x,y,x1,y1;
	ImageView iv;
	SQLiteDatabase MH;
	Cursor res;
	String s1;
	int i;
    /** Called when the activity is first created. */
    @Override
public void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.main);
try{
	
    MH=this.openOrCreateDatabase("MOBILE",MODE_PRIVATE,null);
     //MH.execSQL("create table if not exists P(phone number[10])");
    MH.execSQL("create table if not exists profile(Name varchar[20],value varchar[20])");
    MH.execSQL("create table if not exists CodeWord(code varchar[3],value varchar[20])");
    MH.execSQL("create table if not exists register(userno varchar[8],number varchar[20])");
    
    MH.execSQL("create table if not exists SimSerial(sim varchar[4],value varchar[20])");
    
    
    MH.execSQL("create table if not exists PhoneNo(value varchar[20])");
    MH.execSQL("create table if not exists PhoneDetails(value varchar[20])");
    res=MH.rawQuery("select * from CodeWord", null);
    if(res.getCount()==0){
 	   
    i=1;
   //  MH.execSQL("create table if not exists mhcodes(sr varchar[15] PRIMARY KEY,tpm varchar[15] PRIMARY KEY,cr varchar[15] PRIMARY KEY)");
    MH.execSQL("insert into CodeWord values('SR','#M!@1')");
    MH.execSQL("insert into CodeWord values('CR','#M!@2')");
    MH.execSQL("insert into CodeWord values('TPM','#M!@3')");
    MH.execSQL("insert into CodeWord values('MT','#M!@4')");
    MH.execSQL("insert into CodeWord values('PD','#M!@5')");
    
    MH.execSQL("insert into profile values('USERNAME','')");
    MH.execSQL("insert into profile values('PASSWORD','')");
    MH.execSQL("insert into profile values('PHONENUMBER','')");
    
    MH.execSQL("insert into register values('REGISTER1','')");
    MH.execSQL("insert into register values('REGISTER2','')");
    MH.execSQL("insert into register values('REGISTER3','')");
    
    MH.execSQL("insert into SimSerial values('sim','')");
    
    
    s1="";
    }
    else{
 	   
 	   s1="";
		MH=this.openOrCreateDatabase("MOBILE",Context.MODE_PRIVATE,null);
		res	=MH.rawQuery("Select * from profile where Name='PASSWORD'",null);
		res.moveToFirst();
		if(res!=null)
		{
			
		do{
			
		s1=new String(res.getString(res.getColumnIndex("value")));
		}while(res.moveToNext());
		//Toast toast = Toast.makeText(this,"not null "+s1,Toast.LENGTH_LONG);
        //toast.show();
	
 	   
 	  
 	   
    }
    }

     }
     catch(Exception e)
     {
     	e.printStackTrace();
     	 Toast.makeText(this, "NOT Inserted Values", Toast.LENGTH_LONG).show();
           
          
     	/*res	=MH.rawQuery("Select * from CodeTab",null);
 		res.moveToFirst();
 		if(res!=null)
 		{
 		do{
 		s=new String(res.getString(res.getColumnIndex("code")));
 		}while(res.moveToNext());
 		}*/
 		
 		
     	
     }


iv=(ImageView)findViewById(R.id.iv);
iv.setOnTouchListener(this);

    }

	@SuppressLint("ClickableViewAccessibility")
	public boolean onTouch(View v, MotionEvent m) {
	
		int a=m.getActionMasked();
		switch(a)
		{
		case MotionEvent.ACTION_DOWN:
			x=m.getX();
			y=m.getY();		
			Log.d(TAG,"down");
			break;
		case MotionEvent.ACTION_MOVE:	
			Log.d(TAG,"Move");
			break;
		case MotionEvent.ACTION_UP:
			x1=m.getX();
			y1=m.getY();
			if(y>y1)
			{	
				if(s1.equals(""))
				{
					Intent i=new Intent(this,Second.class);
					startActivity(i);
				}
				else
				{
					Intent i=new Intent(this,Login.class);
					i.putExtra("pass",s1);
					startActivity(i);
				}
				
				}
			Log.d(TAG,String.valueOf(a));				
			break;
		}
		
		
		return true;
	}

		
	}

