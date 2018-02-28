package jxnu.edu.cn.x3321.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import jxnu.edu.cn.x3321.R;
import jxnu.edu.cn.x3321.Student;

public class MyAdapter extends BaseAdapter {

	ArrayList<Student> students=new ArrayList<Student>();
	Context context;
	int images[]=new int[10];
	String names[]=new String[10];
	String tels[]=new String[10];
	String sex[]=new String[10];
	int ages[]=new int[10];
	public MyAdapter(Context applicationContext, ArrayList<Student> students,String tels[],String sex[],int ages[]) {
		// TODO Auto-generated constructor stub
		this.students=students;
		this.context=applicationContext;
		this.tels=tels;
		this.sex=sex;
		this.ages=ages;
	}//初始化？大概

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.students.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return this.students.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	
	int i=0;
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		//加载table.xml格式化每条数据
		LinearLayout ll=(LinearLayout)View.inflate(context, R.layout.table, null);
		
	    ImageView avatar=(ImageView)ll.findViewById(R.id.img);
	    avatar.setImageResource(students.get(position).getAvatar());
		
		TextView tvName=(TextView)ll.findViewById(R.id.tv_userName);
		tvName.setText(students.get(position).getName());
		//ArrayList.get()可以得到当前对象
		
		TextView tvTel=(TextView)ll.findViewById(R.id.tv_tel);
		tvTel.setText(tels[position]);

		return ll;
		
	}

}
