package jxnu.edu.cn.x3321;

import android.os.Bundle;

import java.util.ArrayList;

import android.app.Activity;
import android.view.Menu;
import android.widget.*;
import jxnu.edu.cn.x3321.adapter.MyAdapter;

public class MainActivity extends Activity {

	//1.�����Ա����
	ListView lv;
	ArrayList<String> al=new ArrayList<String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//2.��ʼ��
		init();
		//3.׼������
		for(int i=0;i<9;i++) {
			al.add("tom"+i);
		}
		//4.�Զ���������Adapter��װ����
		MyAdapter ma=new MyAdapter(getApplicationContext(),al);//��������al��װ��ȥ
		//5.��MyAdapter�������е����ݼ��ص�lv��
		lv.setAdapter(ma);
	}

	private void init() {
		// TODO Auto-generated method stub
		lv=(ListView)this.findViewById(R.id.lv);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
