package android.te60;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class BroadCast extends Service {

	boolean flag=true;
	
	//1.为要发送的广播定义一个身份标记，直接在intent实例化的时候写也可以
	static final String MSG="jxnu.edu.cn.x3321.Message";
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		//每隔一秒有一个操作所以用到线程thread
		new Thread() {
			public void run() {
				while(flag) {
					
					
					
					try {
						//2.每隔一秒把要广播的信息通过intent广播出去
						//所有订阅了MSG（唯一标识jxnu.edu.cn.x3321.Message）广播的人都能得到这个intent
						Intent intent=new Intent(MSG);
						//在这个intent中存入一个key为msg的信息！
						//订阅了这个广播的订阅者都可以得到这个信息！
						intent.putExtra("msg", "BroadcasrMsg");
						 
						//发送广播~
						sendBroadcast(intent);
						
						
						/*Intent intent2=new Intent(MSG);
						intent2.putExtra("msg2", "BroadcasrMsg2");
						sendBroadcast(intent2);*/
						
						Thread.sleep(1000);
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					//3.在manifest中注册service！！！ 
				}
			}
		}.start();
		
		
		
		
		
		
		 
	}
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		flag=false;
		super.onDestroy();
	}
	
	
	
	
	

}
