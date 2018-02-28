package jxnu.edu.cn.x3321;

import android.os.Bundle;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import jxnu.edu.cn.x3321.adapter.MyAdapter;

public class MainActivity extends Activity {

	//1.定义成员变量
	ListView lv;
	ExpandableListView elv;
	MyAdapter ma;//主界面适配器
	MyEla myela;//对话框适配器
	String [][] group=new String[][] {
		{"Russian","Poland","Greece","Czech"},
		{"Netherlands","Germany","Portugal","Denmark"},
		{"Spain","Italy","Ireland","Croatia"},
		{"England","France","Sweden","Ukraine"}
	};
	final int [][]flags=new int[][] {
		{R.drawable.russian,R.drawable.poland,R.drawable.greece,R.drawable.czech},
		{R.drawable.netherlands,R.drawable.germany,R.drawable.portugal,R.drawable.denmark},
		{R.drawable.spain,R.drawable.italy,R.drawable.ireland,R.drawable.croatia},
		{R.drawable.england,R.drawable.france,R.drawable.sweden,R.drawable.ukraine},
	};
	
	
	//对话框里要用到的变量们
	int greenNum[]=new int[]{1,2,3,4};//存父选项绿球里面的数字 四大行国家都是不变的
	int littleFlag[]=new int[4];//存父选项里的国旗  四大行国家是会变的！所以这个要放在click方法里去循环赋值！
	String score[]=new String[] {
			"2    0    1    4    5    6","1    1    1    3    3    4","1    1    1    5    3    4","0    2    1    2    3    2"
	};//存父选项里的比分
	//按理说比分也是会变的但是老师只给一张图我干脆比分全部搞成一样吧嗝
	String headline[][]=new String[][] {
		{"Czech 2:1 Greece","Russian 4:1 Czech"},
		{"Czech 2:1 Greece","Russian 4:1 Czech"},
		{"Czech 2:1 Greece","Russian 4:1 Czech"},
		{"Czech 2:1 Greece","Russian 4:1 Czech"}
	};//存子选项里的标题内容
	//同上 只有一张示例结果图 我又不懂足球不能怪我偷懒哈哈哈哈哈
	
	//存父的布局layout
	LinearLayout greenFlagScore;
	//存每个父下面的子布局layout
	LinearLayout result;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//2.初始化
		init();
		
		//3.自定义适配器
		ma=new MyAdapter(getApplicationContext(),group,flags);
		myela=new MyEla(getApplicationContext(),greenNum,littleFlag,score,headline,result,greenFlagScore);
		
		//4.将适配器加载到lv
		lv.setAdapter(ma);
		//5.监听器
		lv.setOnItemClickListener(new OnItemClickListener() {
			//onItemClick()第一个参数是适配器本身，第二个参数就是单击的那一条
			//第三个参数是当前选中的行数（可以理解为数组下标，从0开始），第四个参数表示你选中的这一条的id（如果没赋id就空吧）
			//用的最多的就是第二和第三个参数，最重要的也是第二个和第三个参数
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				AlertDialog ad=createDialog();
				ad.show();
				//定义父选项里的小国旗 卧槽我是天才
				for(int i=0;i<4;i++) {
					littleFlag[i]=flags[arg2][i];
				}
				 
			}
		});
	}

	protected AlertDialog createDialog() {
		// TODO Auto-generated method stub
		AlertDialog alertdialog;
		AlertDialog.Builder ad=new AlertDialog.Builder(MainActivity.this);
		
		ad.setIcon(R.drawable.ic_menu_largetiles);
		ad.setTitle("Welcome to EuroCup 2012");
		
		//加载可扩展列表
		elv=new ExpandableListView(getApplicationContext());
		elv.setAdapter(myela);
		
		ad.setView(elv);
		ad.setPositiveButton("OK", null);
		
		alertdialog=ad.create();
		return alertdialog;
	}

	
	
	
	private void init() {
		// TODO Auto-generated method stub
		lv=(ListView)this.findViewById(R.id.lv);
		result=(LinearLayout)this.findViewById(R.layout.child_score_layout);
		greenFlagScore=(LinearLayout)this.findViewById(R.layout.father_green_flag_score);
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	

}


