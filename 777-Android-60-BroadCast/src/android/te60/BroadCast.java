package android.te60;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class BroadCast extends Service {

	boolean flag=true;
	
	//1.ΪҪ���͵Ĺ㲥����һ����ݱ�ǣ�ֱ����intentʵ������ʱ��дҲ����
	static final String MSG="jxnu.edu.cn.x3321.Message";
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		//ÿ��һ����һ�����������õ��߳�thread
		new Thread() {
			public void run() {
				while(flag) {
					
					
					
					try {
						//2.ÿ��һ���Ҫ�㲥����Ϣͨ��intent�㲥��ȥ
						//���ж�����MSG��Ψһ��ʶjxnu.edu.cn.x3321.Message���㲥���˶��ܵõ����intent
						Intent intent=new Intent(MSG);
						//�����intent�д���һ��keyΪmsg����Ϣ��
						//����������㲥�Ķ����߶����Եõ������Ϣ��
						intent.putExtra("msg", "BroadcasrMsg");
						 
						//���͹㲥~
						sendBroadcast(intent);
						
						
						/*Intent intent2=new Intent(MSG);
						intent2.putExtra("msg2", "BroadcasrMsg2");
						sendBroadcast(intent2);*/
						
						Thread.sleep(1000);
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					//3.��manifest��ע��service������ 
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
