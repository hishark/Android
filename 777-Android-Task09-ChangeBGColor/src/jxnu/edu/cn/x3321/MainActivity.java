package jxnu.edu.cn.x3321;

import android.os.Bundle;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ExpandableListView.OnChildClickListener;

public class MainActivity extends Activity {

	
	//1.define
	Button bt;
	 
	ExpandableListView elv;
	MyelvAdapyer ma1;
	 
	String []group=new String[] {
			"blue","green","yellow","red","black"
	};
	int [][]colors=new int[][] {
		{Color.BLUE},
		{Color.GREEN},
		{Color.YELLOW},
		{Color.RED},
		{Color.BLACK},
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//2.chushihua
		init();
		
		//3.把适配器加载到elv中
		ma1=new MyelvAdapyer(getApplicationContext(),group,colors);
		 
		//4.listener
		bt.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog ad=createDialog();
				ad.show();
				
			}
		});
		
		
		
		 
	}
	
	protected AlertDialog createDialog() {
		// TODO Auto-generated method stub
		AlertDialog alertdialog;
		AlertDialog.Builder ad=new AlertDialog.Builder(MainActivity.this);
		
		ad.setIcon(R.drawable.ic_launcher);
		ad.setTitle("choose your BackGroundColor");
		
		elv=new ExpandableListView(getApplicationContext());
		elv.setAdapter(ma1);
		elv.setOnChildClickListener(new OnChildClickListener() {
			
			@Override
			public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
				// TODO Auto-generated method stub
				RelativeLayout rl=(RelativeLayout)MainActivity.this.findViewById(R.id.rl);
				rl.setBackgroundColor(colors[groupPosition][childPosition]);
				
				return true;
			}
		});
		
		ad.setView(elv);
		
		alertdialog=ad.create();
		return alertdialog;
	}

	private void init() {
		// TODO Auto-generated method stub
		bt=(Button)this.findViewById(R.id.bt);
		 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
