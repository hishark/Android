package jxnu.edu.cn.x3321;

import android.os.Bundle;
import android.os.IBinder;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends Activity {
	//1定义变量
	ProgressBar pb;
	Button btStart;
	DownLoadService ds;
	int progress=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//2.初始化
		init();
		//3.通过bindService启动服务
		Intent intent=new Intent(MainActivity.this,DownLoadService.class);
		bindService(intent, conn, BIND_AUTO_CREATE);
		
        btStart.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//5.调用DownLoadService中的downLoad()方法下载数据
				ds.downLoad();
				
				//6.在androidMainfest.xml配置service

			}
		});
	}
	
	ServiceConnection conn=new ServiceConnection(){

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			//得到DownLoadService对象
			ds=((DownLoadService.DownLoadServiceBinder)service).getDownLoadService();
			//注册回调接口来下载进度的变化
			ds.setOnProgressListener(new OnProgessListener(){

				@Override
				public void onProgress(int progress) {
					// TODO Auto-generated method stub
					pb.setProgress(progress);
				}
				
			});
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			
		}
		
	};
	
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

}
