package deal.with.it;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class WebActivity extends Activity implements OnClickListener {

	private TextView mWebText;
	private Button mButton;
	
	@Override
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		initLayout();
	}
	
	private void initLayout() {
		setContentView(R.layout.web_layout);
		
		mWebText = (TextView)findViewById(R.id.web_response);
		mButton = (Button)findViewById(R.id.connect_button);
		
		mButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.connect_button :
			Log.e("on press", "press");
			WebConnection connection = new WebConnection();
			String string = connection.readFromServer();
			if(string != null) {
				mWebText.setText(string);
				mWebText.setTextColor(getResources().getColor(R.color.white));
			}
			break;
		}
	}
}
