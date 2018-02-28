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

	//1.定义成员变量
	ExpandableListView elv;
	//可扩展的listview 对应的适配器也是可扩展的适配器 记住这个就可以了 其他的都和之前的适配器差不多
	
	String [] grade=new String[] {
		"一年级","二年级","三年级","四年级"	
	};
	String [][] classes=new String[][] {
		{"1班","2班","3班"},
		{"1班","2班","3班"},
		{"1班","2班","3班"},
		{"1班","2班","3班"}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//2.
		init();
		//3.创建BaseExpandableListAdapter对象封装数据
		MyExpandableListAdapter mead=new MyExpandableListAdapter(getApplicationContext(),grade,classes);
		elv.setAdapter(mead);
	}

	private void init() {
		// TODO Auto-generated method stub
		elv=(ExpandableListView)this.findViewById(R.id.elv);
		elv.setGroupIndicator(null);
		//字和箭头会莫名其妙的重合到一起，用这条属性让那个奇怪的箭头走开
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
