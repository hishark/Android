package jxnu.edu.cn.x3321.activity;

import jxnu.edu.cn.x3321.R;
import jxnu.edu.cn.x3321.R.layout;
import jxnu.edu.cn.x3321.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class MyContactActivity extends TabActivity {
	
	//1.�������
	TabHost tabHost;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_cotact);
		
		//2.������ʼ��
		tabHost=getTabHost();
		
		//3.������һ��Tabҳ����ǩ����
		TabSpec tab1=tabHost.newTabSpec("myContact");
		View myContactView=getView(R.drawable.tab_main_nav_contact,"�ҵ�ͨ��¼");
		tab1.setIndicator(myContactView);
		Intent intent1=new Intent(MyContactActivity.this,ContactActivity.class);
		tab1.setContent(intent1);
		tabHost.addTab(tab1);
		
		//4.�����ڶ���Tabҳ����ǩ����
		TabSpec tab2=tabHost.newTabSpec("addContact");
		View addContactView=getView(R.drawable.tab_main_nav_addcontact,"���ͨ��¼");
		tab2.setIndicator(addContactView);
		Intent intent2=new Intent(MyContactActivity.this,AddContactActivity.class);
		tab2.setContent(intent2);
		tabHost.addTab(tab2);
		
		//5.����������Tabҳ����ǩ����
		TabSpec tab3=tabHost.newTabSpec("searchContact");
		View searchContactView=getView(R.drawable.tab_main_nav_search,"������ϵ��");
		tab3.setIndicator(searchContactView);
		Intent intent3=new Intent(MyContactActivity.this,SearchContactActivity.class);
		tab3.setContent(intent3);
		tabHost.addTab(tab3);
		
		//6.�������ĸ�Tabҳ����ǩ����
		TabSpec tab4=tabHost.newTabSpec("aboutContact");
		View aboutContactView=getView(R.drawable.tab_main_nav_more,"����...");
		tab4.setIndicator(aboutContactView);
		Intent intent4=new Intent(MyContactActivity.this,AboutContactActivity.class);
		tab4.setContent(intent4);
		tabHost.addTab(tab4);
	}

	private View getView(int tabMainNavContact, String string) {
		// TODO Auto-generated method stub
		View view=View.inflate(getApplicationContext(),R.layout.tab_main_nav, null);
		ImageView iv=(ImageView)view.findViewById(R.id.ivIcon);
		iv.setBackgroundResource(tabMainNavContact);
		TextView tv=(TextView)view.findViewById(R.id.tvTitle);
		tv.setText(string);
		
		return view;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
