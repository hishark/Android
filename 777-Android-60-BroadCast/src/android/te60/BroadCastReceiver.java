package android.te60;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BroadCastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context arg0, Intent arg1) {
		// TODO Auto-generated method stub

		//4.在manifest.xml中为广播接收者订阅广播	
		//不订阅的话是没办法接收到广播的！
		
		String msg=arg1.getExtras().getString("msg");
		//String msg2=arg1.getExtras().getString("msg2");
		//Toast.makeText(arg0, "这是从后台service发送的广播消息"+msg+msg2, Toast.LENGTH_SHORT).show();
	
		System.out.println(msg);
		//System.out.println(msg2);
		
	}

}
