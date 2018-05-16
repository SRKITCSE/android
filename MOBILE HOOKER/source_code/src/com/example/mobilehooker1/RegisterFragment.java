package com.example.mobilehooker1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Fragment;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterFragment extends Fragment implements OnClickListener{
	Button b3;
	EditText et1,et2,et3;
	String s1,s2,s3;
	public RegisterFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.third, container, false);
		 b3=(Button)rootView.findViewById(R.id.b3);
		 et1=(EditText)rootView.findViewById(R.id.edit31);
		 et2=(EditText)rootView.findViewById(R.id.edit32);
	     et3=(EditText)rootView.findViewById(R.id.edit33);
	     b3.setOnClickListener(this);

		return rootView;
	}
	public void onClick(View v) {
		if(v==b3){
		// TODO Auto-generated method stub
		if(et1.getText().toString().trim().length()==0||
	               et2.getText().toString().trim().length()==0||
	               et3.getText().toString().trim().length()==0)
	            {
					Toast.makeText(getActivity(), "Error Enter All Values",Toast.LENGTH_LONG).show();
	                return;
	            }
		else{
			validatephone();
			s1=""+et1.getText();
			s2=""+et2.getText();
			s3=""+et3.getText();
			database11 d11=new database11();
			d11.update(s1,s2,s3,getActivity());
            return;
		}
	
		

		}
	}//end of OnClick
	
	
	class database11 extends MainActivity
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
	         cv.put("number",s1);
	         g.update("register",cv,"userno='REGISTER1'",null);
	         cv.put("number",s2);
	         g.update("register",cv,"userno='REGISTER2'",null);
	         cv.put("number",s3);
	         g.update("register",cv,"userno='REGISTER3'",null);
			}
			catch(Exception e)
			{
				Toast toast1 = Toast.makeText(context,"Not Registered",Toast.LENGTH_LONG);
		   		toast1.show();
		   	
			
				
	  		}
		
		}
	}

	public void validatephone(){
    	Pattern pattern = Pattern.compile(new String ("^[0-9]{10}$"));
        Matcher matcher = pattern.matcher(et1.getText());
        Matcher matcher1 = pattern.matcher(et2.getText());
        Matcher matcher2 = pattern.matcher(et3.getText());
        
    if(!(matcher.matches()))
    {
    	Toast.makeText(getActivity(), "PhoneNumber should be of length 10",
    			   Toast.LENGTH_LONG).show();
    	clearText();
    }
    else if(!(matcher1.matches()))
    {
    	Toast.makeText(getActivity(), "PhoneNumber should be of length 10",
    			   Toast.LENGTH_LONG).show();
    	clearText();
    }
    else if(!(matcher2.matches()))
	    {
	    	Toast.makeText(getActivity(), "PhoneNumber should be of length 10",
	    			   Toast.LENGTH_LONG).show();
	    	clearText();
	    }
    else{
    Toast.makeText(getActivity(), "You Are Registered",
			   Toast.LENGTH_LONG).show();

    }
   
}
	 public void clearText(){
	    	et1.setText("");
	    	et2.setText("");
	    	et3.setText("");
	    	
	    }//end of clear text



}
