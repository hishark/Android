package jxnu.edu.cn.x3321.activity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.*;
import jxnu.edu.cn.x3321.R;
import jxnu.edu.cn.x3321.R.layout;
import jxnu.edu.cn.x3321.R.menu;
import jxnu.edu.cn.x3321.Interface.UserInterface;
import jxnu.edu.cn.x3321.InterfaceImp.UserInterfaceImp;

public class SearchContactListActivity extends Activity {

	//1.定义成员变量
	ListView lvSearch;
	Button btSearchBack;
	UserInterface ui;
	ArrayList <HashMap<String,Object>> list=new ArrayList<HashMap<String,Object>>();
	SimpleAdapter sa;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_contact_list);
	
	    //2.初始化
		init();
		
		//3.从intent中获得要查找的联系人的姓名
		String name=this.getIntent().getStringExtra("name");
		
		//4.根据要查找的联系人的姓名从数据库表user中取出对应的联系人
		//getUsersBy***
		//肯定要用到接口的 想和数据库联系一定一定要用到接口的 记得要实例化！
	    ui=new UserInterfaceImp(getApplicationContext());
	    list=ui.getUsersByName(name);
	    
	    //5.把list中的数据封装到适配器
	    sa=new SimpleAdapter(this, list, R.layout.contactlistitem,
				new String[] {"name","phone"},new int[] {R.id.tv_Name,R.id.tvPhone});
	
	    //6.在listview上加载适配器
	    lvSearch.setAdapter(sa);
	    
	    //7.上下文就不具体写了  和之前ContackActivity代码一样
	    registerForContextMenu(lvSearch);
	    
	    //8.定义注册事件监听器
	    btSearchBack.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(SearchContactListActivity.this,MyContactActivity.class);
				startActivity(intent);
				finish();
				
			}
		});
	}

	private void init() {
		// TODO Auto-generated method stub
		lvSearch=(ListView)this.findViewById(R.id.lv_search);
		btSearchBack=(Button)this.findViewById(R.id.btSearch_back);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search_contact_list, menu);
		return true;
	}

}
