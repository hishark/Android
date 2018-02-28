package jxnu.edu.cn.x3321;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	Button bt0,bt1,bt2,bt3,bt4,bt5,bt6,bt7,bt8,bt9,add,sub,mul,div,dot;
	TextView result;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		bt0=(Button)this.findViewById(R.id.bt_0);
		bt1=(Button)this.findViewById(R.id.bt_1);
		bt2=(Button)this.findViewById(R.id.bt_2);
		bt3=(Button)this.findViewById(R.id.bt_3);
		bt4=(Button)this.findViewById(R.id.bt_4);
		bt5=(Button)this.findViewById(R.id.bt_5);
		bt6=(Button)this.findViewById(R.id.bt_6);
		bt7=(Button)this.findViewById(R.id.bt_7);
		bt8=(Button)this.findViewById(R.id.bt_8);
		bt9=(Button)this.findViewById(R.id.bt_9);
		result=(TextView)this.findViewById(R.id.tv_result);
		add=(Button)this.findViewById(R.id.bt_add);
		sub=(Button)this.findViewById(R.id.bt_sub);
		mul=(Button)this.findViewById(R.id.bt_mul);
		div=(Button)this.findViewById(R.id.bt_div);
		dot=(Button)this.findViewById(R.id.bt_dot);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	String str1="";
	public void showNum(View v) {
		
		switch(v.getId()) {
		case R.id.bt_0:
			str1=str1+bt0.getText();
			break;
		case R.id.bt_1:
			str1=str1+bt1.getText();
			break;
		case R.id.bt_2:
			str1=str1+bt2.getText();
			break;
		case R.id.bt_3:
			str1=str1+bt3.getText();
			break;
		case R.id.bt_4:
			str1=str1+bt4.getText();
			break;
		case R.id.bt_5:
			str1=str1+bt5.getText();
			break;
		case R.id.bt_6:
			str1=str1+bt6.getText();
			break;
		case R.id.bt_7:
			str1=str1+bt7.getText();
			break;
		case R.id.bt_8:
			str1=str1+bt8.getText();
			break;
		case R.id.bt_9:
			str1=str1+bt9.getText();
			break;
		}
		result.setText(String.valueOf(str1));
	}
	int op=-1;
	//0加 1减 2乘 3除
	public void showSym(View v) {
		switch(v.getId()) {
		case R.id.bt_add:
			str1=str1+add.getText();
			op=0;
			break;
		case R.id.bt_sub:
			str1=str1+sub.getText();
			op=1;
			break;
		case R.id.bt_mul:
			str1=str1+mul.getText();
			op=2;
			break;
		case R.id.bt_div:
			str1=str1+div.getText();
			op=3;
			break;
		case R.id.bt_dot:
			str1=str1+dot.getText();
			break;
		}
		result.setText(String.valueOf(str1));
	}
	public void clear(View v) {
		result.setText("0");
		str1="";
	}
	public void equals(View v) {
		//这是做加法 其他的可以以此类推吧
		if(op==0)
		{
			double res = 0;
			int length=str1.length();
			int index=str1.indexOf("+");
			String strNum1=null,strNum2 = null;
			strNum1=str1.substring(0, index);
			strNum2=str1.substring(index+1, length);
			double num1,num2;
			num1=Double.parseDouble(strNum1);
			num2=Double.parseDouble(strNum2);
			res=num1+num2;
			result.setText(String.valueOf(res));
			str1="";
		}else if(op==1) {
			double res = 0;
			int length=str1.length();
			int index=str1.indexOf("-");
			String strNum1=null,strNum2 = null;
			strNum1=str1.substring(0, index);
			strNum2=str1.substring(index+1, length);
			double num1,num2;
			num1=Double.parseDouble(strNum1);
			num2=Double.parseDouble(strNum2);
			res=num1-num2;
			result.setText(String.valueOf(res));
			str1="";
		}else if(op==2) {
			double res = 0;
			int length=str1.length();
			int index=str1.indexOf("*");
			String strNum1=null,strNum2 = null;
			strNum1=str1.substring(0, index);
			strNum2=str1.substring(index+1, length);
			double num1,num2;
			num1=Double.parseDouble(strNum1);
			num2=Double.parseDouble(strNum2);
			res=num1*num2;
			result.setText(String.valueOf(res));
			str1="";
		}else if(op==3) {
			double res = 0;
			int length=str1.length();
			int index=str1.indexOf("/");
			String strNum1=null,strNum2 = null;
			strNum1=str1.substring(0, index);
			strNum2=str1.substring(index+1, length);
			double num1,num2;
			num1=Double.parseDouble(strNum1);
			num2=Double.parseDouble(strNum2);
			res=num1/num2;
			result.setText(String.valueOf(res));
			str1="";
		}else {
			result.setText(String.valueOf(result.getText()));
		}
	}
	
}
