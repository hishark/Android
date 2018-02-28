package jxnu.edu.cn.x3321;

import android.os.Bundle;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {

	
	//1.�����Ա����
	Button bt;
	ArrayList<String> al=new ArrayList<String>();
	MyAdapter ma;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//2.��ʼ��
		init();
		
		//3.׼������
		for(int i=0;i<10;i++) {
			al.add("Kimi"+i);
		}
		
		//4.�Զ���������
		ma=new MyAdapter(getApplicationContext(),al);
		
		//5.ע���¼�������
		bt.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog ad=createDialog();//�Զ����createDialog���������Ի���
				ad.show();
			}
		});
		//���϶�����·
	}

	protected AlertDialog createDialog() {
		// TODO Auto-generated method stub
		AlertDialog alertDialog;
		AlertDialog.Builder ad=new AlertDialog.Builder(MainActivity.this);
		//����������·
		
		ad.setTitle("�Զ��������б�Ի���");
		ad.setIcon(R.drawable.ic_launcher);
		
		//���Զ�������������ص�ad��
		//ad.setAdapter(ma,null);
		ad.setAdapter(ma, null);
		ad.setPositiveButton("ȷ��",new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				//�Լ�����
			}
		});
		
		ad.setNegativeButton("ȡ��", null);
		
		
		//����������·
		alertDialog=ad.create();//ad��AlertDialog.Builder���� ����create()��������AlertDialog����
		return alertDialog;
	}

	private void init() {
		// TODO Auto-generated method stub
		bt=(Button)this.findViewById(R.id.bt);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
