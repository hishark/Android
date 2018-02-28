package android.te42;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {

	//1.定义成员变量
	EditText etNumber;
	Button btCall,btDail;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//2.初始化
		init();
		
		//3.注册定义事件监听器
		btCall.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				 
				
				//获得电话号码！
				String telNumber=etNumber.getText().toString().trim();
				
				//判断 如果电话为空就吐司提示输入 不为空就准备打电话
				if(telNumber==null||telNumber.equals("")) {
					Toast.makeText(getApplicationContext(), "请输入电话号码", Toast.LENGTH_LONG).show();
				}else {
					//通过隐式方式来启动系统的打电话的的组件
					//隐式启动一定要记得在manifest里面注册下面这个CALL_PHONE权限
					// <uses-permission android:name="android.permission.CALL_PHONE"/>
					
					//隐式启动一个意图intent
					Intent intent=new Intent();
					intent.setAction("android.intent.action.CALL");
					//也可以这样
					//intent.setAction(Intent.ACTION_CALL);
					
					//把电话号码字符串转换(parse)成URI对象，传给系统打电话的组件
					//注意一下这个tel:是固定的，不可以瞎改，改了就没有用了
					Uri uri=Uri.parse("tel:"+telNumber);
					
					//intent有一个方法setData(Uri data)，专门用来传递uri对象
					intent.setData(uri);
					
					//启动这个意图
					startActivity(intent);
					
					
					//在androidmanifest.xml中配置打电话的权限步骤：
					//选permission界面 选add 添加用户权限usespermission 
					//然后在右边name选择CALL_PHONE
					//保存即可
					//熟悉的话直接写一行代码即可
					//<uses-permission android:name="android.permission.CALL_PHONE"/>
					
				}
			}
		});
		
		
            btDail.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				 
				//这里通过隐式方式启动系统的组件！！！
				
				//获得电话号码
				String telNumber=etNumber.getText().toString().trim();
				
					//通过隐式方式来启动系统的打电话的的组件
					Intent intent=new Intent();
					intent.setAction(Intent.ACTION_DIAL);
					//intent.setAction("android.intent.action.DIAL");
					
					
					//把电话号码字符串转换成URI对象，传给系统打电话的组件
					Uri uri=Uri.parse("tel:"+telNumber);
					
					//专门用来传递uri对象
					intent.setData(uri);
					
					
					startActivity(intent);
					
					
					
					//在androidmanifest.xml中配置打电话的权限 
					//选permission界面 选add 添加用户权限usespermission 
					//然后在右边name选择callphone
					//保存即可
					//熟悉的话直接写一行代码即可
					//<uses-permission android:name="android.permission.CALL_PHONE"/>
					
				
			}
		});
		
		
		
	}

	private void init() {
		// TODO Auto-generated method stub
		etNumber=(EditText)this.findViewById(R.id.et);
		btCall=(Button)this.findViewById(R.id.bt_Call);
		btDail=(Button)this.findViewById(R.id.bt_Dail);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
