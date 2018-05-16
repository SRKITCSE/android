package com.example.mobilehooker1;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

public class Demo1 extends Activity{
	
	public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.imageee);
	Intent i=getIntent();
	
	String val=i.getStringExtra("i");
	ImageView imgView=(ImageView) findViewById(R.id.image1);

	if(val.equals("sr"))
	{
	
		Drawable  drawable  = getResources().getDrawable(R.drawable.srd);
		imgView.setImageDrawable(drawable);
		//imgView.setScaleType("imgView.fitXY");

	}
	if(val.equals("cr"))
	{
	
		Drawable  drawable  = getResources().getDrawable(R.drawable.crd);
		imgView.setImageDrawable(drawable);

	}
	if(val.equals("msg"))
	{
	
		Drawable  drawable  = getResources().getDrawable(R.drawable.msgingd);
		imgView.setImageDrawable(drawable);

	}
	if(val.equals("pd"))
	{
	
		Drawable  drawable  = getResources().getDrawable(R.drawable.pdd);
		imgView.setImageDrawable(drawable);

	}
	if(val.equals("loc"))
	{
	
		Drawable  drawable  = getResources().getDrawable(R.drawable.locd);
		imgView.setImageDrawable(drawable);

	}
	if(val.equals("tracer"))
	{
	
		Drawable  drawable  = getResources().getDrawable(R.drawable.tracer);
		imgView.setImageDrawable(drawable);

	}


	//img1=(ImageButton)findViewById(R.id.imageButton1);
	//img2=(ImageButton)findViewById(R.id.imageButton2);
	//img3=(ImageButton)findViewById(R.id.imageButton3);
	//img4=(ImageButton)findViewById(R.id.imageButton4);
	//img5=(ImageButton)findViewById(R.id.imageButton5);
	//img6=(ImageButton)findViewById(R.id.imageButton6);
	//String str2=bundle.getString("Index");
	//String str3=bundle.getString("Index");
	//String str4=bundle.getString("Index");
	//String str5=bundle.getString("Index");
	//String str6=bundle.getString("Index");
	
		//if(str1==str1){
			//setContentView(R.drawable.srd);
		
		//}
	}
		
}
