package jxnu.edu.cn.x3321;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {

	//1.定义成员变量
	EditText etUserName,etPassword,etAge,etResume;
	RadioGroup rg;
	RadioButton rbBoy,rbGirl;
	CheckBox cbRead,cbFootball,cbBasketball,cbDraw;
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
				String userName,password,strAge,resume;
				int age=0;
				String sex="male",hobbies="";
				userName=etUserName.getText().toString().trim();
				password=etPassword.getText().toString().trim();
				strAge=etAge.getText().toString().trim();
				resume=etResume.getText().toString().trim();
				if(strAge.equals("")||strAge==null) {
					Toast.makeText(getApplicationContext(), "Age can't be null", Toast.LENGTH_SHORT).show();
				}else {
					age=Integer.parseInt(strAge);
				}
				if(rbGirl.isChecked()) {
					sex=rbGirl.getText().toString();
				}
				if(cbRead.isChecked()) {
					hobbies=hobbies+" "+cbRead.getText().toString();
				}
				if(cbFootball.isChecked()) {
					hobbies=hobbies+" "+cbFootball.getText().toString();
				}
				if(cbBasketball.isChecked()) {
					hobbies=hobbies+" "+cbBasketball.getText().toString();
				}
				Toast toast=Toast.makeText(getApplicationContext(), 
						"userName:"+userName+"\n"
						+"Password:"+password+"\n"
						+"Age:"+strAge+"\n"
						+"Sex:"+sex+"\n"
						+"Hobbies:"+hobbies+"\n"
						+"Resume:"+resume+"\n"
						, Toast.LENGTH_LONG);
				toast.setGravity(Gravity.CENTER, 0, 0);
				//可是设置吐司显示的位置噢
				toast.show();
			}
		});
	}

	
	private void init() {
		// TODO Auto-generated method stub
		etUserName=(EditText)this.findViewById(R.id.et_userName);
		etPassword=(EditText)this.findViewById(R.id.et_password);
		etAge=(EditText)this.findViewById(R.id.et_age);
		etResume=(EditText)this.findViewById(R.id.et_resume);
		rg=(RadioGroup)this.findViewById(R.id.rg_sex);
		rbBoy=(RadioButton)this.findViewById(R.id.rb_boy);
		rbGirl=(RadioButton)this.findViewById(R.id.rb_girl);
		cbRead=(CheckBox)this.findViewById(R.id.cb_read);
		cbFootball=(CheckBox)this.findViewById(R.id.cb_football);
		cbBasketball=(CheckBox)this.findViewById(R.id.cb_basketball);
		cbDraw=(CheckBox)this.findViewById(R.id.cb_draw);
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
