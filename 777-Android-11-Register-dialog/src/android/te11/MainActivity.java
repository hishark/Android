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

	//1.�����Ա����
	EditText etUserName,etPassword,etAge;
	RadioGroup rg;
	RadioButton rbBoy,rbGirl;
	CheckBox cbRead,cbFootball,cbBasketball;
	Button btRegedit,btCancel;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//2.��ʼ��
		init();
		//3.����ע�������
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
				//�õ�����ת�����ַ�����ȥ��ǰ��Ŀո�
				if(strAge.equals("")||strAge==null) {
					Toast.makeText(getApplicationContext(), "Age can't be null", Toast.LENGTH_SHORT).show();
					//�������壺1.������ 2.��˾���� 3.��˾ʱ�䳤�� 
				}else {
					age=Integer.parseInt(strAge);//�ַ���ǿ��ת��Ϊint����
				}
				if(rbGirl.isChecked()) {
					sex=rbGirl.getText().toString();
				}
				//��ѡ���Ƿ�ѡ��
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
				//�Ի���Ҫ�����ڸ�����ֵ� 
				//����������д��Builder builder=new Builder(Mainactivity.this)Ҳûë��Ŷ
				//�������Ǽ���AlertDialog���ܼ���֪��ɶ�����������д��������ë��ֻ����û��������~ o(*������*)o
				builder.setIcon(R.drawable.ic_launcher);
				builder.setTitle("Your info");
				builder.setMessage(userName+"\n"
                                   +password+"\n"
                                   +age+"\n"
                                   +sex+"\n"
                                   +hobbies);
				
				builder.setPositiveButton("Yes", null);
				//�ڶ�����������Ӧ�¼� �������õ�null
				builder.setNegativeButton("No", null);
				AlertDialog ad=builder.create();//����һ��ad�Ի��� ֮ǰbuilderֻ���ڴ�����ӣ���������˼
				ad.show();//show��ad
				//͵������������������д
				//builder.create().show();
				
				
				//NEW TASK:����˾��ע����Ϣ�ڶ�����ʾ
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
