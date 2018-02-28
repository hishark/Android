package android.te11;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
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
				AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
				//对话框要依附于父类出现的 
				//可是这里我写成Builder builder=new Builder(Mainactivity.this)也没毛病哦
				//不过还是加上AlertDialog更能见名知意吧而且上面这种写法可能有毛病只是我没碰到？嗯~ o(*￣▽￣*)o
				builder.setIcon(R.drawable.ic_launcher);
				builder.setTitle("Your info");
				builder.setMessage(userName+"\n"
                                   +password+"\n"
                                   +age+"\n"
                                   +sex+"\n"
                                   +hobbies);
				
				builder.setPositiveButton("Yes", null);
				//第二个参数是响应事件 这里设置的null
				builder.setNegativeButton("No", null);
				AlertDialog ad=builder.create();//创建一个ad对话框 之前builder只是在搭个房子？大概这个意思
				ad.show();//show出ad
				//偷懒还可以像下面这样写
				//builder.create().show();
				
				
				//NEW TASK:用吐司把注册信息在顶上显示
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
