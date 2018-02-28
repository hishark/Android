package android.te35;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;

public class MainActivity extends Activity {

	//1.定义成员变量
	TextView tv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//2.初始化
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		tv=(TextView)this.findViewById(R.id.tv);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		menu.add("cd1");
		menu.add("cd2");
		menu.add("cd3");
		menu.add(0,1,0,"cd4");
		
		getMenuInflater().inflate(R.menu.main, menu);
		//这句用来显示settings
		return true;
	}

	
	
	//Option选项响应的事件
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		String result="你选择的是：";
		if(item.getTitle().equals("cd1")) {
			result+=item.getTitle();
		}else if(item.getTitle().equals("cd2")) {
			result+=item.getTitle();
		}else if(item.getTitle().equals("cd3")) {
			result+=item.getTitle();
		}else if(item.getTitle().equals("cd4")) {
			result+=item.getTitle();
		}
		
		tv.setText(result);
		return super.onOptionsItemSelected(item);
	}
	
	

}
