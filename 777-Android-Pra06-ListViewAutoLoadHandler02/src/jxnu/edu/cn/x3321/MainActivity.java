package jxnu.edu.cn.x3321;

import android.os.Bundle;
import android.os.Handler;

import java.util.ArrayList;

import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import jxnu.edu.cn.x3321.MyAdapter;

public class MainActivity extends Activity {

	//1.
	ListView lv;
	ArrayList<String> al=new ArrayList<String>();
    LinearLayout ll;
	Handler hd=new Handler();
	//不要传数据 所以用post不用send/handlemessage
	boolean bl=false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//2.
		init();
		//3.prepare data
				for(int i=0;i<9;i++) {
					al.add("tom"+i);
				}
				
				//5.自定义适配器
				final MyAdapter ma=new MyAdapter(getApplicationContext(),al);
				//final是什么来着
				
				//6.jiazai
				lv.setAdapter(ma);
				
				//7.
				lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					
					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
						// TODO Auto-generated method stub
						Toast.makeText(getApplicationContext(), al.get(arg2), Toast.LENGTH_SHORT).show();
					}
					
					
					
					
				});
				
				//滚动监听器
				lv.setOnScrollListener(new AbsListView.OnScrollListener() {
					
					@Override
					public void onScrollStateChanged(AbsListView view, int scrollState) {
						// TODO Auto-generated method stub
						if(lv.getLastVisiblePosition()==al.size()-1&&!bl) {
							bl=true;
							ll.setVisibility(View.VISIBLE);//可见
							new Thread() {
								public void run() {
									try {
										Thread.sleep(1000);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									for(int i=0;i<10;i++) {
										al.add("haha"+i);
									}
									hd.post(new Runnable() {

										@Override
										public void run() {
											// TODO Auto-generated method stub
											ma.notifyDataSetChanged();
											ll.setVisibility(View.INVISIBLE);
											bl=false;
										}
										
									});
									
								}
							}.start();
						}
						
					}
					
					@Override
					public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
						// TODO Auto-generated method stub
						
					}
				});
	}

	private void init() {
		// TODO Auto-generated method stub
		lv=(ListView)this.findViewById(R.id.lv);
		ll=(LinearLayout)this.findViewById(R.id.ll);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
