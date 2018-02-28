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

	//1.�����Ա����
	ListView lvSearch;
	Button btSearchBack;
	UserInterface ui;
	ArrayList <HashMap<String,Object>> list=new ArrayList<HashMap<String,Object>>();
	SimpleAdapter sa;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_contact_list);
	
	    //2.��ʼ��
		init();
		
		//3.��intent�л��Ҫ���ҵ���ϵ�˵�����
		String name=this.getIntent().getStringExtra("name");
		
		//4.����Ҫ���ҵ���ϵ�˵����������ݿ��user��ȡ����Ӧ����ϵ��
		//getUsersBy***
		//�϶�Ҫ�õ��ӿڵ� ������ݿ���ϵһ��һ��Ҫ�õ��ӿڵ� �ǵ�Ҫʵ������
	    ui=new UserInterfaceImp(getApplicationContext());
	    list=ui.getUsersByName(name);
	    
	    //5.��list�е����ݷ�װ��������
	    sa=new SimpleAdapter(this, list, R.layout.contactlistitem,
				new String[] {"name","phone"},new int[] {R.id.tv_Name,R.id.tvPhone});
	
	    //6.��listview�ϼ���������
	    lvSearch.setAdapter(sa);
	    
	    //7.�����ľͲ�����д��  ��֮ǰContackActivity����һ��
	    registerForContextMenu(lvSearch);
	    
	    //8.����ע���¼�������
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
