package jxnu.edu.cn.x3321.activity;

import jxnu.edu.cn.x3321.R;
import jxnu.edu.cn.x3321.R.layout;
import jxnu.edu.cn.x3321.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class AboutContactActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about_contact);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.about_contact, menu);
		return true;
	}

}
