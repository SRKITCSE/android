package com.example.mobilehooker1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Fragment;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateprofileFragment extends Fragment implements OnClickListener{
	Button b1,b11;
	EditText e1,e2,e3;
	EditText t1,t2,t3,t4;
	String s1,s2,s3;
	public CreateprofileFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.first, container, false);	
		
		 b1=(Button)rootView.findViewById(R.id.button1);
		 e1=(EditText)rootView.findViewById(R.id.editText1);
	     e2=(EditText)rootView.findViewById(R.id.editText2);
	     e3=(EditText)rootView.findViewById(R.id.editText3);
	     t1=(EditText)rootView.findViewById(R.id.edit1);
		 t2=(EditText)rootView.findViewById(R.id.edit2);
		 t3=(EditText)rootView.findViewById(R.id.edit3);
		 t4=(EditText)rootView.findViewById(R.id.edit4);

	     b11=(Button)rootView.findViewById(R.id.button2);
	     b1.setOnClickListener(this);
	     b11.setOnClickListener(this);
	     return rootView;
	}
	public void onClick(View v) {
		if(v==b1){
		// TODO Auto-generated method stub
		
		if(e1.getText().toString().trim().length()==0||
	               e2.getText().toString().trim().length()==0||
	               e3.getText().toString().trim().length()==0)
	            {
					Toast.makeText(getActivity(), "Error Enter All Values",Toast.LENGTH_LONG).show();
	                return;
	            }
			else{
				validatephone();
				s1=""+e1.getText();
				s2=""+e2.getText();
				s3=""+e3.getText();
				database13 d13=new database13();
				d13.update(s1,s2,s3,getActivity());
				
	            return;
		}

	}
	if(v==b11){
		//s1=""+e1.getText();
		//s2=""+e2.getText();
		//s3=""+e3.getText();
		//database14 d14=new database14();
		//d14.getData(getActivity());
		Context context=getActivity();
		TelephonyManager tm=(TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
    	String Serialno=tm.getSimSerialNumber();
    	Toast toast1 = Toast.makeText(context,"sim serial "+Serialno,Toast.LENGTH_LONG);
   		toast1.show();
   		database13 a=new database13();
   		a.UpdateSS(Serialno, context);
   		//a.getUsers(context);
   		//a.getData(context);
        return;
		
	}
	
}//end of onClick
	
	class database13 extends MainActivity
	{
		
		SQLiteDatabase g;
		Context xcontext;
				
		

		public void update(String s1,String s2,String s3,Context context)
		{
			
			try
			{
				xcontext=context.getApplicationContext();
				g=xcontext.openOrCreateDatabase("MOBILE",Context.MODE_PRIVATE,null);
				ContentValues cv=new ContentValues();
	         cv.put("value",s1);
	         g.update("profile",cv,"Name='USERNAME'",null);
	         cv.put("value",s2);
	         g.update("profile",cv,"Name='PASSWORD'",null);
	         cv.put("value",s3);
	         g.update("profile",cv,"Name='PHONENUMBER'",null);
			 
			}
			catch(Exception e)
			{
				Toast toast1 = Toast.makeText(context,"Not Registered",Toast.LENGTH_LONG);
		   		toast1.show();
		   	
			
				
	  		}
		
		}
		public void UpdateSS(String s,Context context)
		{
			try
			{
				xcontext=context.getApplicationContext();
				g=xcontext.openOrCreateDatabase("MOBILE",Context.MODE_PRIVATE,null);
				ContentValues cv=new ContentValues();
	         cv.put("value",s);
	         g.update("SimSerial",cv,"sim='sim'",null);
	     	Toast toast1 = Toast.makeText(context,"updated the sim",Toast.LENGTH_LONG);
	   		toast1.show();
	   	
	   			
	         
			}
			catch(Exception e)
			{
				e.printStackTrace();
				Toast toast1 = Toast.makeText(context,"Not Registered "+s1,Toast.LENGTH_LONG);
		   		toast1.show();
		   	
			
				
	  		}
		
			
		}
		
	}
	class database14 extends MainActivity 
	{
		
		private  SQLiteDatabase g;
		
		private  Context xcontext;
		Cursor res;
		String s="nan",t;
		
			public String getData(Context context)
		{
			try
			{
			xcontext=context.getApplicationContext();
			g=xcontext.openOrCreateDatabase("MOBILE",Context.MODE_PRIVATE,null);
			res	=g.rawQuery("Select * from profile",null);
			res.moveToFirst();
			if(res!=null)
			{
			do{
				
			s=new String(res.getString(res.getColumnIndex("Name")));
			}while(res.moveToNext());
			
			}
			
			return s;
			}
			catch(Exception e)
			{
				return "hai";
				
			}
			
		}
	}
	public void validatephone(){
    	Pattern pattern = Pattern.compile(new String ("^[0-9]{10}$"));
        Matcher matcher = pattern.matcher(e3.getText());
        if(!(matcher.matches()))
	    {
	    	Toast.makeText(getActivity(), "PhoneNumber should be of length 10",
	    			   Toast.LENGTH_LONG).show();
	    	clearText();
	    }
        else{
        	Toast toast1 = Toast.makeText(getActivity(),"Profile Created Successfully",Toast.LENGTH_LONG);
    	   	toast1.show();
        }
	}// end of validate phone
        public void clearText(){
        	e1.setText("");
        	e2.setText("");
        	e3.setText("");
        	
        }//end of clear text
	
}

