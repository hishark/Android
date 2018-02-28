package jxnu.edu.cn.x3321;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity{

	//1.定义成员引用变量(?)
	Button btCompute,btClear;
	EditText etNumber1,etNumber2,etResult;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//2.使成员引用变量指向界面上的元素对象（引用变量的初始化）
		init();
		//3.注册(绑定)监听器
		//这种方法的监听器直接绑定的到button按钮上去了，用在button上很方便啊 有点像js里的事件响应
		
	}

	
	public void oper(View v) {//一定要有v参数
		switch(v.getId()) {
		case R.id.bt_compute:
			compute();
			break;
		case R.id.bt_clear:
			clear();
			break;
		}
		//通过button上设置的id，判断一下点中了哪个button，点中哪个就响应哪个方法
	}
	
	private void clear() {
		// TODO Auto-generated method stub
		etNumber1.setText("");
		//让该edittext中的内容变为空
		etNumber2.setText("");
		etResult.setText("");
	}

	private void compute() {
		// TODO Auto-generated method stub
		String strNumber1,strNumber2;
		strNumber1=etNumber1.getText().toString().trim();
		strNumber2=etNumber2.getText().toString().trim();
		//得到edittext中的内容然后转换为字符串然后去掉该字符串前后的空格！
		if(strNumber1.equals("")||strNumber1==null) {
			//吐司吐司
			Toast.makeText(getApplicationContext(), "请输入被加数", Toast.LENGTH_SHORT).show();
		}else if(strNumber2.equals("")||strNumber2==null) {
			Toast.makeText(getApplicationContext(), "请输入加数", Toast.LENGTH_SHORT).show();
		}else {
			double number1=Double.parseDouble(strNumber1);
			double number2=Double.parseDouble(strNumber2);
			double result=number1+number2;
			etResult.setText(String.valueOf(result));
			//把result强制转换为String类型
		}
	}
	
	
	private void init() {
		// TODO Auto-generated method stub
		btCompute=(Button)this.findViewById(R.id.bt_compute);
		btClear=(Button)this.findViewById(R.id.bt_clear);
		etNumber1=(EditText)this.findViewById(R.id.et_num1);
		etNumber2=(EditText)this.findViewById(R.id.et_num2);
		etResult=(EditText)this.findViewById(R.id.et_result);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


	

	

}
