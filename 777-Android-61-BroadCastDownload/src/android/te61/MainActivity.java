package android.te61;

import android.os.Bundle;
 
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends Activity {

	//1.�����Ա����
		ProgressBar pb;
		Button btStart;
		ProgressReceiver progressreceiver;
		IntentFilter intentfilter;
		
		 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
	
		//2.
		init();
		
		//3.��̬�Ķ��Ĺ㲥
		progressreceiver=new ProgressReceiver();
		intentfilter=new IntentFilter();
		intentfilter.addAction("jxnu.edu.cn.x3321.PROGRESS");
		registerReceiver(progressreceiver, intentfilter);
		
		//�����㲥
		btStart.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//4.�����㲥����������
				Intent intent=new Intent(MainActivity.this,DownLoadBroadCast.class);
				startService(intent);
				
			}
		});
		
	}
	
	
	

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		//ֹͣ�㲥
		Intent intent=new Intent(MainActivity.this,DownLoadBroadCast.class);
		stopService(intent);
		//ע���㲥
		unregisterReceiver(progressreceiver);
		super.onDestroy();
	}




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

	
	//����һ���㲥�����ߵ��ڲ���
	public class ProgressReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context arg0, Intent arg1) {
			// TODO Auto-generated method stub
			//�ӹ㲥�л�õ�ǰ���ȵ�ֵ������UI
			int progress=arg1.getIntExtra("progress", 0);
			pb.setProgress(progress);
			
			//������Ҫ�󶨹㲥�����ߺͽ����� ֮ǰ���˾�̬�� ��manifest��ʵ��
			//�����ö�̬�ķ������а�
			//д��oncreate����
			
		}
		
	}
}
