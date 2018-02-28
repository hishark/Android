package android.te57;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class DownloadService extends Service {

	//���������ֵΪ100��ȫ�ֱ������κ��඼����ʹ�ã�����.MAX_PROGRESS��
	public static final int MAX_PROGRESS=100;
	
	//��ʾ��ǰ�Ľ�������ֵ��ÿ������5
	private int progress=0;
	
	/*
	 * �������ؽ���ֵ
	 * �������ߣ�Activity��ÿ��һ�������������õ���ǰ��progressֵ
	 */
	public int getProgress() {
		return progress;
	}
	
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
	
	//���������е�service����activity
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return new DownloadBinder();
	}
	
	//����һ���ڲ���
	public class DownloadBinder extends Binder{
		public DownloadService getService() {
			return DownloadService.this;
		}
	}

}
