package android.te60;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BroadCastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context arg0, Intent arg1) {
		// TODO Auto-generated method stub

		//4.��manifest.xml��Ϊ�㲥�����߶��Ĺ㲥	
		//�����ĵĻ���û�취���յ��㲥�ģ�
		
		String msg=arg1.getExtras().getString("msg");
		//String msg2=arg1.getExtras().getString("msg2");
		//Toast.makeText(arg0, "���ǴӺ�̨service���͵Ĺ㲥��Ϣ"+msg+msg2, Toast.LENGTH_SHORT).show();
	
		System.out.println(msg);
		//System.out.println(msg2);
		
	}

}
