package com.example.ex;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Password extends Activity {

  private EditText p;
  private Button btnSubmit;
  
  static int f=0;
  @Override
  public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.password);
	p=(EditText)findViewById(R.id.password);
	
	 Button b1=(Button)findViewById(R.id.mybutton);
     b1.setOnClickListener(new onClickListener(){
     	public void onClick(View arg0){
     		
     		if(f==0)
     		{
     		SharedPreferences sp=getSharedPreferences("Login",MODE_PRIVATE);
     		SharedPreferences.Editor Ed=sp.edit();
     		Ed.putString("pwd",p.getText().toString());
     		Ed.commit();
     		f++;
     		}
     		
     		
     		SharedPreferences sp1=getSharedPreferences("Login",MODE_PRIVATE);

     		String unm=sp1.getString("pwd",null);       

          //  Toast.makeText(getApplicationContext(), unm, Toast.LENGTH_LONG).show();
     		if(p.getText().toString().equals("clock")){
     		
     		Intent i=new Intent(getApplication(),hide.class);
     		startActivity(i);
}
     	}
     });
 }
 
}