package jxnu.edu.cn.x3321;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {

	//1.定义成员变量
	EditText etDegree,etHobbies;
	Button btDegree,btHobbies;
	
	String []degrees=new String[] {
			"本科","硕士","博士"
	};
	String []hobbies=new String[] {
			"游泳","篮球","足球"
	};
	
	//以后要多灵活运用boolean变量 很多时候一个boolean可以解决很多麻烦的问题 你以前从来不会主动用啊智障
	boolean []bl=new boolean[] {
			false,false,false
	};
	//这个数组表示复选框的选中状态
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//2.初始化
		init();
		
		//3.定义注册事件监听器
		btDegree.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				AlertDialog ad=createSingleChoiceDialog();
				//自定义了一个createSingleDialog函数来创建对话框 这里的代码就简单直观一些
				ad.show();
			}
		});
		btHobbies.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog ad=createMultiChoiceDialog();//自定义的createDialog函数创建对话框
				ad.show();
			}
		});
		
	}

	protected AlertDialog createMultiChoiceDialog() {
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
					etHobbies.setText(result);
				}else {
					etHobbies.setText("");
				}
					
				
				
			}
		} );
		

		
		ad.setPositiveButton("确定",null );
		ad.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				etHobbies.setText("");
				//既然点了取消就干脆回到初始状态 省得出现差错
				bl[0]=false;
				bl[1]=false;
				bl[2]=false;
				
			}
		}) ;
		
		
		alertDialog=ad.create();//ad是builder类型 用了create()就是AlertDialog类型
		return alertDialog;
	}

	protected AlertDialog createSingleChoiceDialog() {
		// TODO Auto-generated method stub
		AlertDialog alertDialog;
		AlertDialog.Builder ad=new AlertDialog.Builder(MainActivity.this);
		
		//头部分
		ad.setTitle("学位选择对话框");
		ad.setIcon(R.drawable.ic_launcher);
		
		//中间部分
		//专门的对话框的方法
		//参数：1.要显示哪个数据集 2.缺省情况是哪一项被选中 3.监听器 单机某一个单选项就会有一个单击事件 处理这个事件的监听器在下面
		ad.setSingleChoiceItems(degrees, 0, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				//参数：1.当前对话框  2.单击了哪一个选项 which就是下标
				etDegree.setText(degrees[which]);
			}
		});
		

		
		ad.setPositiveButton("确定",null );
        ad.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				etDegree.setText("");
			}
		}) ;
		
		//以上都是对ad的填充过程（我自己这么理解），接下来就是把填充好的ad进行创建之后放进alertDialog里面！
		//注意AlertDialog和AlertDialog.Builder是两个不同的东西 注意注意^(*￣(oo)￣)^
        
		alertDialog=ad.create();//ad是builder类型 用了create()就是AlertDialog类型
		return alertDialog;
	}

	private void init() {
		// TODO Auto-generated method stub
		etDegree=(EditText)this.findViewById(R.id.et_degree);
		etHobbies=(EditText)this.findViewById(R.id.et_hobbies);
		btDegree=(Button)this.findViewById(R.id.bt_degree);
		btHobbies=(Button)this.findViewById(R.id.bt_hobbies);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
