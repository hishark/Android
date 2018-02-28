package android.te40;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class DealLoginActivity extends Activity {

	//1.�����Ա����
	Button bt;
	String logState="";//����һ�������洢��½״̬��Ϣ
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_deal_login);
		
		//2.��ʼ��
		init();
		
		//3.
		bt.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//���intent���������MainActivity��������Ϣ�ģ��Ҳ���Ҫ֪����˭�������ģ��յ������ݴ���ͺ�
				Intent intent=getIntent();
				
				//���֮ǰMainActivity��������û�����zxq ��ô������ע�� ֱ�ӵ�½�ɹ�
				if(intent.getExtras().getString("userName").equals("zxq")) {
					logState="��ϲ���¼�ɹ���";
				}else {
					logState="δ�ҵ����û�����¼ʧ�ܣ�";
				}
				
				//Ҫ�����ݻ�ȥ��MainActivity�� ����Ҫ����һ���µ�intent
				Intent intent2=new Intent(DealLoginActivity.this,MainActivity.class);
				
				//�ѵ�½��״̬�ŵ�intent2����������ȥ��MainActivity
				intent2.putExtra("logState", logState);
				
				//֮ǰ��һ��ForResult�ڵȽ�������setResult�͸���ѽ������ȥ
				//��һ�������ǽ���룬�ڶ���������������intent���е��������ݴ��ݣ�����API����ʾ��������data
				setResult(RESULT_OK, intent2);
				
				//������ǰ��DealLoginActivity
				finish();
			}
		});
	}

	private void init() {
		// TODO Auto-generated method stub
		bt=(Button)this.findViewById(R.id.bt);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.deal_login, menu);
		return true;
	}

}
