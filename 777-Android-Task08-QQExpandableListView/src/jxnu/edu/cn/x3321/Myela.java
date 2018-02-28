package jxnu.edu.cn.x3321;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Myela extends BaseExpandableListAdapter {

	String []group;
	String [][]members;
	Context context;
	public Myela(Context applicationContext, String[] group, String[][] members) {
		// TODO Auto-generated constructor stub
		this.context=applicationContext;
		this.group=group;
		this.members=members;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return members[groupPosition][childPosition];
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
		
		LinearLayout ll=(LinearLayout)View.inflate(context, R.layout.qqlayout, null);
		ll.setPadding(0, 6, 6, 6);
		ImageView ava=(ImageView)ll.findViewById(R.id.avatar);
		ava.setImageResource(R.drawable.face);
		
		TextView name=(TextView)ll.findViewById(R.id.name);
		name.setText(members[groupPosition][childPosition]);
		name.setPadding(12, 6, 0, 6);
		name.setTextSize(20);
		name.setTextColor(Color.GRAY);
		
		TextView sign=(TextView)ll.findViewById(R.id.sign);
		sign.setText("Hello I'm "+members[groupPosition][childPosition]);
		sign.setPadding(12, 6, 0, 6);
		return ll;
 
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return members[groupPosition].length;
	}
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
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
		LinearLayout ll=new LinearLayout(context);
		ll.setOrientation(0);//ˮƽ
		ll.setPadding(0, 20, 0, 20);
		
		ImageView arrow=new ImageView(context);
		arrow.setImageResource(R.drawable.arrow2);
		
		TextView tv=new TextView(context);
		tv.setText(group[groupPosition]);
		tv.setTextSize(20);
		tv.setTextColor(Color.parseColor("#4FBA6F"));
		tv.setPadding(25, 0, 0, 0);
		
		ll.addView(arrow);
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
