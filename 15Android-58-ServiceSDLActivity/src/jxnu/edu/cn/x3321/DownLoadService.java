package jxnu.edu.cn.x3321;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class DownLoadService extends Service {
	public static final int MAX_PROGRESS=100;//设置进度条最大值100
	private int progress=0; //当前进度条的值，每秒增加5
	private OnProgessListener onProgressListener;//更新进度的回调接口
	
	/*
	 * 注册回调接口的方法，供外部调用
	 */
	public void setOnProgressListener(OnProgessListener onProgressListener) {
		this.onProgressListener = onProgressListener;
	}

	
	
	/*
	 * 模拟下载任务，每隔1秒更新前进度条的值，每秒增加5
	 */
	public void downLoad(){
		new Thread(){
			public void run(){
				while(progress<MAX_PROGRESS){
					progress=progress+5;
					//进度发生改变通知调用者
					if(onProgressListener!=null){
						onProgressListener.onProgress(progress);
					}
					
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

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		
		return new DownLoadServiceBinder();
	}
	
	public class DownLoadServiceBinder extends Binder{
		public DownLoadService getDownLoadService(){
			return DownLoadService.this;
		}
	}

}
