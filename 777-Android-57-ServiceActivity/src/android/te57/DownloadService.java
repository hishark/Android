package android.te57;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class DownloadService extends Service {

	//进度条最大值为100，全局变量，任何类都可以使用（类名.MAX_PROGRESS）
	public static final int MAX_PROGRESS=100;
	
	//表示当前的进度条的值，每秒增加5
	private int progress=0;
	
	/*
	 * 返回下载进度值
	 * 供调用者（Activity）每隔一秒调用这个方法得到当前的progress值
	 */
	public int getProgress() {
		return progress;
	}
	
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
	
	//把正在运行的service传给activity
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return new DownloadBinder();
	}
	
	//定义一个内部类
	public class DownloadBinder extends Binder{
		public DownloadService getService() {
			return DownloadService.this;
		}
	}

}
