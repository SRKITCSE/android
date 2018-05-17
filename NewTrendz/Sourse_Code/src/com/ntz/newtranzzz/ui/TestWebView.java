package com.ntz.newtranzzz.ui;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import com.ntz.newtranzzz.R;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class TestWebView extends Activity{
	WebView wvDashboardText;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a_test);
		f1();
		wvDashboardText=(WebView)findViewById(R.id.webView1);
		String your_description_from_api="<img class=\"imgL\" src=\"http://img.sakshi.net/images/cms/2015-03/81425617796_Unknown.png\"/>Testing.....";
		setDashbaordWebViewData(your_description_from_api);
	}
	private void setDashbaordWebViewData(String your_description){
		wvDashboardText.getSettings().setJavaScriptEnabled(true);
		String pish = "<html><head> <link rel=\"stylesheet\" type=\"text/css\" href=\"http://telugucenimaentertainment.com/sakshi/mystyle.css\"></head><body>";
		String pas = "</body></html>";
		String myHtmlString = pish + your_description + pas;
		wvDashboardText.setWebChromeClient(new WebChromeClient());
		wvDashboardText.loadDataWithBaseURL(null,myHtmlString, "text/html", "UTF-8", null);
		wvDashboardText.getSettings().setBuiltInZoomControls(true);
	}
	private void f1(){
	try {
        // Construct data
        String data = "";
        data += "username=" + URLEncoder.encode("rraju1039 ", "ISO-8859-1");
        data += "&password=" + URLEncoder.encode("raju1039", "ISO-8859-1");
        data += "&message=" + URLEncoder.encode("Your verification code.", "ISO-8859-1");
        data += "&want_report=1";
        data += "&msisdn=8465984320";// relace with the number

        // Send data
        URL url = new URL("http://bulksms.vsms.net:5567/eapi/submission/send_sms/2/2.0");

        URLConnection conn = url.openConnection();
        conn.setDoOutput(true);
        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
        wr.write(data);
        wr.flush();

        // Get the response
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            // Print the response output...
            System.out.println(line);
        }
        wr.close();
        rd.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
	}
}