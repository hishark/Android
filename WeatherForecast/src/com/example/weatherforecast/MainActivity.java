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

	//1.�����Ա����
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
		
		//2.��ʼ��
		init();
		
		//3.׼��ʡ������
		//  �첽ͨ�Ż�����е�ʡ����Ϣ��װ��al_province����ʾ��spinner�У�
		String getProvinceWs="http://ws.webxml.com.cn/WebServices/WeatherWS.asmx/getRegionProvince";
		 
		new AsyncTask<String, Void, ArrayList<String>>() {

			@Override
			protected ArrayList<String> doInBackground(String... params) {
				// TODO Auto-generated method stub
				
				//����������������ʡ�ݵ�
				ArrayList<String> provinceList=new ArrayList<String>();
				
				try {
					//��������
					URL url=new URL(params[0]);							
					HttpURLConnection con=(HttpURLConnection)url.openConnection();
					
					
					//��������
					con.setRequestMethod("GET");
					con.setRequestProperty("connection", "keep-alive");
					con.setDoInput(true);//�������������ϵ�����	
					con.setReadTimeout(5000);
											
					//�ж������Ƿ�ɹ�
					int code=con.getResponseCode();
					System.out.println("code:"+code);
					if(code==200){
						
						//Inputstream�����ȡ�����ϵ�����
						InputStream is=con.getInputStream();
						
						//XmlPullParser�������is,�õ�������Ϣ
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
						
						//�����˾͹��˶�����
						is.close();
						con.disconnect();
						
					}else{
						Toast.makeText(getApplicationContext(),"�������Ӳ��ɹ�����Դ������!",Toast.LENGTH_SHORT).show();
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
				//���߳�Ҫ��������
				//�ѵõ�������ʡ�ݸ�ֵ��al_province������
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
				
				//5.�Զ�����������װʡ������
				ma_province=new MyAdapter(getApplicationContext(),provinceName);
						
				//6.�����������ص�sp_province��
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
									//��������
									URL url=new URL(params[0]);							
									HttpURLConnection con=(HttpURLConnection)url.openConnection();
									
									
									//��������
									con.setRequestMethod("GET");
									con.setRequestProperty("connection", "keep-alive");
									con.setDoInput(true);//�������������ϵ�����	
									con.setReadTimeout(5000);
															
									//�ж������Ƿ�ɹ�
									int code=con.getResponseCode();
									System.out.println("code:"+code);
									if(code==200){
										
										//Inputstream�����ȡ�����ϵ�����
										InputStream is=con.getInputStream();
										
										//XmlPullParser�������is,�õ�������Ϣ
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
										
										//�����˾͹��˶�����
										is.close();
										con.disconnect();
										
									}else{
										Toast.makeText(getApplicationContext(),"�������Ӳ��ɹ�����Դ������!",Toast.LENGTH_SHORT).show();
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
								
								//result�͵õ��˵�ǰʡ�ݵ����г���
								
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
								
								//cityName�ʹ��˵�ǰʡ�ݵ����г��У�ȥ�����ֵ�
								
								//�Զ�����������װʡ������
								ma_city=new MyAdapter(getApplicationContext(),cityName);
										
								//�����������ص�sp_province��
								sp_city.setAdapter(ma_city);
								
								
								bt.setOnClickListener(new View.OnClickListener() {
									
									@Override
									public void onClick(View v) {
										// TODO Auto-generated method stub
										//Toast.makeText(getApplicationContext(),ProvinceSelected,Toast.LENGTH_SHORT).show();
										String CitySelected;//���ű���
										
										
										//�õ�spinner��ǰ�û�ѡ�����һ��TextView
										TextView tv=(TextView)sp_city.getChildAt(0);
										CitySelected=tv.getText().toString().trim();
										
										
										//Toast.makeText(getApplicationContext(),ProvinceSelected+CitySelected,Toast.LENGTH_SHORT).show();
										
										//-------------------GET��֪��Ϊʲôû����-----------------------
									    /*String cityweatherWs="http://ws.webxml.com.cn/WebServices/WeatherWS.asmx/getWeather?theCityCode=�ϲ�&theUserID=";
										 
										
										new AsyncTask<String, Void, CityWeather>() {

											@Override
											protected CityWeather doInBackground(String... params) {
												// TODO Auto-generated method stub
												CityWeather  cityWeather=null;
												
												try {
													//(3)��������
													
													
													
													URL url=new URL(params[0]);							
													HttpURLConnection con=(HttpURLConnection)url.openConnection();
													
													//(4)��������
													con.setRequestMethod("GET");
													con.setRequestProperty("connection", "keep-alive");
													con.setDoInput(true);//�������������ϵ�����	
													con.setReadTimeout(5000);
													
															
													//(5)�ж������Ƿ�ɹ�
													int code=con.getResponseCode();
												 
													if(code==200){
														cityWeather=new CityWeather();
														//(6).Inputstream�����ȡ�����ϵ�����
														InputStream is=con.getInputStream();
														
														//(7).XmlPullParser�������is,�õ�������Ϣ
													 
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
																"�������Ӳ��ɹ�����Դ������!"
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
												//(8).��������Ϣд��textView��
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
										
										
										
										
										
										///////---------------------POST����--------------------------
										String cityWs="http://ws.webxml.com.cn/"
												+ "WebServices/WeatherWS.asmx/getWeather";
										String params="theCityCode="+CitySelected+"&theUserID=";
										new AsyncTask<String, Void, CityWeather>() {

											@Override
											protected CityWeather doInBackground(String... params) {
												// TODO Auto-generated method stub
												CityWeather  cityWeather=null;
												
												try {
													//(3)��������
													 
													byte[] bt=params[1].getBytes("utf-8");
													
													URL url=new URL(params[0]);							
													HttpURLConnection con=
															(HttpURLConnection)url.openConnection();
													//(4)��������
													con.setRequestMethod("POST");
													
													con.setRequestProperty("Content-Length", ""+bt.length);
													//�����������������
													con.setRequestProperty("Content-Type", 
															"application/x-www-form-urlencoded");
												
													con.setRequestProperty("connection", "keep-alive");
													con.setDoInput(true);//�������������ϵ�����	
													con.setReadTimeout(5000);
													
													//ͨ��OutputStream����Ѳ�������webService
													OutputStream out=con.getOutputStream();
													out.write(bt);								
													
													//(5)�ж������Ƿ�ɹ�
													int code=con.getResponseCode();
													System.out.println("code:"+code);
													if(code==200){
														cityWeather=new CityWeather();
														//(6).Inputstream�����ȡ�����ϵ�����
														InputStream is=con.getInputStream();
														
														//(7).XmlPullParser�������is,�õ�������Ϣ
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
																"�������Ӳ��ɹ�����Դ������!"
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
												//(8).��������Ϣд��textView��
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
											 
												
												//----------�������������Ԥ��---------
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
		//ȡ�û���·��
		File file=getCacheDir();
		//�жϻ���Ŀ¼���Ƿ����imag1NameͼƬ
		File fileName=new File(file,img1Name);
		if(fileName.exists()){
			//nothing
		}else{
			//5.�����첽����������ͼƬ
			/*
			 * AsyncTask��һ���������ͺ�execute()��doInBackground()�еĲ�������һ��
			 * AsyncTask�������������ͺ�doInBackground()�ķ�������һ�²��Һ�
			 * onPostExecute()�еĲ�������һ��
			 */
             new AsyncTask<String,Void,Bitmap>(){

				@Override
				protected Bitmap doInBackground(String... params) {
					// TODO Auto-generated method stub
					try {
						//(1)��������
						URL imageUrl=new URL(params[0]);
						HttpURLConnection con=(HttpURLConnection)imageUrl
								.openConnection();
						//�������ӵ��������
						con.setRequestMethod("GET");
						con.setRequestProperty("connection", "keep-alive");
						con.setDoInput(true);//�������������ϵ�����
		
						con.setReadTimeout(5000);
						
						//(2)�ж������Ƿ�ɹ�
						int code=con.getResponseCode();
						System.out.println("code:"+code);
						if(code==200){
							//(3).Inputstream�����ȡ�����ϵ�����
							InputStream is=con.getInputStream();
						/*
						 * д������
							byte[] bt=new byte[is.available()];
							is.read(bt);
							is.close();
							*/
							bitMap=BitmapFactory.decodeStream(is);
						}else{
							Toast.makeText(getApplicationContext(), 
									"�������Ӳ��ɹ���ͼƬ������!"
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
