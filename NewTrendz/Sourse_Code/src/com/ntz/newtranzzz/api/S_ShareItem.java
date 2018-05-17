package com.ntz.newtranzzz.api;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.util.Log;

public class S_ShareItem {
	private String mServiceUrl="";
	//......Local Varibles ......
	private  int mResultCode=0;
	private StringBuilder mResposeData = new StringBuilder();
	private List<NameValuePair> inputParam = new ArrayList<NameValuePair>(1);
public S_ShareItem(String url){
	mServiceUrl=url;
}
	//...... retrieving Data from server ......
	public  String ShareItem(String itemID,String name,String item,String ids,String phno){
		String result=null;
		setDetailsToServer(itemID,name,item,ids,phno);
		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(mServiceUrl);
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
				
			}
		} catch (Exception e) {
			return null;
		}
		return  result;
	}
	//...... setting InputData to Server ......
	private void setDetailsToServer(String itemID,String name,String item,String ids,String phno){
		inputParam.add(new BasicNameValuePair("itemID",itemID));
		inputParam.add(new BasicNameValuePair("name",name));
		inputParam.add(new BasicNameValuePair("item",item));
		inputParam.add(new BasicNameValuePair("device_ids",ids));
		inputParam.add(new BasicNameValuePair("phno",phno));
		
		
	}
}
