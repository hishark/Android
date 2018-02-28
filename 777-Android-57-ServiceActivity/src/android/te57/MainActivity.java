package android.te57;

import android.os.Bundle;
import android.os.IBinder;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.Menu;
import android.view.View;
import android.widget.*;
import android.widget.ProgressBar;

public class MainActivity extends Activity {

	//1.定义成员变量
	ProgressBar pb;
	Button btStart;
	DownloadService ds;
	int progress=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
	
		//2.初始化
		init();
		
		//3.通过bindService启动服务
		Intent intent=new Intent(MainActivity.this,DownloadService.class);
		bindService(intent, conn, BIND_AUTO_CREATE);

		btStart.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				//5.调用DownloadService中的download方法来模拟下载
				ds.download();
				
				//6.开始监听
				//这里也要每隔一秒监听
				listenProgress();
				
				//7.在androidmanifest中配置service
			}
		});
	}
	
	/*
	 * 监听下载进度，实际上是每隔一秒就去调用DownloadService里的getprogress()方法
	 * 获得当前进度值，并更新UI
	 */
	//每隔一秒来获取当前的progress值
	protected void listenProgress() {
		// TODO Auto-generated method stub
		new Thread() {
			public void run() {
				while(progress<DownloadService.MAX_PROGRESS) {
					progress=ds.getProgress();
					pb.setProgress(progress);
					
					//更新一次等一秒 睡一秒
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}.start();
	}

	//4.通过这个拿到正在运行的service对象
	ServiceConnection conn=new ServiceConnection() {
		
		
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			
		}
		
		//想让访问者和service进行交互就要用这个方法来拿到service对象！
		//Myservice的onbind的方法返回的对象就传给这个方法的2参！！
		//就可以得到已经运行的service的对象
		//=========这个是重点===========
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			//这个service参数是IBinder类型 
			//所以要进行强制类型转换
			
			//得到正在运行的service对象
			ds=((DownloadService.DownloadBinder)service).getService();
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
