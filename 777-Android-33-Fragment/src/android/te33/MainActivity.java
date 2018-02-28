package android.te33;

import android.os.Bundle;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {

	//1.�������
	Button btqq,btwechat;
	QQfragment qqfragment;
	Wecharfragment wechatfragment;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//2.��ʼ��
		init();
		 
		//3.����ȱʡ��fragment �Լ�дһ������
		setDefaultFragment();
		
		//�ܶ��ظ����� ���� ��øĳ�һ������ 
		
		//4.����ע���¼�������
		btqq.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//����qq��Ƭ
				qqfragment=new QQfragment();
				//������Ƭ�������
				FragmentManager manager=getFragmentManager();//android.app�µ�Ŷ
				FragmentTransaction transaction=manager.beginTransaction();
				//����֮��Ҫ��ʲô����
				transaction.replace(R.id.fragment_content, qqfragment);
				//����content�����
				transaction.commit();
			}
		});
		
		btwechat.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				wechatfragment=new Wecharfragment();
				//������Ƭ�������
				FragmentManager manager=getFragmentManager();//android.app�µ�Ŷ
				FragmentTransaction transaction=manager.beginTransaction();
				//����֮��Ҫ��ʲô����
				transaction.replace(R.id.fragment_content, wechatfragment);
				//����content�����
				transaction.commit();
			}
		});
	}

	private void setDefaultFragment() {
		// TODO Auto-generated method stub
		qqfragment=new QQfragment();
		//������Ƭ�������
		FragmentManager manager=getFragmentManager();//android.app�µ�Ŷ
		FragmentTransaction transaction=manager.beginTransaction();
		//����֮��Ҫ��ʲô����
		transaction.replace(R.id.fragment_content, qqfragment);
		//����content�����
		transaction.commit();
	}

	private void init() {
		// TODO Auto-generated method stub
		btqq=(Button)this.findViewById(R.id.bt_qq);
		btwechat=(Button)this.findViewById(R.id.bt_wechat);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
