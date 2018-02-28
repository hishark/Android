package android.te56;

import android.os.Bundle;
import android.os.IBinder;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {

	//1.定义成员变量
	Button btStart,btStop;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		//2.初始化
		init();
		
		//3.定义注册事件监听器
		btStart.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//第一种启动方式(不管哪种都要用intent启动)
				Intent intent=new Intent(MainActivity.this,MyService.class);
				
				//第一种启动方式
				//startService(intent);
				
				//第二种启动方式
				//2参 ServiceConnection服务连接
				//3参 系统自动绑定 一般都是这个
				//这种启动方式，不管绑定的成功与否都要告诉访问者
				//service的状态要通过某种渠道告诉访问者，通过啥？通过2参conn的一个方法来实现
				//那么service和访问者之间如何通信呢？？service要得到启动后的对象 还是得通过2参
				//总而言之！访问者可以通过2参中的一个方法得到service对象！
				bindService(intent,conn,BIND_AUTO_CREATE);
				
				
				//======在manifest.xml中配置service
			}
		});
		
		btStop.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(MainActivity.this,MyService.class);
				
				//第一种停止方式
				//stopService(intent);
			
			
				//第二种停止方式
				//解绑 取消服务连接emmm
				unbindService(conn);
			}
		});
	}

	//内部类
	private ServiceConnection conn=new ServiceConnection() {

		//想让访问者和service进行交互就要用这个方法来拿到service对象！
		//Myservice的onbind的方法返回的对象就传给这个方法的2参！！
		//就可以得到已经运行的service的对象
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			
		}
		
	};
	
	private void init() {
		// TODO Auto-generated method stub
		btStart=(Button)this.findViewById(R.id.btStart);
		btStop=(Button)this.findViewById(R.id.btStop);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
