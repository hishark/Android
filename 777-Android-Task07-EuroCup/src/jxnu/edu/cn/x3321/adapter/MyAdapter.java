package jxnu.edu.cn.x3321.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
 
import jxnu.edu.cn.x3321.R;

public class MyAdapter extends BaseAdapter {

	 
	Context context;
	String[][] group;
	int [][] flags;
	public MyAdapter(Context applicationContext, String[][] group,int [][] flags) {
		// TODO Auto-generated constructor stub
		this.context=applicationContext;
		this.group=group;
		this.flags=flags;
	}
 
	
	

	//�õ�Ҫ��ʾ���ݵ�����
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return group.length;
	}

	//���ص�ǰѡ�е���һ������
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return group[position];
	}
	
 
	
	//���ص�ǰѡ�ж����id
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	 
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LinearLayout ll=(LinearLayout)View.inflate(context, R.layout.finalistview, null);
 
		//country1
		ImageView flag1=(ImageView)ll.findViewById(R.id.iv_flag1);
		flag1.setImageResource(flags[position][0]);
		
		TextView name1=(TextView)ll.findViewById(R.id.tv_name1);
		name1.setText(group[position][0]);
		
		//country2
		ImageView flag2=(ImageView)ll.findViewById(R.id.iv_flag2);
		flag2.setImageResource(flags[position][1]);
		
		TextView name2=(TextView)ll.findViewById(R.id.tv_name2);
	    name2.setText(group[position][1]);
	    
	    //country3
	    ImageView flag3=(ImageView)ll.findViewById(R.id.iv_flag3);
		flag3.setImageResource(flags[position][2]);
		
		TextView name3=(TextView)ll.findViewById(R.id.tv_name3);
	    name3.setText(group[position][2]);
	    
	    //country4
	    ImageView flag4=(ImageView)ll.findViewById(R.id.iv_flag4);
		flag4.setImageResource(flags[position][3]);
		
		TextView name4=(TextView)ll.findViewById(R.id.tv_name4);
	    name4.setText(group[position][3]);
		
	    
		return ll;
	}

}
