package android.te49;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.te48.R;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.content.AsyncTaskLoader;
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
	 
	//使用异步通信不需要用hanlder啦 感觉人性化了一些
	
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
				//从百度下载图片这个事情用线程来做 
				//在主界面上显示图像又是主线程的事情
				//牵涉到线程之间的通信
				
				//4.在androidmanifest.xml中增加访问网络的权限
				urlstr=etUrl.getText().toString().trim();
				if(urlstr==null||urlstr.equals("")) {
					Toast.makeText(getApplicationContext(), "地址不能为空", Toast.LENGTH_SHORT).show();
					
				}else {
					
					//5.启动异步任务下载图片(说白了底层也是用线程实现的)
					//<>泛型中三个参数的含义：
					/*
					 * 第一个参数的类型一定要和execute()中的参数、doInBackground()中的参数类型一致
					 * 
					 * 第二个参数先不管 一般都为空 Void
					 * 
					 * 第三个参数和doInBackground()的返回类型一致
					 * 并且和onPostExecute()中的参数类型一致
					 */
					new AsyncTask<String,Void,Bitmap>() {

						
						//AsyncTask是在后台运行的！启动异步任务之后立马就后台运行（大概这个意思咯）
						//这里头要做的事情相当te48中线程中的run()要做的事情
						//注意：params是一个任意长度的params字符串数组 ！！！
						//数组长度由execute中的参数个数决定！
						//doInBackground接收的参数就是execute传过来的参数
						@Override
						protected Bitmap doInBackground(String... params) {
							// TODO Auto-generated method stub
                               try {
								
								//(1).连接网络 
								//得到想要访问的图片的地址 把string转换为url
								imageUrl=new URL(params[0]);
								//通过这个去连接网络上的资源对象 
								//差不多是 建立一个管道的意思
								HttpURLConnection con=(HttpURLConnection)imageUrl.openConnection();
								//设置连接的相关属性
								con.setRequestMethod("GET");//get从服务器拿资源 post往服务器传资源
								con.setRequestProperty("connection", "keep-alive");//可持续连接
								con.setDoInput(true);//可以下载网络上的资源
								//con.setDoOutput(true);//可以上传资源到网络上
								con.setReadTimeout(5000);//读取时间超过5s就断掉 放弃啦！
								
								//(2).读取资源之前要先判断连接是否成功
								int code=con.getResponseCode();
								if(code==200) {
									//(3).Inputstream对象来读取网络上的资源
									InputStream is=con.getInputStream();
									
									/*byte[] bt=new byte[is.available()];
									is.read(bt);
									is.close();*/
									
									//可以直接重新编码为bitmap类型
									bitmap=BitmapFactory.decodeStream(is);
									
									
								}else {
									Toast.makeText(getApplicationContext(), "网络链接不成功或图片不存在！", Toast.LENGTH_SHORT).show();
									
								}
								
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
                               
							return bitmap;
						}

						//在前台执行 最主要的功能
						//也就是上个例子中想让主线程做的事情
						@Override
						protected void onPostExecute(Bitmap result) {
							// TODO Auto-generated method stub
							//result就是从doInBackground返回的bitmap！
							
							iv.setImageBitmap(result);
						}
						
						
						
					}.execute(urlstr);
					//异步任务需要执行！可以传参数 这里传的就是图片的URL
					//可以传任意多个参数！！
					//多个参数之间用逗号隔开！！
					 
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
