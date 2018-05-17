package com.example.ex;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class hide extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hide);
		ImageButton b1=(ImageButton)findViewById(R.id.imageButton5);
        b1.setOnClickListener(new onClickListener(){
        	public void onClick(View arg0){
        		Intent i=new Intent(getApplication(),HideApp.class);
        		startActivity(i);
        
	      }
});	ImageButton b2=(ImageButton)findViewById(R.id.imageButton2);
b2.setOnClickListener(new onClickListener(){
	public void onClick(View arg0){
		Intent i=new Intent(getApplication(),GetImageActivity.class);
		startActivity(i);

  }
});
ImageButton b3=(ImageButton)findViewById(R.id.imageButton6);
b3.setOnClickListener(new onClickListener(){
	public void onClick(View arg0){		
		Intent i=new Intent(getApplication(),Messages.class);
		startActivity(i);
  }
});
ImageButton b4=(ImageButton)findViewById(R.id.imageButton3);
b4.setOnClickListener(new onClickListener(){
	public void onClick(View arg0){
		Intent i=new Intent(getApplication(),VideoActivity.class);
		startActivity(i);
  }
});
ImageButton b5=(ImageButton)findViewById(R.id.imageButton1);
b5.setOnClickListener(new onClickListener(){
	public void onClick(View arg0){
		Intent i=new Intent(getApplication(),Music.class);
		startActivity(i);
  }
});
ImageButton b6=(ImageButton)findViewById(R.id.imageButton4);
b6.setOnClickListener(new onClickListener(){
	public void onClick(View arg0){
		Intent i=new Intent(getApplication(),Help.class);
		startActivity(i);
  }
});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
