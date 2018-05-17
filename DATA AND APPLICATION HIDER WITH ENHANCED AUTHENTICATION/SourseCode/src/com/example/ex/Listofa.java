package com.example.ex;

import java.util.ArrayList;
import java.util.List;

import com.example.ex.*;
import com.example.ex.R;
import com.example.ex.ApkAdapter;
import com.example.ex.AppData;
import com.example.ex.*;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Listofa extends Activity 
			implements OnItemClickListener {

	PackageManager packageManager;
	ListView apkList;
	View v;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try{
		setContentView(R.layout.listofapps1);

		packageManager = getPackageManager();
		List<PackageInfo> packageList = packageManager
				.getInstalledPackages(PackageManager.GET_PERMISSIONS);

		List<PackageInfo> packageList1 = new ArrayList<PackageInfo>();
		
		/*To filter out System apps*/
		for(PackageInfo pi : packageList) {
			boolean b = isSystemPackage(pi);
			if(!b) {
				packageList1.add(pi);
			}
		}
		apkList = (ListView) findViewById(R.id.applist);
		apkList.setAdapter(new ApkAdapter(this, packageList1, packageManager));

		apkList.setOnItemClickListener(this);
}
catch(Exception e)
{
	Toast.makeText(getApplicationContext(), "error::::"+e.toString(), Toast.LENGTH_LONG).show();
}
	}

       /**
	 * Return whether the given PackgeInfo represents a system package or not.
	 * User-installed packages (Market or otherwise) should not be denoted as
	 * system packages.
	 * 
	 * @param pkgInfo
	 * @return boolean
	 */
	private boolean isSystemPackage(PackageInfo pkgInfo) {
	    return ((pkgInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) ? true
	            : false;
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long row) {
		
		PackageInfo packageInfo = (PackageInfo) parent
				.getItemAtPosition(position);
		v=view;
		Toast.makeText(getApplicationContext(),"pack" +((TextView)view).getText().toString(),Toast.LENGTH_LONG).show();
		AppData appData = new AppData();
		appData.setPackageInfo(packageInfo);	
		AlertDialog alertDialog=new AlertDialog.Builder(this).create();
		alertDialog.setMessage("Are you sure to hide");
		alertDialog.setButton("Yes", new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog,int which){
				PackageManager p = getPackageManager();
				
				 String destinationPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/com.example.ex";

			ComponentName componentName = new ComponentName(getApplicationContext(),"com.example.ex.MainActivity.class"); // activity which is first time open in manifiest file which is declare as <category android:name="android.intent.category.LAUNCHER" />
			p.setComponentEnabledSetting(componentName,PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
			}
			});
		alertDialog.setButton2("No", new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog , int which){
				PackageManager p = getPackageManager();
				

			ComponentName componentName = new ComponentName(getApplicationContext(),com.example.ex.MainActivity.class); // activity which is first time open in manifiest file which is declare as <category android:name="android.intent.category.LAUNCHER" />
			p.setComponentEnabledSetting(componentName,PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
		
			}
		});
alertDialog.show();

			}
		
}
