package jxnu.edu.cn.x3321;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class MainActivity extends Activity {

	//1.定义成员变量
	ListView lv;
	//最好定义一个Student类 姓名什么什么什么
	//这里先不定义类 
	//ArrayList里面可以放任意类型的对象和数据类型
	//任意类型有点不好管理 所以可以用<>泛型来限制一哈类型
	
	//HashMap无序 存值需要成对成对的存 key-value
	//取值只可以通过key去取值
	//java课本P334，335
	//Map<>也可以规定类型
	ArrayList<Map<String,String>> list;
	//定义一个list
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//2.初始化
		init();
		
		//3.准备数据
		//创建多个hashmap然后扔进arraylist
		list=new ArrayList<Map<String,String>>();
		Map<String, String> m1=new HashMap<String,String>();
		//Map是一个接口  HashMap是该接口的具体实现
		m1.put("name", "张小琪");
		m1.put("tel","13767862093");
		m1.put("qq", "358436965");
		//把一个hashmap加到list里去
		list.add(m1);
		
		Map<String, String> m2=new HashMap<String,String>();
		//Map是一个接口 HashMap是他的具体实现
		m2.put("name", "张府");
		m2.put("tel","13879959788");
		m2.put("qq", "492365247");
		//把一个hashmap加到list里去
		list.add(m2);
		
		Map<String, String> m3=new HashMap<String,String>();
		//Map是一个接口 HashMap是他的具体实现
		m3.put("name", "文琳");
		m3.put("tel","13979958543");
		m3.put("qq", "123456789");
		//把一个hashmap加到list里去
		list.add(m3);
		
		Map<String, String> m4=new HashMap<String,String>();
		//Map是一个接口 HashMap是他的具体实现
		m4.put("name", "邓婕");
		m4.put("tel","13970598323");
		m4.put("qq", "986895544");
		//把一个hashmap加到list里去
		list.add(m4);
		
		//4.把数据封装到simpleAdapter适配器
		//第一个参数就是上下文 必须要加的
		//第二个参数就是要显示的数据集合（这个方法应该是限制了数据集合只可以用map）
		//第三个参数就是数据要以怎么样的格式封装到适配器里去（如何显示出来）
		//第四个参数就是表示你想要显示list中每个数据的哪些属性
		//第五个参数就是这些属性要放到table.xml的哪个组件去显示出来
		SimpleAdapter sa=new SimpleAdapter(getApplicationContext(),
				list,R.layout.table,
				new String[] {"name","tel"},
				new int[] {R.id.tv_userName,R.id.tv_tel});//id地址是可以存放到整形数组中的
		
		//5.把simpleadapter适配器加载到listview
		lv.setAdapter(sa);
		
		//6.定义注册监听器
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			
			@Override
			//onItemClick()第一个参数是适配器本身，第二个参数就是单击的那一条
			//第三个参数是当前选中的行数（可以理解为数组下标，从0开始），第四个参数表示你选中的这一条的id（如果没赋id就空吧）
			//用的最多的就是第二和第三个参数，最重要的也是第二个和第三个参数
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), 
						list.get(arg2).get("name")+"\n"
						+list.get(arg2).get("tel")+"\n"
						+list.get(arg2).get("qq")
						, Toast.LENGTH_SHORT).show();
			}
		});
	}
	//New Task
	//map改成student
	//student.java
	//ARRAYLIST STUDENT
	//点一下改成dialog显示具体信息
	//头像不一样images[]
	//第一个人对应images[0]
	//

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
