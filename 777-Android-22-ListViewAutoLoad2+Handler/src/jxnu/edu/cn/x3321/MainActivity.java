package jxnu.edu.cn.x3321;

import android.os.Bundle;
import android.os.Handler;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {

	//1.定义成员变量
	ListView lv;
	ArrayList<String> al=new ArrayList<String>();
	Button bt;
	ProgressDialog pd;
	//这次用post交互方式 所以直接定义hd就可以了，不用再sendmessage+handlemessage了！
	Handler hd=new Handler();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		//2.初始化
		init();
		
		//3.准备数据
		for(int i=0;i<10;i++) {
			al.add("tom"+i);
		}
		
		//4.在lv下面增加一个loadmore
		bt=(Button)View.inflate(getApplicationContext(), R.layout.button, null);//inflate填充R.layout.button到这个bt里
		lv.addFooterView(bt);
		//见名知意 加到最下面 这个方便 
		
		//5.自定义适配器
		final MyAdapter ma=new MyAdapter(getApplicationContext(),al);
		
		//6.将适配器加载到lv中
		lv.setAdapter(ma);
		
		//7.定义、注册监听器
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), al.get(arg2), Toast.LENGTH_SHORT).show();
			}
			//al就是一个字符串数组，所以get直接得到字符串！
		});
		bt.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				pd.show();
				//点按钮就立马显示progressdialog！
				
				//思考：这里为什么要用到线程
				//因为pd显示时，主线程就在显示pd了，但是我们现在又需要去加载新的数据，所以这个地方就要用到子线程，子线程做完加载数据的事情之后
				//就可以回到主线程做主线程应该做的工作
				new Thread() {
					 public void run() {
						 //这里没有从服务器取数据来加载所以会很快，为了能看到pd显示出来的效果设置子线程休眠3秒
						 try {
							Thread.sleep(2000);//单位毫秒
							//如果这里不休眠，那pd显示出来几乎看不到，就第一次闪现了一下
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						 for(int i=0;i<10;i++) {
							 al.add("mike"+i);
						 }
						 hd.post(new Runnable() {//Runnable相当于跨到了主线程内执行了

							 //这里就是想在主线程做的工作
							@Override
							public void run() {
								// TODO Auto-generated method stub
								
								ma.notifyDataSetChanged();
								//数据集改变了，通知适配器再封装一次
								
								pd.dismiss();
								//显示出了新数据就让加载圈圈消失
							}
							 
						 });
					 }
				}.start();
			}
		});
		
	}

	private void init() {
		// TODO Auto-generated method stub
		lv=(ListView)this.findViewById(R.id.lv);
		pd=new ProgressDialog(this);
		pd.setCancelable(false);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
