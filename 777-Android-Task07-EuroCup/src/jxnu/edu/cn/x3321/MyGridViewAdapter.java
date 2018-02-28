package jxnu.edu.cn.x3321;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MyGridViewAdapter extends BaseAdapter {

	int[] playerImg;
	String[] playername;
	int[] playerScore;
	Context context;
	public MyGridViewAdapter(Context context, int[] playerImg2, String[] playername2, int[] playerScore2) {
		// TODO Auto-generated constructor stub
		this.context=context;
		this.playerImg=playerImg2;
		this.playername=playername2;
		this.playerScore=playerScore2;
	}

	
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return playerImg.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return playerImg[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	//这是网格布局中的一个子布局
	//一个ImageView+一个TextView+一个TextView
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LinearLayout ll=(LinearLayout)View.inflate(context, R.layout.gridlayout, null);
		 
		 
		ImageView img=(ImageView)ll.findViewById(R.id.grid_img);
		img.setImageResource(playerImg[position]);
		
		TextView tv=(TextView)ll.findViewById(R.id.grid_playername);
		tv.setText(playername[position]);
		
		TextView tv2=(TextView)ll.findViewById(R.id.grid_playerscore);
		tv2.setText(String.valueOf(playerScore[position]));
		 
		return ll;
	}

}
