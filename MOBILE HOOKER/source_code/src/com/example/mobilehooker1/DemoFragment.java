package com.example.mobilehooker1;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class DemoFragment extends Fragment implements OnClickListener{
	ImageButton img1,img2,img3,img4,img5,img6;
	String s1,s2,s3,s4,s5,s6;
	Intent i;
	//Bundle bundle=new Bundle();
	public DemoFragment() {
		//i=new Intent(getActivity(),Demo1.class);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.four, container, false);
		img1=(ImageButton)rootView.findViewById(R.id.imageButton1);
		img2=(ImageButton)rootView.findViewById(R.id.imageButton2);
		img3=(ImageButton)rootView.findViewById(R.id.imageButton3);
		img4=(ImageButton)rootView.findViewById(R.id.imageButton4);
		img5=(ImageButton)rootView.findViewById(R.id.imageButton5);
		img6=(ImageButton)rootView.findViewById(R.id.imageButton6);
		img1.setOnClickListener(this);
		img2.setOnClickListener(this);
		img3.setOnClickListener(this);
		img4.setOnClickListener(this);
		img5.setOnClickListener(this);
		img6.setOnClickListener(this);
		return rootView;
	}

	@Override
	public void onClick(View v) {
		
		// TODO Auto-generated method stub
		if(v==img1){
			//img1.setImageResource(R.drawable.srd);
			//String s1="sr";
			//bundle.putString("Index",s1);
			//Intent i=new Intent(getActivity(),Demo1.class);
			i=new Intent(getActivity(),Demo1.class);
			i.putExtra("i","sr");
			startActivity(i);
			
			
		}
		if(v==img2){
			//String s1="cr";
			//bundle.putString("Index",s1);
			i=new Intent(getActivity(),Demo1.class);
			i.putExtra("i","cr");
			startActivity(i);
//		
			//img1.setImageResource(R.drawable.crd);
		
			}
		if(v==img3){
			//String s1="msg";
			//bundle.putString("Index",s1);
			i=new Intent(getActivity(),Demo1.class);
			i.putExtra("i","msg");
			startActivity(i);
		
			
			//img1.setImageResource(R.drawable.msgingd);
		}
		if(v==img4){
			//startActivity(i);
			//i.putExtra("", true);
			
			//img1.setImageResource(R.drawable.pdd);
			i=new Intent(getActivity(),Demo1.class);
			i.putExtra("i","pd");
			startActivity(i);
		
		}
		if(v==img5){
			//startActivity(i);
			//i.putExtra("msg", true);
			
			//img1.setImageResource(R.drawable.locd);
			i=new Intent(getActivity(),Demo1.class);
			i.putExtra("i","loc");
			startActivity(i);
		
		}
		if(v==img6){
			//startActivity(i);
			//i.putExtra("msg", true);
			
			//img1.setImageResource(R.drawable.tracer);
			i=new Intent(getActivity(),Demo1.class);
			i.putExtra("i","tracer");
			startActivity(i);
		
		}
	}
}
