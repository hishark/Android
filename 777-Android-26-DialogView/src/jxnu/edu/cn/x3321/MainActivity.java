package jxnu.edu.cn.x3321;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {
	//1.�����Ա����
	Button bt;
	EditText etUserName,etPassword;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//2.��ʼ��
		init();
		
		//3.ע�������
		bt.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog ad=createDialog();
				//�Զ����createDialog���������Ի���
				ad.show();
			}
		});
	}

	protected AlertDialog createDialog() {
		// TODO Auto-generated method stub
		AlertDialog alertDialog;
		AlertDialog.Builder ad=new AlertDialog.Builder(MainActivity.this);
		
		 
		ad.setTitle("�Զ���View�Ի���");
		ad.setIcon(R.drawable.ic_launcher);
		
		//�����Զ����login.xml��ll��ȥ
		//�����ַ�������䣡�����գ�
		//way1
		final LinearLayout ll=(LinearLayout)getLayoutInflater().inflate(R.layout.login, null);
		//way2
		//final LinearLayout ll=(LinearLayout)View.inflate(getApplicationContext(), R.layout.login, null);
		
		//�Ѳ���ll���ص�Dialog����ȥ
		ad.setView(ll);
		
		ad.setPositiveButton("ȷ��",new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				etUserName=(EditText)ll.findViewById(R.id.et1);
			
				etPassword=(EditText)ll.findViewById(R.id.et2);
			
				Toast.makeText(getApplicationContext(), 
						"UserName:"+etUserName.getText()+"\n"
						+"Password:"+etPassword.getText(),
						Toast.LENGTH_LONG).show();
				
			}
		} );
		
		ad.setNegativeButton("ȡ��", null);
		
		alertDialog=ad.create();//ad��builder���� ����create()����AlertDialog����
		return alertDialog;
	}

	private void init() {
		// TODO Auto-generated method stub
		bt=(Button)this.findViewById(R.id.bt);
		etUserName=(EditText)this.findViewById(R.id.et1);
		etPassword=(EditText)this.findViewById(R.id.et2);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
