package android.te60;

import android.os.Bundle;
import android.te60.MainActivity;

import android.te60.R;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	//1.定义成员变量
	Button btStart,btStop;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
	
		//2.初始化
		init();
		
		        //3.定义注册事件监听器
				btStart.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						 Intent intent=new Intent(MainActivity.this,BroadCast.class);
						 startService(intent);
						 
						  
					}
				});
				
				btStop.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent=new Intent(MainActivity.this,BroadCast.class);
						 stopService(intent);
					}
				});
	}

	
	private void init() {
		// TODO Auto-generated method stub
		btStart=(Button)this.findViewById(R.id.btStart);
		btStop=(Button)this.findViewById(R.id.btStop);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
