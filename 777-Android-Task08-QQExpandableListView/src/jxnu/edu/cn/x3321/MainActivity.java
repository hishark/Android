package jxnu.edu.cn.x3321;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

public class MainActivity extends Activity {

	//1.�����Ա��������ʼ��
	ExpandableListView elv;
	
	String [] group=new String[] {
			"Friends","Family","Teachers","Classmates"
	};
	String [][] members=new String[][] {
		{"Timi","Kimi","Cathy"},
		{"Lucy","Kaven","Hebe"},
		{"Selina","Ella","Bull"},
		{"Kerry","Mika","Mraz"},
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//��ʼ��
		init();
		//�Զ���������
		Myela ela=new Myela(getApplicationContext(),group,members);
		//����������
		elv.setAdapter(ela);
	}

	private void init() {
		// TODO Auto-generated method stub
		elv=(ExpandableListView)this.findViewById(R.id.elv);
		elv.setGroupIndicator(null);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
