package android.te61;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class DownLoadBroadCast extends Service {
	//进度条最大值为100，全局变量，任何类都可以使用（类名.MAX_PROGRESS）
	public static final int MAX_PROGRESS=100;
	//表示当前的进度条的值，每秒增加5
	private int progress=0;
	
	/*
	 * 模拟下载任务，每隔一秒更新进度条的值，每秒增加5
	 */
	public void download() {
		
		//每隔一秒！明显用线程！ok！记住了！
		new Thread() {
			
			//更新值
			public void run() {
				while(progress<MAX_PROGRESS) {
					
					progress=progress+5;
					
					//发送广播，把progress广播给订阅者
					//广播的唯一标识：双引号里的参数
					Intent intent=new Intent("jxnu.edu.cn.x3321.PROGRESS");
					intent.putExtra("progress", progress);
					sendBroadcast(intent);
					
					
					
					
					//更新一次等一秒 睡一秒
					//有异常 用trycatch包起来
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

	//在manifest注册service
	

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		download();
		return super.onStartCommand(intent, flags, startId);
	}



	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	 
	

}
