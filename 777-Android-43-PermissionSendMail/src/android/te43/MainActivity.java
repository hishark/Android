package android.te43;

import android.os.Bundle;
import android.telephony.gsm.SmsManager;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {

	//1.�����Ա����
	EditText etNumber,etBody;
	Button btSend,btOpen;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//2.��ʼ��
		init();
		
		//3.����ע���¼�������
		//��smsmanager�����Ͷ���
		btSend.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				//------------��ؼ���һ��----------------
				//Ҫ�ǵ���andoidmanifest.xml�����÷��Ͷ��ŵ�Ȩ��
				//-------------------------------------
				
				//��õ绰�����Լ���Ϣ����
				String toNumber=etNumber.getText().toString().trim();
				String body=etBody.getText().toString().trim();
				
				if(toNumber==null||toNumber.equals("")) {
					Toast.makeText(getApplicationContext(), "������绰���룡", Toast.LENGTH_LONG).show();
				}else {
					//SmsManager�������ֱ�ӷ��Ͷ��� ����Ϊɶ������ʹ����OTZ
					//�������������ֱ��newһ������Ү ����һ�»ᱨ�� ֻ����getDefault�����һ������
					SmsManager sm=SmsManager.getDefault();
					
					
					//�ֻ�������Ϣ��һ�����ֵ���������⴦��ͻᶪʧ������Ҫ�����ݽ��зָ�ָ�����ɲ���
					//��10086��ϲ����ô���ҷ���Ϣ�� �ֳɺü��� ��Ϊʲô�˹����� �����Ը��з��ĳ����Ķ������Ƕ��˺ܶ�ORZ��
					//����Ҫ�Լ������зָֱ�ӵ���һ������divideMessage���зָ�ɡ�
					//����һ��String���͵�List���ϣ������洢�ָ�����Ϣ 
					List<String> sms=sm.divideMessage(body);
					
					/*for(int i=0;i<sms.size();i++) {
						sm.sendTextMessage(toNumber, null, sms.get(i), null, null);
					}*/
					
					//�ָ�����Ϣ�Ϳ���һ�����ķ���ȥ��
					for(String m:sms) {
						//һ������Ϣ���ն˵��ֻ��ţ���������Ϣ���Ͷ˵��ֻ��� ֱ��Ϊnull����������Ϣ����������ͼ ֱ��null
						sm.sendTextMessage(toNumber, null, m, null, null);
					}
				    
				}
				
			}
		});
		
		
		
		   //����ʽ��ͼ���򿪷��Ͷ��ŵĽ���
           btOpen.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				//��andoidmanifest.xml�����÷��Ͷ��ŵ�Ȩ��
				
				//��õ绰�����Լ���Ϣ����
				String toNumber=etNumber.getText().toString().trim();
				String body=etBody.getText().toString().trim();
				
				if(toNumber==null||toNumber.equals("")) {
					Toast.makeText(getApplicationContext(), "������绰���룡", Toast.LENGTH_LONG).show();
				}else {
					
					//ͨ����ʽ��ͼ������ϵͳ�����ŵĽ���
					Intent intent=new Intent();
					intent.setAction(Intent.ACTION_SENDTO);
					//�������Ҳok�������о������Ǹ���������������Ҫ��
					//intent.setAction("android.intent.action.SENDTO"); 
					
					//���绰�����ַ���ת����uri�����ٴ���ϵͳ���Ͷ��ŵ����
					//����intent��ͼ��Ϣ���ն˵ĵ绰������ʲô
					Uri uri=Uri.parse("smsto:"+toNumber);
					
					intent.setData(uri);
					//����ϢҲŪ��ȥ ���sms_bodyҲ�ǹ̶��� �����ԸĶ� �Ķ��˴򿪵���Ϣ�༭����Ͳ���������edittext���������Ϣ�ˣ�
					intent.putExtra("sms_body", body);
					
					startActivity(intent);
					
				}
				
			}
		});
		
		
 
	}

	private void init() {
		// TODO Auto-generated method stub
		etNumber=(EditText)this.findViewById(R.id.et_Number);
		etBody=(EditText)this.findViewById(R.id.et_body);
		btSend=(Button)this.findViewById(R.id.bt_send);
		btOpen=(Button)this.findViewById(R.id.bt_open);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
