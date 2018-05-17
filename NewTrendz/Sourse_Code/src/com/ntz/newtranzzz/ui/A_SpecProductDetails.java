package com.ntz.newtranzzz.ui;


import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.ntz.newtranzzz.R;
import com.ntz.newtranzzz.api.S_ProductDetails;

public class A_SpecProductDetails extends Activity {
	EditText etPhoneNumber,etItemName,etShopName,etPlace,etAddress,etFeedBack,etShopPhoneNumber,etTimeStamp;
	ImageView ivPImage;
	String itemID;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a_product_details);
		
		ivPImage=(ImageView)findViewById(R.id.ivPImage);
		ivPImage.setVisibility(View.VISIBLE);
		etPhoneNumber=(EditText)findViewById(R.id.etPhoneNumber);
		etItemName=(EditText)findViewById(R.id.etItemName);
		etShopName=(EditText)findViewById(R.id.etShopName);
		etPlace=(EditText)findViewById(R.id.etPlace);
		etAddress=(EditText)findViewById(R.id.etAddress);
		etFeedBack=(EditText)findViewById(R.id.etFeedBack);
		etShopPhoneNumber=(EditText)findViewById(R.id.etShopPhoneNumber);
		etTimeStamp=(EditText)findViewById(R.id.etTimeStamp);
		
		etPhoneNumber.setText(getIntent().getExtras().getString("PhoneNumber"));
		etItemName.setText(getIntent().getExtras().getString("ItemName"));
		etShopName.setText(getIntent().getExtras().getString("ShopName"));
		etPlace.setText(getIntent().getExtras().getString("Place"));
		etAddress.setText(getIntent().getExtras().getString("Address"));
		etFeedBack.setText(getIntent().getExtras().getString("FeedBack"));
		etShopPhoneNumber.setText(getIntent().getExtras().getString("ShopPhoneNumber"));
		etTimeStamp.setText(getIntent().getExtras().getString("TimeStamp"));
		url=getIntent().getExtras().getString("Image");
		
		new ProductImage().execute();
		Button btnGoto=(Button)findViewById(R.id.btnGotoDashboard);
		btnGoto.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(getApplicationContext(),A_Dashboard.class));
				finish();
			}
		});
		//new ProductDetailsAsync().execute();
	}
	ProgressDialog pd;
	ArrayList<HashMap<String, String>> mProductDetails = new ArrayList<HashMap<String, String>>();
	
	private class ProductDetailsAsync extends AsyncTask<Void, Void, Void>
	{
		@Override
		protected void onPreExecute() {
			pd = new ProgressDialog(A_SpecProductDetails.this);
			pd.setMessage("Please wait, Data is being Loaded");
			pd.show();
		}
		@Override
		protected Void doInBackground(Void... arg0) {
			/*SharedPreferences prefs = getSharedPreferences(A_Login.class.getSimpleName(), Context.MODE_PRIVATE);
			String name=prefs.getString("username", "name");*/
			S_ProductDetails pdetails=new S_ProductDetails("http://newtrendzzz.net16.net/NTZZZ/getProductDetailsByItemID.php");
			mProductDetails=pdetails.getProductDetails(itemID);
			return null;
		}
		protected void onPostExecute(Void unused){  
			try {
				if(mProductDetails.size()==0){
					Toast.makeText(getApplicationContext(), "No more views.",Toast.LENGTH_SHORT).show();
				}else{
					HashMap<String, String> hm=mProductDetails.get(0);
					//Toast.makeText(getApplicationContext(), ""+,Toast.LENGTH_SHORT).show();
					etPhoneNumber.setText(hm.get("PhoneNumber"));
					etPhoneNumber.setText(hm.get("PhoneNumber"));
					etItemName.setText(hm.get("ItemName"));
					etShopName.setText(hm.get("ShopName"));
					etPlace.setText(hm.get("Place"));
					etAddress.setText(hm.get("Address"));
					etFeedBack.setText(hm.get("FeedBack"));
					etShopPhoneNumber.setText(hm.get("ShopPhoneNumber"));
					etTimeStamp.setText(hm.get("TimeStamp"));
					url=hm.get("Image");
					new ProductImage().execute();
					
				}
				pd.hide();
			} catch (Exception e) {
				e.printStackTrace();
				pd.hide();
			}
		}
	}
	Bitmap bitmap;
	String url;
	private class ProductImage extends AsyncTask<Void, Void, Void>{
		@Override
		protected void onPreExecute() {
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
		}
	}
}

