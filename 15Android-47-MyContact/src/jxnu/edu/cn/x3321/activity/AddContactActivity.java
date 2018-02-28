package jxnu.edu.cn.x3321.activity;

import jxnu.edu.cn.x3321.R;

import jxnu.edu.cn.x3321.R.layout;
import jxnu.edu.cn.x3321.R.menu;
import jxnu.edu.cn.x3321.Interface.UserInterface;
import jxnu.edu.cn.x3321.InterfaceImp.UserInterfaceImp;
import jxnu.edu.cn.x3321.bean.User;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class AddContactActivity extends Activity {

	//1.�����Ա����
	EditText etAddName,etAddTel;
	Button btAddUser,btAddCancel;
	//����һ���ӿ�
	UserInterface ui;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_contact);
		
		//2.��ʼ��
		init();
		
		//3.����ע���¼�������
		//Ūһ����ר������������
		btAddUser.setOnClickListener(new AddUser());
		
	}
	
	public class AddUser implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			//Toast.makeText(getApplicationContext(), "hi", Toast.LENGTH_LONG).show();
			
			//4.�����ϵ����Ϣ
			String name=etAddName.getText().toString().trim();
			String tel=etAddTel.getText().toString().trim();
			
			if(name==null||name.equals("")||tel==null||tel.equals("")) {
				
				Toast.makeText(getApplicationContext(), "��������ȷ���û��������룡", Toast.LENGTH_LONG).show();
				
			}else {
				
				//5.�����ݷ�װ��User������
				User user=new User();
				user.setUserName(name);
				user.setPhone(tel);
				
				//6.��user����д�������浽�����ݿ�contact��user����
				//���������ʵ����ui����ӿڣ�ʹ���ܹ�ȥ���÷���
				ui=new UserInterfaceImp(getApplicationContext());
				
				if(ui.insert(user)==1) {
					Toast.makeText(getApplicationContext(), "��ϵ���ѱ���ɹ���", Toast.LENGTH_LONG).show();
					Intent intent=new Intent(AddContactActivity.this,MyContactActivity.class);
					startActivity(intent);
					finish();
				}
			}
		}
		
	}

	private void init() {
		// TODO Auto-generated method stub
		etAddName=(EditText)this.findViewById(R.id.et_AddName);
		etAddTel=(EditText)this.findViewById(R.id.et_AddTel);
		btAddUser=(Button)this.findViewById(R.id.bt_AddUser);
		btAddCancel=(Button)this.findViewById(R.id.bt_AddCancel);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_contact, menu);
		return true;
	}

}
