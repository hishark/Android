package android.te40;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	//1.定义成员变量
	EditText etUsernmae,etPassword;
	Button btOk,btCancel;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//2.初始化
		init();
		
		//3.定义注册事件监听器
		btOk.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				//获得EditText输入的数据 把这些数据都交给intent带到DealLoginActivity界面里去处理
				String userName,password;
				userName=etUsernmae.getText().toString().trim();
				password=etPassword.getText().toString().trim();
				
				
				//创建新的意图intent
				Intent intent=new Intent();
				
				//往intent里面放数据 intent这时相当于一个大货车
				intent.putExtra("userName", userName);
				intent.putExtra("password", password);
				
				//指定意图启动哪个东西 这里就是要启动DealLoginActivity
				intent.setClass(MainActivity.this, DealLoginActivity.class);
				
				//启动的同时要等待结果，所以用ForResult，不直接用之前的startActivity了 
				//二参数就是一个请求码，用来对这个活动进行标识。可以是任意整数
				startActivityForResult(intent, 101);
				
				//发现一个有趣的事情-。-
				//startActivityForResult(intent,code)请求码在第二参数位置
				//setResult(code,intent)结果码在第一个参数位置
				
				
			}
		});
	}
	
	//请求结果是一对多的， 一个MainActivity可能向多个activity请求了结果
	//并且希望每个activity执行完了都能够返回一个状态，每当状态返回的时候都是执行这个onActivityResult函数。
	//现在就有一个问题，这么多activity该如何区分这个返回的状态是哪个activity发出的
	//这时就需要用到请求码requestCode和结果码resultCode
	//-----------------这个函数用source-override methods可以找到-----------------------
	
	//参数列表：1.请求码 2.结果码 3.数据data
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if(requestCode==101&&resultCode==RESULT_OK) {
			//当请求码是101且结果码是RESULT_OK的时候就执行下面这些动作
			
			//下面两句意思的一样 直接用getStringExtra更方便啦
			//if(data.getExtras().getString("logState").equals(object))
			
			//这个data就是
			if(data.getStringExtra("logState").equals("未找到此用户！登录失败！")) {
				//如果数据库（假装自己有个数据库）中找不到输入的用户名，则跳转到注册页面
				//---------不要瞎改包名---------
				
				Intent intent=new Intent(MainActivity.this,RegeditActivity.class);
				startActivity(intent);
				//finish();
			}
				
		}

		
		super.onActivityResult(requestCode, resultCode, data);
	}

	private void init() {
		// TODO Auto-generated method stub
		etUsernmae=(EditText)this.findViewById(R.id.et1);
		etPassword=(EditText)this.findViewById(R.id.et2);
        btOk=(Button)this.findViewById(R.id.bt1);
        btCancel=(Button)this.findViewById(R.id.bt2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
