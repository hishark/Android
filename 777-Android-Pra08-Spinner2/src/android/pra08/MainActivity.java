package android.pra08;

import android.os.Bundle;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {

	//1.定义成员变量
	EditText et;
	Spinner sp;
	Button bt;
	ArrayList<String> al=new ArrayList<String>();
	MyAdapter ma;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//2.初始化
		init();
		al.add("aaa");
		//3.自定义适配器
		ma=new MyAdapter(getApplicationContext(), al);
		//4.加载适配器
		sp.setAdapter(ma);
		//5.监听器
		bt.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final String str=et.getText().toString().trim();
				if(str.equals("")) {
					Toast.makeText(getApplicationContext(), "请输入数据！", Toast.LENGTH_SHORT).show();
				}else {
					AlertDialog.Builder ad=new AlertDialog.Builder(MainActivity.this);
					ad.setIcon(R.drawable.ic_launcher);
					ad.setTitle("Dialog");
					ad.setMessage("你真的要加上"+str+"吗？？");
					 
					ad.setPositiveButton("确定",new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							al.add(str);
							et.setText("");
						}
					});
ad.setNegativeButton("不要勒", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							 
							et.setText("");
						}
					});
					
					ad.create().show();
				}
			}
		});
		
	}

	private void init() {
		// TODO Auto-generated method stub
		et=(EditText)this.findViewById(R.id.et);
		bt=(Button)this.findViewById(R.id.bt);
		sp=(Spinner)this.findViewById(R.id.sp);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
