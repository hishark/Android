package android.te38;

import android.os.Bundle;
import android.te37.R;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {

	//1.�����Ա����
	Button bt1,bt2;
	
	static String SECONDACTIVITY="android.te38.SA";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//2.��ʼ��
		init();
		
		//3.����ע���¼�������
		bt1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				//������ʽ��ʽ��Ҫ����intent
				
				intent.setAction("android.te38.SA");
				//��ʽ��ָ����Ҫ�����������
				startActivity(intent);
				//finish();
				
				//����������ôд��Ҫ�ǵö���һ��static��String������
				//intent.setAction(SECONDACTIVITY);
				//startActivity(intent);
				//finish();
			}
		});
		 
	}

	private void init() {
		// TODO Auto-generated method stub
		bt1=(Button)this.findViewById(R.id.bt1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
