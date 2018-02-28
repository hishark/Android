package android.te37;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {

	//1.�����Ա����
	Button bt1,bt2;
	
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
				//�����µ���ͼintent��������ѧ��ʽ������
				
				//������ͼ����
				Intent intent=new Intent();
				
				//---------��һ��������ʽ------------
				//ָ����ͼ�����ĸ���� 
				intent.setClass(MainActivity.this, SecondActivity.class);
				//��һ��������ԭ����activity �����ĸ�activity��ʼ����
				//�ڶ�������������������activity��������������Ҫ���ǰ����������ͬһ�����Ϳ���ʡ�԰�����
				//�����������������ֱ�ӵ�
				
				//ִ����ͼintent��Ҫ�������������װ��intent���ˣ�
				startActivity(intent);
				
				//�رյ�ǰactivity ���õ�activity�͹ص� �ͷ���Դ
				finish();
				//������ﲻ��finish�Ļ��ǿ��Է���mainactivity�� 
				//���finish�˾ͷ��ز���mainactivity�� ��Ϊ�Ѿ���ɱ������
				
				
				//----------�ڶ���������ʽ-------------
				//intent.setClassName("android.te37", "android.te37.SecondActivity");
				//��� �����������ǵ���ͬһ�����²��Ӱ���Ҳ���Եģ���ΪɶʧЧ�� �Ǿͼǵ�һ��Ҫ�Ӱ���
				//startActivity(intent);
				
				//Ҫ������������ڵİ��������� �ǵü�˫����
				
				
				//----------������������ʽ----------
				//Intent intent=new Intent(MainActivity.this, SecondActivity.class);
				//ֱ���ù��췽����ָ����Ҫ���������
				//startActivity(intent);
			}
		});
		bt2.setOnClickListener(new View.OnClickListener() {
			//������������µ�Ӧ�ó���
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				
				//ָ����ͼ�����ĸ�Ӧ�ó���
				intent.setClassName("android.te32", "android.te32.MainActivity");
				//��һ�������ǰ���
				//�ڶ�������ע��Ҫ������������·������Ҫ���Ǽ��ϰ�����
				//�������Ҫ��֤�Ѿ����ֻ��ϰ�װ������Ҫ������Ӧ�ó��򣬲�Ȼ�޷��ҵ���Ӧ�ó��򣬷�������
				
				startActivity(intent);
			}
		});
	}

	private void init() {
		// TODO Auto-generated method stub
		bt1=(Button)this.findViewById(R.id.bt1);
		bt2=(Button)this.findViewById(R.id.bt2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
