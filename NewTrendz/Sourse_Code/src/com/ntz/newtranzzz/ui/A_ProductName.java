package com.ntz.newtranzzz.ui;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
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

import com.ntz.newtranzzz.R;
import com.ntz.newtranzzz.api.S_ProductName;

public class A_ProductName extends Activity {
	String uname;
	ListView lvProducts;
	Context mcontext;
	String mUrl;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a_products);
		mcontext=this;
		lvProducts=(ListView)findViewById(R.id.lvProducts);
		lvProducts.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position,long arg3) {
				if(mPNames.size()>0){
					Intent intent=new Intent(A_ProductName.this,A_SpecProductDetails.class);
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
		 uname=getIntent().getExtras().getString("item");
		 mUrl=getIntent().getExtras().getString("mUrl");
		new GetProductName().execute();
	}
	private void ShowAllContent(){
		YVAdpter=new YoutubeVideosAdapter(mcontext,mPNames);
		lvProducts.setAdapter(YVAdpter);
	}
	public static ArrayList<HashMap<String, String>> mPNames = new ArrayList<HashMap<String, String>>();
	public class GetProductName extends AsyncTask<Void, Void, Void> {
		@Override
		protected void onPreExecute() {
			
		}
		@Override
		protected Void doInBackground(Void... params) {
			try{
				S_ProductName latest=new S_ProductName(mUrl);
				mPNames=latest.getProductDetails(uname);
			}catch(Exception e){}
			return null;

		}
		protected void onPostExecute(Void unused){ 
			ShowAllContent();
		}
	}
	ImageLoader imageLoader;
	YoutubeVideosAdapter YVAdpter;
	public class YoutubeVideosAdapter extends BaseAdapter {
		private Context context;
		private ArrayList<HashMap<String, String>> MyArr = new ArrayList<HashMap<String, String>>();
		public YoutubeVideosAdapter(Context c, ArrayList<HashMap<String, String>> myArrList) {
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
				row = inflater.inflate(R.layout.row_data, null); 
				holder = new SearchHolder();
				holder.txtTitle = (TextView)row.findViewById(R.id.tvData);
				holder.imageView = (ImageView)row.findViewById(R.id.imgPhotos);
				row.setTag(holder);
			}else{	
				holder = (SearchHolder)row.getTag();
			}
			HashMap<String, String> hm=MyArr.get(position);
			//holder.txtTitle.setText(hm.get("title"));
			holder.txtTitle.setText(Html.fromHtml(hm.get("ItemName")).toString());
			imageLoader.DisplayImage(hm.get("Image"),holder.imageView);
			return row;
		}
		class SearchHolder{
			TextView txtTitle;
			ImageView imageView;
		}
	}
}
