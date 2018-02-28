package android.te40;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	//1.�����Ա����
	EditText etUsernmae,etPassword;
	Button btOk,btCancel;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//2.��ʼ��
		init();
		
		//3.����ע���¼�������
		btOk.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				//���EditText��������� ����Щ���ݶ�����intent����DealLoginActivity������ȥ����
				String userName,password;
				userName=etUsernmae.getText().toString().trim();
				password=etPassword.getText().toString().trim();
				
				
				//�����µ���ͼintent
				Intent intent=new Intent();
				
				//��intent��������� intent��ʱ�൱��һ�������
				intent.putExtra("userName", userName);
				intent.putExtra("password", password);
				
				//ָ����ͼ�����ĸ����� �������Ҫ����DealLoginActivity
				intent.setClass(MainActivity.this, DealLoginActivity.class);
				
				//������ͬʱҪ�ȴ������������ForResult����ֱ����֮ǰ��startActivity�� 
				//����������һ�������룬�������������б�ʶ����������������
				startActivityForResult(intent, 101);
				
				//����һ����Ȥ������-��-
				//startActivityForResult(intent,code)�������ڵڶ�����λ��
				//setResult(code,intent)������ڵ�һ������λ��
				
				
			}
		});
	}
	
	//��������һ�Զ�ģ� һ��MainActivity��������activity�����˽��
	//����ϣ��ÿ��activityִ�����˶��ܹ�����һ��״̬��ÿ��״̬���ص�ʱ����ִ�����onActivityResult������
	//���ھ���һ�����⣬��ô��activity���������������ص�״̬���ĸ�activity������
	//��ʱ����Ҫ�õ�������requestCode�ͽ����resultCode
	//-----------------���������source-override methods�����ҵ�-----------------------
	
	//�����б�1.������ 2.����� 3.����data
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if(requestCode==101&&resultCode==RESULT_OK) {
			//����������101�ҽ������RESULT_OK��ʱ���ִ��������Щ����
			
			//����������˼��һ�� ֱ����getStringExtra��������
			//if(data.getExtras().getString("logState").equals(object))
			
			//���data����
			if(data.getStringExtra("logState").equals("δ�ҵ����û�����¼ʧ�ܣ�")) {
				//������ݿ⣨��װ�Լ��и����ݿ⣩���Ҳ���������û���������ת��ע��ҳ��
				//---------��ҪϹ�İ���---------
				
				Intent intent=new Intent(MainActivity.this,RegeditActivity.class);
				startActivity(intent);
				//finish();
			}
				
		}

		
		super.onActivityResult(requestCode, resultCode, data);
	}

	private void init() {
		// TODO Auto-generated method stub
		etUsernmae=(EditText)this.findViewById(R.id.et1);
		etPassword=(EditText)this.findViewById(R.id.et2);
        btOk=(Button)this.findViewById(R.id.bt1);
        btCancel=(Button)this.findViewById(R.id.bt2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
