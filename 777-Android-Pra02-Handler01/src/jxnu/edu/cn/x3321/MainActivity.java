package jxnu.edu.cn.x3321;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.*;


public class MainActivity extends Activity {

	//1.定义成员变量
	TextView tv;
	Button bt;
	
	Handler hd=new Handler() {
		public void handleMessage(Message msg) {
			if(msg.what==1) {
				tv.setText(String.valueOf(msg.obj));
			}
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//2.初始化
		init();
		//3.监听器
		bt.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new Thread() {
					public void run() {
						for(int i=0;i<10;i++) {
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							Message ms=new Message();
							ms.obj="The Number After Update is:"+i;
							ms.what=1;
							hd.sendMessage(ms);
						}
						
					} 
				}.start();
			}
		});
	}

	private void init() {
		// TODO Auto-generated method stub
		tv=(TextView)this.findViewById(R.id.tv);
		bt=(Button)this.findViewById(R.id.bt);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
