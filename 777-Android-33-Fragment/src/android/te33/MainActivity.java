package android.te33;

import android.os.Bundle;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {

	//1.定义变量
	Button btqq,btwechat;
	QQfragment qqfragment;
	Wecharfragment wechatfragment;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//2.初始化
		init();
		 
		//3.启动缺省的fragment 自己写一个函数
		setDefaultFragment();
		
		//很多重复代码 垃圾 最好改成一个函数 
		
		//4.定义注册事件监听器
		btqq.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//加载qq碎片
				qqfragment=new QQfragment();
				//启动碎片事务管理
				FragmentManager manager=getFragmentManager();//android.app下的哦
				FragmentTransaction transaction=manager.beginTransaction();
				//启动之后要做什么事情
				transaction.replace(R.id.fragment_content, qqfragment);
				//换成content会出错！
				transaction.commit();
			}
		});
		
		btwechat.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				wechatfragment=new Wecharfragment();
				//启动碎片事务管理
				FragmentManager manager=getFragmentManager();//android.app下的哦
				FragmentTransaction transaction=manager.beginTransaction();
				//启动之后要做什么事情
				transaction.replace(R.id.fragment_content, wechatfragment);
				//换成content会出错！
				transaction.commit();
			}
		});
	}

	private void setDefaultFragment() {
		// TODO Auto-generated method stub
		qqfragment=new QQfragment();
		//启动碎片事务管理
		FragmentManager manager=getFragmentManager();//android.app下的哦
		FragmentTransaction transaction=manager.beginTransaction();
		//启动之后要做什么事情
		transaction.replace(R.id.fragment_content, qqfragment);
		//换成content会出错！
		transaction.commit();
	}

	private void init() {
		// TODO Auto-generated method stub
		btqq=(Button)this.findViewById(R.id.bt_qq);
		btwechat=(Button)this.findViewById(R.id.bt_wechat);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
