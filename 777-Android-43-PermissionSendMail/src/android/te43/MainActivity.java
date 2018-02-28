package android.te43;

import android.os.Bundle;
import android.telephony.gsm.SmsManager;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {

	//1.定义成员变量
	EditText etNumber,etBody;
	Button btSend,btOpen;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//2.初始化
		init();
		
		//3.定义注册事件监听器
		//用smsmanager来发送短信
		btSend.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				//------------最关键的一步----------------
				//要记得在andoidmanifest.xml中配置发送短信的权限
				//-------------------------------------
				
				//获得电话号码以及信息内容
				String toNumber=etNumber.getText().toString().trim();
				String body=etBody.getText().toString().trim();
				
				if(toNumber==null||toNumber.equals("")) {
					Toast.makeText(getApplicationContext(), "请输入电话号码！", Toast.LENGTH_LONG).show();
				}else {
					//SmsManager对象可以直接发送短信 不过为啥不建议使用了OTZ
					//这个鬼东西不可以直接new一个出来耶 试了一下会报错 只可以getDefault来获得一个对象
					SmsManager sm=SmsManager.getDefault();
					
					
					//手机发送信息有一个最大值，不做特殊处理就会丢失嘛所以要对内容进行分割，分割成若干部分
					//（10086就喜欢这么给我发信息诶 分成好几条 懂为什么了哈哈哈 那我以高中发的长长的短信岂不是丢了很多ORZ）
					//不需要自己来进行分割，直接调用一个方法divideMessage进行分割即可。
					//定义一个String类型的List集合，用来存储分割后的信息 
					List<String> sms=sm.divideMessage(body);
					
					/*for(int i=0;i<sms.size();i++) {
						sm.sendTextMessage(toNumber, null, sms.get(i), null, null);
					}*/
					
					//分割完信息就可以一个个的发出去了
					for(String m:sms) {
						//一参是信息接收端的手机号，二参是信息发送端的手机号 直接为null，三参是信息，四五是意图 直接null
						sm.sendTextMessage(toNumber, null, m, null, null);
					}
				    
				}
				
			}
		});
		
		
		
		   //用隐式意图来打开发送短信的界面
           btOpen.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				//在andoidmanifest.xml中配置发送短信的权限
				
				//获得电话号码以及信息内容
				String toNumber=etNumber.getText().toString().trim();
				String body=etBody.getText().toString().trim();
				
				if(toNumber==null||toNumber.equals("")) {
					Toast.makeText(getApplicationContext(), "请输入电话号码！", Toast.LENGTH_LONG).show();
				}else {
					
					//通过隐式意图来启动系统发短信的界面
					Intent intent=new Intent();
					intent.setAction(Intent.ACTION_SENDTO);
					//下面这个也ok，不过感觉上面那个更方便啦，不需要记
					//intent.setAction("android.intent.action.SENDTO"); 
					
					//将电话号码字符串转换成uri对象，再传给系统发送短信的组件
					//告诉intent意图信息接收端的电话号码是什么
					Uri uri=Uri.parse("smsto:"+toNumber);
					
					intent.setData(uri);
					//把信息也弄过去 这个sms_body也是固定的 不可以改动 改动了打开的信息编辑框里就不会有你在edittext里输入的信息了！
					intent.putExtra("sms_body", body);
					
					startActivity(intent);
					
				}
				
			}
		});
		
		
 
	}

	private void init() {
		// TODO Auto-generated method stub
		etNumber=(EditText)this.findViewById(R.id.et_Number);
		etBody=(EditText)this.findViewById(R.id.et_body);
		btSend=(Button)this.findViewById(R.id.bt_send);
		btOpen=(Button)this.findViewById(R.id.bt_open);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
