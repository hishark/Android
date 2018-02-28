package android.te61;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class DownLoadBroadCast extends Service {
	//���������ֵΪ100��ȫ�ֱ������κ��඼����ʹ�ã�����.MAX_PROGRESS��
	public static final int MAX_PROGRESS=100;
	//��ʾ��ǰ�Ľ�������ֵ��ÿ������5
	private int progress=0;
	
	/*
	 * ģ����������ÿ��һ����½�������ֵ��ÿ������5
	 */
	public void download() {
		
		//ÿ��һ�룡�������̣߳�ok����ס�ˣ�
		new Thread() {
			
			//����ֵ
			public void run() {
				while(progress<MAX_PROGRESS) {
					
					progress=progress+5;
					
					//���͹㲥����progress�㲥��������
					//�㲥��Ψһ��ʶ��˫������Ĳ���
					Intent intent=new Intent("jxnu.edu.cn.x3321.PROGRESS");
					intent.putExtra("progress", progress);
					sendBroadcast(intent);
					
					
					
					
					//����һ�ε�һ�� ˯һ��
					//���쳣 ��trycatch������
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

	//��manifestע��service
	

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
