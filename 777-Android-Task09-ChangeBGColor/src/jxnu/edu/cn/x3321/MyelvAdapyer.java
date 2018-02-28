package jxnu.edu.cn.x3321;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyelvAdapyer extends BaseExpandableListAdapter {

	String []group;
	int [][]colors;
	Context context;
	
	public MyelvAdapyer(Context applicationContext, String[] group, int[][] colors) {
		// TODO Auto-generated constructor stub
		this.context=applicationContext;
		this.group=group;
		this.colors=colors;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return colors[groupPosition][childPosition];
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
			ViewGroup parent) {
		// TODO Auto-generated method stub
		LinearLayout ll=(LinearLayout)View.inflate(context, R.layout.childlayout, null);
		
		TextView color=(TextView)ll.findViewById(R.id.color);
		color.setBackgroundColor(colors[groupPosition][childPosition]);
		
		return ll;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return colors[groupPosition].length;
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return group[groupPosition];
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return group.length;
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
        LinearLayout ll=(LinearLayout)View.inflate(context, R.layout.grouplayout, null);
		TextView groups=(TextView)ll.findViewById(R.id.groupname);
		
		groups.setText(group[groupPosition]);
		 
		
		return ll;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	
	//用了elv.setOnChildClickListener的话一定要记得把这里的return改成true！！不然没用啊老哥！！
	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return true;
	}

}
