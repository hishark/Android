package android.te44;

import android.os.Bundle;

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
				
				//4.����ļ������ļ�����
				String fileName=etFileName.getText().toString().trim();
				String fileContent=etFileContent.getText().toString().trim();
				
				if(fileName==null||fileName.equals("")) {
					Toast.makeText(getApplicationContext(), "�������ļ�����", Toast.LENGTH_LONG).show();
				}else {
					
					//5.�����ļ�
					//����FileOutputStream����д���ó�����ļ��У�data/data/projectPackage/files��
					//�����·������data/data/android.te44/files
					//����һ���ļ���������������Ϊһ���ܵ�
					//�ܵ�����ͷ��һͷ������Ҫִ��д��������ļ���һͷ�����Ÿ�Դ����
					
					try {
						//ǰ�����getApplicationҲ���Ի���getApplicationContext������һ��û������
						//�������Ǵ򿪵�ǰ������������������ô���
						//��һ����������ָ���ļ�����������ļ��Ѵ��ھ�ֱ���滻���ݣ���������ھ�ֱ���´���һ��
						//�ڶ��������Ǵ򿪸��ļ��ķ�ʽ����ʦֱ�������������������Ҳ����������������
						
						//����new������ֱ�ӵ������getapp...��Ϊ���ܹ��Զ�����·��
						//��new�Ļ�Ҫ�Լ���·��
						FileOutputStream fout=getApplication().openFileOutput(fileName,MODE_PRIVATE);
						
						
						//����edittext���������Ϣ������ת����Byte�ֽ���ʽȻ��ͨ���ļ������д���ļ�
					    fout.write(fileContent.getBytes("utf-8"));
					    //����һ��Ҫ��utf-8 ������һ��ANSI û������
					    
					    //һ��Ҫ�ǵùرչܵ�����
					    //��ʦ����˵�ĺ����أ�������û�رչܵ�����Ҳû������������������ǹ���Ŷ
					    fout.close();
					    
					    Toast.makeText(getApplicationContext(), "�ļ�����ɹ���", Toast.LENGTH_LONG).show();
					    
					} catch (Exception e) {
						// TODO Auto-generated catch block
						
						e.printStackTrace();
						Toast.makeText(getApplicationContext(), "�ļ����治�ɹ���", Toast.LENGTH_LONG).show();
					}//�ļ����ܲ��������Կ��ܻ��׳��쳣
							
					
				}
			}
		});
		
		
		btClear.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				etFileName.setText("");
				etFileContent.setText("");
			}
		});
		
		btRead.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				//��ȡeditext��������ļ����ֺ�����
				String fileName=etFileName.getText().toString().trim();
				String fileContent=etFileContent.getText().toString().trim();
				
				if(fileName==null||fileName.equals("")) {
					Toast.makeText(getApplicationContext(), "�������ļ�����", Toast.LENGTH_LONG).show();
				}else {
					    //ͨ��FileInputStream��������ݴ��ֻ����ļ��ж�������
					try {
						
						//����new������ֱ�ӵ������getApplication...��Ϊ���ܹ��Զ�����·��
						//��new�Ļ�Ҫ�Լ���·�� 
						//�ǵ�Ȼ����new��
						FileInputStream fin=getApplication().openFileInput(fileName);
						
						//������һ��byte���飬����ĳ��Ⱦ�������ļ��Ĵ�С����available���Եõ�
						//fin.available()���� ����ϸ��
						//Returns an estimated number of bytes that can be read 
						//or skipped without blocking for more input.
						byte []bt=new byte[fin.available()];
						
						//ͨ���ļ����������ļ������ݶ�����Ȼ����bt����
						fin.read(bt);
						
						//�رչܵ�
						fin.close();
						
						//bt��byte���� Ҫת����string���͵����� ֱ��newһ������ �����������������͵�����
						etFileContent.setText(new String(bt));
						
						//֮����С˵�Ķ�����ʱ��Ҫע��ת��һ�¸�ʽ���У���Ȼ������
						//String temp=EncodingUtils.getString(bt,"gb2312");
						//etFileContent.setText(temp);
						
						Toast.makeText(getApplicationContext(), "�ļ���ȡ�ɹ���", Toast.LENGTH_LONG).show();
					    
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						Toast.makeText(getApplicationContext(), "�ļ���ȡ���ɹ���", Toast.LENGTH_LONG).show();
					    
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
