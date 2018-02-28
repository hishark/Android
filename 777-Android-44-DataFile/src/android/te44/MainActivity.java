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
				
				//4.获得文件名和文件内容
				String fileName=etFileName.getText().toString().trim();
				String fileContent=etFileContent.getText().toString().trim();
				
				if(fileName==null||fileName.equals("")) {
					Toast.makeText(getApplicationContext(), "请输入文件名！", Toast.LENGTH_LONG).show();
				}else {
					
					//5.保存文件
					//利用FileOutputStream对象写到该程序的文件中（data/data/projectPackage/files）
					//这里的路径就是data/data/android.te44/files
					//这是一个文件输出流，可以理解为一个管道
					//管道有两头，一头连接着要执行写入操作的文件，一头连接着该源程序
					
					try {
						//前面这个getApplication也可以换成getApplicationContext，试了一下没有问题
						//反正就是打开当前程序的输出流咯，就这么理解
						//第一个参数就是指明文件名，如果该文件已存在就直接替换内容，如果不存在就直接新创建一个
						//第二个参数是打开该文件的方式，老师直接用了这个，其他的我也看不懂哈哈先这样
						
						//不用new，而是直接调用这个getapp...是为了能够自动加上路径
						//用new的话要自己加路径
						FileOutputStream fout=getApplication().openFileOutput(fileName,MODE_PRIVATE);
						
						
						//将在edittext中输入的信息的内容转化成Byte字节形式然后通过文件输出流写入文件
					    fout.write(fileContent.getBytes("utf-8"));
					    //这里一定要用utf-8 我试了一下ANSI 没有用啦
					    
					    //一定要记得关闭管道！！
					    //老师这里说的很严重，可是我没关闭管道好像也没出错？？？保险起见还是关了哦
					    fout.close();
					    
					    Toast.makeText(getApplicationContext(), "文件保存成功！", Toast.LENGTH_LONG).show();
					    
					} catch (Exception e) {
						// TODO Auto-generated catch block
						
						e.printStackTrace();
						Toast.makeText(getApplicationContext(), "文件保存不成功！", Toast.LENGTH_LONG).show();
					}//文件可能不存在所以可能会抛出异常
							
					
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
				
				//获取editext中输入的文件名字和内容
				String fileName=etFileName.getText().toString().trim();
				String fileContent=etFileContent.getText().toString().trim();
				
				if(fileName==null||fileName.equals("")) {
					Toast.makeText(getApplicationContext(), "请输入文件名！", Toast.LENGTH_LONG).show();
				}else {
					    //通过FileInputStream对象把数据从手机的文件中读到程序
					try {
						
						//不用new，而是直接调用这个getApplication...是为了能够自动加上路径
						//用new的话要自己加路径 
						//那当然不用new啦
						FileInputStream fin=getApplication().openFileInput(fileName);
						
						//定义了一个byte数组，数组的长度就是这个文件的大小，用available可以得到
						//fin.available()解释 很详细了
						//Returns an estimated number of bytes that can be read 
						//or skipped without blocking for more input.
						byte []bt=new byte[fin.available()];
						
						//通过文件输入流把文件的内容读出来然后存进bt数组
						fin.read(bt);
						
						//关闭管道
						fin.close();
						
						//bt是byte类型 要转换成string类型的数据 直接new一个即可 参数可以是任意类型的数据
						etFileContent.setText(new String(bt));
						
						//之后做小说阅读器的时候要注意转换一下格式才行，不然会乱码
						//String temp=EncodingUtils.getString(bt,"gb2312");
						//etFileContent.setText(temp);
						
						Toast.makeText(getApplicationContext(), "文件读取成功！", Toast.LENGTH_LONG).show();
					    
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						Toast.makeText(getApplicationContext(), "文件读取不成功！", Toast.LENGTH_LONG).show();
					    
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
