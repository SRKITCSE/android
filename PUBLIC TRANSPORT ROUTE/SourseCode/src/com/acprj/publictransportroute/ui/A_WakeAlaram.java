package com.acprj.publictransportroute.ui;

import java.io.IOException;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import com.acprj.publictransportroute.R;

public class A_WakeAlaram extends Activity {
	Button btnSetAlarm,btnCancelAlarm;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a_set_alarm);
		//playSound();
	}
	MediaPlayer mediaPlayer;
	private void playSound(){
		Uri defaultRingtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

		MediaPlayer mediaPlayer = new MediaPlayer();

		try {
		      mediaPlayer.setDataSource(A_WakeAlaram.this, defaultRingtoneUri);
		      mediaPlayer.setAudioStreamType(AudioManager.STREAM_NOTIFICATION);
		      mediaPlayer.prepare();
		      mediaPlayer.setOnCompletionListener(new OnCompletionListener() {
		         @Override
		         public void onCompletion(MediaPlayer mp)
		         {
		            mp.release();
		         }
		      });
		  mediaPlayer.start();
		} catch (IllegalArgumentException e) {
		 e.printStackTrace();
		} catch (SecurityException e) {
		 e.printStackTrace();
		} catch (IllegalStateException e) {
		 e.printStackTrace();
		} catch (IOException e) {
		 e.printStackTrace();
		}
	}
}
