package android.te40;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class DealLoginActivity extends Activity {

	//1.定义成员变量
	Button bt;
	String logState="";//定义一个变量存储登陆状态信息
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_deal_login);
		
		//2.初始化
		init();
		
		//3.
		bt.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//这个intent是用来获得MainActivity传来的信息的，我并不要知道是谁传过来的，收到了数据处理就好
				Intent intent=getIntent();
				
				//如果之前MainActivity中输入的用户名是zxq 那么就是已注册 直接登陆成功
				if(intent.getExtras().getString("userName").equals("zxq")) {
					logState="恭喜你登录成功！";
				}else {
					logState="未找到此用户！登录失败！";
				}
				
				//要传数据回去给MainActivity了 ，又要启动一个新的intent
				Intent intent2=new Intent(DealLoginActivity.this,MainActivity.class);
				
				//把登陆的状态放到intent2里让他带回去给MainActivity
				intent2.putExtra("logState", logState);
				
				//之前有一个ForResult在等结果，这个setResult就负责把结果传回去
				//第一个参数是结果码，第二个参数就是利用intent进行单纯的数据传递，所以API里显示的类型是data
				setResult(RESULT_OK, intent2);
				
				//结束当前的DealLoginActivity
				finish();
			}
		});
	}

	private void init() {
		// TODO Auto-generated method stub
		bt=(Button)this.findViewById(R.id.bt);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.deal_login, menu);
		return true;
	}

}
