package jxnu.edu.cn.x3321;

import android.os.Bundle;
import android.os.Handler;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {

	//1.define the member variable
	ListView lv;
	Button bt;
	
	Handler hd=new Handler();
	ArrayList<String> al=new ArrayList<String>();
	ProgressDialog pd;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//2.init
		init();
		
		 
		bt=(Button)View.inflate(getApplicationContext(), R.layout.button, null);
		lv.addFooterView(bt);
		
		
		final MyAdapter ma=new MyAdapter(getApplicationContext(),al);
		lv.setAdapter(ma);
		
 
		bt.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				pd.show();
				new Thread() {
					public void run() {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						for(int i=0;i<10;i++) {
							al.add("Mike"+i);
						}
						
						hd.post(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								ma.notifyDataSetChanged();
								pd.dismiss();
							}
							
						});
						
					}
				}.start();
			}
		});
		
	}

	private void init() {
		// TODO Auto-generated method stub
		lv=(ListView)this.findViewById(R.id.lv);
	 
		for(int i=0;i<10;i++) {
			al.add("Kimi"+i);
		}
		
		pd=new ProgressDialog(MainActivity.this);
		pd.setCancelable(false);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
