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

	//String headline[];//����ѡ����ı������� -----����Ϳ���ת�ɶ�ά������
	int greenNum[];//�游ѡ���������������
	int littleFlag[];//�游ѡ����Ĺ���
	String score[];//�游ѡ����ıȷ�
	String headline[][];//����ѡ����ı�������
	Context context;
	LinearLayout result;
	LinearLayout greenFlagScore;
	
	
	//ListView��Ҫ�õ������б���
	//����ѡ�������ϸ�÷����
	//��Ա����
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
	//Сͼ�����
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
	//�÷�
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

	//������child 
	//һ������һ���ͳ�
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

	 
	//�������һ�����linearLayout����TextView+ListView
	//TextView�ű��� ListView�ž�����  
	
	//ListViewҪ��Ūһ�������� ����µ���������һ�зֳ�������
	//ÿ���������ImageView+TextView+TextView����
	
	//TextView����ı������ݴ����洫���� 
	//ListView�����һ����������µ������������ �µ����������մ�������������� �Ȳ���
	
	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
			ViewGroup parent) {
		// TODO Auto-generated method stub
		LinearLayout ll=(LinearLayout)View.inflate(context,R.layout.child_score_layout, null);
		
		TextView tvheadline=(TextView)ll.findViewById(R.id.tv_headline);
		tvheadline.setText(headline[groupPosition][childPosition]);
		 
		
		//�����е���ϸ�ȷֽ���Ž�һ��GridView��װ����
		MyGridView gvchildscore=new MyGridView(context);
		gvchildscore.setNumColumns(2);
		 
		//�Զ���һ�������� �����ݴ���ȥ
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

	
	//��View����ֻҪ��һ�ж��� �����������ּ��� ���ݴ����洫���� һ�������ĸ����ݵ�һά����
	//�������TextView+ImageView+TextView 
	//TV������ IV�Ź��� TV��һ���ȷ�
	//���ִ��������  ������������ �ȷ�Ҳ���������
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
