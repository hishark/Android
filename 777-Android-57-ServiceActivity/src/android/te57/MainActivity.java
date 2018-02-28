package android.te57;

import android.os.Bundle;
import android.os.IBinder;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.Menu;
import android.view.View;
import android.widget.*;
import android.widget.ProgressBar;

public class MainActivity extends Activity {

	//1.�����Ա����
	ProgressBar pb;
	Button btStart;
	DownloadService ds;
	int progress=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
	
		//2.��ʼ��
		init();
		
		//3.ͨ��bindService��������
		Intent intent=new Intent(MainActivity.this,DownloadService.class);
		bindService(intent, conn, BIND_AUTO_CREATE);

		btStart.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				//5.����DownloadService�е�download������ģ������
				ds.download();
				
				//6.��ʼ����
				//����ҲҪÿ��һ�����
				listenProgress();
				
				//7.��androidmanifest������service
			}
		});
	}
	
	/*
	 * �������ؽ��ȣ�ʵ������ÿ��һ���ȥ����DownloadService���getprogress()����
	 * ��õ�ǰ����ֵ��������UI
	 */
	//ÿ��һ������ȡ��ǰ��progressֵ
	protected void listenProgress() {
		// TODO Auto-generated method stub
		new Thread() {
			public void run() {
				while(progress<DownloadService.MAX_PROGRESS) {
					progress=ds.getProgress();
					pb.setProgress(progress);
					
					//����һ�ε�һ�� ˯һ��
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

	//4.ͨ������õ��������е�service����
	ServiceConnection conn=new ServiceConnection() {
		
		
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			
		}
		
		//���÷����ߺ�service���н�����Ҫ������������õ�service����
		//Myservice��onbind�ķ������صĶ���ʹ������������2�Σ���
		//�Ϳ��Եõ��Ѿ����е�service�Ķ���
		//=========������ص�===========
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			//���service������IBinder���� 
			//����Ҫ����ǿ������ת��
			
			//�õ��������е�service����
			ds=((DownloadService.DownloadBinder)service).getService();
		}
	};
	

	private void init() {
		// TODO Auto-generated method stub
		pb=(ProgressBar)this.findViewById(R.id.pb);
		btStart=(Button)this.findViewById(R.id.btStart);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
