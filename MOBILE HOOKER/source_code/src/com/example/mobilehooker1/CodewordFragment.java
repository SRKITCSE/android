package com.example.mobilehooker1;

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

public class CodewordFragment extends Fragment implements OnClickListener{
	Button b2,b1;
	EditText t1,t2,t3,t4,t5;
	String s1,s2,s3,s4,s5;
	public CodewordFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.second, container, false);
		b2=(Button)rootView.findViewById(R.id.button2);
		t1=(EditText)rootView.findViewById(R.id.edit1);
	    t2=(EditText)rootView.findViewById(R.id.edit2);
	    t3=(EditText)rootView.findViewById(R.id.edit3);
	    t4=(EditText)rootView.findViewById(R.id.edit4);
	    t5=(EditText)rootView.findViewById(R.id.edit5);
	    b1=(Button)rootView.findViewById(R.id.button1);
	    b2.setOnClickListener(this);
		return rootView;
	}
	@Override
	public void onClick(View v) {
		if(v==b1){
			t1.setEnabled(true);
			t2.setEnabled(true);
			t3.setEnabled(true);
			t4.setEnabled(true);
			t5.setEnabled(true);
		}
		if(v==b2){
		// TODO Auto-generated method stub
			String x1,x2,x3,x4,x5;
			x1=""+t1.getText();
			x2=""+t2.getText();
			x3=""+t3.getText();
			x4=""+t4.getText();
			x5=""+t5.getText();
			
		if(t1.getText().toString().trim().length()==0||
	               t2.getText().toString().trim().length()==0||
	               t3.getText().toString().trim().length()==0 || t4.getText().toString().trim().length()==0|| t4.getText().toString().trim().length()==0)
	            {
						Toast.makeText(getActivity(), "Error Enter All Values",Toast.LENGTH_LONG).show();
						clearText();
	                return;
	            }
		else if(x1.contains(" ")||x2.contains(" ")||x3.contains(" ")||x4.contains(" ")||x5.contains(" ")){
			Toast.makeText(getActivity(), "Space Not Allowed",Toast.LENGTH_LONG).show();
			clearText();
            return;
		}
		else if(x1.equals(x2)||x1.equals(x3)||x1.equals(x4)||x1.equals(x5)||x2.equals(x3)||x2.equals(x4)||x2.equals(x5)||x3.equals(x4)||x3.equals(x5)||x4.equals(x5)){
			Toast.makeText(getActivity(), "Set Unique Codes",Toast.LENGTH_LONG).show();
			clearText();
            return;
		}
		else{
				s1=""+t1.getText();
				s2=""+t2.getText();
				s3=""+t3.getText();
				s4=""+t4.getText();
				s5=""+t5.getText();
				database12 d12=new database12();
				d12.update(s1,s2,s3,s4,s5,getActivity());
	            return;
		}
	}//end of if
		
	}//end of OnClick
class database12 extends MainActivity
{
		SQLiteDatabase g;
		Context xcontext;

	public void update(String s1,String s2,String s3,String s4,String s5,Context context)
				{
					
					try
					{
					xcontext=context.getApplicationContext();
					g=xcontext.openOrCreateDatabase("MOBILE",Context.MODE_PRIVATE,null);
					 ContentValues cv=new ContentValues();
			         cv.put("value",s1);
			         g.update("CodeWord",cv,"code='SR'",null);
			         cv.put("value",s2);
			         g.update("CodeWord",cv,"code='CR'",null);
			         cv.put("value",s3);
			         g.update("CodeWord",cv,"code='TPM'",null);
			         cv.put("value",s4);
			         g.update("CodeWord",cv,"code='MT'",null);	
			         cv.put("value",s5);
			         g.update("CodeWord",cv,"code='PD'",null);	
			        
			         Toast.makeText(context, "CodeWord's are Created Successfull", Toast.LENGTH_LONG).show();
					}
					catch(Exception e)
					{
						Toast toast1 = Toast.makeText(context,"Please Enter the CodeWord",Toast.LENGTH_LONG);
			   		toast1.show();
				   	
						
			  		}
				
				}

	}
	public void clearText(){
    	t1.setText("");
    	t2.setText("");
    	t3.setText("");
    	t4.setText("");
    	t5.setText("");
    }//end of clear text

}
