package android.te40;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class RegeditActivity extends Activity {
	//1.�����Ա����
	EditText etUserName,etpassWord,etAge;
	RadioGroup rg;
	RadioButton rbBoy,rbGirl;
	CheckBox cbRead,cbFootBall,cbBasketBall;
	Button btRegedit,btCancel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_regedit);
		//2.ʹ��Ա���ñ���ָ������ϵ�Ԫ�ض������ñ����ĳ�ʼ����
		init();
		//3.���塢ע���¼�������
		btRegedit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String userName,passWord,strAge;
				int age=0;
				String sex="��",hobbys="";
				userName=etUserName.getText().toString().trim();
				passWord=etpassWord.getText().toString().trim();
				strAge=etAge.getText().toString().trim();
				if(strAge.equals("")||strAge==null){
					Toast.makeText(getApplicationContext(), "���䲻��Ϊ�գ�",
							Toast.LENGTH_SHORT).show();
				}else{
					age=Integer.parseInt(strAge);
				}
				if(rbGirl.isChecked()){
					sex="Ů";
				}
				if(cbRead.isChecked()){
					hobbys=hobbys+cbRead.getText().toString()+"  ";
				}
				if(cbFootBall.isChecked()){
					hobbys=hobbys+cbFootBall.getText().toString()+"  ";
				}
				if(cbBasketBall.isChecked()){
					hobbys=hobbys+cbBasketBall.getText().toString()+"  ";
				}
				//��һ�����ݴ��ݷ�ʽ
				/*
				Bundle bd=new Bundle();
				bd.putString("userName", userName);
				bd.putString("passWord", passWord);
				bd.putInt("age",age);
				
				Intent intent=new Intent();
				intent.setClass(MainActivity.this, 
						ShowRegeditResultActivity.class);
				intent.putExtras(bd);
				
				startActivity(intent);
				finish();
				*/
				
				
			}
		});
	}

	private void init() {
		// TODO Auto-generated method stub
		etUserName=(EditText)this.findViewById(R.id.et_userName);
	    etpassWord=(EditText)this.findViewById(R.id.et_passWord);
	    etAge=(EditText)this.findViewById(R.id.et_age);
	    rg=(RadioGroup)this.findViewById(R.id.rg_sex);
	    rbBoy=(RadioButton)this.findViewById(R.id.rb_boy);
	    rbGirl=(RadioButton)this.findViewById(R.id.rb_girl);
	    cbRead=(CheckBox)this.findViewById(R.id.cb_read);
	    cbFootBall=(CheckBox)this.findViewById(R.id.cb_football);
	    cbBasketBall=(CheckBox)this.findViewById(R.id.cb_basketball);
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
