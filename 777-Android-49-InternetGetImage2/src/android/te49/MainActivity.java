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

	
	//1.�����Ա����
	EditText etUrl;
	Button btDownload;
	ImageView iv;
	Bitmap bitmap=null;
	URL imageUrl=null;
	String urlstr=null;
	 
	//ʹ���첽ͨ�Ų���Ҫ��hanlder�� �о����Ի���һЩ
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//2.��ʼ��
		init();
		
		//3.����ע���¼�������
		btDownload.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				//�����߳�
				//�Ӱٶ�����ͼƬ����������߳����� 
				//������������ʾͼ���������̵߳�����
				//ǣ�浽�߳�֮���ͨ��
				
				//4.��androidmanifest.xml�����ӷ��������Ȩ��
				urlstr=etUrl.getText().toString().trim();
				if(urlstr==null||urlstr.equals("")) {
					Toast.makeText(getApplicationContext(), "��ַ����Ϊ��", Toast.LENGTH_SHORT).show();
					
				}else {
					
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
					new AsyncTask<String,Void,Bitmap>() {

						
						//AsyncTask���ں�̨���еģ������첽����֮������ͺ�̨���У���������˼����
						//����ͷҪ���������൱te48���߳��е�run()Ҫ��������
						//ע�⣺params��һ�����ⳤ�ȵ�params�ַ������� ������
						//���鳤����execute�еĲ�������������
						//doInBackground���յĲ�������execute�������Ĳ���
						@Override
						protected Bitmap doInBackground(String... params) {
							// TODO Auto-generated method stub
                               try {
								
								//(1).�������� 
								//�õ���Ҫ���ʵ�ͼƬ�ĵ�ַ ��stringת��Ϊurl
								imageUrl=new URL(params[0]);
								//ͨ�����ȥ���������ϵ���Դ���� 
								//����� ����һ���ܵ�����˼
								HttpURLConnection con=(HttpURLConnection)imageUrl.openConnection();
								//�������ӵ��������
								con.setRequestMethod("GET");//get�ӷ���������Դ post������������Դ
								con.setRequestProperty("connection", "keep-alive");//�ɳ�������
								con.setDoInput(true);//�������������ϵ���Դ
								//con.setDoOutput(true);//�����ϴ���Դ��������
								con.setReadTimeout(5000);//��ȡʱ�䳬��5s�Ͷϵ� ��������
								
								//(2).��ȡ��Դ֮ǰҪ���ж������Ƿ�ɹ�
								int code=con.getResponseCode();
								if(code==200) {
									//(3).Inputstream��������ȡ�����ϵ���Դ
									InputStream is=con.getInputStream();
									
									/*byte[] bt=new byte[is.available()];
									is.read(bt);
									is.close();*/
									
									//����ֱ�����±���Ϊbitmap����
									bitmap=BitmapFactory.decodeStream(is);
									
									
								}else {
									Toast.makeText(getApplicationContext(), "�������Ӳ��ɹ���ͼƬ�����ڣ�", Toast.LENGTH_SHORT).show();
									
								}
								
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
                               
							return bitmap;
						}

						//��ǰִ̨�� ����Ҫ�Ĺ���
						//Ҳ�����ϸ��������������߳���������
						@Override
						protected void onPostExecute(Bitmap result) {
							// TODO Auto-generated method stub
							//result���Ǵ�doInBackground���ص�bitmap��
							
							iv.setImageBitmap(result);
						}
						
						
						
					}.execute(urlstr);
					//�첽������Ҫִ�У����Դ����� ���ﴫ�ľ���ͼƬ��URL
					//���Դ���������������
					//�������֮���ö��Ÿ�������
					 
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
