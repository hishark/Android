package jxnu.edu.cn.x3321;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	//1.定义成员变量
	ListView lv;
	
	//2.准备数据（mvc的模型层？）这里弄个数组 本来是要弄数据库的
	String [] food= {
			"小龙虾",
	        "肉包子",
	        "年糕",
	        "排骨",
	        "火锅",
	        "大闸蟹",
	        "披萨",
	        "牛蛙",
	        "牛排",
	        "小炒肉",
	        "干锅花菜",
	        "我饿了"
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//3.使成员引用变量指向界面上的元素对象（初始化）
		init();
		
		//4.把数据封装到arrayadapter适配器     参数含义（ 1.上下文，2.每一项数据的显示格式，3.显示什么数据）
		//ListView显示格式的缺省情况就是一行一个textview
		//android.R.layout.simple_list_item_1就是一行显示一个 然后字体颜色灰白色 基本看不清
		//food有12个数据 所以循环的显示12次~
		//这样数据就按照顺序封装在adapter里了
		ArrayAdapter aa=new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,food);
		
		//5.把arrayAdapter适配器加载到listView中
		lv.setAdapter(aa);
		
		//6.定义、注册事件监听器
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			
			@Override
			//onItemClick()第一个参数是适配器本身，第二个参数就是单击的那一条
			//第三个参数是当前选中的行数（可以理解为数组下标，从0开始），第四个参数表示你选中的这一条的id（如果没赋id就空吧）
			//用的最多的就是第二和第三个参数，最重要的也是第二个和第三个参数
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				TextView tv=(TextView)arg1;
				//View是父类 所以要强制转换
				String str=tv.getText().toString();
				Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
			}
		});
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
