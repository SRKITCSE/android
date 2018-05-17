package com.example.ex;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.net.Uri;
import android.widget.Button;
import android.widget.ImageButton;

import java.io.File;
import java.util.Calendar;


public class ViewVideo extends Activity {
	String srcpath;
	int i;
	 img_cnt img;
String srcPath,destinationPath,srcX,desY;
	 File source,destination;
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movvideo);
       Bundle bundle=getIntent().getExtras();
         srcpath=bundle.getString("sourcePath");
         img=new img_cnt();
      srcX=srcPath;
         
        Button b1=(Button)findViewById(R.id.button1);
    b1.setOnClickListener(new OnClickListener() {

        public void onClick(View v) {
            // TODO Auto-generated method stub
       
        	//Toast.makeText(getApplicationContext(), "testing..........", Toast.LENGTH_LONG);  	
        	
        	
        	//String sourcePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/"srcpath;
                  source = new File(srcpath);
                                  
                 Toast.makeText(getApplicationContext(), source.getPath(), Toast.LENGTH_LONG).show();

                  final File fileDir=getApplicationContext().getFilesDir();
                 String pname=getApplicationContext().getPackageName();
                 	if(fileDir!=null){
                 		Toast.makeText(getApplicationContext(),"my path"+fileDir.getAbsolutePath(),Toast.LENGTH_LONG).show();
                 	
                 		  
                         String d = Environment.getExternalStorageDirectory().getAbsolutePath() +"/save1"; 
                         //String d=Environment.getExternalStorageDirectory().getAbsolutePath()+"/savee";
                         File dd = new File(d);
                         dd.mkdirs();
                         Toast.makeText(getApplicationContext(),"created",Toast.LENGTH_LONG).show();
                     	
                 	
                 	}
                 	
                 	 img.setInt(++i);
                 
                 	 i= img.getInt();
                 	double i1= Math.random();
                 	 Toast.makeText(getApplicationContext()," i value is "+i1,Toast.LENGTH_LONG).show();
                     
       destinationPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/save1/"+i1+".mp4";
                
                 destination = new File(destinationPath);
                 
                 Toast.makeText(getApplicationContext(), destination.getPath(), Toast.LENGTH_LONG).show();
                
                 img.setInt(i++);
                 
                 try 
                 {
                     //FileUtils.copyFile(source, destination);
                	 source.renameTo(destination);
                	 source.delete();
                 } 
                 catch (Exception e) 
                 {
                     e.printStackTrace();
                 }
                 
               
                // Toast.makeText(getApplicationContext(), destination.getPath(), Toast.LENGTH_LONG).show();
                
              /*  final File fileDir=getApplicationContext().getFilesDir();
                String pname=getApplicationContext().getPackageName();
                	if(fileDir!=null){
                		Toast.makeText(getApplicationContext(),"my path"+pname,Toast.LENGTH_LONG).show();
                	
                		  
                        String d = fileDir.getAbsolutePath()+ "/save"; 
                        
                        File dd = new File(d);
                        dd.mkdir();
                        Toast.makeText(getApplicationContext(),"created",Toast.LENGTH_LONG).show();
                    	
                	
                	}*/
                	       
                 
             }
              
             
           }); 
    

    
    
    
}
}
