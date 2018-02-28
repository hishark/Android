package jxnu.edu.cn.x3321;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface.OnClickListener;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class MainActivity extends Activity {

	//1.定义成员变量
	ListView lv;
	ArrayList<Map<String,Object>> list;
	int images[]=new int[] {
			R.drawable.cat1,
			R.drawable.cat2,
			R.drawable.cat3,
			R.drawable.cat4,
			R.drawable.cat5,
			R.drawable.cat6,
			R.drawable.cat7,
			R.drawable.cat8,
			R.drawable.cat9,
			R.drawable.cat10
	};
	//定义好头像图片之后用它初始化student就可以了嘻嘻
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//2.初始化
		init();
		
		//3.准备数据
		Student stu1=new Student(images[0],"Pony","13746658897","452428574");
		Student stu2=new Student(images[1],"Cindy","11845767849","578496542");
		Student stu3=new Student(images[2],"Nancy","17746584499","378469510");
		Student stu4=new Student(images[3],"Gigi","13946754454","956724318");
		Student stu5=new Student(images[4],"Joan","15846754472","254698135");
		Student stu6=new Student(images[5],"Niki","18967854455","578854558");
		Student stu7=new Student(images[6],"Gary","17844583366","333555777");
		Student stu8=new Student(images[7],"Helen","15922235588","478585412");
		Student stu9=new Student(images[8],"Lee","13757845965","785414111");
		Student stu10=new Student(images[9],"Ann","13979954668","456921302");
		
		list=new ArrayList<Map<String,Object>>();
		//map第二个参数改成object因为之后要放图片 string是object的子类
		
		Map<String,Object> m1=new HashMap<String,Object>();
		m1.put("avatar", stu1.avatar);//往hashmap里存东西
		m1.put("name", stu1.name);
		m1.put("tel", stu1.tel);
		m1.put("qq", stu1.qq);
		list.add(m1);
		
		Map<String,Object> m2=new HashMap<String,Object>();
		m2.put("avatar", stu2.avatar);
		m2.put("name", stu2.name);
		m2.put("tel", stu2.tel);
		m2.put("qq", stu2.qq);
		list.add(m2);
		
		Map<String,Object> m3=new HashMap<String,Object>();
		m3.put("avatar", stu3.avatar);
		m3.put("name", stu3.name);
		m3.put("tel", stu3.tel);
		m3.put("qq", stu3.qq);
		list.add(m3);
		
		Map<String,Object> m4=new HashMap<String,Object>();
		m4.put("avatar", stu4.avatar);
		m4.put("name", stu4.name);
		m4.put("tel", stu4.tel);
		m4.put("qq", stu4.qq);
		list.add(m4);
		
		Map<String,Object> m5=new HashMap<String,Object>();
		m5.put("avatar", stu5.avatar);
		m5.put("name", stu5.name);
		m5.put("tel", stu5.tel);
		m5.put("qq", stu5.qq);
		list.add(m5);
		
		Map<String,Object> m6=new HashMap<String,Object>();
		m6.put("avatar", stu6.avatar);
		m6.put("name", stu6.name);
		m6.put("tel", stu6.tel);
		m6.put("qq", stu6.qq);
		list.add(m6);
		
		Map<String,Object> m7=new HashMap<String,Object>();
		m7.put("avatar", stu7.avatar);
		m7.put("name", stu7.name);
		m7.put("tel", stu7.tel);
		m7.put("qq", stu7.qq);
		list.add(m7);
		
		
		
		Map<String,Object> m8=new HashMap<String,Object>();
		m8.put("avatar", stu8.avatar);
		m8.put("name", stu8.name);
		m8.put("tel", stu8.tel);
		m8.put("qq", stu8.qq);
		list.add(m8);
		
		
		Map<String,Object> m9=new HashMap<String,Object>();
		m9.put("avatar", stu9.avatar);
		m9.put("name", stu9.name);
		m9.put("tel", stu9.tel);
		m9.put("qq", stu9.qq);
		list.add(m9);
		
		Map<String,Object> m10=new HashMap<String,Object>();
		m10.put("avatar", stu10.avatar);
		m10.put("name", stu10.name);
		m10.put("tel", stu10.tel);
		m10.put("qq", stu10.qq);
		list.add(m10);
		
		
		//4.把数据封装到SimpleAdapter适配器
		SimpleAdapter stu=new SimpleAdapter(getApplicationContext(),list, R.layout.table,
				new String[] {"avatar","name","tel"},new int[] {R.id.img,R.id.tv_userName,R.id.tv_tel}
				);
		
		//5.把simpleadapter适配器加载到listview
		lv.setAdapter(stu);
		
		//6.定义注册监听器
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
				//对话框要依附于父类AlertDialog出现的 
				builder.setIcon(R.drawable.catcc);
				builder.setTitle("CAT INFOMATION");
				builder.setMessage(
						"NAME:"+list.get(arg2).get("name")+"\n"
						+"    TEL:"+list.get(arg2).get("tel")+"\n"
						+"     QQ:"+list.get(arg2).get("qq"));
				
				builder.setPositiveButton("CANCEL",null);
				builder.setNegativeButton("CALL", null);
				AlertDialog ad=builder.create();
				ad.show();
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
