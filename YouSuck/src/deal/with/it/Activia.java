package deal.with.it;

import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Activia extends Activity implements OnClickListener {
	
	private Button mButton;
	private ImageView mImage;
	private EditText mEditText;
	private Button mChangeScreenButton;
	
	private HashMap<String, Integer> mCharMap;
	
	@Override
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		initializeLayout();
		populateHashMap();
	}
	
	private void populateHashMap() {
		mCharMap = new HashMap<String, Integer>();
		mCharMap.put("bugsbunny", R.drawable.bugs);
		mCharMap.put("clippy", R.drawable.clippy);
		mCharMap.put("duder", R.drawable.duder);
		mCharMap.put("cheesemouse", R.drawable.chuckecheese);
	}

	private void initializeLayout() {
		setContentView(R.layout.ugly_layout);
		mButton = (Button)findViewById(R.id.button);
		mImage = (ImageView)findViewById(R.id.image_view);
		mEditText = (EditText)findViewById(R.id.edit_text);
		mChangeScreenButton = (Button)findViewById(R.id.switch_screen_button);
		
		mButton.setOnClickListener(this);
		mChangeScreenButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.button :
			String enteredValue = mEditText.getText().toString();
			enteredValue = enteredValue.toLowerCase().replace(" ", "");
			
			if(enteredValue.length() == 0) {
				Toast.makeText(this, "You need to enter something, learn the lore nerdo.", Toast.LENGTH_SHORT).show();
			}
			
			Integer drawableId = mCharMap.get(enteredValue);
			if(drawableId != null) {
				mImage.setImageDrawable(getResources().getDrawable(drawableId));
			}
			else {
				Toast.makeText(this, "Sorry! The character you entered is not supported.", Toast.LENGTH_SHORT).show();
			}
			mEditText.setText("");
			break;
		case R.id.switch_screen_button :
			Intent intent = new Intent(this, WebActivity.class);
			startActivity(intent);
			break;
		}
	}

}
