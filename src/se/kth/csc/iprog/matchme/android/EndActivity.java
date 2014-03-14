package se.kth.csc.iprog.matchme.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class EndActivity extends Activity {
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_end_view);
        
        //TODO: Add code for switching screens and starting a view and a controller.
         
     
        Button backbtn = (Button) findViewById(R.id.back_btn);
        backbtn.setOnClickListener(new OnClickListener() {
      			@Override
      			public void onClick(View arg0) {
      				//Move to the next view!
      				Intent i = new Intent(EndActivity.this, StartActivity.class);
      				startActivity(i);
      			}
      		});  
        
        Button playbtn = (Button) findViewById(R.id.play_btn);
        playbtn.setOnClickListener(new OnClickListener() {
      			@Override
      			public void onClick(View arg0) {
      				//Move to the next view!
      				Intent i = new Intent(EndActivity.this, GameActivity.class);
      				startActivity(i);
      			}
      		});  
        
    }
}
