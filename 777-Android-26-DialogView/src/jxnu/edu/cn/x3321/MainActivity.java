package jxnu.edu.cn.x3321;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {
	//1.定义成员变量
	Button bt;
	EditText etUserName,etPassword;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//2.初始化
		init();
		
		//3.注册监听器
		bt.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog ad=createDialog();
				//自定义的createDialog函数创建对话框
				ad.show();
			}
		});
	}

	protected AlertDialog createDialog() {
		// TODO Auto-generated method stub
		AlertDialog alertDialog;
		AlertDialog.Builder ad=new AlertDialog.Builder(MainActivity.this);
		
		 
		ad.setTitle("自定义View对话框");
		ad.setIcon(R.drawable.ic_launcher);
		
		//加载自定义的login.xml到ll中去
		//有两种方法来填充！都掌握！
		//way1
		final LinearLayout ll=(LinearLayout)getLayoutInflater().inflate(R.layout.login, null);
		//way2
		//final LinearLayout ll=(LinearLayout)View.inflate(getApplicationContext(), R.layout.login, null);
		
		//把布局ll加载到Dialog里面去
		ad.setView(ll);
		
		ad.setPositiveButton("确定",new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				etUserName=(EditText)ll.findViewById(R.id.et1);
			
				etPassword=(EditText)ll.findViewById(R.id.et2);
			
				Toast.makeText(getApplicationContext(), 
						"UserName:"+etUserName.getText()+"\n"
						+"Password:"+etPassword.getText(),
						Toast.LENGTH_LONG).show();
				
			}
		} );
		
		ad.setNegativeButton("取消", null);
		
		alertDialog=ad.create();//ad是builder类型 用了create()就是AlertDialog类型
		return alertDialog;
	}

	private void init() {
		// TODO Auto-generated method stub
		bt=(Button)this.findViewById(R.id.bt);
		etUserName=(EditText)this.findViewById(R.id.et1);
		etPassword=(EditText)this.findViewById(R.id.et2);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
