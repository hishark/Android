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

	//1.定义成员变量
	ListView lv;
	ArrayList<String> al=new ArrayList<String>();
	Button bt;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//2.初始化
		lv=(ListView)this.findViewById(R.id.lv);
		
		//3.准备数据
		for(int i=0;i<9;i++) {
			al.add("tom"+i);
		}
		
		//4.在listview底部增加一个load more...
		//做一个button 用xml
		//用inflate填充
		//百度一哈第三个参数的含义
		bt=(Button)View.inflate(getApplicationContext(), R.layout.button, null);
		
		lv.addFooterView(bt);
		//放到view的底部
		//5.自定义适配器
		final MyAdapter ma=new MyAdapter(getApplicationContext(),al);
		//final是什么来着
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
				}//往al里加入新的数据
				
				//数据改变了 通知适配器再封装一次
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
