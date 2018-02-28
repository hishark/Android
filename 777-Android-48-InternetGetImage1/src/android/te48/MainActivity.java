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

	
	//1.�����Ա����
	EditText etUrl;
	Button btDownload;
	ImageView iv;
	Bitmap bitmap=null;
	URL imageUrl=null;
	String urlstr=null;
	
	
	//(5).����handler����
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
		
		//2.��ʼ��
		init();
		
		//3.����ע���¼�������
		btDownload.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				//�����߳�
				//�Ӱٶ�����ͼƬ�������Ҫ���߳����� 
				//��������������ʾͼ���������߳�Ҫ��������
				//����������ǣ�浽���߳�֮���ͨ�� Ҫ�õ�handler������messageʵ�������߳�֮���ͨ��
				
				//4.�ǵ�Ҫ��androidmanifest.xml�����ӷ��������Ȩ��
				
				urlstr=etUrl.getText().toString().trim();
				if(urlstr==null||urlstr.equals("")) {
					Toast.makeText(getApplicationContext(), "��������ȷ����ַ", Toast.LENGTH_SHORT).show();
				}else {
					
					//5.�����߳�ȥ���������ϵ�ͼƬ
					//
					new Thread() {
						public void run() {
							try {
								
								//(1).�������� 
								//�õ���Ҫ���ʵ�ͼƬ�ĵ�ַ����stringת��Ϊurl����
								imageUrl=new URL(urlstr);
								
								//ͨ��conȥ���������ϵ���Դ���� 
								//����� ����һ���ܵ�����˼
								HttpURLConnection con=(HttpURLConnection)imageUrl.openConnection();
								
								//�������ӵ��������
								con.setRequestMethod("GET");//GET�ӷ���������Դ POST������������Դ ����ѧ����
								con.setRequestProperty("connection", "keep-alive");//����http����Ϊ�ɳ�������
								con.setDoInput(true);//�������������ϵ���Դ
								//con.setDoOutput(true);//�����ϴ���Դ��������
								//con.setReadTimeout(5000);//�����ȡʱ�䳬��5s��û�еõ��Ͷϵ�����������
								
								//(2).��ȡ��Դ֮ǰҪ��Ҫ�ж������Ƿ�ɹ�  �ɹ�����202 ���ɹ��᷵��404ʲô���߰����
								int code=con.getResponseCode();
								if(code==200) {
									
									//(3).����Inputstream��������ȡ�����ϵ���Դ
									InputStream is=con.getInputStream();
									
									/*byte[] bt=new byte[is.available()];
									is.read(bt);
									is.close();*/
									
									//����ֱ�ӽ��ֽ����ڵĶ������±���Ϊbitmap����
									//�ǵ�����BitmapFactory������Bitmap��
									bitmap=BitmapFactory.decodeStream(is);
									
								}else {
									Toast.makeText(getApplicationContext(), "�������Ӳ��ɹ���ͼƬ�����ڣ�", Toast.LENGTH_SHORT).show();
								}
								
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							
							//(4).ͼƬ������ɺ�ͨ��handler����֪ͨ���߳�������imageview
							Message msg=new Message();
							msg.what=0;
							hd.sendMessage(msg);
							
							//(5).����Handler���� ��һ�δ�������϶����Ա�������� �ǵ�Ҫ����handleMessage������
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
