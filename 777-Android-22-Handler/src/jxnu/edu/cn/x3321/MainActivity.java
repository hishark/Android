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
	Button bt;
	TextView tv;
	
	Handler hd=new Handler() {
		public void handleMessage(Message msg) {//这个是在重写Handler里的handleMessage方法
			if(msg.what==1) {
				//不断地更新TextView的值
				tv.setText(String.valueOf(msg.obj));
			}
			//处理线程1的过程
		}
	};
	//message不断发送数据存到栈里，系统会每隔一段时间就会执行handler的handlemessage方法 从栈顶拿出对应的messgae
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//2.初始化
		init();
		
		//3.定义注册事件监听器
		bt.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//生成一个线程   方法：1.实现接口 2.new一个
				new Thread() {
					public void run() {
						for(int i=0;i<10;i++) {
							
							try {
								Thread.sleep(1000);
								//以毫秒为单位
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							//可能会产生异常所以要用trycatch裹起来
							
							//用messgae对象封装好数据传给主线程
							Message ms=new Message();
							ms.obj="更新后的值为："+i;
							//这句就是给ms赋值 是object类型
							ms.what=1;//what表示数据的类型或者是标识 其他子线程what为2 相当于编个号
							//接下来通过什么方式发给主线程 通过什么工具
							hd.sendMessage(ms);
							//hd.post(ms);还可以用这个 用这个就可以不要写sendmessage方法了 下节课讲
						}
					}//这个方法是任何线程都要有的 这是线程的入口 一旦启动线程就开始执行run方法（相当于main）
					//线程可以休眠！（任何子线程都可以休眠 主不可以）
				}.start();
			}
		});
	}

	private void init() {
		// TODO Auto-generated method stub
		bt=(Button)this.findViewById(R.id.bt);
		tv=(TextView)this.findViewById(R.id.tv);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
