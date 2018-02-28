package android.te41;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	//1.�����Ա����
	Button bt1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//����activity...
		System.out.println("1:create...");
		
		//2.��ʼ��
		init();
		
		//3.ע�ᶨ���¼�������
		bt1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(MainActivity.this,BActivity.class);
				 
				startActivityForResult(intent, 101);
				//���ﲻ��Ҫ����� ֱ��start�Ϳ����˰� ������������û��Ү �Ǿͼ�һ��hello��һ�¿�
			
				//startActivity(intent);
			}
		});
	}

	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if(requestCode==101&&resultCode==102) {
			Toast.makeText(getApplicationContext(), "hello", Toast.LENGTH_LONG).show();
			
		}
		
		
		super.onActivityResult(requestCode, resultCode, data);
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
	
	
	//------------------------һ��ѻص�����----------------------------

	
	

	

	//��ʼ��
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		System.out.println("2:Start...");
		super.onStart();
	}
	
	//��ʾ�ɹ���
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		System.out.println("3:������ʾ�ɹ�...");
		super.onResume();
	}

	//����BActivity֮��MainActivity�ͱ��ڵ�����
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		System.out.println("4:����������ڵ�...");
		super.onPause();
	}
	
	//���ڵ�֮��Ȼ�Ϳ�������
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		System.out.println("5:��������...");
		super.onStop();
	}
	
	//��B�Ǳߵķ���֮���������������� Ȼ��ͼ�����onstart��ʼ�ˣ�
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		System.out.println("6:��������...");
		super.onRestart();
	}
	
	//���ﰴ����ֱ�����������ˣ�û�е���һ��
		@Override
		protected void onDestroy() {
			// TODO Auto-generated method stub
			System.out.println("7:ɾ����...");
			super.onDestroy();
		}

		
		

		

}
