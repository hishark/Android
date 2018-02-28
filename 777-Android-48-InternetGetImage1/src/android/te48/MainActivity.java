package android.te48;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {

	
	//1.定义成员变量
	EditText etUrl;
	Button btDownload;
	ImageView iv;
	Bitmap bitmap=null;
	URL imageUrl=null;
	String urlstr=null;
	
	
	//(5).定义handler对象
	Handler hd=new Handler() {
		public void handleMessage(Message msg) {
			if(msg.what==0) {
				iv.setImageBitmap(bitmap);
			}
		}
	};
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//2.初始化
		init();
		
		//3.定义注册事件监听器
		btDownload.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				//定义线程
				//从百度下载图片这个事情要用线程来做 
				//而在主界面上显示图像又是主线程要做的事情
				//所以这里又牵涉到了线程之间的通信 要用到handler！利用message实现两个线程之间的通信
				
				//4.记得要在androidmanifest.xml中增加访问网络的权限
				
				urlstr=etUrl.getText().toString().trim();
				if(urlstr==null||urlstr.equals("")) {
					Toast.makeText(getApplicationContext(), "请输入正确的网址", Toast.LENGTH_SHORT).show();
				}else {
					
					//5.定义线程去下载网络上的图片
					//
					new Thread() {
						public void run() {
							try {
								
								//(1).连接网络 
								//得到想要访问的图片的地址，把string转换为url类型
								imageUrl=new URL(urlstr);
								
								//通过con去连接网络上的资源对象 
								//差不多是 建立一个管道的意思
								HttpURLConnection con=(HttpURLConnection)imageUrl.openConnection();
								
								//设置连接的相关属性
								con.setRequestMethod("GET");//GET从服务器拿资源 POST往服务器传资源 计网学啦！
								con.setRequestProperty("connection", "keep-alive");//设置http连接为可持续连接
								con.setDoInput(true);//可以下载网络上的资源
								//con.setDoOutput(true);//可以上传资源到网络上
								//con.setReadTimeout(5000);//如果读取时间超过5s还没有得到就断掉，放弃啦！
								
								//(2).读取资源之前要先要判断连接是否成功  成功返回202 不成功会返回404什么乱七八糟的
								int code=con.getResponseCode();
								if(code==200) {
									
									//(3).利用Inputstream对象来读取网络上的资源
									InputStream is=con.getInputStream();
									
									/*byte[] bt=new byte[is.available()];
									is.read(bt);
									is.close();*/
									
									//可以直接将字节流内的东西重新编码为bitmap类型
									//记得是用BitmapFactory不是用Bitmap！
									bitmap=BitmapFactory.decodeStream(is);
									
								}else {
									Toast.makeText(getApplicationContext(), "网络链接不成功或图片不存在！", Toast.LENGTH_SHORT).show();
								}
								
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							
							//(4).图片下载完成后，通过handler对象通知主线程来更新imageview
							Message msg=new Message();
							msg.what=0;
							hd.sendMessage(msg);
							
							//(5).定义Handler对象 这一段代码在最顶上定义成员变量那里 记得要加上handleMessage方法噢
						}
					}.start();
				}
				
			}
		});
		
		
	}

	private void init() {
		// TODO Auto-generated method stub
		etUrl=(EditText)this.findViewById(R.id.et);
		btDownload=(Button)this.findViewById(R.id.bt);
		iv=(ImageView)this.findViewById(R.id.img);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
