package android.te41;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	//1.定义成员变量
	Button bt1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//创建activity...
		System.out.println("1:create...");
		
		//2.初始化
		init();
		
		//3.注册定义事件监听器
		bt1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(MainActivity.this,BActivity.class);
				 
				startActivityForResult(intent, 101);
				//这里不需要用这个 直接start就可以了吧 我连处理函数都没加耶 那就加一个hello玩一下咯
			
				//startActivity(intent);
			}
		});
	}

	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if(requestCode==101&&resultCode==102) {
			Toast.makeText(getApplicationContext(), "hello", Toast.LENGTH_LONG).show();
			
		}
		
		
		super.onActivityResult(requestCode, resultCode, data);
	}



	private void init() {
		// TODO Auto-generated method stub
		bt1=(Button)this.findViewById(R.id.bt1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	//------------------------一大堆回调函数----------------------------

	
	

	

	//开始啦
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		System.out.println("2:Start...");
		super.onStart();
	}
	
	//显示成功啦
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		System.out.println("3:界面显示成功...");
		super.onResume();
	}

	//启动BActivity之后MainActivity就被遮挡啦！
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		System.out.println("4:被其他组件遮挡...");
		super.onPause();
	}
	
	//被遮挡之后当然就看不到了
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		System.out.println("5:看不见了...");
		super.onStop();
	}
	
	//点B那边的返回之后又重新启动啦！ 然后就继续从onstart开始了！
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		System.out.println("6:重新启动...");
		super.onRestart();
	}
	
	//这里按返回直接重新启动了，没有到这一步
		@Override
		protected void onDestroy() {
			// TODO Auto-generated method stub
			System.out.println("7:删除了...");
			super.onDestroy();
		}

		
		

		

}
