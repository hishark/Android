package jxnu.edu.cn.x3321.activity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.*;
import jxnu.edu.cn.x3321.R;
import jxnu.edu.cn.x3321.R.layout;
import jxnu.edu.cn.x3321.R.menu;
import jxnu.edu.cn.x3321.Interface.UserInterface;
import jxnu.edu.cn.x3321.InterfaceImp.UserInterfaceImp;
import jxnu.edu.cn.x3321.bean.User;

public class UpdateContactActivity extends Activity {

	//1.定义成员变量
	EditText etUpdateName,etUpdatePhone;
	Button btUpdateUser,btUpdateCancel;
	
	//定义一个接口
	UserInterface ui;
	
	//定义一个整型变量存储从ContactActivity传来的intent中的数据
	int userid;
	
	
	User user=null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_contact);
		
		//2.初始化
		init();
		
		//3.从intent对象中获得要修改的联系人的userid
		userid=this.getIntent().getIntExtra("userid", -10000);
		
		//4.根据关键字userid从数据库表user中重新取出要修改的联系人，并在界面上显示
		ui=new UserInterfaceImp(getApplicationContext());
		user=ui.getUserById(userid);
		
		if(user!=null) {
			etUpdateName.setText(user.getUserName());
			etUpdatePhone.setText(user.getPhone());
			
		}else {
			Toast.makeText(getApplicationContext(), "联系人不存在", Toast.LENGTH_SHORT).show();
		}
		
		//5.真正开始做修改操作
		//定义注册事件监听器
		btUpdateUser.setOnClickListener(new UpdateUser());
	}
	
	
	public class UpdateUser implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			//6.获得联系人信息
			String name=etUpdateName.getText().toString().trim();
			String tel=etUpdatePhone.getText().toString().trim();
			
			if(name==null||name.equals("")||tel==null||tel.equals("")) {
				
				Toast.makeText(getApplicationContext(), "请输入正确的用户名和密码！", Toast.LENGTH_LONG).show();
				
			}else {
				//7.把数据封装到User对象中
				User user=new User();
				//修改哪条记录啊？？别忘记setid！！
				user.setUserid(userid);
				user.setUserName(name);
				user.setPhone(tel);
				
				//6.把user对象写到（保存到）数据库contact的user表中
				//下面这句是实例化ui这个接口，使他能够去引用方法
				ui=new UserInterfaceImp(getApplicationContext());
				
				if(ui.update(user)!=0) {
					Toast.makeText(getApplicationContext(), "联系人修改成功！", Toast.LENGTH_LONG).show();
					Intent intent=new Intent(UpdateContactActivity.this,MyContactActivity.class);
					startActivity(intent);
					finish();
				}else {
					Toast.makeText(getApplicationContext(), "联系人修改不成功！", Toast.LENGTH_LONG).show();
					etUpdateName.requestFocus();
				}
			}
		}
		
	}

	private void init() {
		// TODO Auto-generated method stub
		etUpdateName=(EditText)this.findViewById(R.id.et_UpdateName);
		etUpdatePhone=(EditText)this.findViewById(R.id.et_UpdatePhone);
		btUpdateUser=(Button)this.findViewById(R.id.bt_UpdateUser);
		btUpdateCancel=(Button)this.findViewById(R.id.bt_UpdateCancel);
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.update_contact, menu);
		return true;
	}

}
