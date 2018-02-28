package android.te42;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {

	//1.�����Ա����
	EditText etNumber;
	Button btCall,btDail;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//2.��ʼ��
		init();
		
		//3.ע�ᶨ���¼�������
		btCall.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				 
				
				//��õ绰���룡
				String telNumber=etNumber.getText().toString().trim();
				
				//�ж� ����绰Ϊ�վ���˾��ʾ���� ��Ϊ�վ�׼����绰
				if(telNumber==null||telNumber.equals("")) {
					Toast.makeText(getApplicationContext(), "������绰����", Toast.LENGTH_LONG).show();
				}else {
					//ͨ����ʽ��ʽ������ϵͳ�Ĵ�绰�ĵ����
					//��ʽ����һ��Ҫ�ǵ���manifest����ע���������CALL_PHONEȨ��
					// <uses-permission android:name="android.permission.CALL_PHONE"/>
					
					//��ʽ����һ����ͼintent
					Intent intent=new Intent();
					intent.setAction("android.intent.action.CALL");
					//Ҳ��������
					//intent.setAction(Intent.ACTION_CALL);
					
					//�ѵ绰�����ַ���ת��(parse)��URI���󣬴���ϵͳ��绰�����
					//ע��һ�����tel:�ǹ̶��ģ�������Ϲ�ģ����˾�û������
					Uri uri=Uri.parse("tel:"+telNumber);
					
					//intent��һ������setData(Uri data)��ר����������uri����
					intent.setData(uri);
					
					//���������ͼ
					startActivity(intent);
					
					
					//��androidmanifest.xml�����ô�绰��Ȩ�޲��裺
					//ѡpermission���� ѡadd ����û�Ȩ��usespermission 
					//Ȼ�����ұ�nameѡ��CALL_PHONE
					//���漴��
					//��Ϥ�Ļ�ֱ��дһ�д��뼴��
					//<uses-permission android:name="android.permission.CALL_PHONE"/>
					
				}
			}
		});
		
		
            btDail.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				 
				//����ͨ����ʽ��ʽ����ϵͳ�����������
				
				//��õ绰����
				String telNumber=etNumber.getText().toString().trim();
				
					//ͨ����ʽ��ʽ������ϵͳ�Ĵ�绰�ĵ����
					Intent intent=new Intent();
					intent.setAction(Intent.ACTION_DIAL);
					//intent.setAction("android.intent.action.DIAL");
					
					
					//�ѵ绰�����ַ���ת����URI���󣬴���ϵͳ��绰�����
					Uri uri=Uri.parse("tel:"+telNumber);
					
					//ר����������uri����
					intent.setData(uri);
					
					
					startActivity(intent);
					
					
					
					//��androidmanifest.xml�����ô�绰��Ȩ�� 
					//ѡpermission���� ѡadd ����û�Ȩ��usespermission 
					//Ȼ�����ұ�nameѡ��callphone
					//���漴��
					//��Ϥ�Ļ�ֱ��дһ�д��뼴��
					//<uses-permission android:name="android.permission.CALL_PHONE"/>
					
				
			}
		});
		
		
		
	}

	private void init() {
		// TODO Auto-generated method stub
		etNumber=(EditText)this.findViewById(R.id.et);
		btCall=(Button)this.findViewById(R.id.bt_Call);
		btDail=(Button)this.findViewById(R.id.bt_Dail);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
