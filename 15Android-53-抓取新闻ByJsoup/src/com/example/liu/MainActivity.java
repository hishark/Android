package com.example.liu;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
public class MainActivity extends Activity {
	public static ArrayList<News> loaclNewsList = new ArrayList<News>();
	private final String loaclUrl = "http://www.muscles.com.cn/news/";
	//1.定义成员引用变量
	ListView lv;
	MyAdapter ma;
	final Handler hd = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.what == 0) {
				//5.把MyAdapter数据加载到listView中
				lv.setAdapter(ma);
			}
			super.handleMessage(msg);
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		
		new Thread() {
			public void run() {
				try {
					String htmlcode = GetNewsFromInternet.getWebContentCode(loaclUrl);
					loaclNewsList = GetNewsFromInternet.getLocalNewsContent(htmlcode);	
				} catch (Exception e) {
					e.printStackTrace();
				}
				Message msg = new Message();
				msg.what = 0;
				hd.sendMessage(msg);
			}
		}.start();
		
		
		/*
		new AsyncTask<String,Void,Bitmap>(){
			@Override
			protected Bitmap doInBackground(String... params) {
				System.out.print("loaclUrl:"+loaclUrl);
				String htmlcode = GetNewsFromInternet.getWebContentCode(loaclUrl);
				loaclNewsList = GetNewsFromInternet.getLocalNewsContent(htmlcode);	
				return null;
				
			}
			protected void onPostExecute(Bitmap bitmap) {
				lv.setAdapter(ma);
			};
		};
		*/
		
		//4.实现自己定义的Adapter:MyAdapter
		ma=new MyAdapter();	
		
	}
	
	//自定义适配器
	public class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return loaclNewsList.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			
			return loaclNewsList.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			//6.通过代码控制UI组件
			/*
			TextView tv= new TextView(MainActivity.this);
		    tv.setText(al.get(position));
		    tv.setTextSize(20);
		    tv.setTextColor(Color.RED);
		    tv.setPadding(0, 5, 0, 5);
			return tv;
			*/
			
			//通过代码和xml控制UI组件
			LinearLayout ll=(LinearLayout)View.inflate(getApplicationContext(), 
					R.layout.table, null);
			TextView tvName=(TextView)ll.findViewById(R.id.title);
			tvName.setText(loaclNewsList.get(position).getTitle());
	
			TextView tvTel=(TextView)ll.findViewById(R.id.time_count);
			tvTel.setText(loaclNewsList.get(position).getTime_count());
			return ll;		
		}
		
	}
	private void init() {
		// TODO Auto-generated method stub
		lv=(ListView)this.findViewById(R.id.lv);
	}

}
