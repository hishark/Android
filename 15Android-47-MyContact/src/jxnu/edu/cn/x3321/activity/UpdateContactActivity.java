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

	//1.�����Ա����
	EditText etUpdateName,etUpdatePhone;
	Button btUpdateUser,btUpdateCancel;
	
	//����һ���ӿ�
	UserInterface ui;
	
	//����һ�����ͱ����洢��ContactActivity������intent�е�����
	int userid;
	
	
	User user=null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_contact);
		
		//2.��ʼ��
		init();
		
		//3.��intent�����л��Ҫ�޸ĵ���ϵ�˵�userid
		userid=this.getIntent().getIntExtra("userid", -10000);
		
		//4.���ݹؼ���userid�����ݿ��user������ȡ��Ҫ�޸ĵ���ϵ�ˣ����ڽ�������ʾ
		ui=new UserInterfaceImp(getApplicationContext());
		user=ui.getUserById(userid);
		
		if(user!=null) {
			etUpdateName.setText(user.getUserName());
			etUpdatePhone.setText(user.getPhone());
			
		}else {
			Toast.makeText(getApplicationContext(), "��ϵ�˲�����", Toast.LENGTH_SHORT).show();
		}
		
		//5.������ʼ���޸Ĳ���
		//����ע���¼�������
		btUpdateUser.setOnClickListener(new UpdateUser());
	}
	
	
	public class UpdateUser implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			//6.�����ϵ����Ϣ
			String name=etUpdateName.getText().toString().trim();
			String tel=etUpdatePhone.getText().toString().trim();
			
			if(name==null||name.equals("")||tel==null||tel.equals("")) {
				
				Toast.makeText(getApplicationContext(), "��������ȷ���û��������룡", Toast.LENGTH_LONG).show();
				
			}else {
				//7.�����ݷ�װ��User������
				User user=new User();
				//�޸�������¼������������setid����
				user.setUserid(userid);
				user.setUserName(name);
				user.setPhone(tel);
				
				//6.��user����д�������浽�����ݿ�contact��user����
				//���������ʵ����ui����ӿڣ�ʹ���ܹ�ȥ���÷���
				ui=new UserInterfaceImp(getApplicationContext());
				
				if(ui.update(user)!=0) {
					Toast.makeText(getApplicationContext(), "��ϵ���޸ĳɹ���", Toast.LENGTH_LONG).show();
					Intent intent=new Intent(UpdateContactActivity.this,MyContactActivity.class);
					startActivity(intent);
					finish();
				}else {
					Toast.makeText(getApplicationContext(), "��ϵ���޸Ĳ��ɹ���", Toast.LENGTH_LONG).show();
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
