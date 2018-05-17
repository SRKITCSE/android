package com.acprj.publictransportroute;

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
import org.json.JSONObject;
import android.util.Log;

public class S_EnterBusDetails {
	private String mServiceUrl="";
	//......Local Varibles ......
	private  int mResultCode=0;
	private StringBuilder mResposeData = new StringBuilder();
	private List<NameValuePair> inputParam = new ArrayList<NameValuePair>(1);
public S_EnterBusDetails(String url){
	mServiceUrl=url;
}
	//...... retrieving Data from server ......
	public  String insertBusDetails(String buss_no,String source,String destination,String src_time,String des_time,String route_no){
		String result=null;
		setDetailsToServer(buss_no, source, destination, src_time, des_time, route_no);
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
	private void setDetailsToServer(String buss_no,String source,String destination,String src_time,String des_time,String route_no){
		inputParam.add(new BasicNameValuePair("buss_no",buss_no));
		inputParam.add(new BasicNameValuePair("source",source));
		inputParam.add(new BasicNameValuePair("destination",destination));
		inputParam.add(new BasicNameValuePair("src_time",src_time));
		inputParam.add(new BasicNameValuePair("des_time",des_time));
		inputParam.add(new BasicNameValuePair("route_no",route_no));
	}
}
