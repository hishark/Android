package android.te56;

import android.os.Bundle;
import android.os.IBinder;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {

	//1.�����Ա����
	Button btStart,btStop;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		//2.��ʼ��
		init();
		
		//3.����ע���¼�������
		btStart.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//��һ��������ʽ(�������ֶ�Ҫ��intent����)
				Intent intent=new Intent(MainActivity.this,MyService.class);
				
				//��һ��������ʽ
				//startService(intent);
				
				//�ڶ���������ʽ
				//2�� ServiceConnection��������
				//3�� ϵͳ�Զ��� һ�㶼�����
				//����������ʽ�����ܰ󶨵ĳɹ����Ҫ���߷�����
				//service��״̬Ҫͨ��ĳ���������߷����ߣ�ͨ��ɶ��ͨ��2��conn��һ��������ʵ��
				//��ôservice�ͷ�����֮�����ͨ���أ���serviceҪ�õ�������Ķ��� ���ǵ�ͨ��2��
				//�ܶ���֮�������߿���ͨ��2���е�һ�������õ�service����
				bindService(intent,conn,BIND_AUTO_CREATE);
				
				
				//======��manifest.xml������service
			}
		});
		
		btStop.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(MainActivity.this,MyService.class);
				
				//��һ��ֹͣ��ʽ
				//stopService(intent);
			
			
				//�ڶ���ֹͣ��ʽ
				//��� ȡ����������emmm
				unbindService(conn);
			}
		});
	}

	//�ڲ���
	private ServiceConnection conn=new ServiceConnection() {

		//���÷����ߺ�service���н�����Ҫ������������õ�service����
		//Myservice��onbind�ķ������صĶ���ʹ������������2�Σ���
		//�Ϳ��Եõ��Ѿ����е�service�Ķ���
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			
		}
		
	};
	
	private void init() {
		// TODO Auto-generated method stub
		btStart=(Button)this.findViewById(R.id.btStart);
		btStop=(Button)this.findViewById(R.id.btStop);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
