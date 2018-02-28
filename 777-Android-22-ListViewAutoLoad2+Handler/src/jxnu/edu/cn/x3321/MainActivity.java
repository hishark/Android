package jxnu.edu.cn.x3321;

import android.os.Bundle;
import android.os.Handler;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {

	//1.�����Ա����
	ListView lv;
	ArrayList<String> al=new ArrayList<String>();
	Button bt;
	ProgressDialog pd;
	//�����post������ʽ ����ֱ�Ӷ���hd�Ϳ����ˣ�������sendmessage+handlemessage�ˣ�
	Handler hd=new Handler();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		//2.��ʼ��
		init();
		
		//3.׼������
		for(int i=0;i<10;i++) {
			al.add("tom"+i);
		}
		
		//4.��lv��������һ��loadmore
		bt=(Button)View.inflate(getApplicationContext(), R.layout.button, null);//inflate���R.layout.button�����bt��
		lv.addFooterView(bt);
		//����֪�� �ӵ������� ������� 
		
		//5.�Զ���������
		final MyAdapter ma=new MyAdapter(getApplicationContext(),al);
		
		//6.�����������ص�lv��
		lv.setAdapter(ma);
		
		//7.���塢ע�������
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), al.get(arg2), Toast.LENGTH_SHORT).show();
			}
			//al����һ���ַ������飬����getֱ�ӵõ��ַ�����
		});
		bt.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				pd.show();
				//�㰴ť��������ʾprogressdialog��
				
				//˼��������ΪʲôҪ�õ��߳�
				//��Ϊpd��ʾʱ�����߳̾�����ʾpd�ˣ�����������������Ҫȥ�����µ����ݣ���������ط���Ҫ�õ����̣߳����߳�����������ݵ�����֮��
				//�Ϳ��Իص����߳������߳�Ӧ�����Ĺ���
				new Thread() {
					 public void run() {
						 //����û�дӷ�����ȡ�������������Ի�ܿ죬Ϊ���ܿ���pd��ʾ������Ч���������߳�����3��
						 try {
							Thread.sleep(2000);//��λ����
							//������ﲻ���ߣ���pd��ʾ�����������������͵�һ��������һ��
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						 for(int i=0;i<10;i++) {
							 al.add("mike"+i);
						 }
						 hd.post(new Runnable() {//Runnable�൱�ڿ絽�����߳���ִ����

							 //��������������߳����Ĺ���
							@Override
							public void run() {
								// TODO Auto-generated method stub
								
								ma.notifyDataSetChanged();
								//���ݼ��ı��ˣ�֪ͨ�������ٷ�װһ��
								
								pd.dismiss();
								//��ʾ���������ݾ��ü���ȦȦ��ʧ
							}
							 
						 });
					 }
				}.start();
			}
		});
		
	}

	private void init() {
		// TODO Auto-generated method stub
		lv=(ListView)this.findViewById(R.id.lv);
		pd=new ProgressDialog(this);
		pd.setCancelable(false);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
