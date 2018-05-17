package com.ntz.newtranzzz.ui;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ntz.newtranzzz.R;
import com.ntz.newtranzzz.api.S_MobileNumbers;
import com.ntz.newtranzzz.api.S_ShareItem;

public class A_ContactDetails extends Activity{
	LinearLayout llContacts;
	Cursor phones;
	String ItemID;
	Button btnShare;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a_contact_details);
		btnShare=(Button)findViewById(R.id.btnShare);
		btnShare.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				new ShareItem().execute();
			}
		});
		
		ItemID=getIntent().getExtras().getString("ItemID");
		llContacts=(LinearLayout)findViewById(R.id.llContacts);
		llContacts.removeAllViews();
		//tv=(TextView)findViewById(R.id.textView1);
		phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null,null, null);
		
		new GetNumbers().execute();

	}
	
	public static ArrayList<HashMap<String, String>> mMobileNos = new ArrayList<HashMap<String, String>>();
	ArrayList<String> chk=new ArrayList<String>();
	public class GetNumbers extends AsyncTask<Void, Void, Void> {
		@Override
		protected void onPreExecute() {
			
		}
		@Override
		protected Void doInBackground(Void... params) {
			try{
				S_MobileNumbers latest=new S_MobileNumbers("http://newtrendzzz.net16.net/NTZZZ/getMobileInfo.php");
				mMobileNos=latest.getMobileNumbers("");
			}catch(Exception e){}
			return null;

		}
		protected void onPostExecute(Void unused){ 
			
			//Toast.makeText(getApplicationContext(), ""+mMobileNos.size(), Toast.LENGTH_SHORT).show();
			while (phones.moveToNext()) {
				String name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
				String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
				phoneNumber=phoneNumber.replace(" ", "");
				phoneNumber=phoneNumber.replace("+91", "");
				View vContacts = getLayoutInflater().inflate(R.layout.row_contacts, llContacts, false);
				TextView tvName=(TextView)vContacts.findViewById(R.id.tvName);
				TextView tvLine=(TextView)vContacts.findViewById(R.id.tvLine);
				
				tvName.setVisibility(View.GONE);
				tvLine.setVisibility(View.GONE);
				final CheckBox cbName=(CheckBox)vContacts.findViewById(R.id.cbName);
				cbName.setOnClickListener(new OnClickListener() {
					 
					  @Override
					  public void onClick(View v) {
				             
						if (((CheckBox) v).isChecked()) {
							chk.add(cbName.getTag().toString());
						}else{
							chk.remove(cbName.getTag().toString());
						}
				 
					  }
					});
				if(mMobileNos.size()>0){
					for(int i=0;i<mMobileNos.size();i++){
						HashMap<String, String> hm=mMobileNos.get(i);
						String no=hm.get("phonenumber");
						String device_id=hm.get("device_id");
						if(phoneNumber.equals(no)){
							tvName.setVisibility(View.VISIBLE);
							tvLine.setVisibility(View.VISIBLE);
							cbName.setVisibility(View.VISIBLE);
							cbName.setTag(device_id);
						}
					}
				}
				tvName.setText(name+"  [ "+phoneNumber+" ]");
				tvName.setTag(phoneNumber);
				llContacts.addView(vContacts);
			}
			
		}
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		
	};
	
	ProgressDialog pb;
	String ids;
	private class ShareItem extends AsyncTask<Void, Void, Void>{
		@Override
		protected void onPreExecute() {
			pb=new ProgressDialog(A_ContactDetails.this);
			pb.setTitle("Please wait,data is being shared.");
			pb.show();
		}
		@Override
		protected Void doInBackground(Void... arg0) {
			try{
				
				S_ShareItem sitems=new S_ShareItem("http://newtrendzzz.net16.net/NTZZZ/GCM/sendPushMessages.php");
				SharedPreferences prefs = getSharedPreferences("NewTrazzz", Context.MODE_PRIVATE);
				String name=prefs.getString("username", "name");
				String phno=prefs.getString("phonenumber", "phonenumber");
				 ids=TextUtils.join(",", chk);//
				//Toast.makeText(getApplicationContext(), ""+ids, Toast.LENGTH_SHORT).show();
				sitems.ShareItem(ItemID,name+" - Sharing some details...","New Tranzz",ids,phno);
				return null;
			}catch(Exception e){
				return null;
			}
		}
		protected void onPostExecute(Void unused){
			pb.hide();
			startActivity(new Intent(getApplicationContext(),A_Dashboard.class));
			//Toast.makeText(getApplicationContext(), ids, Toast.LENGTH_SHORT).show();
		}
	}
}
