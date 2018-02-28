package jxnu.edu.cn.x3321;

import java.lang.reflect.Field;

import android.R.string;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
 
 
import jxnu.edu.cn.x3321.R;

public class MyEla extends BaseExpandableListAdapter {

	//String headline[];//存子选项里的标题内容 -----这个就可以转成二维数组啦
	int greenNum[];//存父选项绿球里面的数字
	int littleFlag[];//存父选项里的国旗
	String score[];//存父选项里的比分
	String headline[][];//存子选项里的标题内容
	Context context;
	LinearLayout result;
	LinearLayout greenFlagScore;
	
	
	//ListView里要用到的所有变量
	//存子选项里的详细得分情况
	//球员名字
	String playername[][]=new String[][] {
		{"Petr Jiracek","Chalkias","Vaclav Pilar",
			"Sifakis","Tomas Rosicky","Torossidis",
			"Petr Jiracek","Fotakis","Tomas Rosicky",
			"Fanis Gekas","Daniel Kolar","Fanis Gekas"},
		{"Alan Dzageoev","Jan Rezek","Roman Shirokov",
				"Hubschman","Kerzhakov","Vaclav Pilar",
				"Pavlyuchenko","Petr Jiracek","Alan Dzagoev",
				"David Limbersky","Pavlyuchenko","Milan Baros"}
	
	};
	//小图标情况
	int playerImg[][]=new int[][] {
		{R.drawable.goals,R.drawable.substs,R.drawable.goals,
			R.drawable.substs,R.drawable.yells,R.drawable.yells,
			R.drawable.yells,R.drawable.substs,R.drawable.substs,
			R.drawable.substs,R.drawable.substs,R.drawable.goals},
		{R.drawable.goals,R.drawable.substs,R.drawable.goals,
				R.drawable.substs,R.drawable.substs,R.drawable.goals,
				R.drawable.substs,R.drawable.substs,R.drawable.goals,
				R.drawable.substs,R.drawable.goals,R.drawable.substs}
		
	};
	//得分
	int playerScore[][]=new int[][] {
		{3,6,27,36,46,46,23,23,34,46,46,53},
		{13,46,24,46,73,52,73,76,79,76,82,85}
	};
	
	
	public MyEla(Context applicationContext, int[] greenNum2, int[] littleFlag2, String[] score2,
			String[][] headline2, LinearLayout result, LinearLayout greenFlagScore) {
		// TODO Auto-generated constructor stub
		this.context=applicationContext;
		this.greenNum=greenNum2;
		this.littleFlag=littleFlag2;
		this.score=score2;
		this.headline=headline2;
		this.result=result;
		this.greenFlagScore=greenFlagScore;

	}

	//有两个child 
	//一个主场一个客场
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		
		return result;
		//return null;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childPosition;
	}

	 
	//这里加载一个大的linearLayout包括TextView+ListView
	//TextView放标题 ListView放具体结果  
	
	//ListView要再弄一个适配器 这个新的适配器里一行分成两大列
	//每大列里面放ImageView+TextView+TextView即可
	
	//TextView里面的标题内容从外面传进来 
	//ListView里面的一大堆内容用新的适配器来解决 新的适配器接收从外面进来的内容 先不管
	
	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
			ViewGroup parent) {
		// TODO Auto-generated method stub
		LinearLayout ll=(LinearLayout)View.inflate(context,R.layout.child_score_layout, null);
		
		TextView tvheadline=(TextView)ll.findViewById(R.id.tv_headline);
		tvheadline.setText(headline[groupPosition][childPosition]);
		 
		
		//把所有的详细比分结果放进一个GridView里装起来
		MyGridView gvchildscore=new MyGridView(context);
		gvchildscore.setNumColumns(2);
		 
		//自定义一个适配器 把数据传过去
		MyGridViewAdapter mgva=new MyGridViewAdapter(context,playerImg[childPosition],playername[childPosition],playerScore[childPosition]);
		gvchildscore.setAdapter(mgva);
		ll.addView(gvchildscore);
		
		
		return ll;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return greenFlagScore;
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	
	//父View里面只要放一行东西 球球国旗和数字即可 数据从外面传进来 一个包含四个数据的一维数组
	//这里包括TextView+ImageView+TextView 
	//TV填数字 IV放国旗 TV放一串比分
	//数字从外面进来  国旗从外面进来 比分也从外面进来
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LinearLayout ll=(LinearLayout)View.inflate(context,R.layout.father_green_flag_score, null);
	
		TextView greennumber=(TextView)ll.findViewById(R.id.greenball);
		greennumber.setText(String.valueOf(greenNum[groupPosition]));
		greennumber.setBackgroundResource(R.drawable.seq);
	 
		
		ImageView smallflag=(ImageView)ll.findViewById(R.id.smallflag);
		smallflag.setImageResource(littleFlag[groupPosition]);
		
		TextView stringscore=(TextView)ll.findViewById(R.id.greenScore);
		stringscore.setText(score[groupPosition]);
		
		
		
		return ll;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return false;
	}

}
