package jxnu.edu.cn.x3321;

import android.os.Bundle;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {

	//1.�����Ա����
	EditText et;
	Button bt;
	Spinner sp;
	ArrayList<String> al=new ArrayList<String>();
	MyAdapter ma;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//2.��ʼ��
		init();
		
		//3.׼������
		al.add("aaa");
		
		//4.�Զ�����������װ����
		ma=new MyAdapter(getApplicationContext(),al);
		
		//5.����ma��sp
		sp.setAdapter(ma);
		
		//6.����ע�������
		bt.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String str=et.getText().toString().trim();
				if(str==null||str.equals("")) {
					Toast.makeText(getApplicationContext(), "��������ȷ���ݣ�", Toast.LENGTH_SHORT).show();
				}else {
					AlertDialog.Builder ad=new AlertDialog.Builder(MainActivity.this);
					ad.setTitle("��ʾ��Ϣ�Ի���");
					ad.setIcon(R.drawable.ic_launcher);
					ad.setMessage("ȷ��Ҫ���"+str+"������");
					ad.setPositiveButton("ȷ��ȷ��", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							al.add(et.getText().toString().trim());
							et.setText("");
						}
					});
					ad.setNegativeButton("��Ҫ��", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							 
							et.setText("");
						}
					});
					ad.create().show();
				}
				
				
			}
		});
		
	}

	private void init() {
		// TODO Auto-generated method stub
		et=(EditText)this.findViewById(R.id.et);
		bt=(Button)this.findViewById(R.id.bt);
		sp=(Spinner)this.findViewById(R.id.sp);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
