package se.kth.csc.iprog.matchme.android;

import se.kth.csc.iprog.matchme.model.Level;
import se.kth.csc.iprog.matchme.model.MatchModel;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class EndActivity extends Activity {
	private Level model_level;
	private MatchModel model;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_end_view);

	//	Intent intent = getIntent();
	//	final String level = intent.getStringExtra("level_value");
	//	final int WinTime = intent.getIntExtra("Win_Time", 0);

		final String lost = (getResources().getString(R.string.end_msg_lose));
		final String win = (getResources().getString(R.string.end_msg_won));

		//int levelValue = Integer.parseInt(level);

		model = ((MatchMeApplication) this.getApplication()).getModel();
		model_level = ((MatchMeApplication) this.getApplication()).getLevel();
		

		//TODO: Score algorithm
		int baseScoreValue = 100;
		TextView scorevalue = (TextView) findViewById(R.id.scorevalue);
		int score = (int) (baseScoreValue * (model.getTimeLeft()/1000) * model.getCurrentLevel()) ; 
		String strI = String.valueOf(score);
		scorevalue.setText(strI);

		if (score==0){
			TextView game_end_msg = (TextView)findViewById(R.id.game_end_msg);
			game_end_msg.setText(lost);
		}
		else{
			TextView game_end_msg = (TextView)findViewById(R.id.game_end_msg);
			game_end_msg.setText(win);
		}


		//
		//		if (score > 0){			
		//			int best_Score = score;
		//			TextView bestScore = (TextView) findViewById(R.id.bestscore);
		//			String best_score = String.valueOf(best_Score);
		//			bestScore.setText(best_score);
		//			
		//		}
		//		

		// Home Button
		Button backBtn = (Button) findViewById(R.id.back_btn);
		backBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(EndActivity.this, StartActivity.class);
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); //Clear the activity stack and start from the StartActivity.
				startActivity(i);
			}
		}); 



		// Restart Button
		Button playbtn = (Button) findViewById(R.id.play_btn);
		playbtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				System.err.println(model_level.getStatus());
				if (model_level.getStatus() == false)
				{
					model.setCurrentLevel(model.getCurrentLevel()+1);
					//Move to the next view!
					Intent i = new Intent(EndActivity.this, GameActivity.class);
//					i.putExtra("resumeTime", 30000);	// reset timer
//					i.putExtra("level_value", level+1);
					startActivity(i);
					finish(); // finish current activity 	
				}
				
				else{
					model.setCurrentLevel(model.getCurrentLevel());
					
//					int level = model.getCurrentLevel();
//					//Move to the next view!
					Intent i = new Intent(EndActivity.this, GameActivity.class);
//					i.putExtra("resumeTime", 30000);	// reset timer
//					i.putExtra("level_value", level);
					startActivity(i);
					finish(); // finish current activity 	
				}
				
			}
		});
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
}
