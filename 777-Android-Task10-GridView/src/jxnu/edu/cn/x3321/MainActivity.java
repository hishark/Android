package jxnu.edu.cn.x3321;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.GridView;

public class MainActivity extends Activity {

	
	//1.
	GridView gv;
	
	int []number=new int[] {
			1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//2.
		init();
		//3.
		MyAdapter ma=new MyAdapter(getApplicationContext(),number);
		//4.
		gv.setAdapter(ma);
	}

	private void init() {
		// TODO Auto-generated method stub
		gv=(GridView)this.findViewById(R.id.gv);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
