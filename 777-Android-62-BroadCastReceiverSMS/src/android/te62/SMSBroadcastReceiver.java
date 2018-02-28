package android.te62;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.gsm.SmsMessage;

public class SMSBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		/*
		 * 安卓设备收到的SMS是以pdu（protocol description unit）协议封装的
		 * 
		 */
		//拿到本地所有的信息（应该是）
		Object[] bs=(Object[])intent.getExtras().get("pdus");
		
		//取到最新收到的信息（应该是）
		Object pdu=bs[0];
		
		//把pdu转换成字节流然后创建出SmsMessage对象
		SmsMessage sms=SmsMessage.createFromPdu((byte[])pdu);
		
		//得到信息的地址以及内容
		String address=sms.getOriginatingAddress();
		String body=sms.getMessageBody();
		
		//如果SMS中包含一些敏感的信息，则可以停止信息广播
		//Android 4.0之后终止了这个！如果还想实现信息拦截要用其他的方法！就不做了！
		if(body.contains("sb")) {
			System.out.println("收到一条敏感信息");
			//终止接收广播
			abortBroadcast();
		}else {
			System.out.println("address:"+address+"\n"+"body:"+body);
		}
	}
	//在manifesr.xml里增加访问sms的权限，并且要注册订阅sms广播

}
