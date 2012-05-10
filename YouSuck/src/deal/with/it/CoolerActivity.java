package deal.with.it;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class CoolerActivity extends Activity implements OnClickListener {
	
	private ImageButton mPlayerButton;
	private TextView mTextView;
	
	private MediaPlayer mPlayer;
	
	private ArrayList<Integer> mColorList = new ArrayList<Integer>();
	private Random mRandom = new Random();
	
	@Override
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		initLayout();
		mPlayer = MediaPlayer.create(this, R.raw.onemoretime);
		populateColorList();
	}
	
	private void populateColorList() {
		mColorList.add(R.color.white);
		mColorList.add(R.color.green);
		mColorList.add(R.color.sky_blue);
		mColorList.add(R.color.yellow);
		mColorList.add(R.color.red);
	}
	
	private void initLayout() {
		setContentView(R.layout.new_layout);
		
		mTextView = (TextView)findViewById(R.id.holy_shit_textview);
		
		Button button = (Button)findViewById(R.id.back_button);
		button.setOnClickListener(this);
		
		mPlayerButton = (ImageButton)findViewById(R.id.player_button);
		mPlayerButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.back_button :
			mPlayer.stop();
			finish();
			break;
		case R.id.player_button :
			if(!mPlayer.isPlaying()) {
				mPlayerButton.setImageDrawable(this.getResources().
						getDrawable(android.R.drawable.ic_media_pause));
				
				mPlayer.start();
				PlayerThread thread = new PlayerThread();
				thread.execute();
			}
			else {
				mPlayerButton.setImageDrawable(this.getResources().
						getDrawable(android.R.drawable.ic_media_play));
				
				mPlayer.pause();
			}
			break;
		}
	}
	
	private class PlayerThread extends AsyncTask<Void, Void, Void> {
		
		private int mIndex = 0;

		@Override
		protected Void doInBackground(Void... params) {
			Timer timer = new Timer();
			timer.schedule(new Publishment(), 200, 200);
			while(mPlayer.isPlaying()) { }
			timer.cancel();
			return null;
		}
		
		@Override
		protected void onProgressUpdate(Void... v) {
			int index = mRandom.nextInt(mColorList.size());
			while(index == mIndex) {
				index = mRandom.nextInt(mColorList.size());
			}
			mIndex = index;
			Integer i = mColorList.get(mIndex);
			mTextView.setTextColor(getApplicationContext().getResources().getColor(i));
		}
		
		private class Publishment extends TimerTask {

			@Override
			public void run() {
				publishProgress();
			}
			
		}
		
	}
	
	
	@Override
	public void onStop() {
		super.onStop();
		mPlayer.stop();
	}
	
}
