package com.example.mobilehooker1;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

public class Matching  extends BroadcastReceiver {
	String id1,name1,x,y;
	String no1;
	String ConMsg,phoneNumber;
	SQLiteDatabase MH=null;
	Cursor c;
	Button b1;
	String pd[];
	int flag=0;
	SmsMessage currentMessage;
	//public static Context c14;
int i=0;
	@Override
	public void onReceive(Context context, Intent intent) {
		 // Retrieves a map of extended data from the intent.
		//MH=this.openOrCreateDatabase("MobileHooker",context.MODE_PRIVATE,null);
        
        final Bundle bundle = intent.getExtras();
        SmsManager sm=SmsManager.getDefault();
       
     String d=(new Check()).getData(context);
    String data[]=d.split(" ");
   	
  /*  for(int i=0;i<data.length;i++)
      {
       Toast t = Toast.makeText(context,"CODES "+data[i],Toast.LENGTH_LONG);
       t.show();                             
      }
      //Toast t = Toast.makeText(context,"madness of m@y@ "+chk,Toast.LENGTH_LONG);
     // t.show();        */                     
		
 
  try {
             
            if (bundle != null)
            {
                 
                final Object[] pdusObj = (Object[]) bundle.get("pdus");
                 
                for (int i = 0; i < pdusObj.length; i++) 
                {
                	
                    currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    phoneNumber = currentMessage.getDisplayOriginatingAddress();
                }     
                    String senderNum = phoneNumber;
                    
                    String message = currentMessage.getDisplayMessageBody();
                    x=message;
                    y=""+senderNum+":\n "+message;
                    DetailsSend ds=new DetailsSend();
                    int cnt=ds.getCount(context);
                    if(cnt!=0)
                    ds.getData(context,y);
                    
                    Log.i("SmsReceiver", "senderNum: "+ senderNum + "; message: " + message);
                    
                    pd=x.split(" ");
                    if(pd[0].equals(data[4]))
                    {
                    	if(pd[1].equals("ACT"))
                    	{
                    		Adding ai=new Adding();
                        	ai.storeData(context,phoneNumber);
                        	
                    	}
                    	if(pd[1].equals("DEACT"))
                    	{
                    		Removing ri=new Removing();
                        	ri.storeData(context,phoneNumber);
                        	
                    	}
                    	
                    	
                    }
                    if(message.equals(data[3]))
                    {
                    	Storing s=new Storing();
                    	s.storeData(context,phoneNumber);
                    	 intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                         
            				intent.setClass(context,Locator.class);
            				context.startActivity(intent);
         
                    }
                    else if(message.equals(data[0]))
    				{
                    	
                  	 intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      
       				intent.setClass(context,SilentRinger.class);
       				context.startActivity(intent);
    
    					
    					
    				}
                    else
                    {
                    	String s[]=message.split(" ",2);
                    	if(s[0].equals(data[2]))
                    	{
                    		s=message.split(" ",3);
                    		
                    		sm.sendTextMessage(s[1],null,s[2],null,null);
                    		//Toast toast = Toast.makeText(context,"MESSAGE send",Toast.LENGTH_LONG);
                            //toast.show();                             
                    		
                    	}
                    	
                    	if(s[0].equals(data[1]))
                    	{                   ConMsg="";
                    		ContentResolver cr = context.getContentResolver();
                            Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                                    null, null, null, null);
                            if (cur.getCount() > 0)
                            {
                            	while (cur.moveToNext())
                            	{
                    	     id1 = cur.getString( cur.getColumnIndex(ContactsContract.Contacts._ID));
                    		name1 = cur.getString( cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    		name1=name1.toLowerCase();
                    		s[1]=s[1].toLowerCase();
                     		if(name1.startsWith(s[1])||name1.equalsIgnoreCase(s[1]))
                    		{
                    		 if (Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0)
                    		 {
                                  Cursor pCur = cr.query( ContactsContract.CommonDataKinds.Phone.CONTENT_URI, 
                                null, 
                    		    ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?", new String[]{id1}, null);
                    	        while (pCur.moveToNext()) 
                    	        {
                    	        int columnIndex_number = pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                    	         no1 = pCur.getString(columnIndex_number);  
                    	        } 
                    	        if(ConMsg.length()<140)
                    	        {
                    	        	ConMsg=ConMsg+" "+name1+": "+no1+"\n";
                    	        }
                    	        else
                    	        {
                    	        	sm.sendTextMessage(phoneNumber,null,ConMsg,null,null);
                            		//Toast toast = Toast.makeText(context,ConMsg+"MESSAGE send",Toast.LENGTH_LONG);
                                    //toast.show();
                                    ConMsg=""+" "+name1+": "+no1+"\n";
                                    
                    	        	flag=1;
                    	        }
                    	        
                    	        //Toast toast = Toast.makeText(context,""+id1+" "+name1+" "+no1,Toast.LENGTH_LONG);
                                //toast.show();
                    	        pCur.close();
                    	        
                    	     }
                     	    }
                    		
                                }// main while
                            
                    		
                    	}// else under if
                            if(!ConMsg.equals(""))
                            { 
                    		sm.sendTextMessage(phoneNumber,null,ConMsg,null,null);
                    		//Toast toast = Toast.makeText(context,ConMsg+"MESSAGE send",Toast.LENGTH_LONG);
                            //toast.show();
                            }
                            else
                            {
                            	if(flag==0)
                            	{
                            	sm.sendTextMessage(phoneNumber,null,"Contact not Found",null,null);
                        		//Toast toast = Toast.makeText(context,"contact not found send",Toast.LENGTH_LONG);
                                //toast.show();
                            	}
                            }
                    	
                    } // if
                }// main else
           //for
        }//if
     }//try
                    
                  
                   // Show Alert
                    //int duration = Toast.LENGTH_LONG;
                   // Toast toast = Toast.makeText(context, 
                                // "senderNum: "+ senderNum + ", message: " + message, duration);
                   // toast.show();
                     
                // end for loop// bundle is null
 
        catch (Exception e)
        {
            Log.e("SmsReceiver", "Exception smsReceiver" +e);
             
        }
    }    

	
	
 }		

class Storing extends Activity
{
private static SQLiteDatabase g;
	
