package com.ntz.newtranzzz.ui;


import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.internal.hs;
import com.ntz.newtranzzz.R;
import com.ntz.newtranzzz.api.S_LatestItem;

public class A_LatestTopSharedItems extends Activity {
	String uname;
	ListView lvProducts;
	Context mcontext;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a_products);
		mcontext=this;
		phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null,null, null);
		lvProducts=(ListView)findViewById(R.id.lvProducts);
		lvProducts.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position,long arg3) {
				if(mPNames.size()>0){
					Intent intent=new Intent(A_LatestTopSharedItems.this,A_SpecProductDetails.class);
					intent.putExtra("username", mPNames.get(position).get("username"));
					intent.putExtra("PhoneNumber", mPNames.get(position).get("PhoneNumber"));
					intent.putExtra("ItemName", mPNames.get(position).get("ItemName"));
					intent.putExtra("ShopName", mPNames.get(position).get("ShopName"));
					intent.putExtra("Image", mPNames.get(position).get("Image"));
					intent.putExtra("Place", mPNames.get(position).get("Place"));
					intent.putExtra("Address", mPNames.get(position).get("Address"));
					intent.putExtra("FeedBack", mPNames.get(position).get("FeedBack"));
					intent.putExtra("ShopPhoneNumber", mPNames.get(position).get("ShopPhoneNumber"));
					intent.putExtra("TimeStamp", mPNames.get(position).get("TimeStamp"));
					startActivity(intent);
				}
			}
		});	
		 
		new GetLatest().execute();
	}
	private void ShowAllContent(){
		latestItemsAdpter=new LatestItemsAdapter(mcontext,mPNames);
		lvProducts.setAdapter(latestItemsAdpter);
	}
	public static ArrayList<HashMap<String, String>> mPNames = new ArrayList<HashMap<String, String>>();
	public static ArrayList<String> mUserNames = new ArrayList<String>();
	public static ArrayList<String> mDBPhoneNumber = new ArrayList<String>();
	public static ArrayList<String> mMobilePhoneNumber = new ArrayList<String>();
	public static ArrayList<String> mMobileUserName = new ArrayList<String>();
	public class GetLatest extends AsyncTask<Void, Void, Void> {
		@Override
		protected void onPreExecute() {
			
		}
		@Override
		protected Void doInBackground(Void... params) {
			try{
				S_LatestItem latest=new S_LatestItem("http://newtrendzzz.net16.net/NTZZZ/getLatestItem.php");
				mPNames=latest.getProductDetails("");
				mDBPhoneNumber.clear();
				for(int i=0;i<mPNames.size();i++){
					mDBPhoneNumber.add(mPNames.get(i).get("PhoneNumber"));
				}
				
			}catch(Exception e){}
			return null;

		}
		protected void onPostExecute(Void unused){ 
			mMobilePhoneNumber.clear();
			mMobileUserName.clear();
			while (phones.moveToNext()) {
				String name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
				String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
				phoneNumber=phoneNumber.replace(" ", "");
				phoneNumber=phoneNumber.replace("+91", "");
				mMobilePhoneNumber.add(phoneNumber);
				mMobileUserName.add(name);
			}
			for(int i=0;i<mDBPhoneNumber.size();i++){
				if(mMobilePhoneNumber.contains(mDBPhoneNumber.get(i))){
					String tempNo=mDBPhoneNumber.get(i);
					for(int j=0;j<mMobilePhoneNumber.size();j++){
						if(mMobilePhoneNumber.get(j).contains(tempNo)){
							mUserNames.add(mMobileUserName.get(j));
							break;
						}
					}
					
				}else{
					mUserNames.add(mPNames.get(i).get("username"));
				}
			}
			
			ShowAllContent();
		}
	}
	ImageLoader imageLoader;
	Cursor phones;
	LatestItemsAdapter latestItemsAdpter;
	public class LatestItemsAdapter extends BaseAdapter {
		private Context context;
		private ArrayList<HashMap<String, String>> MyArr = new ArrayList<HashMap<String, String>>();
		public LatestItemsAdapter(Context c, ArrayList<HashMap<String, String>> myArrList) {
			// TODO Auto-generated method stub
			context = c;
			imageLoader=new ImageLoader(c);
			MyArr = myArrList;
		}

		public int getCount() {
			// TODO Auto-generated method stub
			return MyArr.size();
		}

		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View row = convertView;
			SearchHolder holder = null;
			if(row == null){
				LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				row = inflater.inflate(R.layout.row_latestitems, null); 
				holder = new SearchHolder();
				holder.txtTitle = (TextView)row.findViewById(R.id.tvData);
				holder.tvPostedBy = (TextView)row.findViewById(R.id.tvPostedBy);
				holder.imageView = (ImageView)row.findViewById(R.id.imgPhotos);
				row.setTag(holder);
			}else{	
				holder = (SearchHolder)row.getTag();
			}
			HashMap<String, String> hm=MyArr.get(position);
			//holder.txtTitle.setText(hm.get("title"));
			holder.txtTitle.setText(Html.fromHtml(hm.get("ItemName")).toString());
			holder.tvPostedBy.setText(Html.fromHtml(mUserNames.get(position)).toString());
			imageLoader.DisplayImage(hm.get("Image"),holder.imageView);
			
			return row;
		}
		class SearchHolder{
			TextView txtTitle,tvPostedBy;
			ImageView imageView;
		}
	}
}

