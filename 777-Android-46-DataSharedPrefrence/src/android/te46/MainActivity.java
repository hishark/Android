package android.te46;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {

	
	//1.定义成员变量
	EditText etUserName,etPassword;
	CheckBox cbRemember,cbShow;
	Button btLogin,btCancel;
	
	//这个对象是专门用来操作xml文档的，已经封装好了的 
	//可以视为一个管道
	SharedPreferences sp;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//2.初始化
		init();
		
		//3.通过sp对象创建或者打开/data/data/SharedPreferences/user.xml
		//user后可以不加.xml后缀,缺省就是.xml后缀，只要告诉他你这个xml文件名是啥就好了
		//第一个参数就是文件名，第二个参数是操作这个xml文档的模式 。
		sp=this.getSharedPreferences("user",Context.MODE_APPEND);
		
		//4.切记切记，一启动界面就要从user.xml文档中读取初始化参数信息
		//如果启动时xml就不存在的话，isRemember的值就是false
		//如果存在的话，就返回xml中已存在的isRemember的值
		//第二个参数的解释：Value to return if this preference does not exist.
		boolean isRemember=sp.getBoolean("isRemember",false);
		
		//若xml文件已存在并且isRemember的值为true时就做下面的动作
		if(isRemember) {
			
			etUserName.setText(sp.getString("name", ""));//xml文件中不存在name的值就返回""，存在就返回已有的值
			etPassword.setText(sp.getString("password", ""));//xml文件中不存在password的值就返回""，存在就返回已有的值
			cbShow.setChecked(sp.getBoolean("isShow", false));//xml文件中不存在isShow的值就返回false，存在就返回已有的值
			cbRemember.setChecked(sp.getBoolean("isRemember", false));//xml文件中不存在isRemember的值就返回false，存在就返回已有的值
			
			//至此已经把之前存下的配置重新配置好了
			//也就是实现了记住用户的操作
			
			
			//这段代码没有任何实际用处
			
			/*//接下来再判断一下是否明文显示密码啦
			//如果isShow已经被选中 那么就明文显示密码
			if(sp.getBoolean("isShow", false)) {
				
				etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
				 
			}*/
			
			
		}
		
		
		//5.定义注册事件监听器
		cbRemember.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				//sp是一个管道，现在调用这个管道的edit方法得到一个editor对象
				//通过editor对象把数据写进user.xml中
				//也就是说，管道有两头，一头是edit，一头是user.xml，用edit往user.xml里面推东西就ok啦！
				Editor edit=sp.edit();
				
				//6.首先要获得用户名和密码的值
				String userName=etUserName.getText().toString().trim();
				String password=etPassword.getText().toString().trim();
				
				//然后判断【记住用户】选项是否被选中，如果被选中了就来做记住用户的一系列操作
				//也就是把数据写进user.xml来记住用户，记住用户的一切配置！
				if(cbRemember.isChecked()) {
					//把数据全部推进edit 传向user.xml
					edit.putString("name", userName);
					edit.putString("password", password);
					
					//设置两个布尔类型的变量来标识两个单选框的状态 
					edit.putBoolean("isRemember", true);
					edit.putBoolean("isShow", cbShow.isChecked());
					
					//事务需要一次性提交
					edit.commit();
					
				}else {
					edit.putString("name", "");
					edit.putString("password", "");
					edit.putBoolean("isRemember", false);
					edit.putBoolean("isShow", cbShow.isChecked());
					
					//事务需要一次性的提交
					edit.commit();
				}
			}
		});
		
		cbShow.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Editor edit=sp.edit();
				
				if(cbShow.isChecked()) {
					
					 
					edit.putBoolean("isShow", true);
					edit.commit();
					
					//etpassword这个编辑框就以密文方式显示密码 
					etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
					
					
				}else {
					edit.putBoolean("isShow", false);
					edit.commit();
					
					//etpassword这个编辑框就以明文方式显示密码
					etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
					
				}
			}
		});
	}

	private void init() {
		// TODO Auto-generated method stub
		etUserName=(EditText)this.findViewById(R.id.et_userName);
		etPassword=(EditText)this.findViewById(R.id.et_Password);
		cbRemember=(CheckBox)this.findViewById(R.id.cd_remember);
		cbShow=(CheckBox)this.findViewById(R.id.cb_show);
		btLogin=(Button)this.findViewById(R.id.bt_login);
		btCancel=(Button)this.findViewById(R.id.bt_cancel);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
