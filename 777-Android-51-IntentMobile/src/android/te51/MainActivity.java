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

	//1.�����Ա����
	EditText etPhone;
	Button btSearch;
	TextView tv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//2.��ʼ��
		init();
		
		//3.����ע���¼�������
		btSearch.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//4.��manifest��ӷ�������Ȩ��
				
				String phone=etPhone.getText().toString().trim();
				if(phone==null||phone.equals("")) {
					Toast.makeText(getApplicationContext(), "�������ֻ���", Toast.LENGTH_SHORT).show();
					
				}else {
					//�������webservice��url
					String ws="http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx"
							+ "/getMobileCodeInfo"
							+ "?"
							//�����ø�ͨ���${}
							+ "mobileCode=${mobile}&userID=";
					ws=ws.replace("${mobile}", phone);
					
					//5.�����첽��������ͼƬ(˵���˵ײ�Ҳ�����߳�ʵ�ֵ�)
					//<>���������������ĺ��壺
					/*
					 * ��һ������������һ��Ҫ��execute()�еĲ�����doInBackground()�еĲ�������һ��
					 * 
					 * �ڶ��������Ȳ��� һ�㶼Ϊ�� Void
					 * 
					 * ������������doInBackground()�ķ�������һ��
					 * ���Һ�onPostExecute()�еĲ�������һ��
					 */
					new AsyncTask<String,Void,String>() {

						
						//AsyncTask���ں�̨���еģ������첽����֮������ͺ�̨���У���������˼����
						//����ͷҪ���������൱te48���߳��е�run()Ҫ��������
						//ע�⣺params��һ�����ⳤ�ȵ�params�ַ������� ������
						//���鳤����execute�еĲ�������������
						//doInBackground���յĲ�������execute�������Ĳ���
						@Override
						protected String doInBackground(String... params) {
							// TODO Auto-generated method stub
                               
							String phoneNumber="";
							try {
								
								//(1).�������� 
								//�õ���Ҫ���ʵ�ͼƬ�ĵ�ַ ��stringת��Ϊurl
								URL url=new URL(params[0]);
								//ͨ�����ȥ���������ϵ���Դ���� 
								//����� ����һ���ܵ�����˼
								HttpURLConnection con=(HttpURLConnection)url.openConnection();
								//�������ӵ��������
								con.setRequestMethod("GET");//get�ӷ���������Դ post������������Դ
								con.setRequestProperty("connection", "keep-alive");//�ɳ�������
								con.setDoInput(true);//�������������ϵ���Դ
								//webservice�����ύ����
								//con.setDoOutput(true);//�����ϴ���Դ��������
								con.setReadTimeout(5000);//��ȡʱ�䳬��5s�Ͷϵ� ��������
								
								//(2).��ȡ��Դ֮ǰҪ���ж������Ƿ�ɹ�
								int code=con.getResponseCode();
								if(code==200) {
									//(3).Inputstream��������ȡ�����ϵ���Դ
									InputStream is=con.getInputStream();
									
									//(4).����XmlPullParser����������is���õ�mobile��Ӧ�����ڵ�
									XmlPullParser xp=Xml.newPullParser();
									
									//������is�е�xml�ĵ���Ϊ��������xp
									xp.setInput(is,"utf-8");
									
									//�õ�xml�ĵ����¼�����
									int type=xp.getEventType();//���Ե������ݿ��е��α�
									//û����ĩβ��һֱ��
									while(type!=xp.END_DOCUMENT) {
										//START_TAG��һ����ǩ��ʼ�ı��
										if(type==xp.START_TAG) {
											if(xp.getName().equals("string")) {
												 
												phoneNumber=xp.nextText();
												break;
											}
										}
										
										type=xp.next();
									}
									is.close();
									//�ǵöϿ����� 
									con.disconnect();
									
								}else {
									//Toast.makeText(getApplicationContext(), "�������Ӳ��ɹ�����Դ�����ڣ�", Toast.LENGTH_SHORT).show();
									
								}
								
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
                               
							return phoneNumber;
						}

						//��ǰִ̨�� ����Ҫ�Ĺ���
						//Ҳ�����ϸ��������������߳���������
						@Override
						protected void onPostExecute(String result) {
							// TODO Auto-generated method stub
							//result���Ǵ�doInBackground���ص�bitmap��
							
							tv.setText(result);
						}
						
						
						
					}.execute(ws);
					//�첽������Ҫִ�У����Դ����� ���ﴫ�ľ���ͼƬ��URL
					//���Դ���������������
					//�������֮���ö��Ÿ�������
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
