package com.acprj.publictransportroute.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONObject;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import com.acprj.publictransportroute.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class A_View_DirectionsMap extends FragmentActivity{
	GoogleMap googleMap;
	String mStart,mEnd;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a_mapslayout);
		mStart="17.39931,78.50510";
		mEnd="17.40686,78.49662";
		SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
		googleMap = fm.getMap();
		getRoute();
	}
	private String getDirectionsUrl(){
		// Origin of route
		String str_origin = "origin="+mStart;
		// Destination of route
		String str_dest = "destination="+mEnd;
		// Building the parameters to the web service
		//String url ="https://maps.googleapis.com/maps/api/directions/json?"+str_origin+"&"+str_dest+"&waypoints="+alternative_path1+"|via:"+alternative_path2+"&sensor=false&units=metric&mode=walking";//walking
		String url ="https://maps.googleapis.com/maps/api/directions/json?"+str_origin+"&"+str_dest+"&waypoints=17.39897,78.49813|via:17.39985,78.49733&sensor=false&units=metric&mode=driving";
		return url;
	}
	private void getRoute(){
		String url = getDirectionsUrl();
		new ReadDirectionsAsynTask().execute(url);
	}
	private class ReadDirectionsAsynTask extends AsyncTask<String, Void, String> {
		@Override
		protected String doInBackground(String... url) {
			String data = "";
			try {
				HttpConnection http = new HttpConnection();
				data = http.readUrl(url[0]);
			} catch (Exception e) {
				Log.d("Background Task", e.toString());
			}
			return data;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			new ParserTask().execute(result);
		}
	}
	ArrayList<LatLng> points = null;
	private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {
		@Override
		protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {
			JSONObject jObject;
			List<List<HashMap<String, String>>> routes = null;
			try {
				jObject = new JSONObject(jsonData[0]);
				PathJSONParser parser = new PathJSONParser();
				routes = parser.parse(jObject);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return routes;
		}
		@Override
		protected void onPostExecute(List<List<HashMap<String, String>>> routes) {

		/*	PolylineOptions polyLineOptions = null;

			polyLineOptions = new PolylineOptions();
			// traversing through routes
			for (int i = 0; i < routes.size(); i++) {
				points = new ArrayList<LatLng>();
				List<HashMap<String, String>> path = routes.get(i);
				for (int j = 0; j < path.size(); j++) {
					HashMap<String, String> point = path.get(j);
					double lat = Double.parseDouble(point.get("lat"));
					double lng = Double.parseDouble(point.get("lng"));
					LatLng position = new LatLng(lat, lng);
					points.add(position);
				}
				polyLineOptions.addAll(points);
				polyLineOptions.width(3);
				polyLineOptions.color(Color.BLUE);
			}
			destination=points.get(points.size()-2);
			start=points.get(0);
			googleMap.addPolyline(polyLineOptions);
			googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(start,13));
			addMarkers();*/
			f1(routes);
		}
	}
	private void f1(List<List<HashMap<String, String>>> result){
		ArrayList<LatLng> points = null;
        PolylineOptions lineOptions = null;
        MarkerOptions markerOptions = new MarkerOptions();

        // Traversing through all the routes
        for(int i=0;i<result.size();i++){
            points = new ArrayList<LatLng>();
            lineOptions = new PolylineOptions();

            // Fetching i-th route
            List<HashMap<String, String>> path = result.get(i);

            // Fetching all the points in i-th route
            for(int j=0;j<path.size();j++){
                HashMap<String,String> point = path.get(j);

                double lat = Double.parseDouble(point.get("lat"));
                double lng = Double.parseDouble(point.get("lng"));
                LatLng position = new LatLng(lat, lng);

                points.add(position);
            }

            // Adding all the points in the route to LineOptions
            lineOptions.addAll(points);
            lineOptions.width(2);
            lineOptions.color(Color.RED);
        }

        // Drawing polyline in the Google Map for the i-th route
        googleMap.addPolyline(lineOptions);
	}
	LatLng start =null,destination=null;
	private void addMarkers() {
		if (googleMap != null) {
			googleMap.addMarker(new MarkerOptions().position(start).title("Start Point").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
			googleMap.addMarker(new MarkerOptions().position(destination).title("End Point").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
		}
	}
}
