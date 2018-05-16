package com.example.mobilehooker1;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class QuitFragment extends Fragment implements OnClickListener {
	Button b1;
	public QuitFragment(){
		
	}
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.quit1, container, false);
		 b1=(Button)rootView.findViewById(R.id.button1);
		 b1.setOnClickListener(this);
		return rootView;
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		getActivity().finish();
		System.exit(0);
	}


}
