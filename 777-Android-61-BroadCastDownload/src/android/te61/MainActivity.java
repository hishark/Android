package android.te61;

import android.os.Bundle;
 
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends Activity {

	//1.定义成员变量
		ProgressBar pb;
		Button btStart;
		ProgressReceiver progressreceiver;
		IntentFilter intentfilter;
		
		 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
	
		//2.
		init();
		
		//3.动态的订阅广播
		progressreceiver=new ProgressReceiver();
		intentfilter=new IntentFilter();
		intentfilter.addAction("jxnu.edu.cn.x3321.PROGRESS");
		registerReceiver(progressreceiver, intentfilter);
		
		//启动广播
		btStart.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//4.启动广播（启动服务）
				Intent intent=new Intent(MainActivity.this,DownLoadBroadCast.class);
				startService(intent);
				
			}
		});
		
	}
	
	
	

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		//停止广播
		Intent intent=new Intent(MainActivity.this,DownLoadBroadCast.class);
		stopService(intent);
		//注销广播
		unregisterReceiver(progressreceiver);
		super.onDestroy();
	}




	private void init() {
		// TODO Auto-generated method stub
		pb=(ProgressBar)this.findViewById(R.id.pb);
		btStart=(Button)this.findViewById(R.id.btStart);
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
	//定义一个广播接收者的内部类
	public class ProgressReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context arg0, Intent arg1) {
			// TODO Auto-generated method stub
			//从广播中获得当前进度的值并更新UI
			int progress=arg1.getIntExtra("progress", 0);
			pb.setProgress(progress);
			
			//接下来要绑定广播发送者和接收者 之前用了静态绑定 在manifest中实现
			//这里用动态的方法进行绑定
			//写在oncreate里面
			
		}
		
	}
}
