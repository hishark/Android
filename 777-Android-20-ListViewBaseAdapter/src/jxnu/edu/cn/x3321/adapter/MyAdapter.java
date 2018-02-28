package jxnu.edu.cn.x3321.adapter;

import java.util.ArrayList;

//import android.R;java文件初始就是这一句
import jxnu.edu.cn.x3321.R;//但是R文件所在包名是初始创建的包名
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import jxnu.edu.cn.x3321.MainActivity;

public class MyAdapter extends BaseAdapter {
	ArrayList<String> al=new ArrayList<String>();
	Context context;
	
	public MyAdapter(Context context, ArrayList<String> al) {
		// TODO Auto-generated constructor stub
		this.al=al;
		this.context=context;
	}

	//得到要显示数据的条数
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.al.size();//数组长度
	}

	//返回当前选中的那一条对象
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return this.al.get(position);
	}

	//返回当前选中对象的id
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
    //决定每条数据的样式,它会被调用若干次
	//用来格式化每一行的数据 （下标从0开始）position从0开始取值一直到al.size()-1
	//第二个参数当前的view，第三个参数父view，这两个参数基本用不到
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		//加载table.xml格式化每条数据
		LinearLayout ll=(LinearLayout)View.inflate(context, R.layout.table, null);
		
		TextView tvUserName=(TextView)ll.findViewById(R.id.tv_userName);
		tvUserName.setText(al.get(position));

		TextView tvTel=(TextView)ll.findViewById(R.id.tv_tel);
		tvTel.setText("1390799000"+position);
		
		return ll;
	}

}
