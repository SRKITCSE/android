package com.ntz.newtranzzz.ui;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ntz.newtranzzz.R;
import com.ntz.newtranzzz.api.S_SearchItems;
import com.ntz.newtranzzz.api.S_ShareItem;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

public class A_SearchItemsItemName extends Activity{
	AutoCompleteTextView actvItems;
	String item;
	String pID=null;
	String mUrl="";
	Button btnShare;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a_searchitems);
		//mUrl="";
		//
		mUrl=getIntent().getExtras().getString("url");
		btnShare=(Button)findViewById(R.id.btnShare);
		btnShare.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if(pID!=null){
					new ShareItem().execute();
				}
			}
		});
		actvItems=(AutoCompleteTextView)findViewById(R.id.aItems);
		actvItems.setThreshold(1);
		actvItems.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View arg1, int arg2,long arg3) {
				item = (String) parent.getItemAtPosition(arg2);
				/*for(int i=0;i<mSourceDestination.size();i++){
					if(mSourceDestination.get(i).get("ItemName").equalsIgnoreCase(item)){
						pID=mSourceDestination.get(i).get("ItemID");
						
					}
				}*/
				//Toast.makeText(getApplicationContext(), ""+item, Toast.LENGTH_SHORT).show();
				Intent intent=new Intent(getApplicationContext(),A_ProductName.class);
				intent.putExtra("item", item);//mUrl
				intent.putExtra("mUrl", "http://newtrendzzz.net16.net/NTZZZ/getAllProducatByItemName.php");//
				startActivity(intent);
				//
			}
		});
		new GetSearchItems().execute();
	}
	private class ShareItem extends AsyncTask<Void, Void, Void>{
		@Override
		protected void onPreExecute() {
			pb=new ProgressDialog(A_SearchItemsItemName.this);
			pb.setTitle("Please wait,data is being shared.");
			pb.show();
		}
		@Override
		protected Void doInBackground(Void... arg0) {
			try{
				
				S_ShareItem sitems=new S_ShareItem("http://newtrendzzz.net16.net/NTZZZ/GCM/sendPushMessages.php");
				SharedPreferences prefs = getSharedPreferences("NewTrazzz", Context.MODE_PRIVATE);
				String name=prefs.getString("username", "name");
				sitems.ShareItem(pID,name,item,"","");
				return null;
			}catch(Exception e){
				return null;
			}
		}
		protected void onPostExecute(Void unused){
			pb.hide();
		}
	}
	ProgressDialog pb;
	ArrayList<HashMap<String, String>> mRoutes = new ArrayList<HashMap<String, String>>();
	public static ArrayList<HashMap<String, String>> mSourceDestination = new ArrayList<HashMap<String, String>>();

	private class GetSearchItems extends AsyncTask<Void, Void, Void>{
		@Override
		protected void onPreExecute() {
			pb=new ProgressDialog(A_SearchItemsItemName.this);
			pb.setTitle("Please wait,data is being loaded.");
			pb.show();
		}
		@Override
		protected Void doInBackground(Void... arg0) {
			try{
				
				S_SearchItems sitems=new S_SearchItems(mUrl);
				mSourceDestination.clear();
				SharedPreferences prefs = getSharedPreferences("NewTrazzz", Context.MODE_PRIVATE);
				String name=prefs.getString("username", "name");
				mSourceDestination=sitems.getSearchItems(name);
				return null;
			}catch(Exception e){
				return null;
			}
		}
		protected void onPostExecute(Void unused){
			SharedPreferences prefs = getSharedPreferences("NewTrazzz", Context.MODE_PRIVATE);
			String name=prefs.getString("username", "name");
			Toast.makeText(getApplicationContext(), ""+name, Toast.LENGTH_SHORT).show();
			String srcDes[]=new String[mSourceDestination.size()];

			List<String> menus = new ArrayList<String>();
			for(int i=0;i<mSourceDestination.size();i++){
				HashMap<String, String> hm=mSourceDestination.get(i);
				/*menus.add(hm.get("ItemName"));
				srcDes[i]=hm.get("ItemName");*/
				menus.add(hm.get("username"));
				srcDes[i]=hm.get("username");
			}

			ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(A_SearchItemsItemName.this,android.R.layout.simple_spinner_item, menus);
			dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			ArrayAdapter adapter = new ArrayAdapter (A_SearchItemsItemName.this,android.R.layout.simple_list_item_1,srcDes);
			actvItems.setAdapter(adapter);
			pb.hide();
			
		}
	}
}
