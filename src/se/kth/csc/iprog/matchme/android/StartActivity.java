package se.kth.csc.iprog.matchme.android;

import se.kth.csc.iprog.matchme.android.view.StartView;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;

public class StartActivity extends Activity {

	private AudioManager mAudioManager;
	private boolean mPhoneIsSilent;
	private MediaPlayer mPlay;
	private ImageView mScanner;
	private ImageView mScanner_1;
	private Animation mAnimation;
	private Animation mAnimation_1;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// Default call to load previous state
		super.onCreate(savedInstanceState);

		//Play sound
		mPlay = MatchMeApplication.getMediaPlayer(this, R.raw.audio_file);
		//mPlay = MediaPlayer.create(this, R.raw.audio_file);

		// Set the view for the main activity screen
		// it must come before any call to findViewById method
		setContentView(R.layout.start_view);

		// get info for audio manager
		mAudioManager = (AudioManager)getSystemService(AUDIO_SERVICE);
		checkIfPhoneIsSilent();

		mScanner = (ImageView)findViewById(R.id.animate_bubble);
		mScanner.setVisibility(View.VISIBLE);
		mAnimation = new TranslateAnimation(0, 0, 200, 0);
		mAnimation.setDuration(4000);
		mAnimation.setStartOffset(100);
		//mAnimation.setFillAfter(true);
		mAnimation.setRepeatCount(20);
		mAnimation.setRepeatMode(Animation.INFINITE);
		mScanner.setAnimation(mAnimation);
		mScanner.setVisibility(View.VISIBLE);


		mScanner_1 = (ImageView)findViewById(R.id.animate_bubble_1);
		mScanner_1.setVisibility(View.VISIBLE);
		mAnimation_1 = new TranslateAnimation(0, 0, 400, 0);
		mAnimation_1.setDuration(7000);
		mAnimation_1.setStartOffset(8000);
		//mAnimation_1.setFillAfter(true);
		mAnimation_1.setRepeatCount(20);
		mAnimation_1.setRepeatMode(Animation.INFINITE);
		mScanner_1.setAnimation(mAnimation);
		mScanner_1.setVisibility(View.VISIBLE);
		
		// Creating the view class instance
		StartView startView = new StartView(findViewById(R.id.start_view));

		//Buttons in start_view
		Button startButton = (Button) findViewById(R.id.start_game);
		startButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// Move to the next view!
				Intent i = new Intent(StartActivity.this, LevelActivity.class);
				startActivity(i);
			}
		});


		Button toggleSoundBtn = (Button) findViewById(R.id.volume);
		toggleSoundBtn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				if (mPhoneIsSilent) {
					mPlay.start();
					mPhoneIsSilent = false;
				} else {
					mPlay.pause();
					mPhoneIsSilent = true;
				}
				toggleUI();
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
		checkIfPhoneIsSilent();
		toggleUI();
	}

	private void checkIfPhoneIsSilent() {
		int ringerMode = mAudioManager.getRingerMode();
		if (ringerMode == AudioManager.RINGER_MODE_SILENT) {
			mPhoneIsSilent = true;
			mPlay.pause();
		} else {
			mPhoneIsSilent = false;
			mPlay.start();
		}
	}

	@Override
	public void onPause() {
		mPlay.pause();
		super.onPause();
	}

	// Toggles the UI images from silent to normal and vice-versa
	private void toggleUI() {
		Button imageView = (Button) findViewById(R.id.volume);
		Drawable soundIcon;

		if (mPhoneIsSilent) {
			soundIcon = getResources().getDrawable(R.drawable.volume_off);
		} else {
			soundIcon = getResources().getDrawable(R.drawable.volume_on);
		}
		//Using deprecated method to support minSDKlevel below 16. Maybe change minSDKlevel in manifest and use setBackground instead?
		imageView.setBackgroundDrawable(soundIcon);

	}

	@Override
	protected void onDestroy() {
		mPlay.release();
		super.onDestroy();
	}

}
