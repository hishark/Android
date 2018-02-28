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
		 * ��׿�豸�յ���SMS����pdu��protocol description unit��Э���װ��
		 * 
		 */
		//�õ��������е���Ϣ��Ӧ���ǣ�
		Object[] bs=(Object[])intent.getExtras().get("pdus");
		
		//ȡ�������յ�����Ϣ��Ӧ���ǣ�
		Object pdu=bs[0];
		
		//��pduת�����ֽ���Ȼ�󴴽���SmsMessage����
		SmsMessage sms=SmsMessage.createFromPdu((byte[])pdu);
		
		//�õ���Ϣ�ĵ�ַ�Լ�����
		String address=sms.getOriginatingAddress();
		String body=sms.getMessageBody();
		
		//���SMS�а���һЩ���е���Ϣ�������ֹͣ��Ϣ�㲥
		//Android 4.0֮����ֹ��������������ʵ����Ϣ����Ҫ�������ķ������Ͳ����ˣ�
		if(body.contains("sb")) {
			System.out.println("�յ�һ��������Ϣ");
			//��ֹ���չ㲥
			abortBroadcast();
		}else {
			System.out.println("address:"+address+"\n"+"body:"+body);
		}
	}
	//��manifesr.xml�����ӷ���sms��Ȩ�ޣ�����Ҫע�ᶩ��sms�㲥

}
