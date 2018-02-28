package android.te38;

import android.os.Bundle;
import android.te37.R;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {

	//1.定义成员变量
	Button bt1,bt2;
	
	static String SECONDACTIVITY="android.te38.SA";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//2.初始化
		init();
		
		//3.定义注册事件监听器
		bt1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				//不管显式隐式都要定义intent
				
				intent.setAction("android.te38.SA");
				//隐式的指定想要启动的组件！
				startActivity(intent);
				//finish();
				
				//大多数情况这么写（要记得定义一个static的String变量）
				//intent.setAction(SECONDACTIVITY);
				//startActivity(intent);
				//finish();
			}
		});
		 
	}

	private void init() {
		// TODO Auto-generated method stub
		bt1=(Button)this.findViewById(R.id.bt1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
