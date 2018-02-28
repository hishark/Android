package android.te32;

import android.os.Bundle;
import android.app.Activity;
import android.app.TabActivity;
import android.view.Menu;
import android.widget.*;
import android.widget.TabHost.TabSpec;

//extends要改变了
//TabActivity不建议使用了 被碎片代替了
public class MainActivity extends TabActivity {

	//1.定义成员变量
	TabHost tabHost;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_host);
		//2.初始化
		tabHost=getTabHost();
		//3.创建四个tab标签页
		TabSpec tab1=tabHost.newTabSpec("tab1");
		tab1.setContent(R.id.ltab1);
		tab1.setIndicator("联系人");
		
		TabSpec tab2=tabHost.newTabSpec("tab2");
		tab2.setContent(R.id.ltab2);
		tab2.setIndicator("消息");
		
		TabSpec tab3=tabHost.newTabSpec("tab3");
		tab3.setContent(R.id.ltab3);
		tab3.setIndicator("查找");
		
		TabSpec tab4=tabHost.newTabSpec("tab4");
		tab4.setContent(R.id.ltab4);
		tab4.setIndicator("最近通话");
		
		//把内容加到tabHost里去
		tabHost.addTab(tab1);
		tabHost.addTab(tab2);
		tabHost.addTab(tab3);
		tabHost.addTab(tab4);
	
				
		
	}

 
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
