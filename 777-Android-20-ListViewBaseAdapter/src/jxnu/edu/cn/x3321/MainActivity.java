package jxnu.edu.cn.x3321;

import android.os.Bundle;

import java.util.ArrayList;

import android.app.Activity;
import android.view.Menu;
import android.widget.*;
import jxnu.edu.cn.x3321.adapter.MyAdapter;

public class MainActivity extends Activity {

	//1.定义成员变量
	ListView lv;
	ArrayList<String> al=new ArrayList<String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//2.初始化
		init();
		//3.准备数据
		for(int i=0;i<9;i++) {
			al.add("tom"+i);
		}
		//4.自定义适配器Adapter封装数据
		MyAdapter ma=new MyAdapter(getApplicationContext(),al);//把上下文al封装过去
		//5.把MyAdapter适配器中的数据加载到lv中
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
