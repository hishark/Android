package android.te37;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {

	//1.定义成员变量
	Button bt1,bt2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//2.初始化
		init();
		
		//3.定义注册事件监听器
		bt1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//启动新的意图intent（现在先学显式启动）
				
				//定义意图对象
				Intent intent=new Intent();
				
				//---------第一种启动方式------------
				//指定意图启动哪个组件 
				intent.setClass(MainActivity.this, SecondActivity.class);
				//第一个参数是原来的activity 即从哪个activity开始启动
				//第二个参数是你想启动的activity的完整类名（不要忘记包名，如果在同一个包就可以省略包名）
				//这种启动方法最简单最直接的
				
				//执行意图intent（要启动的组件都封装到intent里了）
				startActivity(intent);
				
				//关闭当前activity 不用的activity就关掉 释放资源
				finish();
				//如果这里不用finish的话是可以返回mainactivity的 
				//如果finish了就返回不到mainactivity了 因为已经被杀掉啦！
				
				
				//----------第二种启动方式-------------
				//intent.setClassName("android.te37", "android.te37.SecondActivity");
				//奇怪 这里我明明记得在同一个包下不加包名也可以的？？为啥失效了 那就记得一定要加包名
				//startActivity(intent);
				
				//要启动的组件所在的包名和类名 记得加双引号
				
				
				//----------第三种启动方式----------
				//Intent intent=new Intent(MainActivity.this, SecondActivity.class);
				//直接用构造方法来指定想要启动的组件
				//startActivity(intent);
			}
		});
		bt2.setOnClickListener(new View.OnClickListener() {
			//这个用来启动新的应用程序
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				
				//指定意图启动哪个应用程序
				intent.setClassName("android.te32", "android.te32.MainActivity");
				//第一个参数是包名
				//第二个参数注意要是完整的类名路径，不要忘记加上包名！
				//这个必须要保证已经在手机上安装好了想要启动的应用程序，不然无法找到该应用程序，发生错误。
				
				startActivity(intent);
			}
		});
	}

	private void init() {
		// TODO Auto-generated method stub
		bt1=(Button)this.findViewById(R.id.bt1);
		bt2=(Button)this.findViewById(R.id.bt2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
