package jxnu.edu.cn.x3321;

import java.util.ArrayList;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;


public class MyAdapter extends BaseAdapter {

	Context context;
	ArrayList<String> al;
	public MyAdapter(Context context, ArrayList<String> al) {
		// TODO Auto-generated constructor stub
		this.context=context;
		this.al=al;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return al.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return al.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		//������table.xml�ˣ�ֱ���Զ��岼�ֹ���������ʽ��ÿ������
		 
		
		TextView tv=new TextView(context);
		tv.setText(al.get(position).trim());
		tv.setTextSize(20);
		tv.setTextColor(Color.BLACK);
		tv.setWidth(200);
		//tv.setGravity(Gravity.CENTER);
	 
		
		return tv;
	}

}
