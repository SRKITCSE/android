package com.example.mobilehooker1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends Activity implements OnClickListener{
	Button b1;
	EditText et1;
	TextView tv2;
	String s1,p;
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        Intent i=getIntent();
	    	
	    	s1=i.getStringExtra("pass");
	    	
	        setContentView(R.layout.check);
			et1=(EditText)findViewById(R.id.editText1);
			
			b1=(Button)findViewById(R.id.sub1);
			b1.setOnClickListener(this);
			
	 }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v==b1)
		{
		p=""+et1.getText();
			if(p.equals(s1))
			{
				
			Intent i=new Intent(this,Second.class);
			startActivity(i);
			}
			else
			{
				 tv2=(TextView)findViewById(R.id.tv2);
				 tv2.setText("Entered Wrong Password");
				 
			}
		}
	
	}
}
