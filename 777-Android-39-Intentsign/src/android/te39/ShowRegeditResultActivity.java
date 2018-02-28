package android.te39;

import android.os.Bundle;
import android.text.LoginFilter.UsernameFilterGeneric;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.*;
import jxnu.edu.cn.x3321.R;

public class ShowRegeditResultActivity extends Activity {

	//1.定义成员变量
	TextView tv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_regedit_result_main);
		
		//2.初始化
		init();
		
		//3-1 第一种数据获得方法
		//先得到从源Activity传过来的intent，再从intent里面取得bundle
		/*Intent intent=getIntent();//不需要知道源头是谁 只要拿到intent就可以了
		Bundle bd=intent.getExtras();
		String result="";
		result+=bd.getString("userName")+"\n";
		result+=bd.getString("password")+"\n";
		result+=bd.getString("age")+"\n";*/
		
		
		//3-2 第二种数据获得方法
		//先得到从源Activity传过来的intent，再从intent里直接取得数据
		Intent intent=getIntent();
		String result="";
		result+=intent.getExtras().getString("userName")+"\n";
		result+=intent.getExtras().getString("password")+"\n";
		result+=intent.getExtras().getInt("age")+"\n";
		
		
		//4.把数据显示到textview上去
		tv.setText(result);
	}

	private void init() {
		// TODO Auto-generated method stub
		tv=(TextView)this.findViewById(R.id.tv);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show_regedit_result_main, menu);
		return true;
	}

}
