package android.te36;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.SubMenu;
import android.widget.*;
 

public class MainActivity extends Activity {

	//1.
	TextView tv;
	String []hobbies=new String[] {
			"游泳","篮球","足球"
	};
	
	//以后要多灵活运用boolean变量 很多时候一个boolean可以解决很多麻烦的问题 你以前从来不会主动用啊智障
	boolean []bl=new boolean[] {
			false,false,false
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//2.
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		tv=(TextView)this.findViewById(R.id.tv);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		//3.创建子菜单
		SubMenu submenu=menu.addSubMenu("性别");
		submenu.add(0,1,0,"♂");
		submenu.add(0,2,0,"♀");
		submenu.setGroupCheckable(0, true, true);
		//使组号为0的只能选一个
		
		menu.add("爱好");
		
		
		MenuItem exit=menu.add("退出");
		exit.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				// TODO Auto-generated method stub
				//退出整个应用程序
				finish();
				return false;
			}
		});
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		//4.响应用户点击的菜单项
		String result="你的选择是：";
		if(item.getTitle().equals("♂")) {
			result+="男 ...";
		}else if(item.getTitle().equals("♀")) {
			result+="女...";
		}else if(item.getTitle().equals("爱好")) {
			AlertDialog dialog=createDialog();
			dialog.show();
		}
		
		tv.setText(result);
		
		return super.onOptionsItemSelected(item);
	}

	private AlertDialog createDialog() {
		// TODO Auto-generated method stub
		AlertDialog alertDialog;
		AlertDialog.Builder ad=new AlertDialog.Builder(MainActivity.this);
		
		 
		ad.setTitle("爱好选择对话框");
		ad.setIcon(R.drawable.ic_launcher);
	 	 
		 //参数：1.要显示哪个数据集 3.监听器 单机某一个单选项就会有一个单击事件 处理这个事件的监听器在下面
		 //第二个参数是一个boolean数组 用来表示复选框的选中状态
		 //该程序设置的初始状态是三个都不选。
		 ad.setMultiChoiceItems(hobbies,bl,new DialogInterface.OnMultiChoiceClickListener() {
			
			 //参数：1.对话框2.选中的第几个3.选中的下标为which的那一项是否被选中
			 //这个复选框的状态比单选框的复杂一些，要判断是选中了还是没选中。
			 //第三个参数表示当前复选框的选择状态（选中or未选中） 
			@Override
			public void onClick(DialogInterface dialog, int which, boolean isChecked) {
				//isChecked : True if the click checked the item, else false. 
				// TODO Auto-generated method stub
				//存储最终结果的字符串
				String result="";
				//这个字符串每点一次选项都会清空！刺激刺激
				
				bl[which]=isChecked;//当前单击的选项被选中，下标which
				//bl一直保存着复选框的选中状态
				
				//遍历一下bl数组判断一下true还是false
				for(int i=0;i<bl.length;i++) {
					if(bl[i]) {
						//如果选中了
						result=result+" "+hobbies[i];
						//就把所选项加进字符串！
					}
				}
				
				
				//设置显示前再判断一下 如果字符串为空就设置空的 如果有内容就原封不动设置上去
				if(result.length()>0) {
					tv.setText(result);
				}else {
					tv.setText("");
				}
					
				
				
			}
		} );
		

		
		ad.setPositiveButton("确定",null );
		ad.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				tv.setText("");
				//既然点了取消就干脆回到初始状态 省得出现差错
				bl[0]=false;
				bl[1]=false;
				bl[2]=false;
				
			}
		}) ;
		
		
		alertDialog=ad.create();//ad是builder类型 用了create()就是AlertDialog类型
		return alertDialog;
	}

}
