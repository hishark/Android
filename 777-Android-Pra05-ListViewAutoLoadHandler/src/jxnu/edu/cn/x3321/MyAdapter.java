package jxnu.edu.cn.x3321;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {

	ArrayList<String> al=new ArrayList<String>();
	Context context;
	public MyAdapter(Context applicationContext, ArrayList<String> al) {
		// TODO Auto-generated constructor stub
		this.al=al;
		this.context=applicationContext;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return al.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return al.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		LinearLayout ll=new LinearLayout(context);
		ll.setOrientation(LinearLayout.VERTICAL);//不用0和1，可读性更好
		
		TextView name=new TextView(context);
		name.setText(al.get(arg0));
		
		TextView tel=new TextView(context);
		tel.setText("1390799000"+arg0);
		
		ll.addView(name);
		ll.addView(tel);
		
		return ll;
	}

}
