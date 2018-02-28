package com.example.weatherforecast;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Xml;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

import com.example.weatherforecast.CityWeather;

public class MainActivity extends Activity {

	//1.定义成员变量
	Button bt;
	Spinner sp_province,sp_city;
	ImageView Day1_img1,Day1_img2,Day2_img1,Day2_img2,Day3_img1,Day3_img2;
	TextView Day1_content,Day2_content,Day3_content,Today_weather;
	ArrayList<String> al_province=new ArrayList<String>();
	ArrayList<String> al_city=new ArrayList<String>();
	MyAdapter ma_province,ma_city;
	ArrayList<String> provinceName=new ArrayList<String>();
	ArrayList<String> cityName=new ArrayList<String>();
	String ProvinceSelected="";
	 
	protected Bitmap bitMap;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//2.初始化
		init();
		
		//3.准备省份数据
		//  异步通信获得所有的省份信息封装进al_province并显示到spinner中！
		String getProvinceWs="http://ws.webxml.com.cn/WebServices/WeatherWS.asmx/getRegionProvince";
		 
		new AsyncTask<String, Void, ArrayList<String>>() {

			@Override
			protected ArrayList<String> doInBackground(String... params) {
				// TODO Auto-generated method stub
				
				//这个数组就是用来存省份的
				ArrayList<String> provinceList=new ArrayList<String>();
				
				try {
					//连接网络
					URL url=new URL(params[0]);							
					HttpURLConnection con=(HttpURLConnection)url.openConnection();
					
					
					//设置属性
					con.setRequestMethod("GET");
					con.setRequestProperty("connection", "keep-alive");
					con.setDoInput(true);//可以下载网络上的数据	
					con.setReadTimeout(5000);
											
					//判断连接是否成功
					int code=con.getResponseCode();
					System.out.println("code:"+code);
					if(code==200){
						
						//Inputstream对象读取网络上的数据
						InputStream is=con.getInputStream();
						
						//XmlPullParser对象解析is,得到天气信息
						XmlPullParser xp=Xml.newPullParser();
						xp.setInput(is, "utf-8");
						int type=xp.getEventType();
				
						while(type!=XmlPullParser.END_DOCUMENT){
							 
							if(type==XmlPullParser.START_TAG){
								if(xp.getName().equals("string")){
									provinceList.add(xp.nextText());
								}		
							}
							
							type=xp.next();
						}			
						
						//做完了就关了都关了
						is.close();
						con.disconnect();
						
					}else{
						Toast.makeText(getApplicationContext(),"网络连接不成功或资源不存在!",Toast.LENGTH_SHORT).show();
					}
					
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return provinceList;
			}

			@Override
			protected void onPostExecute(ArrayList<String> result) {
				// TODO Auto-generated method stub
				//主线程要做的事情
				//把得到的所有省份赋值给al_province存起来
				//Toast.makeText(getApplicationContext(), result.toString(),Toast.LENGTH_SHORT).show();
				al_province.addAll(result);
				//Toast.makeText(getApplicationContext(), al_province.toString(),Toast.LENGTH_SHORT).show();
				
				//String test1[]=new String[5];
				//test1=al_province.get(0).split(",");
				//Toast.makeText(getApplicationContext(),al_province.get(0).split(",")[0],Toast.LENGTH_SHORT).show();
				
				
				 
		 
				//String provinceName[]=new String[al_province.size()];
				for(int i=0;i<al_province.size();i++) {
					provinceName.add(al_province.get(i).split(",")[0]);
				}
				
				//5.自定义适配器封装省份数据
				ma_province=new MyAdapter(getApplicationContext(),provinceName);
						
				//6.将适配器加载到sp_province上
				sp_province.setAdapter(ma_province);

				
				
				sp_province.setOnItemSelectedListener(new OnItemSelectedListener() {
					
					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
						// TODO Auto-generated method stub
						ProvinceSelected=sp_province.getSelectedItem().toString();
						//Toast.makeText(getApplicationContext(),cityName,Toast.LENGTH_SHORT).show();
					
						
						String getCityWs="http://ws.webxml.com.cn/WebServices/WeatherWS.asmx/getSupportCityString?theRegionCode="+ProvinceSelected;
						
						new AsyncTask<String,Void, ArrayList<String>>(){

							@Override
							protected ArrayList<String> doInBackground(String... params) {
								// TODO Auto-generated method stub
								ArrayList<String> cityList=new ArrayList<String>();
								
								
								try {
									//连接网络
									URL url=new URL(params[0]);							
									HttpURLConnection con=(HttpURLConnection)url.openConnection();
									
									
									//设置属性
									con.setRequestMethod("GET");
									con.setRequestProperty("connection", "keep-alive");
									con.setDoInput(true);//可以下载网络上的数据	
									con.setReadTimeout(5000);
															
									//判断连接是否成功
									int code=con.getResponseCode();
									System.out.println("code:"+code);
									if(code==200){
										
										//Inputstream对象读取网络上的数据
										InputStream is=con.getInputStream();
										
										//XmlPullParser对象解析is,得到天气信息
										XmlPullParser xp=Xml.newPullParser();
										xp.setInput(is, "utf-8");
										int type=xp.getEventType();
								
										while(type!=XmlPullParser.END_DOCUMENT){
											 
											if(type==XmlPullParser.START_TAG){
												if(xp.getName().equals("string")){
													cityList.add(xp.nextText());
												}		
											}
											
											type=xp.next();
										}			
										
										//做完了就关了都关了
										is.close();
										con.disconnect();
										
									}else{
										Toast.makeText(getApplicationContext(),"网络连接不成功或资源不存在!",Toast.LENGTH_SHORT).show();
									}
									
									
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
								
								
								
								return cityList;
							}

							@Override
							protected void onPostExecute(ArrayList<String> result) {
								// TODO Auto-generated method stub
								//Toast.makeText(getApplicationContext(),result.toString(),Toast.LENGTH_SHORT).show();
								
								//result就得到了当前省份的所有城市
								
								if(al_city.size()!=0) {
									al_city.clear();
									al_city.addAll(result);
								}else {
									al_city.addAll(result);
								}
								
								if(cityName.size()!=0) {
									cityName.clear();
									for(int i=0;i<al_city.size();i++) {
										cityName.add(al_city.get(i).split(",")[0]);
									}
								}else {
									for(int i=0;i<al_city.size();i++) {
										cityName.add(al_city.get(i).split(",")[0]);
									}
								}
								
								//cityName就存了当前省份的所有城市（去掉数字的
								
								//自定义适配器封装省份数据
								ma_city=new MyAdapter(getApplicationContext(),cityName);
										
								//将适配器加载到sp_province上
								sp_city.setAdapter(ma_city);
								
								
								bt.setOnClickListener(new View.OnClickListener() {
									
									@Override
									public void onClick(View v) {
										// TODO Auto-generated method stub
										//Toast.makeText(getApplicationContext(),ProvinceSelected,Toast.LENGTH_SHORT).show();
										String CitySelected;//符号变量
										
										
										//得到spinner当前用户选择的那一个TextView
										TextView tv=(TextView)sp_city.getChildAt(0);
										CitySelected=tv.getText().toString().trim();
										
										
										//Toast.makeText(getApplicationContext(),ProvinceSelected+CitySelected,Toast.LENGTH_SHORT).show();
										
										//-------------------GET不知道为什么没有用-----------------------
									    /*String cityweatherWs="http://ws.webxml.com.cn/WebServices/WeatherWS.asmx/getWeather?theCityCode=南昌&theUserID=";
										 
										
										new AsyncTask<String, Void, CityWeather>() {

											@Override
											protected CityWeather doInBackground(String... params) {
												// TODO Auto-generated method stub
												CityWeather  cityWeather=null;
												
												try {
													//(3)连接网络
													
													
													
													URL url=new URL(params[0]);							
													HttpURLConnection con=(HttpURLConnection)url.openConnection();
													
													//(4)设置属性
													con.setRequestMethod("GET");
													con.setRequestProperty("connection", "keep-alive");
													con.setDoInput(true);//可以下载网络上的数据	
													con.setReadTimeout(5000);
													
															
													//(5)判断连接是否成功
													int code=con.getResponseCode();
												 
													if(code==200){
														cityWeather=new CityWeather();
														//(6).Inputstream对象读取网络上的数据
														InputStream is=con.getInputStream();
														
														//(7).XmlPullParser对象解析is,得到天气信息
													 
														XmlPullParser xp=Xml.newPullParser();
														xp.setInput(is, "utf-8");
														int type=xp.getEventType();
														int index=1;
														while(type!=XmlPullParser.END_DOCUMENT){
															 
															if(type==XmlPullParser.START_TAG){
																if(xp.getName().equals("string")){
																	 
																	if(index==1){
																		cityWeather.setCityName(xp.nextText());
																	}else if(index==5){
																		cityWeather.setWeather(xp.nextText());
																	}else if(index==7){
																		cityWeather.setSum(xp.nextText());
																	}else if(index==8){
																		cityWeather.setDate(xp.nextText());
																	}else if(index==9){
																		cityWeather.setCap(xp.nextText());
																	}else if(index==11){
																		cityWeather.setImg1(xp.nextText());
																	}else if(index==12){
																		cityWeather.setImg2(xp.nextText());
																	}
																	index++;
																}							
																
															}
															if(index>12){
																break;
															}
															type=xp.next();
														}									
														is.close();
														con.disconnect();
														
													}else{
														Toast.makeText(getApplicationContext(), 
																"网络连接不成功或资源不存在!"
																, Toast.LENGTH_SHORT).show();
													}
													
													
												} catch (Exception e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
												
												return cityWeather;
											}

											@Override
											protected void onPostExecute(CityWeather cw) {
												// TODO Auto-generated method stub
												//(8).把天气信息写到textView上
												String htmlStr="<font color='red'>"
														            +cw.getCityName()
														        +"</font></br>"
														        +"<font color='blue'>"
													                +cw.getWeather()
													            +"</font></br>"
																+"<font color='black'>"
												                     +cw.getDate()
												                +"</font></br>"
												                +"<font color='yellow'>"
												                     +cw.getSum()
												                +"</font></br>"
												                +"<font color='green'>"
												                     +cw.getCap()
												                +"</font></br>";
												
												Today_weather.setText(Html.fromHtml(htmlStr));
												
											}

											
											
											
										}.execute(cityweatherWs);*/
										
										
										
										
										
										///////---------------------POST有用--------------------------
										String cityWs="http://ws.webxml.com.cn/"
												+ "WebServices/WeatherWS.asmx/getWeather";
										String params="theCityCode="+CitySelected+"&theUserID=";
										new AsyncTask<String, Void, CityWeather>() {

											@Override
											protected CityWeather doInBackground(String... params) {
												// TODO Auto-generated method stub
												CityWeather  cityWeather=null;
												
												try {
													//(3)连接网络
													 
													byte[] bt=params[1].getBytes("utf-8");
													
													URL url=new URL(params[0]);							
													HttpURLConnection con=
															(HttpURLConnection)url.openConnection();
													//(4)设置属性
													con.setRequestMethod("POST");
													
													con.setRequestProperty("Content-Length", ""+bt.length);
													//设置请求的内容类型
													con.setRequestProperty("Content-Type", 
															"application/x-www-form-urlencoded");
												
													con.setRequestProperty("connection", "keep-alive");
													con.setDoInput(true);//可以下载网络上的数据	
													con.setReadTimeout(5000);
													
													//通过OutputStream对象把参数传给webService
													OutputStream out=con.getOutputStream();
													out.write(bt);								
													
													//(5)判断连接是否成功
													int code=con.getResponseCode();
													System.out.println("code:"+code);
													if(code==200){
														cityWeather=new CityWeather();
														//(6).Inputstream对象读取网络上的数据
														InputStream is=con.getInputStream();
														
														//(7).XmlPullParser对象解析is,得到天气信息
														System.out.println("is:"+is);
														XmlPullParser xp=Xml.newPullParser();
														xp.setInput(is, "utf-8");
														int type=xp.getEventType();
														int index=1;
														while(type!=XmlPullParser.END_DOCUMENT){
															System.out.println("type:"+type);
															if(type==XmlPullParser.START_TAG){
																if(xp.getName().equals("string")){
																	System.out.println("index:"+index);
																	if(index==1){
																		cityWeather.setCityName(xp.nextText());
																	}else if(index==5){
																		cityWeather.setWeather(xp.nextText());
																	}else if(index==7){
																		cityWeather.setSum(xp.nextText());
																	}else if(index==8){
																		cityWeather.setDate(xp.nextText());
																	}else if(index==9){
																		cityWeather.setCap(xp.nextText());
																	}else if(index==11){
																	 
																		cityWeather.setTodayimg1(xp.nextText());
																	}else if(index==12){
																		cityWeather.setTodayimg2(xp.nextText());
																	}else if(index==13) {
																		cityWeather.setDate1(xp.nextText());
																	}else if(index==14) {
																		cityWeather.setCap1(xp.nextText());
																	}else if(index==15) {
																		cityWeather.setWeather1(xp.nextText());
																	}else if(index==16) {
																		cityWeather.setDay1img1(xp.nextText());
																	}else if(index==17) {
																		cityWeather.setDay1img2(xp.nextText());
																	}else if(index==18) {
																		cityWeather.setDate2(xp.nextText());
																	}else if(index==19) {
																		cityWeather.setCap2(xp.nextText());
																	}else if(index==20) {
																		cityWeather.setWeather2(xp.nextText());
																	}else if(index==21) {
																		cityWeather.setDay2img1(xp.nextText());
																	}else if(index==22) {
																		cityWeather.setDay2img2(xp.nextText());
																	}else if(index==23) {
																		cityWeather.setDate3(xp.nextText());
																	}else if(index==24) {
																		cityWeather.setCap3(xp.nextText());
																	}else if(index==25) {
																		cityWeather.setWeather3(xp.nextText());
																	}else if(index==26) {
																		cityWeather.setDay3img1(xp.nextText());
																	}else if(index==27) {
																		cityWeather.setDay3img2(xp.nextText());
																	}
																	index++;
																}							
																
															}
															if(index>28){
																break;
															}
															type=xp.next();
														}									
														is.close();
														con.disconnect();
													}else{
														Toast.makeText(getApplicationContext(), 
																"网络连接不成功或资源不存在!"
																, Toast.LENGTH_SHORT).show();
													}
													
													
												} catch (Exception e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
												
												return cityWeather;
											}

											@Override
											protected void onPostExecute(CityWeather cw) {
												// TODO Auto-generated method stub
												//(8).把天气信息写到textView上
												String htmlStr="<font color='red'>"
														            +cw.getCityName()
														        +"</font><br>"
														        +"<font color='blue'>"
													                +cw.getWeather()
													            +"</font><br>"
																+"<font color='black'>"
												                     +cw.getDate()
												                +"</font><br>"
												                +"<font color='blue'>"
												                
												                     +cw.getSum()
												                +"</font><br>"
												                +"<font color='black'>"
												                     +cw.getCap()
												                +"</font><br>";
												Today_weather.setText(Html.fromHtml(htmlStr));
											 
												
												//----------设置三天的天气预报---------
												String day1img1name=cw.getDay1img1();
												String day1img2name=cw.getDay1img2();
												showImage(day1img1name,Day1_img1);
												showImage(day1img2name,Day1_img2);
												String htmlStr1=
											        
													"<font color='black'>"
									                     +cw.getDate1()
									                +"</font><br>"
									                
									                +"<font color='black'>"
									                     +cw.getCap1()
									                +"</font><br>"
									                +"<font color='blue'>"
										                +cw.getWeather1()
										            +"</font><br>";
												Day1_content.setText(Html.fromHtml(htmlStr1));
												
												
												
												
												
												String day2img1name=cw.getDay2img1();
												String day2img2name=cw.getDay2img2();
												showImage(day2img1name,Day2_img1);
												showImage(day2img2name,Day2_img2);
												String htmlStr2=
												        
														"<font color='black'>"
										                     +cw.getDate2()
										                +"</font><br>"
										                
										                +"<font color='black'>"
										                     +cw.getCap2()
										                +"</font><br>"
										                +"<font color='blue'>"
											                +cw.getWeather2()
											            +"</font><br>";
													Day2_content.setText(Html.fromHtml(htmlStr2));
												
												
												String day3img1name=cw.getDay3img1();
												String day3img2name=cw.getDay3img2();
												showImage(day3img1name,Day3_img1);
												showImage(day3img2name,Day3_img2);
												String htmlStr3=
												        
														"<font color='black'>"
										                     +cw.getDate3()
										                +"</font><br>"
										                
										                +"<font color='black'>"
										                     +cw.getCap3()
										                +"</font><br>"
										                +"<font color='blue'>"
											                +cw.getWeather3()
											            +"</font><br>";
													Day3_content.setText(Html.fromHtml(htmlStr3));
												
												
											}

											
											
											
										}.execute(cityWs,params);
										
										
										
										
										
										
									}
								});
								
								
								
							}
							
						}.execute(getCityWs);
					
					}
				});
				
				 
				
			}

			
		}.execute(getProvinceWs);
		
 
		 
		
	}
	
	
	private void showImage(String img1Name,final ImageView iv) {
		// TODO Auto-generated method stub
		String dowUrl="http://www.webxml.com.cn/images/weather/"+img1Name;
		//取得缓存路径
		File file=getCacheDir();
		//判断缓存目录中是否存在imag1Name图片
		File fileName=new File(file,img1Name);
		if(fileName.exists()){
			//nothing
		}else{
			//5.启动异步任务现下载图片
			/*
			 * AsyncTask第一个参数类型和execute()、doInBackground()中的参数类型一致
			 * AsyncTask第三个参数类型和doInBackground()的返回类型一致并且和
			 * onPostExecute()中的参数类型一致
			 */
             new AsyncTask<String,Void,Bitmap>(){

				@Override
				protected Bitmap doInBackground(String... params) {
					// TODO Auto-generated method stub
					try {
						//(1)连接网络
						URL imageUrl=new URL(params[0]);
						HttpURLConnection con=(HttpURLConnection)imageUrl
								.openConnection();
						//设置连接的相关属性
						con.setRequestMethod("GET");
						con.setRequestProperty("connection", "keep-alive");
						con.setDoInput(true);//可以下载网络上的数据
		
						con.setReadTimeout(5000);
						
						//(2)判断连接是否成功
						int code=con.getResponseCode();
						System.out.println("code:"+code);
						if(code==200){
							//(3).Inputstream对象读取网络上的数据
							InputStream is=con.getInputStream();
						/*
						 * 写到缓存
							byte[] bt=new byte[is.available()];
							is.read(bt);
							is.close();
							*/
							bitMap=BitmapFactory.decodeStream(is);
						}else{
							Toast.makeText(getApplicationContext(), 
									"网络连接不成功或图片不存在!"
									, Toast.LENGTH_SHORT).show();
						}
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					return bitMap;
				}

				@Override
				protected void onPostExecute(Bitmap result) {
					// TODO Auto-generated method stub
					iv.setImageBitmap(result);
				}						
				
			}.execute(dowUrl);
		}
		
	}
	

	private void init() {
		// TODO Auto-generated method stub
		bt=(Button)this.findViewById(R.id.bt);
		sp_province=(Spinner)this.findViewById(R.id.sp_province);
		sp_city=(Spinner)this.findViewById(R.id.sp_city);
		Day1_img1=(ImageView)this.findViewById(R.id.day1_img1);
		Day1_img2=(ImageView)this.findViewById(R.id.day1_img2);
		Day2_img1=(ImageView)this.findViewById(R.id.day2_img1);
		Day2_img2=(ImageView)this.findViewById(R.id.day2_img2);
		Day3_img1=(ImageView)this.findViewById(R.id.day3_img1);
		Day3_img2=(ImageView)this.findViewById(R.id.day3_img2);
		Day1_content=(TextView)this.findViewById(R.id.day1_Content);
		Day2_content=(TextView)this.findViewById(R.id.day2_Content);
		Day3_content=(TextView)this.findViewById(R.id.day3_Content);
		Today_weather=(TextView)this.findViewById(R.id.today_weather);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
