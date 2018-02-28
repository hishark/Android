package jxnu.edu.cn.x3321;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.*;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		//创建线性布局管理器
		LinearLayout ll=new LinearLayout(this);
		ll.setOrientation(1);//0水平 1垂直
		TextView tv=new TextView(this);
		tv.setText("Hello x3321");
		Button bt=new Button(this);
		bt.setText("ok");
		ll.addView(tv);
		ll.addView(bt,200,200);
		setContentView(ll);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
