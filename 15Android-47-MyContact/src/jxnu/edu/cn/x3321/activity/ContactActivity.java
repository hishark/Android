package jxnu.edu.cn.x3321.activity;

import jxnu.edu.cn.x3321.R;
import jxnu.edu.cn.x3321.R.layout;
import jxnu.edu.cn.x3321.R.menu;
import jxnu.edu.cn.x3321.Interface.UserInterface;
import jxnu.edu.cn.x3321.InterfaceImp.UserInterfaceImp;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class ContactActivity extends Activity {

	//1.定义成员变量
	ListView lv;

	//本来<>想直接放User，但是既要和SimpleAdapter无缝连接又要干嘛来着，就把User变成HashMap
	//ArrayList <User> list=new ArrayList<User>();
	ArrayList <HashMap<String,Object>> list=new ArrayList<HashMap<String,Object>>();
	
	//定义接口
	UserInterface ui;
	
	//定义sa适配器
	SimpleAdapter sa;
	
	//定义一个变量来记住你选中了哪一条记录
	//这个变量用来记下该记录的userid
	//赋一个负值表示为空
	//只要当这个变量不为空时就表示成功选中了一条记录
	public static int CLICK_ID=-10000;
	
	 
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact);
		
		//2.初始化
		init();
		
		//3.从数据库的user表里取出所有的联系人，封装到arraylist里去
		//实例化一个接口，用UserInterfaceImp来进行实例化。记得getApplicationContext()
		ui=new UserInterfaceImp(getApplicationContext());
		//通过接口实现的方法来得到所有联系人存进ArrayList 
		list=ui.getAllUsers();
		
		 
		
		
		//4.把list数据封装到适配器中
		//三参是列表中每一行的布局文件，自己写的
		//四参是list中要在列表中显示出来的数据
		//五参是这些数据要在哪里显示！
		sa=new SimpleAdapter(this, list, R.layout.contactlistitem,
				new String[] {"name","phone"},new int[] {R.id.tv_Name,R.id.tvPhone});
	
		//将适配器加载到listview当中
	    lv.setAdapter(sa);
	    
	    //注册上下文菜单
	    registerForContextMenu(lv);
	    
	}

	
	
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		//如何知道当前选中的是listview中的哪一条记录？
		//通过这个东西可以得到：
		//AdapterContextMenuInfo info=(AdapterContextMenuInfo)item.getMenuInfo();
		//info.position就给出了listview当前选中的记录的下标
		
		AdapterContextMenuInfo info=(AdapterContextMenuInfo)item.getMenuInfo();
		
		//得到电话号码 
		String phone=(String)list.get(info.position).get("phone");
		
		//获得被选中的记录的关键字（userid）
		CLICK_ID=(Integer)list.get(info.position).get("userid");
		
		
		//判断
		if(item.getTitle().equals("拨号")) {
			//通过隐式意图启动系统打电话功能
			//通过隐式方式来启动系统的打电话的的组件
			//隐式启动一定要记得在manifest里面注册下面这个CALL_PHONE权限
			// <uses-permission android:name="android.permission.CALL_PHONE"/>
			
			//隐式启动一个意图intent
			Intent intent=new Intent();
			intent.setAction("android.intent.action.CALL");
			//也可以这样
			//intent.setAction(Intent.ACTION_CALL);
			
			//把电话号码字符串转换(parse)成URI对象，传给系统打电话的组件
			//注意一下这个tel:是固定的，不可以瞎改，改了就没有用了
			Uri uri=Uri.parse("tel:"+phone);
			
			//intent有一个方法setData(Uri data)，专门用来传递uri对象
			intent.setData(uri);
			
			//启动这个意图
			startActivity(intent);
			
		}else if(item.getTitle().equals("发送短信")) {
			//通过隐式意图来启动系统发短信的界面
			Intent intent=new Intent();
			intent.setAction(Intent.ACTION_SENDTO);
			//下面这个也ok，不过感觉上面那个更方便啦，不需要记
			//intent.setAction("android.intent.action.SENDTO"); 
			
			//将电话号码字符串转换成uri对象，再传给系统发送短信的组件
			//告诉intent意图信息接收端的电话号码是什么
			Uri uri=Uri.parse("smsto:"+phone);
			
			intent.setData(uri);
			//把信息也弄过去 这个sms_body也是固定的 不可以改动 改动了打开的信息编辑框里就不会有你在edittext里输入的信息了！
			intent.putExtra("sms_body", "");
			
			startActivity(intent);
		}else if(item.getTitle().equals("修改联系人")) {
			
			//CLICKID得到了一个id的话就可以执行以下操作
			if(CLICK_ID!=-10000) {
				Intent intent=new Intent(ContactActivity.this,UpdateContactActivity.class);
				intent.putExtra("userid", CLICK_ID);
				startActivity(intent);
				finish();
				
			}
			
			
		}else if(item.getTitle().equals("删除联系人")) {
			if(CLICK_ID!=-10000) {
				//删除是一个严肃的事情
				//删之前要弹出一个对话框 你确认删除吗朋友？？
				AlertDialog ad=createDialog();
				ad.show();
			}
		}
		
		return super.onContextItemSelected(item);
	}




	private AlertDialog createDialog() {
		// TODO Auto-generated method stub
		AlertDialog dialog;
		AlertDialog.Builder ad=new AlertDialog.Builder(ContactActivity.this);
		
		ad.setTitle("删除联系人");
		ad.setIcon(R.drawable.icon);
		ad.setMessage("删除他你舍得？？？");
		ad.setPositiveButton("删删删", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				//删除这一条记录
				if(deleteItem()!=0) {
					
					//删完再调用自己就好了 又重新的取出全部的数据
					//但是调用自己就没有标签栏了
					//所以直接调用mycontactactivity
					//其实我觉得这样的没啥意思 重新启动一个intent的感觉很假 
					Toast.makeText(getApplicationContext(), "好了他离开你了", Toast.LENGTH_SHORT).show();
				    Intent intent=new Intent(ContactActivity.this,MyContactActivity.class);
				    startActivity(intent);
				    finish();
				    
				}else {
					Toast.makeText(getApplicationContext(), "好了他离开你失败了", Toast.LENGTH_SHORT).show();
				    
				}
				
			}
		});
		ad.setNegativeButton("取消", null);
		dialog=ad.create();
		return dialog;
	}




	protected int deleteItem() {
		// TODO Auto-generated method stub
		//调用业务逻辑层
		//做一个事情：调用接口中的方法
		//先实例化
		int bl=0;
		
		ui=new UserInterfaceImp(getApplicationContext());
		ui.deleteById(CLICK_ID);
		
		bl=1;
		return bl;
	}




	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		menu.add("拨号");
		menu.add("发送短信");
		menu.add("修改联系人");
		menu.add("删除联系人");
		
		super.onCreateContextMenu(menu, v, menuInfo);
	}




	private void init() {
		// TODO Auto-generated method stub
		lv=(ListView)this.findViewById(R.id.lv_user);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.contact, menu);
		return true;
	}

}
