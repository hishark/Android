package android.te51;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Xml;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.xmlpull.v1.XmlPullParser;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {

	//1.定义成员变量
	EditText etPhone;
	Button btSearch;
	TextView tv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//2.初始化
		init();
		
		//3.定义注册事件监听器
		btSearch.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//4.在manifest添加访问网络权限
				
				String phone=etPhone.getText().toString().trim();
				if(phone==null||phone.equals("")) {
					Toast.makeText(getApplicationContext(), "请输入手机号", Toast.LENGTH_SHORT).show();
					
				}else {
					//定义访问webservice的url
					String ws="http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx"
							+ "/getMobileCodeInfo"
							+ "?"
							//这里用个通配符${}
							+ "mobileCode=${mobile}&userID=";
					ws=ws.replace("${mobile}", phone);
					
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
					new AsyncTask<String,Void,String>() {

						
						//AsyncTask是在后台运行的！启动异步任务之后立马就后台运行（大概这个意思咯）
						//这里头要做的事情相当te48中线程中的run()要做的事情
						//注意：params是一个任意长度的params字符串数组 ！！！
						//数组长度由execute中的参数个数决定！
						//doInBackground接收的参数就是execute传过来的参数
						@Override
						protected String doInBackground(String... params) {
							// TODO Auto-generated method stub
                               
							String phoneNumber="";
							try {
								
								//(1).连接网络 
								//得到想要访问的图片的地址 把string转换为url
								URL url=new URL(params[0]);
								//通过这个去连接网络上的资源对象 
								//差不多是 建立一个管道的意思
								HttpURLConnection con=(HttpURLConnection)url.openConnection();
								//设置连接的相关属性
								con.setRequestMethod("GET");//get从服务器拿资源 post往服务器传资源
								con.setRequestProperty("connection", "keep-alive");//可持续连接
								con.setDoInput(true);//可以下载网络上的资源
								//webservice不能提交数据
								//con.setDoOutput(true);//可以上传资源到网络上
								con.setReadTimeout(5000);//读取时间超过5s就断掉 放弃啦！
								
								//(2).读取资源之前要先判断连接是否成功
								int code=con.getResponseCode();
								if(code==200) {
									//(3).Inputstream对象来读取网络上的资源
									InputStream is=con.getInputStream();
									
									//(4).调用XmlPullParser对象来解释is，得到mobile对应的所在地
									XmlPullParser xp=Xml.newPullParser();
									
									//把整个is中的xml文档作为参数传给xp
									xp.setInput(is,"utf-8");
									
									//得到xml文档的事件类型
									int type=xp.getEventType();//可以当成数据库中的游标
									//没读到末尾就一直读
									while(type!=xp.END_DOCUMENT) {
										//START_TAG是一个标签开始的标记
										if(type==xp.START_TAG) {
											if(xp.getName().equals("string")) {
												 
												phoneNumber=xp.nextText();
												break;
											}
										}
										
										type=xp.next();
									}
									is.close();
									//记得断开连接 
									con.disconnect();
									
								}else {
									//Toast.makeText(getApplicationContext(), "网络链接不成功或资源不存在！", Toast.LENGTH_SHORT).show();
									
								}
								
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
                               
							return phoneNumber;
						}

						//在前台执行 最主要的功能
						//也就是上个例子中想让主线程做的事情
						@Override
						protected void onPostExecute(String result) {
							// TODO Auto-generated method stub
							//result就是从doInBackground返回的bitmap！
							
							tv.setText(result);
						}
						
						
						
					}.execute(ws);
					//异步任务需要执行！可以传参数 这里传的就是图片的URL
					//可以传任意多个参数！！
					//多个参数之间用逗号隔开！！
				}
			}
		});
	}

	private void init() {
		// TODO Auto-generated method stub
		etPhone=(EditText)this.findViewById(R.id.et);
		btSearch=(Button)this.findViewById(R.id.bt);
		tv=(TextView)this.findViewById(R.id.tv);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
