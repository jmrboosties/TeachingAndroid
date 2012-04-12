package deal.with.it;

import android.app.Activity;
import android.os.Bundle;

public class CoolerActivity extends Activity {

	@Override
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		initLayout();
	}
	
	private void initLayout() {
		setContentView(R.layout.new_layout);
	}
	
}
