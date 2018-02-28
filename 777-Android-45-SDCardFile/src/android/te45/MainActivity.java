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

	//1.定义成员变量
	EditText etFileName,etFileContent;
	Button btSave,btRead,btClear;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//2.初始化
		init();
		
		//3.注册定义事件监听器
		btSave.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				//4.获得EditText中输入的文件名和文件内容
				String fileName=etFileName.getText().toString().trim();
				String fileContent=etFileContent.getText().toString().trim();
				
				if(fileName==null||fileName.equals("")) {
					Toast.makeText(getApplicationContext(), "请输入文件名！", Toast.LENGTH_LONG).show();
				}else {
					//5.一定要记得在安卓的manifest.xml中增加访问SD卡的权限 有两个噢 都不能少 MOUNT和WRITE
					
					/*
					<uses-permission android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS"/>
				    <!-- 是不是外部文件系统 -->
				    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
				    <!-- 是否对外部设备有写的权限 -->
				    */
					
					//6.检查sd卡是否可用
					//Environment.getExternalStorageState可以获得当前外部设备的状态
					//Environment.MEDIA_MOUNTED就是外部设备是SD卡的意思咯
					if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
						
						//先用File对象打开文件（已存在就打开，不存在就新建一个出来）
						//FileOutputStream应该是只能对本程序内的文件进行直接的操作
						//我把下面这句注释掉之后 直接只用FileOutputStream就没有用啦！！
						//所以对SD卡进行操作一定要用到File
						File file=new File(Environment.getExternalStorageDirectory(),fileName);
						
						//7.通过FileOutputStream对象把数据写到sd卡中
						try {
							//将FileOutputStream文件输出流这个管道对准这个file文件 
							FileOutputStream fout=new FileOutputStream(file);
							
							//将Edittext-filecontent里的内容转换成byte形式然后写入file文件
							fout.write(fileContent.getBytes("utf-8"));
							
							//记得关闭输出流
							fout.close();
							
							Toast.makeText(getApplicationContext(), "文件保存到sd卡成功！", Toast.LENGTH_LONG).show();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							
							
							e.printStackTrace();
							Toast.makeText(getApplicationContext(), "文件保存到sd卡失败！", Toast.LENGTH_LONG).show();
							
						}
						
						
					}else {
						//Environment.getExternalStorageState()！=Environment.MEDIA_MOUNTED的情况
						//sd卡不存在
						
						Toast.makeText(getApplicationContext(), "SD卡不存在！", Toast.LENGTH_LONG).show();
					}
							
					
				}
			}
		});
		
		
		btClear.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				//清空
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
					Toast.makeText(getApplicationContext(), "请输入文件名！", Toast.LENGTH_LONG).show();
				}else {
					
                     //5.在安卓的manifest.xml中增加访问SD卡权限
					
					/*
					<uses-permission android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS"/>
				    <!-- 是不是外部文件系统 -->
				    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
				    <!-- 是否对外部设备有写的权限 -->
				    */
					
					//6.检查sd卡是否可用
					if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
						File file=new File(Environment.getExternalStorageDirectory(),fileName);
						//7.通过fileInputstream对象把数据从手机的sd卡中读出数据
						try {
							
							FileInputStream fin=new FileInputStream(file);
							
							//定义了一个byte数组，数组的长度就是这个文件的大小，用available可以得到
							//fin.available()解释 很详细了
							//Returns an estimated number of bytes that can be read 
							//or skipped without blocking for more input.
							byte []bt=new byte[fin.available()];
							
							//通过文件输入流把文件的内容读到程序里来，然后存进bt数组
							fin.read(bt);
							
							//如果文件是ASNI格式 用下面两句转换一下就可以成功在手机上显示了 不会出现乱码问题
							//String temp=EncodingUtils.getString(bt,"gb2312");
							//etFileContent.setText(temp);
							
							//如果文件是UTF-8的格式 直接用这句就可以 不会出现乱码问题
							etFileContent.setText(new String(bt));
							
							Toast.makeText(getApplicationContext(), "数据读取成功！", Toast.LENGTH_LONG).show();
							
							fin.close();
							
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							Toast.makeText(getApplicationContext(), "数据读取不成功！", Toast.LENGTH_LONG).show();
							
						}
						
						
					}else {
						//sd卡不存在
						Toast.makeText(getApplicationContext(), "SD卡不存在！", Toast.LENGTH_LONG).show();
					
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
