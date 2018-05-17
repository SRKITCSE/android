package com.ntz.newtranzzz.ui;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.ntz.newtranzzz.Base64;
import com.ntz.newtranzzz.R;
import com.ntz.newtranzzz.api.S_Products;


public class A_Post extends Activity {
	EditText etPhoneNumber,etItemName,etShopName,etPlace,etAddress,etFeedBack,etShopPhoneNumber,etTimeStamp;
	Button btnPost,btnGetImage;
	String PhoneNumber="",ItemName="",ShopName="",Place="",Address="",FeedBack="",ShopPhoneNumber="",TimeStamp="";
	ImageView ivImage;
	byte[] byteArray=null;
	private static final int TAKENPHOTO = 999;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a_post);
		ivImage=(ImageView)findViewById(R.id.ivPImage);
		
		etPhoneNumber=(EditText)findViewById(R.id.etPhoneNumber);
		SharedPreferences prefs = getSharedPreferences("NewTrazzz", Context.MODE_PRIVATE);
		String phonenumber=prefs.getString("phonenumber", "phno");
		etPhoneNumber.setText(phonenumber);
		etPhoneNumber.setEnabled(false);
		etItemName=(EditText)findViewById(R.id.etItemName);
		etShopName=(EditText)findViewById(R.id.etShopName);
		etPlace=(EditText)findViewById(R.id.etPlace);
		etAddress=(EditText)findViewById(R.id.etAddress);
		etFeedBack=(EditText)findViewById(R.id.etFeedBack);
		etShopPhoneNumber=(EditText)findViewById(R.id.etShopPhoneNumber);
		etTimeStamp=(EditText)findViewById(R.id.etTimeStamp);
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		//System.out.println(dateFormat.format(date)); 
		etTimeStamp.setText(dateFormat.format(date));
		etTimeStamp.setEnabled(false);
		btnGetImage=(Button)findViewById(R.id.btnGetImage);
		btnGetImage.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				getImageFromGallery();
			}
		});
		btnPost=(Button)findViewById(R.id.btnPost);
		btnPost.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				PhoneNumber=etPhoneNumber.getText().toString();
				ItemName=etItemName.getText().toString();
				ShopName=etShopName.getText().toString();
				Place=etPlace.getText().toString();
				Address=etAddress.getText().toString();
				FeedBack=etFeedBack.getText().toString();
				ShopPhoneNumber=etShopPhoneNumber.getText().toString();
				TimeStamp=etTimeStamp.getText().toString();
				
				if(PhoneNumber.length()==0){
					Toast.makeText(getApplicationContext(), "Please enter PhoneNumber", Toast.LENGTH_SHORT).show();
				}else if(ItemName.length()==0){
					Toast.makeText(getApplicationContext(), "Please enter ItemName", Toast.LENGTH_SHORT).show();
				}else if(ShopName.length()==0){
					Toast.makeText(getApplicationContext(), "Please enter ShopName", Toast.LENGTH_SHORT).show();
				}else if(image==null){
					Toast.makeText(getApplicationContext(), "Please enter Image", Toast.LENGTH_SHORT).show();
				}else if(Place.length()==0){
					Toast.makeText(getApplicationContext(), "Please enter Place", Toast.LENGTH_SHORT).show();
				}else if(Address.length()==0){
					Toast.makeText(getApplicationContext(), "Please enter Address", Toast.LENGTH_SHORT).show();
				}else if(FeedBack.length()==0){
					Toast.makeText(getApplicationContext(), "Please enter FeedBack", Toast.LENGTH_SHORT).show();
				}else if(ShopPhoneNumber.length()==0){
					Toast.makeText(getApplicationContext(), "Please enter ShopPhoneNumber", Toast.LENGTH_SHORT).show();
				}else if(ShopPhoneNumber.length()!=10){
					Toast.makeText(getApplicationContext(), "Shop PhoneNumber should be 10 digits.", Toast.LENGTH_SHORT).show();
				}
				else if(TimeStamp.length()==0){
					Toast.makeText(getApplicationContext(), "Please enter TimeStamp", Toast.LENGTH_SHORT).show();
				}else{
					callProdcutService();
				}
			}
		});
	}
	private void callProdcutService(){
		new InserProducts().execute();
	}
	String res=null;
	ProgressDialog pd;
	public class InserProducts extends AsyncTask<Void, Void, Void> {
		@Override
		protected void onPreExecute() {
			pd = new ProgressDialog(A_Post.this);
			pd.setMessage("Pleaase wait,data is being loaded...");
			pd.show();
		
			
		}
		@Override
		protected Void doInBackground(Void... params) {
			try{
				S_Products products=new S_Products("http://newtrendzzz.net16.net/NTZZZ/insertProfileInfo.php");
				SharedPreferences prefs = getSharedPreferences("NewTrazzz", Context.MODE_PRIVATE);
				String username=prefs.getString("username", "username");
				String phonenumber=prefs.getString("phonenumber", "phonenumber");
				
				res=products.insertProductData(username,phonenumber, ItemName, ShopName, image, Place, Address, FeedBack, ShopPhoneNumber, TimeStamp);
			}catch(Exception e){}
			return null;

		}
		protected void onPostExecute(Void unused){ 
			pd.hide();
			if(res!=null){
				Intent intent=new Intent(getApplicationContext(),A_ContactDetails.class);
				intent.putExtra("ItemID", res);
				startActivity(intent);
				finish();
				
			}
		}
	}
	String image=null;
	
	/*public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode==TAKENPHOTO){
			Bitmap photo = (Bitmap) data.getExtras().get("data");
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			photo.compress(Bitmap.CompressFormat.JPEG, 50, stream);
			byteArray = stream.toByteArray();
			
			Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
			ivImage.setVisibility(View.VISIBLE);
			ivImage.setImageBitmap(bmp);
			try{
				 image = Base64.encodeBytes(byteArray);
			}catch(Exception e){}
		}
	}*/
	private static int RESULT_LOAD_IMAGE = 1000;
	private void getImageFromGallery(){
		Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

		startActivityForResult(i, RESULT_LOAD_IMAGE);
		}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
			Uri selectedImage = data.getData();
			String[] filePathColumn = { MediaStore.Images.Media.DATA };

			Cursor cursor = getContentResolver().query(selectedImage,
					filePathColumn, null, null, null);
			cursor.moveToFirst();

			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			String picturePath = cursor.getString(columnIndex);
			cursor.close();


			ivImage.setImageBitmap(BitmapFactory.decodeFile(picturePath));
			Bitmap bitmap=BitmapFactory.decodeFile(picturePath);
			
			
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			bitmap.compress(Bitmap.CompressFormat.JPEG, 60, stream);
			byte[] bitMapData = stream.toByteArray();
			 image = Base64.encodeBytes(bitMapData);
		}
	}
}