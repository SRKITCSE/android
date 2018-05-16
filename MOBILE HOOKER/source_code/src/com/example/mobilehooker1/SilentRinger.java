package com.example.mobilehooker1;
import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SilentRinger extends  Activity implements OnClickListener  {
	/** Called when the activity is first created. */
	Button b1;
	MediaPlayer player;
	AudioManager am;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stop);
   	
        player = MediaPlayer.create(this,R.raw.music);
		 am=(AudioManager) getSystemService(AUDIO_SERVICE);
		 am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
	 	am.setStreamVolume(AudioManager.STREAM_MUSIC,am.getStreamMaxVolume(AudioManager.STREAM_MUSIC),0);
	 	player.setLooping(true);
	 	player.start();
	 	this.setContentView(R.layout.stop);
		b1=(Button)findViewById(R.id.button1);
		b1.setOnClickListener(this);
       
    }
    
	
	

		
	
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if(arg0==b1)
		{
			player.stop();
			finish();
		}
		
	}

}
