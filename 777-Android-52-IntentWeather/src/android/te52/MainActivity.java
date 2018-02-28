package android.te52;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.*;

public class MainActivity extends Activity {

	//1.
	EditText etCity;
	Button btSearch;
	ImageView img1,img2;
	TextView tv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//2.
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
