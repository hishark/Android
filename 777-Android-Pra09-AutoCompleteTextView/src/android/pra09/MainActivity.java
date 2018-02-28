package android.pra09;

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
		"java从入门到放弃",
		"Android从入门到放弃",
		"java教你做人",
		"java咖啡学",
		"Android教你打嗝",
		"C语言从入门到放弃",
		"C++从入门到放弃"
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//2.初始化
		init();
		//3.适配器 用最简单的arrayadapter
		ArrayAdapter<String> ma=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_dropdown_item_1line,books);
		//4.加载适配器
		ac.setAdapter(ma);
		mac.setAdapter(ma);
		
		//这一句一定要记得 不然不会有效果的
		mac.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
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
