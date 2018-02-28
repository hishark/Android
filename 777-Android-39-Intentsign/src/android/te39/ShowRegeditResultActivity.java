package android.te39;

import android.os.Bundle;
import android.text.LoginFilter.UsernameFilterGeneric;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.*;
import jxnu.edu.cn.x3321.R;

public class ShowRegeditResultActivity extends Activity {

	//1.�����Ա����
	TextView tv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_regedit_result_main);
		
		//2.��ʼ��
		init();
		
		//3-1 ��һ�����ݻ�÷���
		//�ȵõ���ԴActivity��������intent���ٴ�intent����ȡ��bundle
		/*Intent intent=getIntent();//����Ҫ֪��Դͷ��˭ ֻҪ�õ�intent�Ϳ�����
		Bundle bd=intent.getExtras();
		String result="";
		result+=bd.getString("userName")+"\n";
		result+=bd.getString("password")+"\n";
		result+=bd.getString("age")+"\n";*/
		
		
		//3-2 �ڶ������ݻ�÷���
		//�ȵõ���ԴActivity��������intent���ٴ�intent��ֱ��ȡ������
		Intent intent=getIntent();
		String result="";
		result+=intent.getExtras().getString("userName")+"\n";
		result+=intent.getExtras().getString("password")+"\n";
		result+=intent.getExtras().getInt("age")+"\n";
		
		
		//4.��������ʾ��textview��ȥ
		tv.setText(result);
	}

	private void init() {
		// TODO Auto-generated method stub
		tv=(TextView)this.findViewById(R.id.tv);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show_regedit_result_main, menu);
		return true;
	}

}
