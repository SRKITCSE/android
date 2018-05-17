package com.ntz.newtranzzz.ui;

import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.ntz.newtranzzz.R;
import com.ntz.newtranzzz.api.S_LatestItem;

public class A_Dashboard extends Activity {
	Button btnPost,btnSearchItems,btnSearchItemsName,btnLatestSharedItems;
	byte[] byteArray=null;
	private static final int PICK_CONTACT = 999;
	private static int RESULT_LOAD_IMAGE = 1000;
	ImageView ivPImage;
	TextView tvPostedBy;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a_dashboard);
		ivPImage=(ImageView)findViewById(R.id.ivPImage);
		
		tvPostedBy=(TextView)findViewById(R.id.tvPostedBy);
		if(AppSplashScreen.mLatest.size()>0){
			new ProductImage().execute();
		}else{
			new GetLatest().execute();
		}

		btnPost=(Button)findViewById(R.id.btnPost);
		btnPost.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(A_Dashboard.this,A_Post.class));
			}
		});
		ivPImage.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {

				Intent intent=new Intent(A_Dashboard.this,A_SpecProductDetails.class);
				intent.putExtra("username", AppSplashScreen.mLatest.get(0).get("username"));
				intent.putExtra("PhoneNumber", AppSplashScreen.mLatest.get(0).get("PhoneNumber"));
				intent.putExtra("ItemName", AppSplashScreen.mLatest.get(0).get("ItemName"));
				intent.putExtra("ShopName", AppSplashScreen.mLatest.get(0).get("ShopName"));
				intent.putExtra("Image", AppSplashScreen.mLatest.get(0).get("Image"));
				intent.putExtra("Place", AppSplashScreen.mLatest.get(0).get("Place"));
				intent.putExtra("Address", AppSplashScreen.mLatest.get(0).get("Address"));
				intent.putExtra("FeedBack", AppSplashScreen.mLatest.get(0).get("FeedBack"));
				intent.putExtra("ShopPhoneNumber", AppSplashScreen.mLatest.get(0).get("ShopPhoneNumber"));
				intent.putExtra("TimeStamp", AppSplashScreen.mLatest.get(0).get("TimeStamp"));
				startActivity(intent);

			}
		});
		btnSearchItems=(Button)findViewById(R.id.btnSearchItems);
		btnSearchItems.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent=new Intent(A_Dashboard.this,A_SearchItems.class);
				intent.putExtra("url", "http://newtrendzzz.net16.net/NTZZZ/getAllUsers.php");
				startActivity(intent);
			}
		});

		btnSearchItemsName=(Button)findViewById(R.id.btnSearchItemsName);
		btnSearchItemsName.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent=new Intent(A_Dashboard.this,A_SearchItemsItemName.class);
				intent.putExtra("url", "http://newtrendzzz.net16.net/NTZZZ/getAllItems.php");
				startActivity(intent);
			}
		});
		btnLatestSharedItems=(Button)findViewById(R.id.btnLatestSharedItems);
		btnLatestSharedItems.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent=new Intent(A_Dashboard.this,A_LatestTopSharedItems.class);
				startActivity(intent);
			}
		});
	}
	String url;
	Bitmap bitmap;
	String name;
	private class ProductImage extends AsyncTask<Void, Void, Void>{
		@Override
		protected void onPreExecute() {

			HashMap<String, String> hm=AppSplashScreen.mLatest.get(0);
			url=hm.get("Image");
			name=hm.get("username").toString();
		}
		@Override
		protected Void doInBackground(Void... arg0) {
			try{
				bitmap = BitmapFactory.decodeStream((InputStream)new URL(url).getContent());
				return null;
			}catch(Exception e){
				return null;
			}
		}
		protected void onPostExecute(Void unused){
			ivPImage.setImageBitmap(bitmap);
			tvPostedBy.setText("Posted by : "+name);
		}
	}
	
	public class GetLatest extends AsyncTask<Void, Void, Void> {
		@Override
		protected void onPreExecute() {
			
		}
		@Override
		protected Void doInBackground(Void... params) {
			try{
				S_LatestItem latest=new S_LatestItem("http://newtrendzzz.net16.net/NTZZZ/getLatestItem.php");
				AppSplashScreen.mLatest=latest.getProductDetails("");
			}catch(Exception e){}
			return null;

		}
		protected void onPostExecute(Void unused){ 
			new ProductImage().execute();
		}
	}
}
