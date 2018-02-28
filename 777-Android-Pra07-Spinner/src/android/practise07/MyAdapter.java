package android.practise07;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {

	String symbol[];
	Context context;
	
	public MyAdapter(Context applicationContext, String[] symbol) {
		// TODO Auto-generated constructor stub
		this.context=applicationContext;
		this.symbol=symbol;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return symbol.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return symbol[position];
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
		tv.setText(symbol[position]);
		tv.setTextColor(Color.BLACK);
		tv.setTextSize(20);
		
		
		return tv;
	}

}
