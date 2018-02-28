package android.pra08;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {

	ArrayList<String> al;
	Context context;
	
	public MyAdapter(Context applicationContext, ArrayList<String> al) {
		// TODO Auto-generated constructor stub
		this.context=applicationContext;
		this.al=al;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return  al.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return  al.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		TextView tv=new TextView(context);
		tv.setText(al.get(position));
		tv.setTextColor(Color.BLACK);
		tv.setTextSize(20);
		return tv;
	}

}
