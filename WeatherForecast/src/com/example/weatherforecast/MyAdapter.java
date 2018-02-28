package com.example.weatherforecast;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {

	Context context;
	ArrayList<String> al_province;
	
	
	public MyAdapter(Context applicationContext, ArrayList<String> al_province) {
		// TODO Auto-generated constructor stub
		this.context=applicationContext;
		this.al_province=al_province;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return al_province.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return al_province.get(position);
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
		tv.setText(al_province.get(position));
		tv.setTextSize(20);
		tv.setTextColor(Color.BLACK);
		tv.setWidth(200);
		tv.setGravity(Gravity.CENTER);
	 
		
		return tv;
	}

}
