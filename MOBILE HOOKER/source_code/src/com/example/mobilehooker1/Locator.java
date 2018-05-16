package com.example.mobilehooker1;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.SmsManager;
import android.widget.Toast;

public class Locator extends Activity implements LocationListener {
    
    private LocationManager locationManager;
    ConnectivityManager dataManager;
    Context context;
private static SQLiteDatabase g;
	Cursor res;
	String s,j,no[];

    SmsManager sm=SmsManager.getDefault();
    String ph;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
     
        super.onCreate(savedInstanceState);
        setContentView(R.layout.locator);
        context=getBaseContext();
        try
		{
			s="";
		g=this.openOrCreateDatabase("MOBILE",Context.MODE_PRIVATE,null);
		res	=g.rawQuery("Select * from PhoneNo",null);
		res.moveToFirst();
		if(res!=null)
		{
			//Toast toast = Toast.makeText(context,"have numbers",Toast.LENGTH_LONG);
            //toast.show();
			
		do{
			
		j=new String(res.getString(res.getColumnIndex("value")));
		s=s+j+" ";
		}while(res.moveToNext());
		
		}
		}
		catch(Exception e)
		{
			//Toast toast = Toast.makeText(context,"not working",Toast.LENGTH_LONG);
            //toast.show();
			
		}
        no=s.split(" ");
        try {
			turnDATAOn();
		} catch (SecurityException e) {
		
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
        turnGPSOn();
     
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        
        locationManager.requestLocationUpdates( LocationManager.GPS_PROVIDER,0,0, this); 
        if (locationManager != null) {
            Location location = locationManager
                    .getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location != null) {
            	String str = "Latitude: "+location.getLatitude()+" Longitude: "+location.getLongitude();
            	for(int i=0;i<no.length;i++)
            	{
            	sm.sendTextMessage(no[i],null,str,null,null);
        		Toast toast = Toast.makeText(context,"MSG send 2 "+no[i],Toast.LENGTH_LONG);
                toast.show();                             
                Toast.makeText(getBaseContext(), str, Toast.LENGTH_LONG).show();
                try
                {
                	g=this.openOrCreateDatabase("MOBILE",Context.MODE_PRIVATE,null);
                	g.execSQL("delete from PhoneNo where value='"+no[i]+"'");
                	 Toast.makeText(getBaseContext(),"Deleted "+no[i], Toast.LENGTH_LONG).show();
                	//g.delete("PhoneNo","value",no[i])
                }
                catch(Exception e)
                {
                	 Toast.makeText(getBaseContext(),"Not Deleted", Toast.LENGTH_LONG).show();
                }
            	locationManager.removeUpdates(this);
                finish();
            	}
            }
            else
            {
            	  Toast.makeText(getBaseContext(),"No Location", Toast.LENGTH_LONG).show();
            }
           
            
        }
        else
        {
        	  Toast.makeText(getBaseContext(),"Not Connected", Toast.LENGTH_LONG).show();
        }

       
                 
        /********* After registration onLocationChanged method  ********/
        /********* called periodically after each 3 sec ***********/
    }
     
    /************* Called after each 3 sec **********/
    @Override
    public void onLocationChanged(Location location) {
            
        String str = "Latitude: "+location.getLatitude()+" Longitude: "+location.getLongitude();

        Toast.makeText(getBaseContext(), str, Toast.LENGTH_LONG).show();
        for(int i=0;i<no.length;i++)
    	{
    	sm.sendTextMessage(no[i],null,str,null,null);
		Toast toast = Toast.makeText(context,"MSG send 2 "+no[i],Toast.LENGTH_LONG);
        toast.show();                             
        Toast.makeText(getBaseContext(), str, Toast.LENGTH_LONG).show();
        try
        {
        	g=this.openOrCreateDatabase("MOBILE",Context.MODE_PRIVATE,null);
        	g.execSQL("delete from PhoneNo where value='"+no[i]+"'");
        	//g.delete("PhoneNo","value",no[i])
        }
        catch(Exception e)
        {
        	 Toast.makeText(getBaseContext(),"Not Deleted", Toast.LENGTH_LONG).show();
        }
    	}
        locationManager.removeUpdates(this);
        finish();
    	
        
    }
 
    @Override
    public void onProviderDisabled(String provider) {
         
        /******** Called when User off Gps *********/
         
        Toast.makeText(getBaseContext(), "Gps turned off", Toast.LENGTH_LONG).show();
    }
 
    @Override
    public void onProviderEnabled(String provider) {
         
        /******** Called when User on Gps  *********/
         
        Toast.makeText(getBaseContext(), "Gps turned on ", Toast.LENGTH_LONG).show();
    }
    
    public void turnGPSOn()
    {
    	startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
    	 //Intent intent = new Intent("android.location.GPS_ENABLED_CHANGE");
         //intent.putExtra("enabled", true);
         //sendBroadcast(intent);
          Toast.makeText(getBaseContext(), "Gps Work on", Toast.LENGTH_LONG).show();//True - to enable data connectivity .
          @SuppressWarnings("deprecation")
		String provider = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
          if(!provider.contains("gps")){
              //if gps is disabled
              final Intent poke = new Intent();
              poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider"); 
              poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
              poke.setData(Uri.parse("3")); 
              context.sendBroadcast(poke);
          }

        
    }
   private void turnDATAOn() throws SecurityException, NoSuchMethodException, Exception, IllegalAccessException, InvocationTargetException
     {
    	
    	 dataManager  = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
    	 Method dataMtd = ConnectivityManager.class.getDeclaredMethod("setMobileDataEnabled", boolean.class);
    	 dataMtd.setAccessible(true);
    	 dataMtd.invoke(dataManager, true);  
    	 Toast.makeText(getBaseContext(), "DATA on", Toast.LENGTH_LONG).show();//True - to enable data connectivity .

     }

  	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		
	}

}
/*
private void turnGPSOn(){
	 String provider = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
   if(!provider.contains("gps")){
       //if gps is disabled
       final Intent poke = new Intent();
       poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider"); 
       poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
       poke.setData(Uri.parse("3")); 
       context.sendBroadcast(poke);
   }
  }*/
/* private void turnGPSOff(){
String provider = Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);

if(provider.contains("gps")){ //if gps is enabled
    final Intent poke = new Intent();
    poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
    poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
    poke.setData(Uri.parse("3")); 
    sendBroadcast(poke);
}
}
*/

  
