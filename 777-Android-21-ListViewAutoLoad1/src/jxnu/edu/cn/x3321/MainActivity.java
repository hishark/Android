package jxnu.edu.cn.x3321;

import android.os.Bundle;

import java.util.ArrayList;

import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import jxnu.edu.cn.x3321.adapter.MyAdapter;

public class MainActivity extends Activity {

	//1.�����Ա����
	ListView lv;
	ArrayList<String> al=new ArrayList<String>();
	Button bt;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//2.��ʼ��
		lv=(ListView)this.findViewById(R.id.lv);
		
		//3.׼������
		for(int i=0;i<9;i++) {
			al.add("tom"+i);
		}
		
		//4.��listview�ײ�����һ��load more...
		//��һ��button ��xml
		//��inflate���
		//�ٶ�һ�������������ĺ���
		bt=(Button)View.inflate(getApplicationContext(), R.layout.button, null);
		
		lv.addFooterView(bt);
		//�ŵ�view�ĵײ�
		//5.�Զ���������
		final MyAdapter ma=new MyAdapter(getApplicationContext(),al);
		//final��ʲô����
		//6.jiazai
		lv.setAdapter(ma);
		
		//7.
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), al.get(arg2), Toast.LENGTH_SHORT).show();
			}
			
			
			
			
		});
		
		bt.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				for(int i=0;i<10;i++) {
					al.add("jack"+i);
				}//��al������µ�����
				
				//���ݸı��� ֪ͨ�������ٷ�װһ��
				ma.notifyDataSetChanged();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
