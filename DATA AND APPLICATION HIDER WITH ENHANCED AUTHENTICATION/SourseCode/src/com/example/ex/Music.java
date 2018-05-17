package com.example.ex;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Music extends Activity 
{
 private static final int MY_INTENT_CLICK=302;
 private TextView txta;
 private Button btn_selectImage;

 @Override
 protected void onCreate(Bundle savedInstanceState) 
 {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.music);
  Button b1=(Button)findViewById(R.id.button1);
  b1.setOnClickListener(new onClickListener(){
  	public void onClick(View arg0){
  		Intent i=new Intent(getApplication(),ViewMusic.class);
                 i.putExtra("sourcePath",txta.getText().toString());
  		startActivity(i);
  	}
  });

  txta = (TextView) findViewById(R.id.textView1);
  btn_selectImage = (Button) findViewById(R.id.btn_selectImage);

  btn_selectImage.setOnClickListener(new OnClickListener() 
  {
   @Override
   public void onClick(View v) 
   {
    Intent intent = new Intent(); 
    intent.setType("audio/*"); 
    intent.setAction(Intent.ACTION_GET_CONTENT); 
    startActivityForResult(Intent.createChooser(intent, "Select File"),MY_INTENT_CLICK);
   }
  });

 }

 @Override
 public void onActivityResult(int requestCode, int resultCode, Intent data)
 {
  if (resultCode == RESULT_OK)
  {
   if (requestCode == MY_INTENT_CLICK)
   {
    if (null == data) return;

    String selectedImagePath;
    Uri selectedImageUri = data.getData();

    //MEDIA GALLERY
    selectedImagePath = ImageFilePath.getPath(getApplicationContext(), selectedImageUri);
    Log.i("Image File Path", ""+selectedImagePath);
    txta.setText(""+selectedImagePath);
   }
  }
 }
}
