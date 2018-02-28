package jxnu.edu.cn.x3321;

import android.os.Bundle;

import java.util.ArrayList;

import jxnu.edu.cn.x3321.Student;
import jxnu.edu.cn.x3321.adapter.MyAdapter;
import android.app.Activity;
import android.app.AlertDialog;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {

	//1.定义成员变量
	ListView lv;
	ArrayList<Student> students;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final int images[]=new int[]{
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
		String names[]=new String[] {
				"Lucy","Niki","Tom","Jack","Gigi",
				"Dean","Ann","Cindy","Kimi","Tiya",
		};
		String tels[]=new String[] {
				"13707990000","13707990001","13707990002","13707990003","13707990004",
				"13707990005","13707990006","13707990007","13707990008","13707990009"
		};
		String sex[]=new String[] {
				"girl","boy","girl","boy","girl",
				"boy","girl","boy","girl","boy"
		};
		int ages[]=new int[] {
				16,17,18,19,20,
				23,12,21,16,19
		};
		//对话框
		final AlertDialog.Builder builder=new AlertDialog.Builder(this);
		
		lv=(ListView)this.findViewById(R.id.lv);
		
		students= new ArrayList<Student>();  
		
		for(int i=0;i<10;i++) {
			students.add(new Student(images[i],names[i],sex[i],ages[i]));
		}
		
		//4.自定义适配器Adapter封装数据
		MyAdapter Ma=new MyAdapter(getApplicationContext(),students,tels,sex,ages);
		
		//5.把MyAdapter适配器中的数据加载到lv中
		lv.setAdapter(Ma);
		
		//6.监听器
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				builder.setMessage(students.get(arg2).getStuIfo());
	            builder.setTitle("Hi!\nThat's My Info!");
				builder.setIcon(images[arg2]);
				builder.setPositiveButton("CANCEL", null);
				builder.setNegativeButton("OK", null);
				AlertDialog ad=builder.create();
				ad.show();
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
