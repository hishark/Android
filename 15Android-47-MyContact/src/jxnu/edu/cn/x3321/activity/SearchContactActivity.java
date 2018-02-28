package jxnu.edu.cn.x3321.activity;

import jxnu.edu.cn.x3321.R;
import jxnu.edu.cn.x3321.R.layout;
import jxnu.edu.cn.x3321.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class SearchContactActivity extends Activity {

	//1.定义成员变量
	EditText etSearch;
	ImageButton ibSearch;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_contact);
        
		//2.初始化
		init();
		
		//3.定义注册事件监听器
		ibSearch.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//4.获得要查找的条件
				String name=etSearch.getText().toString().trim();
				if(name==null||name.equals("")) {
					Toast.makeText(getApplicationContext(), "请输入要查找的联系人姓名", Toast.LENGTH_SHORT).show();
				}else {
					Intent intent=new Intent(SearchContactActivity.this,SearchContactListActivity.class);
				    intent.putExtra("name", name);
				    startActivity(intent);
				}
			}
		});
	}

	private void init() {
		// TODO Auto-generated method stub
		etSearch=(EditText)this.findViewById(R.id.et_search);
		ibSearch=(ImageButton)this.findViewById(R.id.ib_search);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search_contact, menu);
		return true;
	}

}
