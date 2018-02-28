package android.te45;

import android.os.Bundle;
import android.os.Environment;
import android.te44.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.http.util.EncodingUtils;

import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	//1.�����Ա����
	EditText etFileName,etFileContent;
	Button btSave,btRead,btClear;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//2.��ʼ��
		init();
		
		//3.ע�ᶨ���¼�������
		btSave.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				//4.���EditText��������ļ������ļ�����
				String fileName=etFileName.getText().toString().trim();
				String fileContent=etFileContent.getText().toString().trim();
				
				if(fileName==null||fileName.equals("")) {
					Toast.makeText(getApplicationContext(), "�������ļ�����", Toast.LENGTH_LONG).show();
				}else {
					//5.һ��Ҫ�ǵ��ڰ�׿��manifest.xml�����ӷ���SD����Ȩ�� �������� �������� MOUNT��WRITE
					
					/*
					<uses-permission android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS"/>
				    <!-- �ǲ����ⲿ�ļ�ϵͳ -->
				    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
				    <!-- �Ƿ���ⲿ�豸��д��Ȩ�� -->
				    */
					
					//6.���sd���Ƿ����
					//Environment.getExternalStorageState���Ի�õ�ǰ�ⲿ�豸��״̬
					//Environment.MEDIA_MOUNTED�����ⲿ�豸��SD������˼��
					if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
						
						//����File������ļ����Ѵ��ھʹ򿪣������ھ��½�һ��������
						//FileOutputStreamӦ����ֻ�ܶԱ������ڵ��ļ�����ֱ�ӵĲ���
						//�Ұ��������ע�͵�֮�� ֱ��ֻ��FileOutputStream��û����������
						//���Զ�SD�����в���һ��Ҫ�õ�File
						File file=new File(Environment.getExternalStorageDirectory(),fileName);
						
						//7.ͨ��FileOutputStream���������д��sd����
						try {
							//��FileOutputStream�ļ����������ܵ���׼���file�ļ� 
							FileOutputStream fout=new FileOutputStream(file);
							
							//��Edittext-filecontent�������ת����byte��ʽȻ��д��file�ļ�
							fout.write(fileContent.getBytes("utf-8"));
							
							//�ǵùر������
							fout.close();
							
							Toast.makeText(getApplicationContext(), "�ļ����浽sd���ɹ���", Toast.LENGTH_LONG).show();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							
							
							e.printStackTrace();
							Toast.makeText(getApplicationContext(), "�ļ����浽sd��ʧ�ܣ�", Toast.LENGTH_LONG).show();
							
						}
						
						
					}else {
						//Environment.getExternalStorageState()��=Environment.MEDIA_MOUNTED�����
						//sd��������
						
						Toast.makeText(getApplicationContext(), "SD�������ڣ�", Toast.LENGTH_LONG).show();
					}
							
					
				}
			}
		});
		
		
		btClear.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				//���
				etFileName.setText("");
				etFileContent.setText("");
			}
		});
		
		btRead.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				String fileName=etFileName.getText().toString().trim();
				String fileContent=etFileContent.getText().toString().trim();
				if(fileName==null||fileName.equals("")) {
					Toast.makeText(getApplicationContext(), "�������ļ�����", Toast.LENGTH_LONG).show();
				}else {
					
                     //5.�ڰ�׿��manifest.xml�����ӷ���SD��Ȩ��
					
					/*
					<uses-permission android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS"/>
				    <!-- �ǲ����ⲿ�ļ�ϵͳ -->
				    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
				    <!-- �Ƿ���ⲿ�豸��д��Ȩ�� -->
				    */
					
					//6.���sd���Ƿ����
					if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
						File file=new File(Environment.getExternalStorageDirectory(),fileName);
						//7.ͨ��fileInputstream��������ݴ��ֻ���sd���ж�������
						try {
							
							FileInputStream fin=new FileInputStream(file);
							
							//������һ��byte���飬����ĳ��Ⱦ�������ļ��Ĵ�С����available���Եõ�
							//fin.available()���� ����ϸ��
							//Returns an estimated number of bytes that can be read 
							//or skipped without blocking for more input.
							byte []bt=new byte[fin.available()];
							
							//ͨ���ļ����������ļ������ݶ�������������Ȼ����bt����
							fin.read(bt);
							
							//����ļ���ASNI��ʽ ����������ת��һ�¾Ϳ��Գɹ����ֻ�����ʾ�� ���������������
							//String temp=EncodingUtils.getString(bt,"gb2312");
							//etFileContent.setText(temp);
							
							//����ļ���UTF-8�ĸ�ʽ ֱ�������Ϳ��� ���������������
							etFileContent.setText(new String(bt));
							
							Toast.makeText(getApplicationContext(), "���ݶ�ȡ�ɹ���", Toast.LENGTH_LONG).show();
							
							fin.close();
							
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							Toast.makeText(getApplicationContext(), "���ݶ�ȡ���ɹ���", Toast.LENGTH_LONG).show();
							
						}
						
						
					}else {
						//sd��������
						Toast.makeText(getApplicationContext(), "SD�������ڣ�", Toast.LENGTH_LONG).show();
					
					}
				}
			}
		});
	}

	private void init() {
		// TODO Auto-generated method stub
		etFileName=(EditText)this.findViewById(R.id.et_fileName);
		etFileContent=(EditText)this.findViewById(R.id.et_fileContent);
		btSave=(Button)this.findViewById(R.id.bt_save);
		btRead=(Button)this.findViewById(R.id.bt_read);
		btClear=(Button)this.findViewById(R.id.bt_clear);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
