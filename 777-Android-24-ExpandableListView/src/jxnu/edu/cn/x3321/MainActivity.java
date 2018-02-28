package jxnu.edu.cn.x3321;

import android.os.Bundle;
import android.app.Activity;
import android.database.DataSetObserver;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

public class MainActivity extends Activity {

	//1.�����Ա����
	ExpandableListView elv;
	//����չ��listview ��Ӧ��������Ҳ�ǿ���չ�������� ��ס����Ϳ����� �����Ķ���֮ǰ�����������
	
	String [] grade=new String[] {
		"һ�꼶","���꼶","���꼶","���꼶"	
	};
	String [][] classes=new String[][] {
		{"1��","2��","3��"},
		{"1��","2��","3��"},
		{"1��","2��","3��"},
		{"1��","2��","3��"}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//2.
		init();
		//3.����BaseExpandableListAdapter�����װ����
		MyExpandableListAdapter mead=new MyExpandableListAdapter(getApplicationContext(),grade,classes);
		elv.setAdapter(mead);
	}

	private void init() {
		// TODO Auto-generated method stub
		elv=(ExpandableListView)this.findViewById(R.id.elv);
		elv.setGroupIndicator(null);
		//�ֺͼ�ͷ��Ī��������غϵ�һ���������������Ǹ���ֵļ�ͷ�߿�
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
