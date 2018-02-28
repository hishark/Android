package jxnu.edu.cn.x3321;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class MainActivity extends Activity {

	//1.�����Ա����
	ListView lv;
	//��ö���һ��Student�� ����ʲôʲôʲô
	//�����Ȳ������� 
	//ArrayList������Է��������͵Ķ������������
	//���������е㲻�ù��� ���Կ�����<>����������һ������
	
	//HashMap���� ��ֵ��Ҫ�ɶԳɶԵĴ� key-value
	//ȡֵֻ����ͨ��keyȥȡֵ
	//java�α�P334��335
	//Map<>Ҳ���Թ涨����
	ArrayList<Map<String,String>> list;
	//����һ��list
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//2.��ʼ��
		init();
		
		//3.׼������
		//�������hashmapȻ���ӽ�arraylist
		list=new ArrayList<Map<String,String>>();
		Map<String, String> m1=new HashMap<String,String>();
		//Map��һ���ӿ�  HashMap�Ǹýӿڵľ���ʵ��
		m1.put("name", "��С��");
		m1.put("tel","13767862093");
		m1.put("qq", "358436965");
		//��һ��hashmap�ӵ�list��ȥ
		list.add(m1);
		
		Map<String, String> m2=new HashMap<String,String>();
		//Map��һ���ӿ� HashMap�����ľ���ʵ��
		m2.put("name", "�Ÿ�");
		m2.put("tel","13879959788");
		m2.put("qq", "492365247");
		//��һ��hashmap�ӵ�list��ȥ
		list.add(m2);
		
		Map<String, String> m3=new HashMap<String,String>();
		//Map��һ���ӿ� HashMap�����ľ���ʵ��
		m3.put("name", "����");
		m3.put("tel","13979958543");
		m3.put("qq", "123456789");
		//��һ��hashmap�ӵ�list��ȥ
		list.add(m3);
		
		Map<String, String> m4=new HashMap<String,String>();
		//Map��һ���ӿ� HashMap�����ľ���ʵ��
		m4.put("name", "���");
		m4.put("tel","13970598323");
		m4.put("qq", "986895544");
		//��һ��hashmap�ӵ�list��ȥ
		list.add(m4);
		
		//4.�����ݷ�װ��simpleAdapter������
		//��һ���������������� ����Ҫ�ӵ�
		//�ڶ�����������Ҫ��ʾ�����ݼ��ϣ��������Ӧ�������������ݼ���ֻ������map��
		//������������������Ҫ����ô���ĸ�ʽ��װ����������ȥ�������ʾ������
		//���ĸ��������Ǳ�ʾ����Ҫ��ʾlist��ÿ�����ݵ���Щ����
		//���������������Щ����Ҫ�ŵ�table.xml���ĸ����ȥ��ʾ����
		SimpleAdapter sa=new SimpleAdapter(getApplicationContext(),
				list,R.layout.table,
				new String[] {"name","tel"},
				new int[] {R.id.tv_userName,R.id.tv_tel});//id��ַ�ǿ��Դ�ŵ����������е�
		
		//5.��simpleadapter���������ص�listview
		lv.setAdapter(sa);
		
		//6.����ע�������
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			
			@Override
			//onItemClick()��һ�������������������ڶ����������ǵ�������һ��
			//�����������ǵ�ǰѡ�е��������������Ϊ�����±꣬��0��ʼ�������ĸ�������ʾ��ѡ�е���һ����id�����û��id�Ϳհɣ�
			//�õ����ľ��ǵڶ��͵���������������Ҫ��Ҳ�ǵڶ����͵���������
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), 
						list.get(arg2).get("name")+"\n"
						+list.get(arg2).get("tel")+"\n"
						+list.get(arg2).get("qq")
						, Toast.LENGTH_SHORT).show();
			}
		});
	}
	//New Task
	//map�ĳ�student
	//student.java
	//ARRAYLIST STUDENT
	//��һ�¸ĳ�dialog��ʾ������Ϣ
	//ͷ��һ��images[]
	//��һ���˶�Ӧimages[0]
	//

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
