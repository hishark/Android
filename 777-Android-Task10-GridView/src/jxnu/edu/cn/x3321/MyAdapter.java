package jxnu.edu.cn.x3321;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MyAdapter extends BaseAdapter {

	Context context;
	int []number;
	
	public MyAdapter(Context applicationContext, int[] number) {
		// TODO Auto-generated constructor stub
		this.context=applicationContext;
		this.number=number;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return number.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return number[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LinearLayout ll=new LinearLayout(context);
		ll.setOrientation(LinearLayout.HORIZONTAL);
		ll.setPadding(12, 12, 12, 12);
		Button bt=new Button(context);
		bt.setText("Button");
		bt.setBackgroundColor(Color.GRAY);
		bt.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(context, String.valueOf(number[position]), Toast.LENGTH_SHORT).show();
			}
		});
		TextView tv=new TextView(context);
		tv.setText(String.valueOf(number[position]));
		tv.setPadding(20, 0, 0, 0);
		ll.addView(bt);
		ll.addView(tv);
		
		return ll;
		
		
		
	}

}
