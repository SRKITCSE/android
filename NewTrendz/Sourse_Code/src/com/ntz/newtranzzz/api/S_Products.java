package com.ntz.newtranzzz.api;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;
import android.util.Log;

public class S_Products {
	private String mServiceUrl="";
	//......Local Varibles ......
	private  int mResultCode=0;
	private StringBuilder mResposeData = new StringBuilder();
	private JSONArray jArray;
	private List<NameValuePair> inputParam = new ArrayList<NameValuePair>(1);
	private ArrayList<HashMap<String, String>> mArryLstResultData = new ArrayList<HashMap<String, String>>();
	public S_Products(String url){
		mServiceUrl=url;
	}
	//...... retrieving Data from server ......
	public  String insertProductData(String username,String PhoneNumber,String ItemName,String ShopName,String Image,String Place,String Address,String FeedBack,String ShopPhoneNumber,String TimeStamp){
		String result=null;
		setDetailsToServer(username,PhoneNumber, ItemName, ShopName, Image, Place, Address, FeedBack, ShopPhoneNumber, TimeStamp);
		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(mServiceUrl);
			//HttpGet httpGet=new HttpGet(mServiceUrl);
			httpPost.setEntity(new UrlEncodedFormEntity(inputParam));
			HttpResponse response = httpClient.execute(httpPost);
			Log.i("HttpClient...", "HttpClient......."+response.getEntity().toString());
			HttpEntity entity = response.getEntity();
			BufferedReader buf = new BufferedReader(new InputStreamReader(entity.getContent()));
			mResultCode=response.getStatusLine().getStatusCode();
			String line = null;
			while ((line = buf.readLine()) != null) {
				mResposeData.append(line+"\n");
			}
			Log.i("Video Respose Data : ", mResposeData+" Code : "+mResultCode);
			if(mResultCode==200){
				JSONObject json = new JSONObject(mResposeData.toString());
				//jArray =json.getJSONArray("result");
				result=json.get("result").toString();
			}
		} catch (Exception e) {
			return null;
		}
		return  result;
	}
	//...... setting InputData to Server ......
	private void setDetailsToServer(String username,String PhoneNumber,String ItemName,String ShopName,String Image,String Place,String Address,String FeedBack,String ShopPhoneNumber,String TimeStamp){
		inputParam.add(new BasicNameValuePair("username",username));
		inputParam.add(new BasicNameValuePair("PhoneNumber",PhoneNumber));
		inputParam.add(new BasicNameValuePair("ItemName",ItemName));
		inputParam.add(new BasicNameValuePair("ShopName",ShopName));
		inputParam.add(new BasicNameValuePair("Image",Image));
		inputParam.add(new BasicNameValuePair("Place",Place));
		inputParam.add(new BasicNameValuePair("Address",Address));
		inputParam.add(new BasicNameValuePair("FeedBack",FeedBack));
		inputParam.add(new BasicNameValuePair("ShopPhoneNumber",ShopPhoneNumber));
		inputParam.add(new BasicNameValuePair("TimeStamp",TimeStamp));
	}
}

