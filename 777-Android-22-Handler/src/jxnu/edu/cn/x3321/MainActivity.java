package jxnu.edu.cn.x3321;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {

	//1.�����Ա����
	Button bt;
	TextView tv;
	
	Handler hd=new Handler() {
		public void handleMessage(Message msg) {//���������дHandler���handleMessage����
			if(msg.what==1) {
				//���ϵظ���TextView��ֵ
				tv.setText(String.valueOf(msg.obj));
			}
			//�����߳�1�Ĺ���
		}
	};
	//message���Ϸ������ݴ浽ջ�ϵͳ��ÿ��һ��ʱ��ͻ�ִ��handler��handlemessage���� ��ջ���ó���Ӧ��messgae
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//2.��ʼ��
		init();
		
		//3.����ע���¼�������
		bt.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//����һ���߳�   ������1.ʵ�ֽӿ� 2.newһ��
				new Thread() {
					public void run() {
						for(int i=0;i<10;i++) {
							
							try {
								Thread.sleep(1000);
								//�Ժ���Ϊ��λ
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							//���ܻ�����쳣����Ҫ��trycatch������
							
							//��messgae�����װ�����ݴ������߳�
							Message ms=new Message();
							ms.obj="���º��ֵΪ��"+i;
							//�����Ǹ�ms��ֵ ��object����
							ms.what=1;//what��ʾ���ݵ����ͻ����Ǳ�ʶ �������߳�whatΪ2 �൱�ڱ����
							//������ͨ��ʲô��ʽ�������߳� ͨ��ʲô����
							hd.sendMessage(ms);
							//hd.post(ms);����������� ������Ϳ��Բ�Ҫдsendmessage������ �½ڿν�
						}
					}//����������κ��̶߳�Ҫ�е� �����̵߳���� һ�������߳̾Ϳ�ʼִ��run�������൱��main��
					//�߳̿������ߣ����κ����̶߳��������� �������ԣ�
				}.start();
			}
		});
	}

	private void init() {
		// TODO Auto-generated method stub
		bt=(Button)this.findViewById(R.id.bt);
		tv=(TextView)this.findViewById(R.id.tv);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
