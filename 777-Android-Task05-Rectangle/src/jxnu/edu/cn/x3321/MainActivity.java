package jxnu.edu.cn.x3321;

import android.os.Bundle;
import android.app.Activity;
import android.content.res.Configuration;
import android.view.Menu;
import android.widget.TextView;
import android.view.ViewGroup.LayoutParams;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig){
		super.onConfigurationChanged(newConfig);
		//newConfig.orientation获得当前屏幕状态是横向或者是竖向
		if(newConfig.orientation==Configuration.ORIENTATION_PORTRAIT){
			TextView tv = (TextView)findViewById(R.id.tv);
			LayoutParams p=tv.getLayoutParams();
			int w1,h1;
			w1=p.width;
			h1=p.height;
			p.height=w1;
			p.width=h1;
			tv.setLayoutParams(p);
		}
		if(newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE){
			TextView tv = (TextView)findViewById(R.id.tv);
			LayoutParams p=tv.getLayoutParams();
			int w1,h1;
			w1=p.width;
			h1=p.height;
			p.height=w1;
			p.width=h1;
			tv.setLayoutParams(p);
		}
		
	}
	
	 

}
