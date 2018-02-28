package android.te39;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.*;
import jxnu.edu.cn.x3321.R;

public class MainActivity extends Activity {

	//1.定义成员变量
	EditText etUserName,etPassword,etAge;
	RadioGroup rg;
	RadioButton rbBoy,rbGirl;
	CheckBox cbRead,cbFootball,cbBasketball;
	Button btRegedit,btCancel;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//2.初始化
		init();
		
		//3.定义注册监听器
		btRegedit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String userName,password,strAge;
				int age=0;
				String sex="male",hobbies="";
				userName=etUserName.getText().toString().trim();
				password=etPassword.getText().toString().trim();
				strAge=etAge.getText().toString().trim();
				//得到内容转换成字符串再去掉前后的空格
				if(strAge.equals("")||strAge==null) {
					Toast.makeText(getApplicationContext(), "Age can't be null", Toast.LENGTH_SHORT).show();
					//参数含义：1.上下文 2.吐司内容 3.吐司时间长短 
				}else {
					age=Integer.parseInt(strAge);//字符串强制转换为int类型
				}
				if(rbGirl.isChecked()) {
					sex=rbGirl.getText().toString();
				}
				//单选框是否被选中
				if(cbRead.isChecked()) {
					hobbies=hobbies+cbRead.getText().toString();
				}
				if(cbFootball.isChecked()) {
					hobbies=hobbies+cbFootball.getText().toString();
				}
				if(cbBasketball.isChecked()) {
					hobbies=hobbies+cbBasketball.getText().toString();
				}
				
				
				
				//第一种数据传递方式---用Bundle 把数据扔到集装箱再把集装箱扔到卡车上去
				
				Bundle bd=new Bundle();
				//用这个集装箱封装所有数据 可以放任意类型的数据 有点像ArrayList
				 
				bd.putString("userName", userName);
				//也是键值对key-value
				bd.putString("password", password);
				bd.putInt("age", age);
				//其他数据以此类推 这里就意思一哈
				
				Intent intent=new Intent();
				//Intent相当于一个大卡车 我们要把Bundle全部封装到卡车上去
				intent.putExtras(bd);
				//把bundle封装到intent上去（相当于把集装箱装进卡车）
				intent.setClass(MainActivity.this, ShowRegeditResultActivity.class);
				startActivity(intent);
				//finish();
				
				
				//第二种数据传递方式---不用Bundle 直接把数据扔到卡车上去
				/*Intent intent=new Intent();
				
				intent.putExtra("userName", userName);
				intent.putExtra("password", password);
				intent.putExtra("age", age);
				
				//把车开到哪里去哦
				intent.setClass(MainActivity.this, ShowRegeditResultActivity.class);
				startActivity(intent);*/
				
				
				
			}
		});
	}

	
	private void init() {
		// TODO Auto-generated method stub
		etUserName=(EditText)this.findViewById(R.id.et_userName);
		etPassword=(EditText)this.findViewById(R.id.et_password);
		etAge=(EditText)this.findViewById(R.id.et_age);
		rg=(RadioGroup)this.findViewById(R.id.rg_sex);
		rbBoy=(RadioButton)this.findViewById(R.id.rb_boy);
		rbGirl=(RadioButton)this.findViewById(R.id.rb_girl);
		cbRead=(CheckBox)this.findViewById(R.id.cb_read);
		cbFootball=(CheckBox)this.findViewById(R.id.cb_football);
		cbBasketball=(CheckBox)this.findViewById(R.id.cb_basketball);
		
		btRegedit=(Button)this.findViewById(R.id.bt_regedit);
		btCancel=(Button)this.findViewById(R.id.bt_cancel);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
