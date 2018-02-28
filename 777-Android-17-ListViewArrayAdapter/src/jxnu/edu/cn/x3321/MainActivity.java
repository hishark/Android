package jxnu.edu.cn.x3321;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	//1.�����Ա����
	ListView lv;
	
	//2.׼�����ݣ�mvc��ģ�Ͳ㣿������Ū������ ������ҪŪ���ݿ��
	String [] food= {
			"С��Ϻ",
	        "�����",
	        "���",
	        "�Ź�",
	        "���",
	        "��բз",
	        "����",
	        "ţ��",
	        "ţ��",
	        "С����",
	        "�ɹ�����",
	        "�Ҷ���"
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//3.ʹ��Ա���ñ���ָ������ϵ�Ԫ�ض��󣨳�ʼ����
		init();
		
		//4.�����ݷ�װ��arrayadapter������     �������壨 1.�����ģ�2.ÿһ�����ݵ���ʾ��ʽ��3.��ʾʲô���ݣ�
		//ListView��ʾ��ʽ��ȱʡ�������һ��һ��textview
		//android.R.layout.simple_list_item_1����һ����ʾһ�� Ȼ��������ɫ�Ұ�ɫ ����������
		//food��12������ ����ѭ������ʾ12��~
		//�������ݾͰ���˳���װ��adapter����
		ArrayAdapter aa=new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,food);
		
		//5.��arrayAdapter���������ص�listView��
		lv.setAdapter(aa);
		
		//6.���塢ע���¼�������
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			
			@Override
			//onItemClick()��һ�������������������ڶ����������ǵ�������һ��
			//�����������ǵ�ǰѡ�е��������������Ϊ�����±꣬��0��ʼ�������ĸ�������ʾ��ѡ�е���һ����id�����û��id�Ϳհɣ�
			//�õ����ľ��ǵڶ��͵���������������Ҫ��Ҳ�ǵڶ����͵���������
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				TextView tv=(TextView)arg1;
				//View�Ǹ��� ����Ҫǿ��ת��
				String str=tv.getText().toString();
				Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
			}
		});
	}

	private void init() {
		// TODO Auto-generated method stub
		lv=(ListView)this.findViewById(R.id.lv);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