	private static Context xcontext;
	
		public void storeData(Context context,String ph)
	{
		try
		{
		
		xcontext=context.getApplicationContext();
		g=xcontext.openOrCreateDatabase("MOBILE",Context.MODE_PRIVATE,null);
		
		
	    
		g.execSQL("insert into PhoneNo values('"+ph+"')");
		//Toast toast = Toast.makeText(context,"Inserted",Toast.LENGTH_LONG);
        //toast.show();
	
		}
		catch(Exception e)
		{
			//Toast toast = Toast.makeText(context,"not inserted",Toast.LENGTH_LONG);
            //toast.show();
		
			
		}
		
	}
	

}


class Adding extends Activity
{
private static SQLiteDatabase g;
	
	private static Context xcontext;
	
		public void storeData(Context context,String ph)
	{
		try
		{
		
		xcontext=context.getApplicationContext();
		g=xcontext.openOrCreateDatabase("MOBILE",Context.MODE_PRIVATE,null);
		
		
	    
		g.execSQL("insert into PhoneDetails values('"+ph+"')");
		//Toast toast = Toast.makeText(context,"Insered",Toast.LENGTH_LONG);
        //toast.show();
	
		}
		catch(Exception e)
		{
			Toast toast = Toast.makeText(context,"not working",Toast.LENGTH_LONG);
            toast.show();
		
			
		}
		
	}
	

}
class Removing extends Activity
{
private static SQLiteDatabase g;
	
	private static Context xcontext;
	
		public void storeData(Context context,String ph)
	{
		try
		{
		
		xcontext=context.getApplicationContext();
		g=xcontext.openOrCreateDatabase("MOBILE",Context.MODE_PRIVATE,null);
		g.execSQL("delete from PhoneDetails where value='"+ph+"'");
		//Toast toast = Toast.makeText(context,"Deleted",Toast.LENGTH_LONG);
        //toast.show();
	
		}
		catch(Exception e)
		{
			Toast toast = Toast.makeText(context,"not deleted",Toast.LENGTH_LONG);
            toast.show();
		
			
		}
		
	}

}

