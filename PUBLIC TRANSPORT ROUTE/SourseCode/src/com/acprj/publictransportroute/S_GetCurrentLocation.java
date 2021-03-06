package com.acprj.publictransportroute;


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
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;

public class S_GetCurrentLocation {
	private String mServiceUrl="";
	//......Local Varibles ......
	private  int mResultCode=0;
	private StringBuilder mResposeData = new StringBuilder();
	private JSONArray jArray;
	private List<NameValuePair> inputParam = new ArrayList<NameValuePair>(1);
	private ArrayList<HashMap<String, String>> mArryLstResultData = new ArrayList<HashMap<String, String>>();
    public S_GetCurrentLocation(String url){
    	mServiceUrl=url;
    }
	//...... retrieving Data from server ......
	public  ArrayList<HashMap<String, String>> getCurrentLoc(){
		Log.i("getVideosIndexDetails", "getVideosIndexDetails.......");
		setDetailsToServer("0");
		try {
			HttpClient httpClient = new DefaultHttpClient();
			//HttpPost httpPost = new HttpPost("http://bytecodetechnosolutions.com/RamCharan/Toofan/Services/audiofunction.php");
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
				jArray =json.getJSONArray("items");
			}
		} catch (Exception e) {
			Log.i("Error in Videos : ", " Error : "+e);
			jArray=null;
		}
		return  parseJSONData(jArray);
	}
	//...... setting InputData to Server ......
	private void setDetailsToServer(String rno){
		inputParam.add(new BasicNameValuePair("random",rno));
	}

	//...... parsing JsonData ......
	private ArrayList<HashMap<String, String>> parseJSONData(JSONArray jArray){
		if(jArray!=null){
			for (int i = 0; i < jArray.length(); i++) {
				HashMap<String, String> map = new HashMap<String, String>();
				JSONObject jObj;
				try {
					jObj = jArray.getJSONObject(i);
					
					map.put("c_lat",jObj.getString("c_lat"));
					map.put("c_lng",jObj.getString("c_lng"));
					
					mArryLstResultData.add(map);

				} catch (JSONException e1) {
					e1.printStackTrace();
				}
			}
		}
		return mArryLstResultData;
	}
	
}