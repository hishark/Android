package jxnu.edu.cn.x3321;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyExpandableListAdapter extends BaseExpandableListAdapter {

	
	String [] grade;
	String [][] classes;
	Context context;
	public MyExpandableListAdapter(Context applicationContext, String[] grade, String[][] classes) {
		// TODO Auto-generated constructor stub
		this.context=applicationContext;
		this.grade=grade;
		this.classes=classes;
	}//��ʼ��

	
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return classes[groupPosition][childPosition];
	}//�õ���ǰ�Ӷ�����Ԫ�أ����

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childPosition;
	}//�õ���ǰ�Ӷ����id

	
	//grouppostion��childeposition������
	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
			ViewGroup parent) {
		// TODO Auto-generated method stub
		TextView tv=new TextView(context);
		tv.setText(classes[groupPosition][childPosition]);
	    tv.setPadding(0, 6, 6, 6);
	    
		return tv;
	}

	//����groupposition��һ�еĳ���
	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return classes[groupPosition].length;
	}

	//�����ʽ�����б�
	///////////////////////////////////////////////////////////////////////
	//�����ʽ�����б�
	
	//�õ���ǰ���±�idΪgroupposition��������¼
	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return grade[groupPosition];
	}

	//����һ���˵��ĳ���
	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return grade.length;
	}

	
	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		//�Զ��岼�ֹ�������ʽ��ÿ�飨һ���˵�������
		LinearLayout ll=new LinearLayout(context);
		ll.setOrientation(0);
		ll.setPadding(0, 12, 0, 12);
		
		TextView logo=new TextView(context);
		logo.setText("   ");
		logo.setWidth(20);
		ll.addView(logo);
		
		TextView tv=new TextView(context);
		tv.setText(grade[groupPosition]);
	    tv.setPadding(0, 12,12, 12);
	    
	    ll.addView(tv);
	    
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
