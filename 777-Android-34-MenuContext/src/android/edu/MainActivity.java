package android.edu;

import android.os.Bundle;
import android.app.Activity;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {

	//1.定义成员变量
	EditText et1,et2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//2.初始化
		init();
		
		//3.为两个文本框注册上下文菜单，长按将显示上下文菜单(不需要设置长按事件  这东西自带的)
		registerForContextMenu(et1);//为et1注册一个上下文菜单 
		registerForContextMenu(et2);//为et2注册一个上下文菜单
		//至于注册的上下文菜单是什么 又有专门的方法 onCreateContextMenu
	}

	
	
	//view就是事件源 menu就是当前菜单
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		//如果事件源为et1 则显示以下菜单
		if(v==et1) {
			//长按第一个文本框显示的菜单
			menu.add(0, 1, 0, "全部复制");
			menu.add(0, 2, 0, "全部粘贴");
			menu.add(0, 3, 0, "全部剪切");
			//第一个参数是组号groupid
			//第二个参数是组内的编号itemid
			//第三个参数表示显示的顺序orderid
			//第四个参数表示显示的内容title
		}else if(v==et2) {
			menu.add(0, 4, 0, "menu1");
			menu.add(0, 5, 0, "menu2");
			menu.add(0, 6, 0, "menu3");
		}
		
		super.onCreateContextMenu(menu, v, menuInfo);
	}
	

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()) {
		case 1:
		case 2:
		case 3:
			et1.append("\n"+item.getTitle()+"被按下");
			//append表示追加字符串  settext表示覆盖
			break;
		case 4:
		case 5:
		case 6:
			et2.append("\n"+item.getTitle()+"被按下");
			break;
		}
		  
		return super.onContextItemSelected(item);
	}

	private void init() {
		// TODO Auto-generated method stub
		et1=(EditText)this.findViewById(R.id.et1);
		et2=(EditText)this.findViewById(R.id.et2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
