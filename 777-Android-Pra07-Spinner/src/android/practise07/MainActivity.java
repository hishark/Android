package android.practise07;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {

	//1.定义成员变量
	EditText etNumber1,etNumber2,etResult;
	Button btCompute,btClear;
	Spinner sp;
	MyAdapter ma;
	String symbol[]= {
			"  +  ","  -  ","  *  ","  /  "
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//2.初始化
		init();
		//3.自定义适配器
		ma=new MyAdapter(getApplicationContext(),symbol);
		//4.加载适配器
		sp.setAdapter(ma);
		//5.监听器
btCompute.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String strNumber1,strNumber2;
				String symbol;//符号变量
				
				TextView tvsp=(TextView)sp.getChildAt(0);//得到当前的符号textview
				symbol=tvsp.getText().toString().trim();
				 
				strNumber1=etNumber1.getText().toString().trim();
				strNumber2=etNumber2.getText().toString().trim();
				if(strNumber1.equals("")||strNumber1==null) {
					//吐司吐司
					Toast.makeText(getApplicationContext(), "请输入操作数1", Toast.LENGTH_SHORT).show();
				}else if(strNumber2.equals("")||strNumber2==null) {
					Toast.makeText(getApplicationContext(), "请输入操作数2", Toast.LENGTH_SHORT).show();
				}else {
					double number1=Double.parseDouble(strNumber1);
					double number2=Double.parseDouble(strNumber2);
					double result=0;//记得初始化为0不然会报错诶
					
					//接下来就要判断符号是什么
					//if(symbol=="+")不能这样判断字符串恒等 在这里==是在判断对象是不是一样的 
					//也就是判断地址是不是一样的 显然这个symbol和+的地址肯定不一样 所以不应该这样判断
					//现在要判断的是这个对象的值是不是等于某个值  所以应该用String.equals()方法
					if(symbol.equals("+")) {
						result=number1+number2;
					}else if(symbol.equals("-")){
						result=number1-number2;
					}else if(symbol.equals("*")){
						result=number1*number2;
					}else if(symbol.equals("/")){
						result=number1/number2;
					}
					//设置结果显示
					etResult.setText(String.valueOf(result));
				}
			}
		});

btClear.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		etNumber1.setText("");
		etNumber2.setText("");
		etResult.setText("");
	}
});

	}

	private void init() {
		// TODO Auto-generated method stub
		etNumber1=(EditText)this.findViewById(R.id.et_num1);
		etNumber2=(EditText)this.findViewById(R.id.et_num2);
		etResult=(EditText)this.findViewById(R.id.et_result);
		btCompute=(Button)this.findViewById(R.id.bt_compute);
		btClear=(Button)this.findViewById(R.id.bt_clear);
		sp=(Spinner)this.findViewById(R.id.sp);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
