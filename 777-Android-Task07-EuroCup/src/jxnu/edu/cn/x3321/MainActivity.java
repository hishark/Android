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

	//1.�����Ա����
	ListView lv;
	ExpandableListView elv;
	MyAdapter ma;//������������
	MyEla myela;//�Ի���������
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
	
	
	//�Ի�����Ҫ�õ��ı�����
	int greenNum[]=new int[]{1,2,3,4};//�游ѡ��������������� �Ĵ��й��Ҷ��ǲ����
	int littleFlag[]=new int[4];//�游ѡ����Ĺ���  �Ĵ��й����ǻ��ģ��������Ҫ����click������ȥѭ����ֵ��
	String score[]=new String[] {
			"2    0    1    4    5    6","1    1    1    3    3    4","1    1    1    5    3    4","0    2    1    2    3    2"
	};//�游ѡ����ıȷ�
	//����˵�ȷ�Ҳ�ǻ��ĵ�����ʦֻ��һ��ͼ�Ҹɴ�ȷ�ȫ�����һ������
	String headline[][]=new String[][] {
		{"Czech 2:1 Greece","Russian 4:1 Czech"},
		{"Czech 2:1 Greece","Russian 4:1 Czech"},
		{"Czech 2:1 Greece","Russian 4:1 Czech"},
		{"Czech 2:1 Greece","Russian 4:1 Czech"}
	};//����ѡ����ı�������
	//ͬ�� ֻ��һ��ʾ�����ͼ ���ֲ��������ܹ���͵������������
	
	//�游�Ĳ���layout
	LinearLayout greenFlagScore;
	//��ÿ����������Ӳ���layout
	LinearLayout result;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//2.��ʼ��
		init();
		
		//3.�Զ���������
		ma=new MyAdapter(getApplicationContext(),group,flags);
		myela=new MyEla(getApplicationContext(),greenNum,littleFlag,score,headline,result,greenFlagScore);
		
		//4.�����������ص�lv
		lv.setAdapter(ma);
		//5.������
		lv.setOnItemClickListener(new OnItemClickListener() {
			//onItemClick()��һ�������������������ڶ����������ǵ�������һ��
			//�����������ǵ�ǰѡ�е��������������Ϊ�����±꣬��0��ʼ�������ĸ�������ʾ��ѡ�е���һ����id�����û��id�Ϳհɣ�
			//�õ����ľ��ǵڶ��͵���������������Ҫ��Ҳ�ǵڶ����͵���������
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				AlertDialog ad=createDialog();
				ad.show();
				//���常ѡ�����С���� �Բ��������
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
		
		//���ؿ���չ�б�
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


