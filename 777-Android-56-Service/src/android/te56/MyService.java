package android.te56;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		System.out.println("=====onCreate()=====");
		super.onCreate();
	}
	

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		System.out.println("=====onStartCommand()=====");
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		System.out.println("=====onBind()=====");
		return null;
	}

	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		System.out.println("=====onUnbind()=====");
		return super.onUnbind(intent);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		System.out.println("=====onDestroy()=====");
		super.onDestroy();
	}
	
}
