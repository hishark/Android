package jxnu.edu.cn.x3321;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

public class MainActivity extends Activity {

	//1.定义成员变量
	AutoCompleteTextView ac;
	MultiAutoCompleteTextView mac;
	String books[]=new String[] {
		"Java疯狂讲义",
		"Android疯狂讲义",
		"Xml疯狂讲义",
		"疯狂英语",
		"Java程序设计",
		"Java设计模式"
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//2.初始化
		init();
		
		//3.arrayAdapter封装数据
		//参数一上下文 二格式 三数据
		ArrayAdapter<String> aa=new ArrayAdapter<String>(
				getApplicationContext(),android.R.layout.simple_dropdown_item_1line,books);
		ac.setAdapter(aa);
		
		mac.setAdapter(aa);
		//多选之间的分隔符
		
		mac.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
		//这个方法的缺省情况是逗号隔开 这一句一定要记得加  不设置分隔符的话连最基本的显示一个数据都办不到的
	
	}

	private void init() {
		// TODO Auto-generated method stub
		ac=(AutoCompleteTextView)this.findViewById(R.id.ac);
		mac=(MultiAutoCompleteTextView)this.findViewById(R.id.mac);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
